package ua.com.deviant.requestsAPI;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ua.com.deviant.database.FactoryProduct;
import ua.com.deviant.entity.Product;

public class StorePhoneAPI implements StoreAPI {
	private static final String GET_URL = "store_phone";
	private static final String GET_ALL_PRODUCTS_STORE = "SELECT product.id, product.name, store.name, category.name, manufacturers.name, product.price FROM product JOIN store ON store.id = product.store_id JOIN category ON category.id = product.category_id JOIN manufacturers ON manufacturers.id = product.manufacturers_id ";
	private static final String GET_ID_PRODUCT_STORE = GET_ALL_PRODUCTS_STORE + "WHERE product.id = %d;";
	private DBWorkerAPI worker;

	public StorePhoneAPI() {
		this.worker = new DBWorkerAPI(GET_URL);
	}

	@Override
	public Product get(int id) {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		Product product = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(String.format(GET_ID_PRODUCT_STORE, id));
				if (rs.next()) {
					product = FactoryProduct.creatProduct(rs.getInt("product.id"), rs.getString("product.name"),
							rs.getString("store.name"), rs.getString("category.name"),
							rs.getString("manufacturers.name"), rs.getInt("product.price"));
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
		return product;
	}

	@Override
	public List<Product> getAll() {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Product> products = new LinkedList<Product>();

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(GET_ALL_PRODUCTS_STORE);
				while (rs.next()) {
					products.add(FactoryProduct.creatProduct(rs.getInt("product.id"), rs.getString("product.name"),
							rs.getString("store.name"), rs.getString("category.name"),
							rs.getString("manufacturers.name"), rs.getInt("product.price")));
				}

			} catch (SQLException e) {
				e.printStackTrace();
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
		return products;
	}
}
