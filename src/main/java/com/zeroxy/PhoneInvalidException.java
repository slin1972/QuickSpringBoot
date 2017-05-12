package com.zeroxy;

public class PhoneInvalidException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -994547451028199846L;

	public PhoneInvalidException() {
		super();
	}

	public PhoneInvalidException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public PhoneInvalidException(String message, Throwable cause) {
		super(message, cause);
	}

	public PhoneInvalidException(String message) {
		super(message);
	}

	public PhoneInvalidException(Throwable cause) {
		super(cause);
	}
	
}
