package com.gao.factory;

import com.gao.autocoder.AutoCoder;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;

// 抽象工厂
public interface Factory {
	// 产品簇包括的产品有： Resouce、AutoCoder，也就是说，每一个产品簇中有这么两个产品等级
	Resource getResource();
	AutoCoder<? extends MetaData> getEntityAutoCoder();
	AutoCoder<? extends MetaData> getDaoInterfaceAutoCoder();
	AutoCoder<? extends MetaData> getDaoImplAutoCoder();
}
