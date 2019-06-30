package com.gao.autocoder.java;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.util.List;
import java.util.Set;

import com.gao.autocoder.AutoCoder;
import com.gao.autocoder.codetemplate.CodeTemplateUtils;
import com.gao.code.Code;
import com.gao.code.java.JavaCode;
import com.gao.metadata.java.JavaClassMetaData;
import com.gao.utils.FileUtils;
import com.gao.utils.StringUtils;
import com.gao.utils.Tuple;

// 生成最简单的Jdbc的dao层实现
public class JavaJdbcDaoImplAutoCoder implements AutoCoder<JavaClassMetaData>{

	@Override
	public Code generateCode(JavaClassMetaData md) {
		
		generateJdbcUtils();
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		PrintWriter pw = new PrintWriter(bout);
		
		// package
		pw.println("package " + md.getBasePackageName() + ".dao.impl" + ";");
		// import
		pw.println("import java.util.List;");
		pw.println("import java.util.ArrayList;");
		pw.println("import java.sql.Connection;");
		pw.println("import java.sql.PreparedStatement;");
		pw.println("import java.sql.ResultSet;");
		pw.println("import " + md.getBasePackageName() + ".dao.I"+md.getClassName()+"Dao;");
		pw.println("import " + md.getBasePackageName() + ".utils.JdbcUtils;");
		pw.println("import " + md.getBasePackageName() + ".entity." + md.getClassName() + ";");
		Set<Class> importSet = md.getImportSet();
		for (Class clazz : importSet) {
			pw.println("import " + clazz.getName() + ";");
		}
		// class
		pw.println("public class " + md.getClassName() + "DaoImpl implements I"+md.getClassName()+"Dao{");
		// save
		pw.println("\tpublic void save("+ md.getClassName() +" "+ StringUtils.unraiseInitail(md.getClassName()) +") {");
		pw.println("\t\tConnection conn = null;");
		pw.println("\t\tPreparedStatement ps = null;");
		pw.println("\t\ttry {");
		pw.println("\t\t\tconn = JdbcUtils.getConnection();");
		// 动态生产insert语句
		String insertSql = generateInsertSql(md);
		pw.println("\t\t\tString sql="+insertSql+";");
		pw.println("\t\t\tps = conn.prepareStatement(sql);");
		pw.println("\t\t\tps.executeUpdate();");
		pw.println("\t\t} catch(Exception e) {");
		pw.println("\t\t\tthrow new RuntimeException(e);");
		pw.println("\t\t} finally {");
		pw.println("\t\t\tJdbcUtils.free(null,ps,conn);");
		pw.println("\t\t}");
		pw.println("\t}");
		// delete
		pw.println("\tpublic void delete("+ md.getObjectIdClass().getSimpleName() +" "+ md.getObjectIdName() +") {");
		pw.println("\t\tConnection conn = null;");
		pw.println("\t\tPreparedStatement ps = null;");
		pw.println("\t\ttry {");
		pw.println("\t\t\tconn = JdbcUtils.getConnection();");
		// 动态生产delete语句
		String deleteSql = generateDeleteSql(md);
		pw.println("\t\t\tString sql="+deleteSql+";");
		pw.println("\t\t\tps = conn.prepareStatement(sql);");
		pw.println("\t\t\tps.executeUpdate();");
		pw.println("\t\t} catch(Exception e) {");
		pw.println("\t\t\tthrow new RuntimeException(e);");
		pw.println("\t\t} finally {");
		pw.println("\t\t\tJdbcUtils.free(null,ps,conn);");
		pw.println("\t\t}");
		pw.println("\t}");
		// update
		pw.println("\tpublic void update("+ md.getClassName() +" "+ StringUtils.unraiseInitail(md.getClassName()) +") {");
		pw.println("\t\tConnection conn = null;");
		pw.println("\t\tPreparedStatement ps = null;");
		pw.println("\t\ttry {");
		pw.println("\t\t\tconn = JdbcUtils.getConnection();");
		// 动态生产update语句
		String updateSql = generateUpdateSql(md);
		pw.println("\t\t\tString sql="+updateSql+";");
		pw.println("\t\t\tps = conn.prepareStatement(sql);");
		pw.println("\t\t\tps.executeUpdate();");
		pw.println("\t\t} catch(Exception e) {");
		pw.println("\t\t\tthrow new RuntimeException(e);");
		pw.println("\t\t} finally {");
		pw.println("\t\t\tJdbcUtils.free(null,ps,conn);");
		pw.println("\t\t}");
		pw.println("\t}");
		// find all
		pw.println("\tpublic List<" + md.getClassName()  + "> find() {");
		pw.println("\t\tConnection conn = null;");
		pw.println("\t\tPreparedStatement ps = null;");
		pw.println("\t\tResultSet rs = null;");
		pw.println("\t\ttry {");
		pw.println("\t\t\tconn = JdbcUtils.getConnection();");
		String selectSql = "\"select * from " + StringUtils.unraiseInitail(md.getClassName()) + "\"";
		pw.println("\t\t\tString sql="+selectSql+";");
		pw.println("\t\t\tps = conn.prepareStatement(sql);");
		pw.println("\t\t\trs = ps.executeQuery();");
		pw.println("\t\t\tList<"+md.getClassName()+"> list = new ArrayList<>();");
		pw.println("\t\t\twhile(rs.next()) {");
		pw.println("\t\t\t\t" + md.getClassName() + " " + StringUtils.unraiseInitail(md.getClassName()) + " = new " + md.getClassName() + "();");
		List<Tuple<String, Class>> fieldList = md.getFieldList();
		for (Tuple<String, Class> tuple : fieldList) {
			String type = class2type(tuple); 
			pw.println("\t\t\t\t" + StringUtils.unraiseInitail(md.getClassName()) + ".set" + StringUtils.raiseInitail(tuple.getFisrt()) + "(rs.get"+type+"(\""+StringUtils.unraiseInitail(tuple.getFisrt())+"\"));");
		}
		pw.println("\t\t\t\tlist.add("+StringUtils.unraiseInitail(md.getClassName())+");");
		pw.println("\t\t\t}");
		pw.println("\t\t\treturn list;");
		pw.println("\t\t} catch(Exception e) {");
		pw.println("\t\t\tthrow new RuntimeException(e);");
		pw.println("\t\t} finally {");
		pw.println("\t\t\tJdbcUtils.free(rs,ps,conn);");
		pw.println("\t\t}");
		pw.println("\t}");
		// find one
		pw.println("\tpublic " + md.getClassName()  + " find(" + md.getObjectIdClass().getSimpleName() +" "+ md.getObjectIdName() + ") {");
		pw.println("\t\tConnection conn = null;");
		pw.println("\t\tPreparedStatement ps = null;");
		pw.println("\t\tResultSet rs = null;");
		pw.println("\t\ttry {");
		pw.println("\t\t\tconn = JdbcUtils.getConnection();");
		selectSql = "\"select * from " + StringUtils.unraiseInitail(md.getClassName()) + " where "+ md.getObjectIdName() +" = " + md.getObjectIdName() + "\"";
		pw.println("\t\t\tString sql="+selectSql+";");
		pw.println("\t\t\tps = conn.prepareStatement(sql);");
		pw.println("\t\t\trs = ps.executeQuery();");
		pw.println("\t\t\t" + md.getClassName() + " " + StringUtils.unraiseInitail(md.getClassName()) + " = null;");
		pw.println("\t\t\tif(rs.next()) {");
		pw.println("\t\t\t\t" + StringUtils.unraiseInitail(md.getClassName()) + " = new " + md.getClassName() + "();");
		fieldList = md.getFieldList();
		for (Tuple<String, Class> tuple : fieldList) {
			String type = class2type(tuple); 
			pw.println("\t\t\t\t" + StringUtils.unraiseInitail(md.getClassName()) + ".set" + StringUtils.raiseInitail(tuple.getFisrt()) + "(rs.get"+type+"(\""+StringUtils.unraiseInitail(tuple.getFisrt())+"\"));");
		}
		pw.println("\t\t\t}");
		pw.println("\t\t\treturn " + StringUtils.unraiseInitail(md.getClassName()) + ";");
		pw.println("\t\t} catch(Exception e) {");
		pw.println("\t\t\tthrow new RuntimeException(e);");
		pw.println("\t\t} finally {");
		pw.println("\t\t\tJdbcUtils.free(rs,ps,conn);");
		pw.println("\t\t}");
		pw.println("\t}");
		pw.println("}");
		pw.close();
		
		byte[] bb = bout.toByteArray();
		String codeText = new String(bb);
		
		JavaCode code = new JavaCode(md.getClassName() + "DaoImpl.java", codeText);
		code.setDescription("java dao implemention");
		
		return code;
	}


	private String class2type(Tuple<String, Class> tuple) {
		String type = null;
		if(tuple.getSecond() == Byte.class || tuple.getSecond() == byte.class) {
			type = "Byte";
		} else if(tuple.getSecond() == Short.class || tuple.getSecond() == short.class) {
			type = "Short";
		} else if(tuple.getSecond() == Integer.class || tuple.getSecond() == int.class) {
			type = "Int";
		} else if(tuple.getSecond() == Long.class || tuple.getSecond() == long.class) {
			type = "Long";
		} else if(tuple.getSecond() == Float.class || tuple.getSecond() == float.class) {
			type = "Float";
		} else if(tuple.getSecond() == Double.class || tuple.getSecond() == double.class) {
			type = "Double";
		} else if(tuple.getSecond() == BigDecimal.class) {
			type = "Double";
		} else if(tuple.getSecond() == Boolean.class || tuple.getSecond() == boolean.class) {
			type = "Boolean";
		} else if(tuple.getSecond() == Character.class || tuple.getSecond() == char.class) {
			type = "String";
		} else if(tuple.getSecond() == String.class) {
			type = "String";
		} else if(tuple.getSecond() == Date.class) {
			type = "Date";
		}
		return type;
	}


	private String generateUpdateSql(JavaClassMetaData md) {
		// 实体参数的名字
		String entityVarName = StringUtils.unraiseInitail(md.getClassName());
		
		StringBuilder sb = new StringBuilder("\"update ");
		sb.append(StringUtils.unraiseInitail(md.getClassName()) + " ");
		sb.append("set ");
		List<Tuple<String, Class>> fieldList = md.getFieldList();
		for (Tuple<String, Class> tuple : fieldList) {
			if(tuple.getFisrt().equals(md.getObjectIdName()))
				continue;
			sb.append(tuple.getFisrt());
			sb.append("='\" + ");
			sb.append(entityVarName + ".get" + StringUtils.raiseInitail(tuple.getFisrt()) + "()");
			sb.append(" + \"',");
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(" where " + md.getObjectIdName() + " = \" + " + entityVarName + ".get" + StringUtils.raiseInitail(md.getObjectIdName()) + "()");
		return sb.toString();
	}


	private String generateDeleteSql(JavaClassMetaData md) {
		StringBuilder sb = new StringBuilder("\"delete from ");
		sb.append(StringUtils.unraiseInitail(md.getClassName()) + " ");
		sb.append("where " + md.getObjectIdName() + " = \" + " + md.getObjectIdName());
		return sb.toString();
	}


	private String generateInsertSql(JavaClassMetaData md) {
		// 实体参数的名字
		String entityVarName = StringUtils.unraiseInitail(md.getClassName());
		
		StringBuilder sb = new StringBuilder("\"insert into ");
		sb.append(StringUtils.unraiseInitail(md.getClassName()) + " ");
		sb.append("values(");
		List<Tuple<String, Class>> fieldList = md.getFieldList();
		for (Tuple<String, Class> tuple : fieldList) {
			if(tuple.getFisrt().equals(md.getObjectIdName())) {
				sb.append("null,");
			} else {
				sb.append("'\" + " + entityVarName + ".get" + StringUtils.raiseInitail(tuple.getFisrt()) + "() + \"',");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append(")\"");
		return sb.toString();
	}


	private void generateJdbcUtils() {
		// 获取基本包名
		String basePath = FileUtils.package2FilePath("${gaoboot.base.package}") + File.separator;
		// 根据JdbcUtils.tmp模板来生成JdbcUtils.java源文件
		Code jdbcUtilsCode = CodeTemplateUtils.template2code("JdbcUtils.tmp");
		// 存盘
		jdbcUtilsCode.saveToDisk(basePath + "utils");
	}

}
