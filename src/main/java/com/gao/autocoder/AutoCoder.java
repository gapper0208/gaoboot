package com.gao.autocoder;

import com.gao.code.Code;
import com.gao.metadata.MetaData;

// �����������ӿ�
public interface AutoCoder<T extends MetaData> {
	// ����Ԫ���������ɴ���
	Code generateCode(T md); 
}
