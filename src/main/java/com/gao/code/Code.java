package com.gao.code;

// �����������Ľӿ�
public interface Code {
	// �������ڵ��ļ���
	String fileName();
	// ��ǰ����������������
	String language();
	// ������ǰ���������������ͣ�ģ�͡�ҵ�񡢿�������dao���ȵ�..��
	String getDescription();
	void setDescription(String description);
	// ���ַ�����ʽ��ȡ����
	String getCodeText();
	// ����̬���ɵ�Դ�������
	void saveToDisk(String path);
	// ��ȡԴ�������չ��
	String getExt();
	
}