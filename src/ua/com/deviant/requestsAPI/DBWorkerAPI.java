package ua.com.deviant.requestsAPI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBWorkerAPI {
	private String url;

	
	public DBWorkerAPI(String url) {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		this.url = url;
	}

	public Connection getConnection() {
		Connection conn = null;
		try {
			if (Objects.nonNull(url)) {
				conn = DriverManager.getConnection(String.format("jdbc:mysql://localhost/%s?" + "user=root&password=", url));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
