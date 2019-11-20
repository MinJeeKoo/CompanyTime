package model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionManager {
	private static final String DB_DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DB_URL = "jdbc:oracle:thin:@202.20.119.117:1521:orcl";
	private static final String DB_USERNAME = "dbp0102";
	private static final String DB_PASSWORD = "dbp0102";
	private static DataSource ds = null;
	
	public ConnectionManager() {
		try {
			BasicDataSource bds = new BasicDataSource();
			bds.setDriverClassName(DB_DRIVER);
			bds.setUrl(DB_URL);
			bds.setUsername(DB_USERNAME);
			bds.setPassword(DB_PASSWORD);
			ds = bds;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public void close() {
		BasicDataSource bds = (BasicDataSource) ds;
		try {
			bds.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	public void printDataSourceStats() {
		try {
			BasicDataSource bds = (BasicDataSource) ds;
			System.out.println("NumActive: " + bds.getNumActive());
			System.out.println("NumIdle: " + bds.getNumIdle());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
