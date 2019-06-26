package com.gao.resource;

import java.util.List;

import com.gao.metadata.MetaData;

// 资源接口
public interface Resource {
	// 把资源信息，解析为MetaData
	List<? extends MetaData> parse();
}