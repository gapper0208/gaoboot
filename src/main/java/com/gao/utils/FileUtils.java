package com.gao.utils;

import java.io.File;

public class FileUtils {

	public static String getMavenSrcPath() {
		File tmp = new File("");
		String path = tmp.getAbsolutePath() + File.separator + "src main java".replace(" ", File.separator);
		return path;
	}
	
	// 把主配置中，所配置的包路径，转换为文件的绝对路径
	public static String package2FilePath(String packageKey) {
		String mavenSrcPath = FileUtils.getMavenSrcPath();
		String entityPackage = MainConfigUtils.getPropery(packageKey);
		String entityPath = entityPackage.replace(".", File.separator);
		String path = mavenSrcPath + File.separator + entityPath;
		return path;
	}
	
}