package com.gao.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.gao.exception.MainConfigNotFoundException;

public class MainConfigUtils {
	
	private static Properties prop;
	
	static {
		try {
			InputStream in = MainConfigUtils.class.getClassLoader().getResourceAsStream("gaoboot.properties");
			if(in == null) {
				throw new MainConfigNotFoundException("There is no gaoboot.properties in the classpath");
			}
			prop = new Properties();
			prop.load(in);
		} catch (IOException e) {
			throw new ExceptionInInitializerError(e);
		}
	}
	
	public static String getPropery(String key) {
		return prop.getProperty(key);
	}
}