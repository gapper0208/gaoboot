package com.gao.resource.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
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
			
			// 获取主键的列名（暂时没有考虑复合主键）
			DatabaseMetaData dbmd = conn.getMetaData();
			ResultSet key_rs = dbmd.getPrimaryKeys("", "", tableName);
			String primaryColumnName = null;
			if(key_rs.next()) {
				primaryColumnName = key_rs.getString("COLUMN_NAME");
			}
			
			String sql = "select * from " + tableName;
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			
			// 设置基本文件名(不带前缀，不带后缀)，将来在具体的代码生成器中在加前后缀。比如: I+文件名+Dao
			md.setFileName(StringUtils.raiseInitail(tableName));
			// 设置基本包的名字，比如com.gao,后续在不同的代码生成器中，再将包名补全。
			md.setBasePackageName(MainConfigUtils.getPropery("gaoboot.base.package"));
			md.setClassName(StringUtils.raiseInitail(tableName));
			md.setFieldList(new ArrayList<Tuple<String,Class>>());
			
			int columnCount = rsmd.getColumnCount();
			
			for(int i = 1; i <= columnCount; i++) {
				String columnClassName = rsmd.getColumnClassName(i);
				String columnName = rsmd.getColumnName(i);
				Class<?> clazz = Class.forName(columnClassName);
				
				// 如果迭代的列名与主键的列名相同，则把该列（也既是主键）的类型和列名封装进自定义的元数据对象中
				if(columnName.equals(primaryColumnName)) {
					md.setObjectIdClass(clazz);
					md.setObjectIdName(columnName);
				}
				
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