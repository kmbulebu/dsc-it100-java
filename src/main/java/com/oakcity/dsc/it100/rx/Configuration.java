package com.oakcity.dsc.it100.rx;

import java.net.SocketAddress;

import org.apache.mina.core.service.IoConnector;

public interface Configuration {
	
	public IoConnector getConnector();
	
	public SocketAddress getAddress();
	
	public long getConnectTimeout();

}
