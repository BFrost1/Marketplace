package ua.com.deviant.entity;

public class Tablet extends Product{

	public Tablet(String name, Store store, Category category, Manufacturer manufacturer, int price) {
		super(name, store, category, manufacturer, price);
	}

	public Tablet(int id, String name, Store store, Category category, Manufacturer manufacturer, int price) {
		super(id, name, store, category, manufacturer, price);
	}


}
