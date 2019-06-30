package com.gao.factory;

import com.gao.factory.impl.JdbcCodeFactory;
import com.gao.utils.MainConfigUtils;

// �򵥹�����ȱ�㣺��չ�Բ��ã���ʱ��������..
public class GaoBootFacoryBuilder {
	
	public GaoBootFacoryBuilder() {
	}
	
	public static Factory buildGaoBootFactory() {
		Factory factory = null;
		String framework = MainConfigUtils.getPropery("gaoboot.framework");
		switch (framework) {
		case "java.jdbc":
			factory = new JdbcCodeFactory();
			break;
		case "java.ssm":
			// ����ssm��dao�����
			// not yet..
			// ����ʣ����������
			break;
		case "java.ssh":
			// ����ssh��dao�����
			// not yet..
			// ����ʣ����������
			break;
		}
		return factory;
	}
}
