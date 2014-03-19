package com.oakcity.dsc.it100;

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

import com.oakcity.dsc.it100.IT100.Configuration;

/**
 * Configuration Builder for the IT100.
 * @author Kevin Bulebush
 *
 */
public class ConfigurationBuilder {
	
	private final ConfigurationImpl configuration;
	
	public ConfigurationBuilder() {
		configuration = new ConfigurationImpl();
	}
	
	/**
	 * Use a TCP connection for remotely connecting to the IT-100.
	 * 
	 * Typically used with a utility such as ser2net for connecting to a remote serial port.
	 * @param host Hostname or IP address of the remote device.
	 * @param port TCP port of the remote device.
	 * @return This builder instance.
	 */
	public ConfigurationBuilder withRemoteSocket(String host, int port) {
		configuration.connector = new NioSocketConnector();
		configuration.address = new InetSocketAddress(host, port);
		return this;
	}
	
	/**
	 * Use a local serial port for communicating with the IT-100.
	 * @param serialPort The serial port name. (/dev/ttyUSB0, COM1:, etc).
	 * @param baudRate The baud rate to use while communicating with the IT-100. IT-100 default is 19200.
	 * @return This builder instance.
	 */
	public ConfigurationBuilder withSerialPort(String serialPort, int baudRate) {
		configuration.connector = new SerialConnector();    
		configuration.address = new SerialAddress(serialPort, baudRate, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE );
		return this;
	}
	
	/**
	 * Time to wait to establish a connection with the IT-100.
	 * @param milliseconds Timeout in milliseconds.
	 * @return This builder instance.
	 */
	public ConfigurationBuilder withConnectTimeout(long milliseconds) {
		configuration.connectTimeout = milliseconds;
		return this;
	}
	
	/**
	 * Enable status polling to the IT-100. The IT-100 will return with the status
	 * of all zones, partitions, etc. 
	 * @param seconds Time in seconds between status pollings. -1 disables polling.
	 * @return This builder instance.
	 */
	public ConfigurationBuilder withStatusPolling(int seconds) {
		configuration.statusPollingInterval = seconds;
		return this;
	}
	
	/**
	 * Create an immutable Configuration instance.
	 * @return
	 */
	public Configuration build() {
		if (configuration.connector == null || configuration.address == null) {
			throw new IllegalArgumentException("You must call either withRemoteSocket or withSerialPort.");
		}
		return configuration;
	}
	
	private static class ConfigurationImpl implements Configuration {
		
		private IoConnector connector = null;
		private SocketAddress address = null;
		private long connectTimeout = 5 * 1000; // 5 seconds
		private int statusPollingInterval = -1; // Disabled

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
		
		@Override
		public int getStatusPollingInterval() {
			return statusPollingInterval;
		}
		
	}

}
