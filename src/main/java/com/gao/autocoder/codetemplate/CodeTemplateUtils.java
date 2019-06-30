package com.gao.autocoder.codetemplate;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gao.code.Code;
import com.gao.code.java.JavaCode;
import com.gao.exception.Template2CodeException;
import com.gao.utils.MainConfigUtils;

public class CodeTemplateUtils {

	public static Code template2code(String resource) {
		
		try {
			InputStream in = CodeTemplateUtils.class.getResourceAsStream(resource);
			InputStreamReader isr = new InputStreamReader(in,"gbk");
			BufferedReader br = new BufferedReader(isr);
			
			ByteArrayOutputStream bout = new ByteArrayOutputStream();
			PrintWriter pw = new PrintWriter(bout);
			
			String line = null;
			while((line = br.readLine()) != null) {
				pw.println(line);
			}
			
			br.close();
			pw.close();
			
			byte[] bb = bout.toByteArray();
			String codeText = new String(bb);
			
			// 将模板中的占位符(${})统统替换为配置文件中的信息
			codeText = replaceTemplateCodeWithConfig(codeText);
			
			JavaCode code = new JavaCode("JdbcUtils.java", codeText);
			
			return code;
		} catch (Exception e) {
			throw new Template2CodeException("exception occured when JdbcUtils.tmp is translate to JdbcUtils.java",e);
		}
		
	}
	
	private static String replaceTemplateCodeWithConfig(String template) {
		Pattern p = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher m = p.matcher(template);
		while(m.find()) {
			String placeholder = m.group(0);
			String key = m.group(1);
			String value = MainConfigUtils.getPropery(key);
			template = template.replace(placeholder, value);
		}
		return template;
	}
	
	
}
