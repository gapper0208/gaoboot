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
		
		Resource r = new JdbcResource("db.properties");
		List<? extends MetaData> list = r.parse();
		
		AutoCoder ac = new JavaDaoInterfaceAutoCoder();
		for (MetaData md : list) {
			Code code = ac.generateCode(md);
			String path = FileUtils.package2FilePath("gaoboot.base.package") + File.separator + "entity";
			String codeText = code.getCodeText();
			System.out.println(codeText);
			// code.saveToDisk(path);
		}
	}
}