package com.abc.task.lang;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class TemplateResponse extends HttpServletResponseWrapper {
	private OutputStream templateOut;
	private ServletOutputStream  out;
	private PrintWriter writer;

	public TemplateResponse(HttpServletResponse response,
			OutputStream templateOut) {
		super(response);
		this.templateOut = templateOut;
	}

	public void flushBuffer() throws IOException {
		if (writer != null) {
			writer.flush();
		} else if (out != null) {
			out.flush();
		}
	}

	public ServletOutputStream getOutputStream() throws IOException {
		if (writer != null)
			throw new IllegalStateException(
					"method getWriter() has been called");
		if (out == null)
			out = new TemplateServletOutputStream(templateOut);
		return out;
	}

	public PrintWriter getWriter() throws IOException {
		if (out != null)
			throw new IllegalStateException(
					"method getOutputStream() has been called");
		if (writer == null) {
			String encoding = getCharacterEncoding();
			if (encoding == null)
				encoding = "UTF-8";
			writer = new PrintWriter(new OutputStreamWriter(templateOut, encoding));
		}
		return writer;
	}

	public void closeStream() throws IOException {
		if (out != null) {
			out.flush();
			out.close();
		} else if (writer != null) {
			writer.flush();
			writer.close();
		}
		templateOut.flush();
		templateOut.close();
	}
}
