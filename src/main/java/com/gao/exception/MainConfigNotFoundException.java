package com.gao.exception;

public class MainConfigNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MainConfigNotFoundException(String msg) {
		super(msg);
	}
	
	public MainConfigNotFoundException(Throwable t) {
		super(t);
	}
	
	public MainConfigNotFoundException(String msg, Throwable t) {
		super(msg, t);
	}
}