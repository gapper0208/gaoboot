package com.gao.autocoder.java;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import com.gao.autocoder.AutoCoder;
import com.gao.code.Code;
import com.gao.code.java.JavaCode;
import com.gao.metadata.java.JavaClassMetaData;
import com.gao.utils.StringUtils;
import com.gao.utils.Tuple;

public class JavaEntityAutoCoder implements AutoCoder<JavaClassMetaData> {
	
	public Code generateCode(JavaClassMetaData md) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(bout);
		
		// package
		pw.println("package " + md.getBasePackageName() + ".entity;");
		// import
		Set<Class> importSet = md.getImportSet();
		for (Class clazz : importSet) {
			pw.println("import " + clazz.getName() + ";");
		}
		// class
		pw.println("public class " + md.getClassName() + "{");
		// field
		List<Tuple<String, Class>> fieldList = md.getFieldList();
		for (Tuple<String, Class> tuple : fieldList) {
			pw.println("\tprivate " + tuple.getSecond().getSimpleName() + " " + tuple.getFisrt() + ";");
		}
		// getter and setter
		for (Tuple<String, Class> tuple : fieldList) {
			String type = tuple.getSecond().getSimpleName();
			String name = StringUtils.raiseInitail(tuple.getFisrt());
			pw.println("\tpublic " + type + " get" + name + "() {");
			pw.println("\t\treturn " + tuple.getFisrt() + ";");
			pw.println("\t}");
			
			pw.println("\tpublic void set" + name + "(" + type + " " + tuple.getFisrt() + ") {");
			pw.println("\t\tthis." + tuple.getFisrt() + " = " + tuple.getFisrt() + ";");
			pw.println("\t}");
		}
		pw.println("}");
		
		pw.close();
		
		byte[] bb = bout.toByteArray();
		
		String codeText = new String(bb);
		
		JavaCode jc = new JavaCode(md.getFileName(), codeText);
		
		return jc;
	}


}