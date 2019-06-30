package com.gao.exception;

public class Template2CodeException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public Template2CodeException(String msg) {
		super(msg);
	}
	
	public Template2CodeException(Throwable t) {
		super(t);
	}
	
	public Template2CodeException(String msg, Throwable t) {
		super(msg, t);
	}
}