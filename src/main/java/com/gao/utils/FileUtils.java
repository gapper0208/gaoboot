package com.gao.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {

	public static String getMavenSrcPath() {
		File tmp = new File("");
		String path = tmp.getAbsolutePath() + File.separator + "src main java".replace(" ", File.separator);
		return path;
	}
	
	// pack, �����${ }���������ʾһ����ֵ���������ֱ����
	public static String package2FilePath(String pack) {
		String mavenSrcPath = FileUtils.getMavenSrcPath();
		String entityPath = null;
		// �жϲ����Ƿ���${...}��ʽ�ģ�����ǣ����ʾ���������ļ��е�һ����ֵ
		Pattern p = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher = p.matcher(pack);
		if(matcher.find()) {
			pack = matcher.group(1);
			// ���������У������õİ�·����ת��Ϊ�ļ���path
			String entityPackage = MainConfigUtils.getPropery(pack);
			entityPath = entityPackage.replace(".", File.separator);
		} else {
			// �Ѵ���İ���ֱ��ת��Ϊ�ļ���path
			entityPath = pack.replace(".", File.separator);
		}
		String path = mavenSrcPath + File.separator + entityPath;
		
		return path;
	}
}