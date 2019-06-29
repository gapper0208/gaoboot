package com.gao.test;

import java.io.File;
import java.util.List;

import org.junit.Test;

import com.gao.autocoder.AutoCoder;
import com.gao.autocoder.java.JavaDaoInterfaceAutoCoder;
import com.gao.autocoder.java.JavaEntityAutoCoder;
import com.gao.code.Code;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;
import com.gao.resource.jdbc.JdbcResource;
import com.gao.utils.FileUtils;


public class AppTest {
	@Test
	public void test() throws Exception {
		
		String basePath = FileUtils.package2FilePath("gaoboot.base.package") + File.separator;
		
		Resource r = new JdbcResource("db.properties");
		List<? extends MetaData> list = r.parse();
		
		AutoCoder ac = new JavaEntityAutoCoder();
		AutoCoder ac2 = new JavaDaoInterfaceAutoCoder();
		for (MetaData md : list) {
			Code code = ac.generateCode(md);
			Code code2 = ac2.generateCode(md);
			String codeText = code.getCodeText();
			System.out.println(codeText);
			String codeText2 = code2.getCodeText();
			System.out.println(codeText2);
			
			code.saveToDisk(basePath + "entity");
			code2.saveToDisk(basePath + "dao");
		}
	}
}