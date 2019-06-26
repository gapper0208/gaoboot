package com.gao.resource.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import com.gao.exception.JdbcParseTableException;
import com.gao.exception.JdbcResourceInitializationException;
import com.gao.metadata.java.JavaClassMetaData;
import com.gao.resource.Resource;
import com.gao.utils.MainConfigUtils;
import com.gao.utils.StringUtils;
import com.gao.utils.Tuple;

public class JdbcResource implements Resource {

	private String driver;
	private String url;
	private String username;
	private String password;
	private String[] tableNames;
	
	private static List<Class> langClassList;
	
	static {
		langClassList = Arrays.asList(byte.class,Byte.class,short.class,Short.class,int.class,Integer.class,long.class,Long.class, //
				float.class,Float.class,double.class,Double.class,char.class,Character.class,String.class);
	}
	
	public JdbcResource(String resource) {
		if(resource == null) {
			throw new NullPointerException("resource can not be null");
		}
		try {
			Properties prop = initProperties(resource);
			loadPropertiesContent(prop);
		} catch (IOException e) {
			throw new JdbcResourceInitializationException(e);
		}
	}
	

	private Properties initProperties(String resource) throws IOException {
		InputStream in = JdbcResource.class.getClassLoader().getResourceAsStream(resource);
		Properties prop = new Properties();
		prop.load(in);
		return prop;
	}

	private void loadPropertiesContent(Properties prop) {
		driver = prop.getProperty("jdbc.driver");
		url = prop.getProperty("jdbc.url");
		username = prop.getProperty("jdbc.username");
		password = prop.getProperty("jdbc.password");
		tableNames = prop.getProperty("jdbc.reverseengineering.table").split(",");
	}

	@Override
	public List<JavaClassMetaData> parse() {
	
		List<JavaClassMetaData> metaDataList = new ArrayList<>();
		
		for (String tableName : tableNames) {
			JavaClassMetaData md = parseTable(tableName);
			metaDataList.add(md);
		}
		return metaDataList;
	}

	private JavaClassMetaData parseTable(String tableName) {
		JavaClassMetaData md = new JavaClassMetaData();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			String sql = "select * from " + tableName;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			// 设置文件名
			md.setFileName(StringUtils.raiseInitail(tableName) + ".java");
			// 设置entity的包的位置
			md.setPackageName(MainConfigUtils.getPropery("gaoboot.entity.package"));
			md.setClassName(StringUtils.raiseInitail(tableName));
			md.setFieldList(new ArrayList<Tuple<String,Class>>());
			
			int columnCount = rsmd.getColumnCount();
			for(int i = 1; i <= columnCount; i++) {
				String columnClassName = rsmd.getColumnClassName(i);
				String columnName = rsmd.getColumnName(i);
				Class<?> clazz = Class.forName(columnClassName);
				
				if(!langClassList.contains(clazz)) {
					md.getImportSet().add(clazz);
				}
				
				Tuple<String, Class> t = new Tuple<String, Class>(columnName, clazz);
				md.getFieldList().add(t);
			}
		} catch (Exception e) {
			throw new JdbcParseTableException("exception occured when parsed table " + tableName,e);
		}
		return md;
	}
	
	private Connection getConnection() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	private void free(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if(rs != null) rs.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(stmt != null) stmt.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			} finally {
				try {
					if(conn != null) conn.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
	

}