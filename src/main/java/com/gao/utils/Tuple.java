package com.gao.utils;

public class Tuple<A, B> {
	private A fisrt;
	private B second;
	public Tuple(A fisrt, B second) {
		super();
		this.fisrt = fisrt;
		this.second = second;
	}
	public A getFisrt() {
		return fisrt;
	}
	public void setFisrt(A fisrt) {
		this.fisrt = fisrt;
	}
	public B getSecond() {
		return second;
	}
	public void setSecond(B second) {
		this.second = second;
	}
}
