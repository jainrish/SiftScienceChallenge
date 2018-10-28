package com.sift.exception;

public class SetGameException extends Exception {

	private static final long serialVersionUID = 4049060184596939536L;

	public SetGameException() {
		super();
	}

	public SetGameException(String message) {
		super(message);
	}

	public SetGameException(String message, Throwable cause) {
		super(message, cause);
	}

}
