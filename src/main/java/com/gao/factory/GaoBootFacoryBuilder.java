package com.gao.factory;

import com.gao.factory.impl.JdbcCodeFactory;
import com.gao.utils.MainConfigUtils;

// 简单工厂的缺点：扩展性不好，暂时先这样吧..
public class GaoBootFacoryBuilder {
	
	public GaoBootFacoryBuilder() {
	}
	
	public static Factory buildGaoBootFactory() {
		Factory factory = null;
		String framework = MainConfigUtils.getPropery("gaoboot.framework");
		switch (framework) {
		case "java.jdbc":
			factory = new JdbcCodeFactory();
			break;
		case "java.ssm":
			// 生成ssm的dao层代码
			// not yet..
			// 仅仅剩下体力活了
			break;
		case "java.ssh":
			// 生成ssh的dao层代码
			// not yet..
			// 仅仅剩下体力活了
			break;
		}
		return factory;
	}
}
