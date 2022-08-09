package ua.com.deviant.requestsAPI;


import java.util.List;

import ua.com.deviant.entity.Product;

public interface StoreAPI {
	
	Product get(int id);
	List<Product> getAll();
	
}
