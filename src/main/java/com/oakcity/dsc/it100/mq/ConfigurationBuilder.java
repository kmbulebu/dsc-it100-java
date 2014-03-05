package com.oakcity.dsc.it100.mq;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.core.service.IoConnector;
import org.apache.mina.transport.serial.SerialAddress;
import org.apache.mina.transport.serial.SerialAddress.DataBits;
import org.apache.mina.transport.serial.SerialAddress.FlowControl;
import org.apache.mina.transport.serial.SerialAddress.Parity;
import org.apache.mina.transport.serial.SerialAddress.StopBits;
import org.apache.mina.transport.serial.SerialConnector;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

public class ConfigurationBuilder {
	
	private final ConfigurationImpl configuration;
	
	
	public ConfigurationBuilder() {
		configuration = new ConfigurationImpl();
	}
	
	// Remote IP/port
	public ConfigurationBuilder withRemoteSocket(String host, int port) {
		configuration.connector = new NioSocketConnector();
		configuration.address = new InetSocketAddress(host, port);
		return this;
	}
	
	// Local Serial
	public ConfigurationBuilder withSerialPort(String serialPort, int baudRate) {
		configuration.connector = new SerialConnector();    
		configuration.address = new SerialAddress(serialPort, baudRate, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE );
		return this;
	}
	
	public ConfigurationBuilder withConnectTimeout(long milliseconds) {
		configuration.connectTimeout = milliseconds;
		return this;
	}
	
	public Configuration build() {
		if (configuration.connector == null || configuration.address == null) {
			throw new IllegalArgumentException("You must call either withRemoteSocket or withSerialPort.");
		}
		return configuration;
	}
	
	private static class ConfigurationImpl implements Configuration {
		
		private IoConnector connector = null;
		private SocketAddress address = null;
		private long connectTimeout = 5000;

		@Override
		public IoConnector getConnector() {
			return connector;
		}
		
		public SocketAddress getAddress() {
			return address;
		}

		@Override
		public long getConnectTimeout() {
			return connectTimeout;
		}
		
	}

}
