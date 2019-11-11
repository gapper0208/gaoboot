package com.gao.autocoder;

import com.gao.code.Code;
import com.gao.metadata.MetaData;

// 代码生成器接口
public interface AutoCoder<T extends MetaData> {
	// 根据元数据来生成代码
	Code generateCode(T md); 
}
