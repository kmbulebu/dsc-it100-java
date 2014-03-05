package com.oakcity.dsc.it100.mq;

public class TestApp {

	public static void main(String[] args) {
		Server server = new Server(new ConfigurationBuilder().withRemoteSocket("raspberrypi", 2000).build());
		server.getZoneOpenListeners().add(new ZoneOpenListener() {
			
			@Override
			public void onZoneOpen(int zone, String label, boolean isOpen) {
				System.out.println("Zone " + zone + " open state is " + isOpen);
			}
		});
		try {
			server.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
