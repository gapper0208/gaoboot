package com.gao.boot;

import com.gao.factory.Factory;
import com.gao.factory.GaoBootFacoryBuilder;

public class GaoBootApplication {
	public static void main(String[] args) {
		
		Factory factory = GaoBootFacoryBuilder.buildGaoBootFactory();
		GaoBootManager manager = new GaoBootManager(factory);
		manager.justGO();
		
	}
}
