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
import com.gao.factory.Factory;
import com.gao.factory.GaoBootFacoryBuilder;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;
import com.gao.resource.jdbc.JdbcResource;
import com.gao.utils.FileUtils;
import com.gao.utils.MainConfigUtils;


public class AppTest {
	@Test
	public void test() throws Exception {
		
	}
	
	// ����JdbcUtils.tmp����JdbcUtils.javaԴ�ļ�
	@Test
	public void test2() throws Exception {
		String basePath = FileUtils.package2FilePath("gaoboot.base.package") + File.separator;
		Code code = CodeTemplateUtils.template2code("JdbcUtils.tmp");
		code.saveToDisk(basePath + "utils");
	}
	
	// ��������ʽ
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
	
	// ���Զ�̬����sql���
	@Test
	public void test4() throws Exception {
		
	}
}