package com.gao.metadata;

import java.util.List;
import java.util.Set;

import com.gao.utils.Tuple;

// 元数据接口
// 尽量抽象出不同语言的共性
public interface MetaData {
	// 获取文件名
	public String getFileName();
	// 获取包名
	public String getPackageName();
	// 获取需要import的类资源的列表
	public Set<Class> getImportSet();
	// 获取类名
	public String getClassName();
	// 获取父类名
	public String getSuperClassName();
	// 获取接口
	public Set<Class> getInterfaceSet();
	// 获取字段信息
	public List<Tuple<String, Class>> getFieldList();
}