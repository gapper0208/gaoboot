package com.gao.utils;

import java.io.File;

public class FileUtils {

	public static String getMavenSrcPath() {
		File tmp = new File("");
		String path = tmp.getAbsolutePath() + File.separator + "src main java".replace(" ", File.separator);
		return path;
	}
	
	// ���������У������õİ�·����ת��Ϊ�ļ��ľ���·��
	public static String package2FilePath(String packageKey) {
		String mavenSrcPath = FileUtils.getMavenSrcPath();
		String entityPackage = MainConfigUtils.getPropery(packageKey);
		String entityPath = entityPackage.replace(".", File.separator);
		String path = mavenSrcPath + File.separator + entityPath;
		return path;
	}
	
}