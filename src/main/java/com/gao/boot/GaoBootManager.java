package com.gao.boot;

import java.io.File;
import java.util.List;

import com.gao.autocoder.AutoCoder;
import com.gao.autocoder.java.JavaDaoInterfaceAutoCoder;
import com.gao.autocoder.java.JavaEntityAutoCoder;
import com.gao.autocoder.java.JavaJdbcDaoImplAutoCoder;
import com.gao.code.Code;
import com.gao.factory.Factory;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;
import com.gao.resource.jdbc.JdbcResource;
import com.gao.utils.FileUtils;

public class GaoBootManager {
	
	private Factory factory;
	
	public GaoBootManager(Factory factory) {
		this.factory = factory;
	}

	public void justGO() {
		String basePath = FileUtils.package2FilePath("${gaoboot.base.package}") + File.separator;
		Resource r = factory.getResource();
		List<? extends MetaData> list = r.parse();
		
		AutoCoder entityAutoCoder = factory.getEntityAutoCoder();
		AutoCoder daoInterfaceAutoCoder = factory.getDaoInterfaceAutoCoder();
		AutoCoder daoImplAutoCoder = factory.getDaoImplAutoCoder();
		for (MetaData md : list) {
			Code code = entityAutoCoder.generateCode(md);
			Code code2 = daoInterfaceAutoCoder.generateCode(md);
			Code code3 = daoImplAutoCoder.generateCode(md);
			
			// for test
			/*
			String codeText = code.getCodeText();
			System.out.println(codeText);
			String codeText2 = code2.getCodeText();
			System.out.println(codeText2);
			String codeText3 = code3.getCodeText();
			System.out.println(codeText3);
			*/
			
			code.saveToDisk(basePath + "entity");
			code2.saveToDisk(basePath + "dao");
			code3.saveToDisk(basePath + "dao" + File.separator + "impl");
		}
	}
}
