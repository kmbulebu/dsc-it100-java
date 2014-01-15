#DSC IT-100 Java Library

##Introduction

This library exposes all the capabilities of the DSC PowerSeries Integration Module as a Java API. With it, you should be able to automate security system tasks and receive notifications of events.

The library is very much a work in progress. It is built using Apache MINA, a library for implementing protocols. The first pass of the API is nearly complete, and usable as is. The most common IT-100 commands and messages are implemented. The less common ones still need implemented. Expect a major refactoring of the base API in the near future.

##Basic Usage

###Typical Serial Setup and Connection
```
// Setup MINA codecs
final IT100CodecFactory it100CodecFactory = new IT100CodecFactory();
final ProtocolCodecFilter protocolCodecFilter = new ProtocolCodecFilter(it100CodecFactory);

// Connect to system serial port.
final IoConnector connector = new SerialConnector();	
final SerialAddress portAddress = new SerialAddress( "/dev/ttyUSB0", 19200, DataBits.DATABITS_8, StopBits.BITS_1, Parity.NONE, FlowControl.NONE );

// Typical configuration
connector.setConnectTimeoutMillis(2000);
connector.getSessionConfig().setUseReadOperation(true);

// Add IT100 codec to MINA filter chain to interpret messages.
connector.getFilterChain().addLast("codec", protocolCodecFilter);

// Connect now
final ConnectFuture future = connector.connect(portAddress); 
future.awaitUninterruptibly();

// Get a reference to the session
final IoSession session = future.getSession(); 
```

###Add logging filter
```
// Use MINA's logging filter to log messages
connector.getFilterChain().addLast("logger", new LoggingFilter());
```

###Add keep-alive pings
```
// Periodically sends a NOOP to the IT-100
connector.getFilterChain().addLast("keepalive", new PollKeepAliveFilter(KeepAliveRequestTimeoutHandler.EXCEPTION);
```

###Add status polling
```
// Periodically send status request commands to the IT-100. The IT-100 will reply with zone status, etc.
connector.getFilterChain().addLast("statuspolling", new StatusRequestFilter());
```

##References

[DSC IT-100 Product Page](http://www.dsc.com/index.php?n=products&amp;o=view&amp;id=22)

[DSC IT-100 Developer Guide, hosted at HomeSeer](http://homeseer.com/pdfs/DSC/29007363R003_IT_100_developer_guide.pdf)

