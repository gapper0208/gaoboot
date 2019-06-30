package com.gao.factory.impl;

import com.gao.autocoder.AutoCoder;
import com.gao.autocoder.java.JavaDaoInterfaceAutoCoder;
import com.gao.autocoder.java.JavaEntityAutoCoder;
import com.gao.autocoder.java.JavaJdbcDaoImplAutoCoder;
import com.gao.factory.Factory;
import com.gao.metadata.MetaData;
import com.gao.resource.Resource;
import com.gao.resource.jdbc.JdbcResource;
import com.gao.utils.MainConfigUtils;

public class JdbcCodeFactory implements Factory {

	@Override
	public Resource getResource() {
		String resource = MainConfigUtils.getPropery("gaoboot.jdbc.properties");
		return new JdbcResource(resource);
	}

	@Override
	public AutoCoder<? extends MetaData> getEntityAutoCoder() {
		return new JavaEntityAutoCoder();
	}

	@Override
	public AutoCoder<? extends MetaData> getDaoInterfaceAutoCoder() {
		return new JavaDaoInterfaceAutoCoder();
	}

	@Override
	public AutoCoder<? extends MetaData> getDaoImplAutoCoder() {
		return new JavaJdbcDaoImplAutoCoder();
	}

}
