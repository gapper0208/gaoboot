package com.gao.exception;

public class GenerateCodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public GenerateCodeException(String msg) {
		super(msg);
	}
	
	public GenerateCodeException(Throwable t) {
		super(t);
	}
	
	public GenerateCodeException(String msg, Throwable t) {
		super(msg, t);
	}
}