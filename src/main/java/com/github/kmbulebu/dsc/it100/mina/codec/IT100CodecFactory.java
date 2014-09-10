package com.github.kmbulebu.dsc.it100.mina.codec;

import org.apache.mina.core.buffer.BufferDataException;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;

public class IT100CodecFactory implements ProtocolCodecFactory {
	
	private final IT100Encoder encoder;

    private final IT100Decoder decoder;

	public IT100CodecFactory() {
		encoder = new IT100Encoder();
		decoder = new IT100Decoder();
	}

	@Override
	public ProtocolDecoder getDecoder(IoSession session) {
		return decoder;
	}

	@Override
	public ProtocolEncoder getEncoder(IoSession session) {
		return encoder;
	}
	
	/**
     * Returns the allowed maximum size of the encoded line.
     * If the size of the encoded line exceeds this value, the encoder
     * will throw a {@link IllegalArgumentException}.  The default value
     * is {@link Integer#MAX_VALUE}.
     * <p>
     * This method does the same job with {@link TextLineEncoder#getMaxLineLength()}.
     */
    public int getEncoderMaxLineLength() {
        return encoder.getMaxLineLength();
    }

    /**
     * Sets the allowed maximum size of the encoded line.
     * If the size of the encoded line exceeds this value, the encoder
     * will throw a {@link IllegalArgumentException}.  The default value
     * is {@link Integer#MAX_VALUE}.
     * <p>
     * This method does the same job with {@link TextLineEncoder#setMaxLineLength(int)}.
     */
    public void setEncoderMaxLineLength(int maxLineLength) {
        encoder.setMaxLineLength(maxLineLength);
    }

    /**
     * Returns the allowed maximum size of the line to be decoded.
     * If the size of the line to be decoded exceeds this value, the
     * decoder will throw a {@link BufferDataException}.  The default
     * value is <tt>1024</tt> (1KB).
     * <p>
     * This method does the same job with {@link TextLineDecoder#getMaxLineLength()}.
     */
    public int getDecoderMaxLineLength() {
        return decoder.getMaxLineLength();
    }

    /**
     * Sets the allowed maximum size of the line to be decoded.
     * If the size of the line to be decoded exceeds this value, the
     * decoder will throw a {@link BufferDataException}.  The default
     * value is <tt>1024</tt> (1KB).
     * <p>
     * This method does the same job with {@link TextLineDecoder#setMaxLineLength(int)}.
     */
    public void setDecoderMaxLineLength(int maxLineLength) {
        decoder.setMaxLineLength(maxLineLength);
    }
	
	

}
