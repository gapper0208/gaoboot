package com.gao.code;

public interface Code {
	// �������ڵ��ļ���
	String fileName();
	// ��ǰ����������������
	String language();
	// ������ǰ���������������ͣ�ģ�͡�ҵ�񡢿�������dao���ȵ�..��
	String description();
	// ���ַ�����ʽ��ȡ����
	String getCodeText();
	// ����̬���ɵ�Դ�������
	void saveToDisk(String path);
	
}