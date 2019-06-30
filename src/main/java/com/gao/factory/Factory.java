package com.gao.factory;

import com.gao.autocoder.AutoCoder;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;

// ���󹤳�
public interface Factory {
	// ��Ʒ�ذ����Ĳ�Ʒ�У� Resouce��AutoCoder��Ҳ����˵��ÿһ����Ʒ��������ô������Ʒ�ȼ�
	Resource getResource();
	AutoCoder<? extends MetaData> getEntityAutoCoder();
	AutoCoder<? extends MetaData> getDaoInterfaceAutoCoder();
	AutoCoder<? extends MetaData> getDaoImplAutoCoder();
}
