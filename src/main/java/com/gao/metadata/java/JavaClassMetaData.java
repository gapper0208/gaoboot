package com.gao.metadata.java;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.gao.metadata.MetaData;
import com.gao.utils.Tuple;

public class JavaClassMetaData implements MetaData {
	private String fileName;
	private String basePackageName;
	private Set<Class> importSet = new HashSet<>();
	private String className;
	private String superClassName;
	private Set<Class> interfaceSet;
	private List<Tuple<String,Class>> fieldList;
	// 主属性类型
	private Class ObjectIdClass;
	// 主属性名字
	private String ObjectIdName;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getBasePackageName() {
		return basePackageName;
	}
	public void setBasePackageName(String basePackageName) {
		this.basePackageName = basePackageName;
	}
	public Set<Class> getImportSet() {
		return importSet;
	}
	public void setImportSet(Set<Class> importSet) {
		this.importSet = importSet;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getSuperClassName() {
		return superClassName;
	}
	public void setSuperClassName(String superClassName) {
		this.superClassName = superClassName;
	}
	public Set<Class> getInterfaceSet() {
		return interfaceSet;
	}
	public void setInterfaceSet(Set<Class> interfaceSet) {
		this.interfaceSet = interfaceSet;
	}
	public List<Tuple<String, Class>> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<Tuple<String, Class>> fieldList) {
		this.fieldList = fieldList;
	}
	public Class getObjectIdClass() {
		return ObjectIdClass;
	}
	public void setObjectIdClass(Class objectIdClass) {
		ObjectIdClass = objectIdClass;
	}
	public String getObjectIdName() {
		return ObjectIdName;
	}
	public void setObjectIdName(String objectIdName) {
		ObjectIdName = objectIdName;
	}
}
