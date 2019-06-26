package com.gao.metadata;

import java.util.List;
import java.util.Set;

import com.gao.utils.Tuple;

// Ԫ���ݽӿ�
// �����������ͬ���ԵĹ���
public interface MetaData {
	// ��ȡ�ļ���
	public String getFileName();
	// ��ȡ����
	public String getPackageName();
	// ��ȡ��Ҫimport������Դ���б�
	public Set<Class> getImportSet();
	// ��ȡ����
	public String getClassName();
	// ��ȡ������
	public String getSuperClassName();
	// ��ȡ�ӿ�
	public Set<Class> getInterfaceSet();
	// ��ȡ�ֶ���Ϣ
	public List<Tuple<String, Class>> getFieldList();
}