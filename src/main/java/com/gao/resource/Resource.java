package com.gao.resource;

import java.util.List;

import com.gao.metadata.MetaData;

// ��Դ�ӿ�
public interface Resource {
	// ����Դ��Ϣ������ΪMetaData
	List<? extends MetaData> parse();
}