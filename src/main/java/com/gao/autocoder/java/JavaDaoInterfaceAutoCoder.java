package com.gao.autocoder.java;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;
import java.util.List;

import com.gao.autocoder.AutoCoder;
import com.gao.code.Code;
import com.gao.code.java.JavaCode;
import com.gao.metadata.MetaData;
import com.gao.metadata.java.JavaClassMetaData;
import com.gao.utils.StringUtils;
import com.gao.utils.Tuple;

public class JavaDaoInterfaceAutoCoder implements AutoCoder<JavaClassMetaData> {
	@Override
	public Code generateCode(JavaClassMetaData md) {
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(bout);
		
		// package
		pw.println("package " + md.getBasePackageName() + ".dao;");
		// import
		pw.println("import " + md.getBasePackageName() + ".entity." + md.getClassName() + ";");
		// class
		pw.println("public class I" + md.getClassName() + "Dao {");
		// abstract method
		pw.println("\tvoid save("+ md.getClassName() +" "+ StringUtils.unraiseInitail(md.getClassName()) +");");
		pw.println("}");
		
		pw.close();
		
		byte[] bb = bout.toByteArray();
		String codeText = new String(bb);
		JavaCode jc = new JavaCode(md.getFileName(), codeText);
		
		return jc;
	}

}
