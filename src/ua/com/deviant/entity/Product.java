package ua.com.deviant.entity;


public abstract class Product {
	protected int id;
	protected String name;
	protected Store store;
	protected Category category;
	protected Manufacturer manufacturer;
	protected int price;
	
	public Product(String name, Store store, Category category, Manufacturer manufacturer, int price) {
		this.name = name;
		this.store = store;
		this.category = category;
		this.manufacturer = manufacturer;
		this.price = price;
	}

	public Product(int id, String name, Store store, Category category, Manufacturer manufacturer, int price) {
		this.id = id;
		this.name = name;
		this.store = store;
		this.category = category;
		this.manufacturer = manufacturer;
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", store=" + store + ", category=" + category
				+ ", manufacturers=" + manufacturer + ", price=" + price + "]";
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Store getStore() {
		return store;
	}

	public Category getCategory() {
		return category;
	}

	public Manufacturer getManufacturers() {
		return manufacturer;
	}

	public int getPrice() {
		return price;
	}
}
