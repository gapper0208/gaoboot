package com.gao.exception;

public class JdbcParseTableException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JdbcParseTableException(String msg) {
		super(msg);
	}
	
	public JdbcParseTableException(Throwable t) {
		super(t);
	}
	
	public JdbcParseTableException(String msg, Throwable t) {
		super(msg, t);
	}
}