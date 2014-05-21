package com.abc.task.lang;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

public class TemplateServletOutputStream extends ServletOutputStream {

	private OutputStream out;

	public TemplateServletOutputStream(OutputStream out) {
		this.out = out;
	}

	public void write(int b) throws IOException {
		out.write(b);
	}

	public void write(byte[] b, int off, int len) throws IOException {
		out.write(b, off, len);
	}

	public void close() throws IOException {
		out.close();
	}

	public void flush() throws IOException {
		out.flush();
	}

}
