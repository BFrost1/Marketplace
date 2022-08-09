package ua.com.deviant.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

import ua.com.deviant.entity.Account;

import ua.com.deviant.entity.Store;

public class AccountManager {
	private static final String GET_ACCOUNT = "SELECT account.loggin, account.PASSWORD, store.name FROM account JOIN store ON account.store_id = store.id WHERE account.loggin = '%s';";
	
	private DBWorker worker;

	public AccountManager(DBWorker worker) {
		this.worker = worker;
	}
	
	
	public Account get(String loggin) {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		Account account = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(String.format(GET_ACCOUNT, loggin));
				if (rs.next()) {
					account = new Account(rs.getString("account.loggin"), rs.getString("account.PASSWORD"), new Store(rs.getString("store.name")));
				}

			} catch (SQLException e) {
				System.out.println(e.getMessage());
			} finally {
				try {
					if (Objects.nonNull(rs)) {
						rs.close();
					}
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
				try {
					if (Objects.nonNull(st)) {
						st.close();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				try {
					if (Objects.nonNull(conn)) {
						conn.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return account;
	}
}
