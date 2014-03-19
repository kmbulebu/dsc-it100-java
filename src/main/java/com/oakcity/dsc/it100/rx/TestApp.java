package com.oakcity.dsc.it100.rx;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.subjects.PublishSubject;

import com.oakcity.dsc.it100.commands.read.BroadcastLabelsCommand;
import com.oakcity.dsc.it100.commands.read.ReadCommand;
import com.oakcity.dsc.it100.commands.read.ZoneOpenCommand;
import com.oakcity.dsc.it100.commands.write.LabelsRequestCommand;
import com.oakcity.dsc.it100.commands.write.StatusRequestCommand;
import com.oakcity.dsc.it100.commands.write.WriteCommand;

public class TestApp {

	public static void main(String[] args) {
		final IT100 it100 = new IT100(new ConfigurationBuilder().withRemoteSocket("raspberrypi", 2000).build());
		
		try {	
			it100.connect();
			Observable<ReadCommand> observable = it100.getReadObservable();
			
			observable.subscribe(new Action1<ReadCommand>() {

				@Override
				public void call(ReadCommand t1) {
					System.out.println(System.currentTimeMillis() + " " + t1.toString());
				}
				
			});
			System.out.println("Sending command now");
			
			it100.getWriteObservable().onNext(new LabelsRequestCommand());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
