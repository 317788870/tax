package com.tsms.util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 数据库操作通用工具类
 */
public class DBUtil {
	private static String driverClass = "";
	private static String user = "";
	private static String password = "";
	private static String url = "";
	private static DBUtil instance = null;

	private DBUtil() {}

	public static DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}

	/**
	 * 在静态代码块中加载，获取数据库配置参数
	 */
	static {
		Properties prop = new Properties();
		// 将jdbc配置信息加载内存并且设置到properites对象中
		try {
			prop.load(DBUtil.class.getResourceAsStream("/jdbc.properties"));
			driverClass = prop.getProperty("driverClass");
			user = prop.getProperty("user");
			password = prop.getProperty("password");
			url = prop.getProperty("url");
			Class.forName(driverClass);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 获取数据库连接的方法
	 * 
	 * @return:返回连接对象
	 */
	private Connection getConnection() {
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * 关闭操作
	 * 
	 * @param rs:查询的结果集
	 * @param stmt:查询语句对象
	 * @param conn:查询的连接对象
	 */
	private void close(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 执行查询语句的通用方法
	 * 
	 * @param sql:执行的sql预编译语句
	 * @param params:可变参数
	 * @return:将结果返回到list集合
	 */
	public List<Map<String, String>> query(String sql, Object...params) {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Connection conn = getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			// 执行结果
			rs = ps.executeQuery();
			// 获取到结果集元数据,封装本次查询，查询多少列，这些列名是什么
			ResultSetMetaData rsmd = rs.getMetaData();
			// 获取查询的总列数
			int count = rsmd.getColumnCount();

			while (rs.next()) {
				// 创建一个Map集合，将当前行的所有的列放置到map集合中
				Map<String, String> rowsData = new HashMap<String, String>();
				for (int i = 0; i < count; i++) {
					// 根据列的索引获取列名
					String columnLabel = rsmd.getColumnLabel(i + 1);
					// 根据列名从结果集中获取某一列的值
					String columnValue = rs.getString(columnLabel);

					rowsData.put(columnLabel, columnValue);
				}
				// 将当前行数据放置到list集合中
				list.add(rowsData);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 根据id查询指定记录
	 * 
	 * @param sql:执行的sql语句
	 * @param row:指定id
	 * @return:返回记录结果
	 */
	public Map<String, String> queryOneRow(String sql, int id) {
		List<Map<String, String>> result = query(sql, id);
		if (result != null && result.size() == 1) {
			return result.get(0);
		}
		return null;
	}

	/**
	 * 增删改通用方法
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public boolean update(String sql, Object[] params) {
		Connection conn = getConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql);
			if (params != null && params.length > 0) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			int rows = ps.executeUpdate();
			if (rows >= 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(null, ps, conn);
		}
		return false;
	}

	
}
