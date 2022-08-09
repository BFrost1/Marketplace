package ua.com.deviant.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ua.com.deviant.entity.Category;


public class CategoryManager {
	private static final String GET_ALL_CATEGORY = "SELECT category.id, category.name FROM category ";
	private static final String GET_ID_CATEGORY = GET_ALL_CATEGORY + "WHERE id = %d";
	private static final String ADD_CATEGORY = "INSERT INTO category (name) SELECT '%s' FROM DUAL WHERE NOT EXISTS(SELECT 1 FROM category WHERE name = '%s');";
	private static final String DELETE_ID_PRODUCTS = "DELETE FROM category WHERE id=%d;";
	
	private DBWorker worker;
	
	public CategoryManager(DBWorker worker) {
		this.worker = worker;
	}

	
	public Category get(int id) {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		Category category = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(String.format(GET_ID_CATEGORY, id));
				
				if (rs.next()) {
					category = new Category(rs.getInt("id"),rs.getString("name"));
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
		return category;
	}
	
	public List<Category> getAll() {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Category> categorys = new LinkedList<Category>();

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(GET_ALL_CATEGORY);

				while (rs.next()) {
					categorys.add(new Category(rs.getInt("id"), rs.getString("name")));
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
		return categorys;
	}
	
	public void add(Category category) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(String.format(ADD_CATEGORY, category.getName(), category.getName()));
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
				st.execute(String.format(DELETE_ID_PRODUCTS, id));
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
	
}
