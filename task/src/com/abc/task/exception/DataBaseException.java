package com.abc.task.exception;

public class DataBaseException extends RuntimeException {

	private static final long serialVersionUID = -3177196726945890392L;

	public DataBaseException(String msg) {
		super(msg);
	}

	public DataBaseException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public DataBaseException(Throwable cause) {
		super(cause);
	}
}
