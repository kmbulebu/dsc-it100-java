package com.oakcity.dsc.it100.rx;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

import com.oakcity.dsc.it100.commands.read.ReadCommand;
import com.oakcity.dsc.it100.commands.read.ZoneOpenCommand;

public class TestApp {

	public static void main(String[] args) {
		IT100 server = new IT100(new ConfigurationBuilder().withStatusPolling(300).withRemoteSocket("raspberrypi", 2000).build());
		
		try {	
			Observable<ReadCommand> observable = server.connect();
			
			observable.filter(new Func1<ReadCommand, Boolean>() {

				@Override
				public Boolean call(ReadCommand t1) {
					return t1 instanceof ZoneOpenCommand;
				}
			}).subscribe(new Action1<ReadCommand>() {

				@Override
				public void call(ReadCommand t1) {
					System.out.println(System.currentTimeMillis() + " " + t1.toString());
				}
				
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
