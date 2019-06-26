package com.gao.code.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import com.gao.code.Code;
import com.gao.exception.GenerateCodeException;
import com.gao.utils.FileUtils;
import com.gao.utils.MainConfigUtils;

public class JavaCode implements Code {
	private String fileName;
	private String codeText;
	public JavaCode() {
	}
	public JavaCode(String fileName) {
		this.fileName = fileName;
	}
	public JavaCode(String fileName, String codeText) {
		this.fileName = fileName;
		this.codeText = codeText;
	}
	@Override
	public void saveToDisk(String path) {
		File entityDir = new File(path);
		if(!entityDir.exists()) {
			entityDir.mkdirs();
		}
		File entityFile = new File(entityDir, fileName);
		try {
			Writer out = new FileWriter(entityFile);
			out.write(codeText);
			out.close();
		} catch (IOException e) {
			throw new GenerateCodeException("exception occured when generate code of " + fileName, e);
		}
	}
	
	@Override
	public String language() {
		return "java";
	}
	@Override
	public String description() {
		return "entity, or model";
	}
	@Override
	public String fileName() {
		return fileName;
	}
	@Override
	public String getCodeText() {
		return codeText;
	}
	public void setCodeText(String codeText) {
		this.codeText = codeText;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}