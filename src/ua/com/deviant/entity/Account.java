package ua.com.deviant.entity;

import java.util.Objects;

public class Account {
	private String loggin;
	private String password;
	private Store store;
		
	public Account() {
	}

	public Account(String loggin, String password, Store store) {
		this.loggin = loggin;
		this.password = password;
		this.store = store;
	}
	
	
	@Override
	public String toString() {
		return "Account [loggin=" + loggin + ", password=" + password + ", store=" + store + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(loggin, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(loggin, other.loggin) && Objects.equals(password, other.password);
	}

	
	public String getLoggin() {
		return loggin;
	}

	public String getPassword() {
		return password;
	}

	public Store getStore() {
		return store;
	}
	
}
