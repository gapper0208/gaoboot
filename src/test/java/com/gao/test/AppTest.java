package com.gao.test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.gao.autocoder.AutoCoder;
import com.gao.autocoder.codetemplate.CodeTemplateUtils;
import com.gao.autocoder.java.JavaDaoInterfaceAutoCoder;
import com.gao.autocoder.java.JavaEntityAutoCoder;
import com.gao.autocoder.java.JavaJdbcDaoImplAutoCoder;
import com.gao.code.Code;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;
import com.gao.resource.jdbc.JdbcResource;
import com.gao.utils.FileUtils;
import com.gao.utils.MainConfigUtils;


public class AppTest {
	@Test
	public void test() throws Exception {
		
		String basePath = FileUtils.package2FilePath("${gaoboot.base.package}") + File.separator;
		
		Resource r = new JdbcResource("db.properties");
		List<? extends MetaData> list = r.parse();
		
		AutoCoder ac = new JavaEntityAutoCoder();
		AutoCoder ac2 = new JavaDaoInterfaceAutoCoder();
		AutoCoder ac3 = new JavaJdbcDaoImplAutoCoder();
		for (MetaData md : list) {
			Code code = ac.generateCode(md);
			Code code2 = ac2.generateCode(md);
			Code code3 = ac3.generateCode(md);
			
			String codeText = code.getCodeText();
			System.out.println(codeText);
			String codeText2 = code2.getCodeText();
			System.out.println(codeText2);
			String codeText3 = code3.getCodeText();
			System.out.println(codeText3);
			
			code.saveToDisk(basePath + "entity");
			code2.saveToDisk(basePath + "dao");
			code3.saveToDisk(basePath + "dao" + File.separator + "impl");
		}
	}
	
	// 根据JdbcUtils.tmp生成JdbcUtils.java源文件
	@Test
	public void test2() throws Exception {
		String basePath = FileUtils.package2FilePath("gaoboot.base.package") + File.separator;
		Code code = CodeTemplateUtils.template2code("JdbcUtils.tmp");
		code.saveToDisk(basePath + "utils");
	}
	
	// 测试正则式
	@Test
	public void test3() throws Exception {
		String str = "aaa ${gaoboot.base.package} bbb ${gaoboot.jdbc.properties} ccc";
		Pattern p = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher m = p.matcher(str);
		while(m.find()) {
			String key = m.group(0);
			String content = m.group(1);
			String value = MainConfigUtils.getPropery(content);
			str = str.replace(key, value);
		}
		System.out.println(str);
	}
	
	// 测试动态生成sql语句
	@Test
	public void test4() throws Exception {
		
	}
}