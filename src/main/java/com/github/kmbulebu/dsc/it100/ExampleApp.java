package com.github.kmbulebu.dsc.it100;

import rx.Observable;
import rx.functions.Action1;

import com.github.kmbulebu.dsc.it100.commands.read.ReadCommand;
import com.github.kmbulebu.dsc.it100.commands.read.ZoneOpenCommand;

public class ExampleApp {

	public static void main(String[] args) {
		// Configure for remote RaspberryPI with serial dongle and ser2net setup on port 2000 TCP.
		final IT100 it100 = new IT100(new ConfigurationBuilder().withRemoteSocket("raspberrypi", 2000).build());
		
		try {	
			// Start communicating with IT-100.
			it100.connect();
			
			final Observable<ReadCommand> readObservable = it100.getReadObservable();
			
			// Labels gives us friendly names to our zones.
			final Labels labels = new Labels(readObservable, it100.getWriteObservable());
			
			// Subscribe to Zone opening events
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
