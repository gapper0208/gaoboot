package com.gao.code;

// 代表任意代码的接口
public interface Code {
	// 代码所在的文件名
	String fileName();
	// 当前代码属于哪种语言
	String language();
	// 描述当前代码属于哪种类型（模型、业务、控制器、dao、等等..）
	String getDescription();
	void setDescription(String description);
	// 以字符串形式获取代码
	String getCodeText();
	// 将动态生成的源代码存盘
	void saveToDisk(String path);
	// 获取源代码的扩展名
	String getExt();
	
}