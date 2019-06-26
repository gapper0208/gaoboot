package com.gao.exception;

public class JdbcResourceInitializationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public JdbcResourceInitializationException(String msg) {
		super(msg);
	}
	
	public JdbcResourceInitializationException(Throwable t) {
		super(t);
	}
	
	public JdbcResourceInitializationException(String msg, Throwable t) {
		super(msg, t);
	}
}
