package ua.com.deviant.entity;

import java.util.Objects;

public class Store {
	private int id;
	private String name;

	public Store(String name) {
		this.name = name;

	}
	
	public Store(int id, String name) {
		this.id = id;
		this.name = name;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Store other = (Store) obj;
		return Objects.equals(name, other.name);
	}

	@Override
	public String toString() {
		return "Store [name=" + name + "]";
	}

	
	
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
