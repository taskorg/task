package com.abc.task.exception;

public class ParameterException extends RuntimeException {

	private static final long serialVersionUID = -3177196726945890392L;

	public ParameterException(String msg) {
		super(msg);
	}

	public ParameterException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ParameterException(Throwable cause) {
		super(cause);
	}
}
