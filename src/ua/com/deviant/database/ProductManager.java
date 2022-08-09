package ua.com.deviant.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ua.com.deviant.entity.Product;
import ua.com.deviant.entity.Store;
import ua.com.deviant.requestsAPI.StoreAPI;

public class ProductManager {
	private static final String GET_ALL_PRODUCTS = "SELECT product.id, product.name, store.name, category.name, manufacturers.name, product.price FROM product JOIN store ON store.id = product.store_id JOIN category ON category.id = product.category_id JOIN manufacturers ON manufacturers.id = product.manufacturers_id ";
	private static final String GET_ID_PRODUCT = GET_ALL_PRODUCTS + "WHERE product.id = %d;";
	private static final String GET_STORE_PRODUCT = GET_ALL_PRODUCTS + "WHERE store.name = '%s';";
	private static final String ADD_PRODUCT = "INSERT INTO product (name, price, category_id, manufacturers_id, store_id) SELECT '%s', '%d', category.id, manufacturers.id, store.id FROM category, manufacturers, store WHERE category.name = '%s' AND manufacturers.name = '%s' AND store.name = '%s';";
	private static final String UPDATE_PRODUCT = "UPDATE product SET name = '%s', price = %d, category_id  = (SELECT category.id FROM category WHERE category.name = '%s'), manufacturers_id = (SELECT manufacturers.id FROM manufacturers WHERE manufacturers.name = '%s'), store_id = (SELECT store.id FROM store WHERE store.name = '%s') WHERE product.id = %d;";
	private static final String DELETE_ALL_PRODUCTS = "DELETE FROM product ";
	private static final String DELETE_ID_PRODUCT = DELETE_ALL_PRODUCTS + "WHERE id=%d;";
	private static final String DELETE_STORE_PRODUCTS = "DELETE product FROM product JOIN store ON product.store_id = store.id WHERE store.name = '%s';";

	private DBWorker worker;

	public ProductManager(DBWorker worker) {
		this.worker = worker;
	}

	public Product get(int id) {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		Product product = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(String.format(GET_ID_PRODUCT, id));
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

	public List<Product> getAll() {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Product> products = new LinkedList<Product>();

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(GET_ALL_PRODUCTS);

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

	public List<Product> getAll(Store store) {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Product> products = new LinkedList<Product>();

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(String.format(GET_STORE_PRODUCT, store.getName()));
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

	public void add(Product product) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(String.format(ADD_PRODUCT, product.getName(), product.getPrice(),
						product.getCategory().getName(), product.getManufacturers().getName(),
						product.getStore().getName()));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void add(List<Product> products) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn) && Objects.nonNull(products)) {
			try {
				st = conn.createStatement();
				for (Product product : products) {
					st.execute(String.format(ADD_PRODUCT, product.getName(), product.getPrice(),
							product.getCategory().getName(), product.getManufacturers().getName(),
							product.getStore().getName()));
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void update(Product product) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(String.format(UPDATE_PRODUCT, product.getName(), product.getPrice(),
						product.getCategory().getName(), product.getManufacturers().getName(),
						product.getStore().getName(), product.getId()));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void update(List<Product> products) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				for (Product product : products) {
					st.execute(String.format(UPDATE_PRODUCT, product.getName(), product.getPrice(),
							product.getCategory().getName(), product.getManufacturers().getName(),
							product.getStore().getName(), product.getId()));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void remove(int id) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(String.format(DELETE_ID_PRODUCT, id));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void remove(Store story) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(String.format(DELETE_STORE_PRODUCTS, story.getName()));
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void removeAll() {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(DELETE_ALL_PRODUCTS);
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
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
	}

	public void synchronizeBD(StoreAPI storeAPI) {
		final List<Product> list = storeAPI.getAll();
		if (list.size() > 0) {
			remove(list.get(0).getStore());
		}
		add(storeAPI.getAll());
	}

}
