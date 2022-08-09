package ua.com.deviant.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ua.com.deviant.entity.Manufacturer;

public class ManufacturerManadger {
	private static final String GET_ALL_MANUFACTURERS = "SELECT manufacturers.id, manufacturers.name FROM manufacturers ";
	private static final String GET_ID_MANUFACTURER = GET_ALL_MANUFACTURERS + "WHERE id = %d";
	private static final String ADD_MANUFACTURER = "INSERT INTO manufacturers (name) SELECT '%s' FROM DUAL WHERE NOT EXISTS(SELECT 1 FROM manufacturers WHERE name = '%s');";
	private static final String DELETE_ID_MANUFACTURER = "DELETE FROM manufacturers WHERE id=%d;";

	private DBWorker worker;

	public ManufacturerManadger(DBWorker worker) {
		this.worker = worker;
	}

	public Manufacturer get(int id) {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		Manufacturer manufacturer = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(String.format(GET_ID_MANUFACTURER, id));

				if (rs.next()) {
					manufacturer = new Manufacturer(rs.getInt("id"), rs.getString("name"));
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
		return manufacturer;
	}

	public List<Manufacturer> getAll() {
		Connection conn = worker.getConnection();
		Statement st = null;
		ResultSet rs = null;
		List<Manufacturer> manufacturers = new LinkedList<Manufacturer>();

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				rs = st.executeQuery(GET_ALL_MANUFACTURERS);

				while (rs.next()) {
					manufacturers.add(new Manufacturer(rs.getInt("id"), rs.getString("name")));
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
		return manufacturers;
	}

	public void add(Manufacturer manufacturer) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				st.execute(String.format(ADD_MANUFACTURER, manufacturer.getName(), manufacturer.getName()));
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
	public void add(List<Manufacturer> manufacturers) {
		Connection conn = worker.getConnection();
		Statement st = null;

		if (Objects.nonNull(conn)) {
			try {
				st = conn.createStatement();
				for (Manufacturer manufacturer1 : manufacturers) {
					st.execute(String.format(ADD_MANUFACTURER, manufacturer1.getName(), manufacturer1.getName()));
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
				st.execute(String.format(DELETE_ID_MANUFACTURER, id));
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
