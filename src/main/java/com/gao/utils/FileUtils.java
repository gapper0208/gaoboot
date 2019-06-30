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
	
	// pack, 如果以${ }包起来则表示一个键值，否则就是直接量
	public static String package2FilePath(String pack) {
		String mavenSrcPath = FileUtils.getMavenSrcPath();
		String entityPath = null;
		// 判断参数是否是${...}格式的，如果是，则表示是主配置文件中的一个键值
		Pattern p = Pattern.compile("\\$\\{(.+?)\\}");
		Matcher matcher = p.matcher(pack);
		if(matcher.find()) {
			pack = matcher.group(1);
			// 把主配置中，所配置的包路径，转换为文件的path
			String entityPackage = MainConfigUtils.getPropery(pack);
			entityPath = entityPackage.replace(".", File.separator);
		} else {
			// 把传入的包，直接转换为文件的path
			entityPath = pack.replace(".", File.separator);
		}
		String path = mavenSrcPath + File.separator + entityPath;
		
		return path;
	}
}