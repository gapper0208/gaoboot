package com.gao.utils;

public class StringUtils {
	public static String raiseInitail(String str) {
		return str.substring(0,1).toUpperCase() + str.substring(1);
	}
	public static String unraiseInitail(String str) {
		return str.substring(0,1).toLowerCase() + str.substring(1);
	}
}
