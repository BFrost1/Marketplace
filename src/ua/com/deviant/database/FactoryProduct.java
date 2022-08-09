package ua.com.deviant.database;

import ua.com.deviant.entity.Category;
import ua.com.deviant.entity.Manufacturer;
import ua.com.deviant.entity.Phone;
import ua.com.deviant.entity.Product;
import ua.com.deviant.entity.Store;
import ua.com.deviant.entity.Tablet;

public class FactoryProduct {

	public static Product creatProduct(int id, String name, String store, String category, String manufacturers, int price) {
		Product product = null;
		switch (category) {
		case "Phone":
			product = new Phone(id, name, new Store(store), new Category(category), new Manufacturer(manufacturers), price);
			break;
		case "Tablet":
			product = new Tablet(id, name, new Store(store), new Category(category), new Manufacturer(manufacturers), price);
			break;
			
		}
		return product;
	}
}
