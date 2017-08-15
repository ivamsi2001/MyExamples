package com.venkat.jaxbxsdexample.run;

import java.io.IOException;
import java.io.OutputStream;

public class StringOutputStream extends OutputStream {

	StringBuilder mBuf;
	StringOutputStream(){
		mBuf = new StringBuilder();
	}
	public String getString() {
		return mBuf.toString();
	}

	@Override
	public void write(int b) throws IOException {
		mBuf.append((char) b);
	}
}