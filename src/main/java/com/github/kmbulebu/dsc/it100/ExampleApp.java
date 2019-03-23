package com.github.kmbulebu.dsc.it100;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneOpenCommand;
import com.github.kmbulebu.dsc.it100.commands.write.WriteCommand;

import rx.Observable;
import rx.functions.Action1;

public class ExampleApp {

	public static void main(String[] args) {
		
		// Configure for Envisalink (defaults)
		final IT100 it100 = new IT100(new ConfigurationBuilder().withRemoteSocket("envisalink", 4025)
				.withEnvisalinkPassword("user").build());
		
		try {	
			// Start communicating with IT-100.
			it100.connect();
			
			final Observable<ReadCommand> readObservable = it100.getReadObservable();
			
			// Labels gives us friendly names to our zones.
			final Labels labels = new Labels(readObservable, it100.getWriteObservable());
			
			it100.getWriteObservable().subscribe(new Action1<WriteCommand>() {

				@Override
				public void call(WriteCommand command) {
					System.out.println("Write: " + System.currentTimeMillis() + " " + command.getCommandCode() + " " + command.getData());
				}
				
			});
			
			// Subscribe to all
			readObservable.subscribe(new Action1<ReadCommand>() {

				@Override
				public void call(ReadCommand command) {
					// TODO Auto-generated method stub
					System.out.println("Read: " + System.currentTimeMillis() + " " + command.getCommandCode() + " " + command.toString());
				}
				
			});
			
			// Subscribe to Zone opening events to print zone labels (Only on IT-100)
			readObservable.ofType(ZoneOpenCommand.class).subscribe(new Action1<ZoneOpenCommand>() {

				@Override
				public void call(ZoneOpenCommand t1) {
					// Print time and name of zone that opened.
					System.out.println(System.currentTimeMillis() + " " + labels.getZoneLabel(t1.getZone()) + " opened.");
				}
				
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
