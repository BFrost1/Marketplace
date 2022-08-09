package ua.com.deviant.swingGUI;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

import ua.com.deviant.database.AccountManager;
import ua.com.deviant.database.CategoryManager;
import ua.com.deviant.database.DBWorker;
import ua.com.deviant.database.FactoryProduct;
import ua.com.deviant.database.ManufacturerManadger;
import ua.com.deviant.database.ProductManager;
import ua.com.deviant.entity.Account;
import ua.com.deviant.entity.Category;
import ua.com.deviant.entity.Manufacturer;
import ua.com.deviant.entity.Product;
import ua.com.deviant.requestsAPI.FactoryStoreAPI;

public class AccountGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JButton jButton4;
	private JButton jButton5;
	private JButton jButton6;
	private JButton jButton7;
	
	private JComboBox<String> jComboBox2;
	
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JLabel jLabel3;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JLabel jLabel6;
	private JLabel jLabel7;
	private JLabel jLabel8;
	private JLabel jLabel9;
	private JLabel jLabel10;
	private JLabel jLabel11;
	private JLabel jLabel12;
	private JLabel jLabel13;
	private JLabel jLabel14;
	
	private JPanel jPanel1;
	private JPanel jPanel2;
	private JPanel jPanel4;
	private JPanel jPanel5;
	
	private JPasswordField jPasswordField1;
	private JScrollPane jScrollPane2;
	private JTable jTable1;
	
	private JTextField jTextField1;
	private JTextField jTextField2;
	private JTextField jTextField3;
	private JTextField jTextField4;

	private DefaultTableModel tableModel;

	private Account account;
	
	private DBWorker dbWorker;
	private ManufacturerManadger manufacturerManadger;
	private AccountManager accountManager;
	private ProductManager productManager;

	public AccountGUI() {
		initComponents();
	}

	private void initComponents() {
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jPanel4 = new JPanel();
		jPanel5 = new JPanel();
		
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();
		jLabel10 = new JLabel();
		jLabel11 = new JLabel();
		jLabel12 = new JLabel();
		jLabel13 = new JLabel();
		jLabel14 = new JLabel();

		jScrollPane2 = new JScrollPane();
		
		jTable1 = new JTable();
		
		jButton1 = new JButton();
		jButton2 = new JButton();
		jButton3 = new JButton();
		jButton4 = new JButton();
		jButton5 = new JButton();
		jButton6 = new JButton();
		jButton7 = new JButton();

		jTextField1 = new JTextField();
		jTextField2 = new JTextField();
		jTextField3 = new JTextField();
		jTextField4 = new JTextField();
		
		jPasswordField1 = new JPasswordField();

		jComboBox2 = new JComboBox<>();

		dbWorker = new DBWorker();

		manufacturerManadger = new ManufacturerManadger(dbWorker);
		accountManager = new AccountManager(dbWorker);
		productManager = new ProductManager(dbWorker);

		
		
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setCursor(new Cursor(java.awt.Cursor.DEFAULT_CURSOR));

		tableModel = new DefaultTableModel(new String[][] {}, new String[] { "ID", "NAME", "CATEGORY", "MANUFACTURERS", "PRICE" }) {
			Class[] types = new Class[] { java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class };
			boolean[] canEdit = new boolean[] { false, true, false, true, true };

			public Class getColumnClass(int columnIndex) {
				return types[columnIndex];
			}

			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit[columnIndex];
			}
		};

		jTable1.setModel(tableModel);
		jScrollPane2.setViewportView(jTable1);
		if (jTable1.getColumnModel().getColumnCount() > 0) {
			jTable1.getColumnModel().getColumn(0).setResizable(false);
			jTable1.getColumnModel().getColumn(1).setResizable(false);
			jTable1.getColumnModel().getColumn(2).setResizable(false);
			jTable1.getColumnModel().getColumn(3).setResizable(false);
			jTable1.getColumnModel().getColumn(4).setResizable(false);
		}

		DefaultTableCellRenderer centerRend = new DefaultTableCellRenderer();
		centerRend.setHorizontalAlignment(JLabel.LEFT);
		jTable1.setDefaultRenderer(Integer.class, centerRend);
		((DefaultTableCellRenderer) jTable1.getTableHeader().getDefaultRenderer())
				.setHorizontalAlignment(JLabel.CENTER);

		PlainDocument doc = (PlainDocument) jTextField1.getDocument();
		doc.setDocumentFilter(new DigitFilter());

		jLabel1.setText("Company");

		jButton1.setText("DELETE");
		jButton1.setMaximumSize(new Dimension(100, 25));
		jButton1.setMinimumSize(new Dimension(100, 25));
		jButton1.setPreferredSize(new Dimension(100, 25));
		jButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("DELETE ALL");
		jButton2.setMaximumSize(new Dimension(100, 25));
		jButton2.setMinimumSize(new Dimension(100, 25));
		jButton2.setPreferredSize(new Dimension(100, 25));
		jButton2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jLabel4.setText("CREATE PRODUCT");

		jLabel13.setFont(new Font("Segoe UI", 0, 20));
		jLabel13.setText("MARKETPLACE");

		jButton4.setText("UPDATE");
		jButton4.setMaximumSize(new Dimension(100, 25));
		jButton4.setMinimumSize(new Dimension(100, 25));
		jButton4.setPreferredSize(new Dimension(100, 25));
		jButton4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton4ActionPerformed(evt);
			}
		});

		jButton5.setText("SYNCHRONIZE API");
		jButton5.setMaximumSize(new Dimension(100, 25));
		jButton5.setMinimumSize(new Dimension(100, 25));
		jButton5.setPreferredSize(new Dimension(100, 25));
		jButton5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		jButton7.setText("Exite");
		jButton7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton7ActionPerformed(evt);
			}
		});
		jButton7.setVisible(false);
		jTextField4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jTextField4ActionPerformed(evt);
			}
		});

		jPasswordField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jPasswordField1ActionPerformed(evt);
			}
		});

		jButton6.setText("Submit");
		jButton6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton6ActionPerformed(evt);
			}
		});

		jLabel3.setText("Password");

		jLabel11.setText("Loggin");
		jLabel14.setForeground(new Color(255, 0, 0));
		jLabel2.setForeground(new Color(255, 0, 0));

		GroupLayout jPanel2Layout = new GroupLayout(jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout.setHorizontalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel2Layout.createSequentialGroup().addContainerGap(12, Short.MAX_VALUE).addGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addComponent(jLabel14, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(jButton6))
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(jPasswordField1,
										GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel2Layout.createSequentialGroup()
								.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18).addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 162,
										GroupLayout.PREFERRED_SIZE)))
						.addContainerGap()));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel2Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField4, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel11, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jPasswordField1, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel3, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jButton6)
								.addComponent(jLabel14, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(16, Short.MAX_VALUE)));

		jTextField2.setMinimumSize(new Dimension(70, 22));
		jTextField2.setPreferredSize(new Dimension(70, 22));
		jTextField2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jTextField2ActionPerformed(evt);
			}
		});

		jComboBox2.setMinimumSize(new Dimension(70, 22));
		jComboBox2.setPreferredSize(new Dimension(70, 25));
		jComboBox2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jComboBox2ActionPerformed(evt);
			}
		});

		jTextField3.setMinimumSize(new Dimension(70, 22));
		jTextField3.setPreferredSize(new Dimension(70, 22));

		jTextField1.setMinimumSize(new Dimension(70, 22));
		jTextField1.setPreferredSize(new Dimension(70, 22));
		jTextField1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jTextField1ActionPerformed(evt);
			}
		});

		jLabel5.setText("Name");

		jLabel7.setText("MANUFACTURERS");

		jLabel6.setText("CATEGORY");

		jLabel8.setText("PRICE");

		jButton3.setText("CTEATE");
		jButton3.setMaximumSize(new Dimension(100, 25));
		jButton3.setMinimumSize(new Dimension(100, 25));
		jButton3.setPreferredSize(new Dimension(100, 25));
		jButton3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		GroupLayout jPanel5Layout = new GroupLayout(jPanel5);
		jPanel5.setLayout(jPanel5Layout);
		jPanel5Layout.setHorizontalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup()
						.addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
								.addGroup(GroupLayout.Alignment.TRAILING,
										jPanel5Layout.createSequentialGroup()
												.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(jLabel4).addGap(115, 115, 115))
								.addGroup(GroupLayout.Alignment.TRAILING,
										jPanel5Layout.createSequentialGroup().addGap(21, 21, 21).addGroup(jPanel5Layout
												.createParallelGroup(GroupLayout.Alignment.LEADING)
												.addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout
														.createSequentialGroup()
														.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE,
																93, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jTextField1,
																GroupLayout.PREFERRED_SIZE, 299,
																GroupLayout.PREFERRED_SIZE))
												.addGroup(jPanel5Layout.createSequentialGroup().addGroup(jPanel5Layout
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE,
																105, GroupLayout.PREFERRED_SIZE)
														.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE,
																60, GroupLayout.PREFERRED_SIZE))
														.addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addGroup(jPanel5Layout
																.createParallelGroup(
																		GroupLayout.Alignment.LEADING)
																.addComponent(jTextField2,
																		GroupLayout.Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 300,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(jTextField3,
																		GroupLayout.Alignment.TRAILING,
																		GroupLayout.PREFERRED_SIZE, 300,
																		GroupLayout.PREFERRED_SIZE)))
												.addGroup(GroupLayout.Alignment.TRAILING, jPanel5Layout
														.createSequentialGroup()
														.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE,
																97, GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(
																LayoutStyle.ComponentPlacement.RELATED,
																GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
														.addComponent(jComboBox2,
																GroupLayout.PREFERRED_SIZE, 300,
																GroupLayout.PREFERRED_SIZE))))
								.addGroup(jPanel5Layout.createSequentialGroup()
										.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 296,
												GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(jButton3, GroupLayout.PREFERRED_SIZE, 150,
												GroupLayout.PREFERRED_SIZE)
										.addGap(0, 0, Short.MAX_VALUE)))
						.addContainerGap()));
		jPanel5Layout.setVerticalGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel5Layout.createSequentialGroup().addGap(9, 9, 9).addComponent(jLabel4)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField2, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 25,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jComboBox2, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel6, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField3, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel7, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
								GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(jTextField1, GroupLayout.PREFERRED_SIZE, 22,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(jLabel8))
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addGroup(jPanel5Layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
								.addGroup(jPanel5Layout.createSequentialGroup()
										.addComponent(jButton3, GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addContainerGap())
								.addComponent(jLabel2, GroupLayout.PREFERRED_SIZE, 37,
										GroupLayout.PREFERRED_SIZE))));

		jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 20));
		jLabel13.setText("MARKETPLACE");

		GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
		jPanel4.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup().addGap(17, 17, 17).addComponent(jLabel13)
						.addContainerGap(18, Short.MAX_VALUE)));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel4Layout.createSequentialGroup()
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(jLabel13,
								GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE)));

		GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(jScrollPane2)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap()
								.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(GroupLayout.Alignment.TRAILING,
												jPanel1Layout.createSequentialGroup()
														.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE,
																70, GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jLabel12, GroupLayout.PREFERRED_SIZE,
																150, GroupLayout.PREFERRED_SIZE)
														.addGap(18, 18, 18)
														.addComponent(jButton7, GroupLayout.PREFERRED_SIZE,
																100, GroupLayout.PREFERRED_SIZE)
														.addGap(13, 13, 13))
										.addGroup(GroupLayout.Alignment.TRAILING,
												jPanel1Layout.createSequentialGroup()
														.addComponent(jPanel4, GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addGap(517, 517, 517))))
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(15, 15, 15)
								.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 76,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton5, GroupLayout.PREFERRED_SIZE, 150,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(jButton4, GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton2, GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(jButton1, GroupLayout.PREFERRED_SIZE, 150,
														GroupLayout.PREFERRED_SIZE))
										.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 230,
												GroupLayout.PREFERRED_SIZE))))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(jPanel1Layout.createSequentialGroup().addGroup(jPanel1Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addGap(31, 31, 31)
								.addGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
										.addComponent(jLabel1, GroupLayout.DEFAULT_SIZE,
												GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(jLabel12, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(jButton7, GroupLayout.PREFERRED_SIZE, 22,
												GroupLayout.PREFERRED_SIZE))
								.addGap(33, 33, 33).addComponent(jPanel4, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(jPanel1Layout.createSequentialGroup()
								.addComponent(jPanel2, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGap(0, 0, Short.MAX_VALUE)))
						.addGap(18, 18, 18)
						.addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addGap(18, 18, 18).addGroup(
								jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addGroup(jPanel1Layout
														.createParallelGroup(GroupLayout.Alignment.BASELINE)
														.addComponent(jButton1, GroupLayout.PREFERRED_SIZE,
																25, GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton2, GroupLayout.PREFERRED_SIZE,
																25, GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton4, GroupLayout.PREFERRED_SIZE,
																25, GroupLayout.PREFERRED_SIZE)
														.addComponent(jButton5, GroupLayout.PREFERRED_SIZE,
																25, GroupLayout.PREFERRED_SIZE))
												.addGap(7, 7, 7)
												.addComponent(jLabel10, GroupLayout.PREFERRED_SIZE, 15,
														GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
														149, Short.MAX_VALUE)
												.addComponent(jLabel9, GroupLayout.PREFERRED_SIZE, 22,
														GroupLayout.PREFERRED_SIZE)
												.addGap(12, 12, 12))
										.addGroup(jPanel1Layout.createSequentialGroup()
												.addComponent(jPanel5, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE)
												.addContainerGap(GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)))));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
				GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(
				jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
				GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}

	private void jTextField1ActionPerformed(ActionEvent evt) {

	}

	private void jComboBox2ActionPerformed(ActionEvent evt) {

	}

	private void jTextField2ActionPerformed(ActionEvent evt) {

	}

	private void jPasswordField1ActionPerformed(ActionEvent evt) {

	}

	private void jTextField4ActionPerformed(ActionEvent evt) {

	}

	private void jButton1ActionPerformed(ActionEvent evt) {
		if (Objects.nonNull(account) && Objects.nonNull(productManager)) {
			int[] indexs = jTable1.getSelectedRows();
			for (int i = 0; i < indexs.length; i++) {
				productManager.remove((Integer) jTable1.getValueAt(indexs[i], 0));
			}
			updateTable(productManager.getAll(account.getStore()));
		}
	}

	private void jButton6ActionPerformed(ActionEvent evt) {
		Account temporaryAccount = accountManager.get(jTextField4.getText());
		if (Objects.nonNull(temporaryAccount)) {
			if (temporaryAccount.getPassword().equals(jPasswordField1.getText())) {
				jPanel2.setVisible(false);
				jButton7.setVisible(true);
				jTextField4.setText("");
				jPasswordField1.setText("");
				jLabel14.setText("");
				account = temporaryAccount;
				DefaultComboBoxModel modelComboBox = (DefaultComboBoxModel) jComboBox2.getModel();
				List<Category> list = new CategoryManager(dbWorker).getAll();
				for (int i = 0; i < list.size(); i++) {
					modelComboBox.addElement(list.get(i).getName());
				}
				jComboBox2.setModel(modelComboBox);
				jLabel12.setText(account.getStore().getName());
				updateTable(productManager.getAll(account.getStore()));
			} else {
				jLabel14.setText("wrong login or password");
			}
		} else {
			jLabel14.setText("wrong login or password");
		}
	}

	private void jButton3ActionPerformed(ActionEvent evt) {
		if (Objects.nonNull(account) && Objects.nonNull(productManager)) {
			ArrayList<String> error = new ArrayList<String>();
			if (jTextField2.getText().equals("")) {
				error.add("Name");
			}
			if (jTextField3.getText().equals("")) {
				error.add("MANUFACTURERS");
			}
			if (jTextField1.getText().equals("") || jTextField1.getText().length() > 9) {
				error.add("PRICE");
			}

			if (error.size() == 0) {
				Product product = FactoryProduct.creatProduct(0, jTextField2.getText(), account.getStore().getName(),
						jComboBox2.getSelectedItem().toString(), jTextField3.getText(),
						Integer.parseInt(jTextField1.getText()));
				manufacturerManadger.add(product.getManufacturers());
				productManager.add(product);

				jTextField2.setText("");
				jTextField3.setText("");
				jTextField1.setText("0");
				jLabel2.setText("");
				
				updateTable(productManager.getAll(account.getStore()));
			} else {
				jLabel2.setText("Not valid fields " + error.toString());

			}
		}
	};

	private void jButton5ActionPerformed(ActionEvent evt) {
		if (Objects.nonNull(account) && Objects.nonNull(productManager)) {
			productManager.synchronizeBD(FactoryStoreAPI.creatConnetcStore(account.getStore().getName()));
			updateTable(productManager.getAll(account.getStore()));
		}
	}

	private void jButton4ActionPerformed(ActionEvent evt) {
		if (Objects.nonNull(account) && Objects.nonNull(productManager)) {
			int[] indexs = jTable1.getSelectedRows();
			List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
			List<Product> products = new ArrayList<Product>();
			for (int i = 0; i < indexs.length; i++) {
				if (Objects.nonNull(jTable1.getValueAt(indexs[i], 1))
						&& Objects.nonNull(jTable1.getValueAt(indexs[i], 2))
						&& Objects.nonNull(jTable1.getValueAt(indexs[i], 4))) {
					manufacturers.add(new Manufacturer(jTable1.getValueAt(indexs[i], 3).toString()));
					products.add(FactoryProduct.creatProduct((int) jTable1.getValueAt(indexs[i], 0),
							jTable1.getValueAt(indexs[i], 1).toString(), account.getStore().getName(),
							jTable1.getValueAt(indexs[i], 2).toString(), jTable1.getValueAt(indexs[i], 3).toString(),
							(int) jTable1.getValueAt(indexs[i], 4)));

				}

			}
			manufacturerManadger.add(manufacturers);
			productManager.update(products);
			updateTable(productManager.getAll(account.getStore()));
		}
	}

	private void jButton2ActionPerformed(ActionEvent evt) {
		if (Objects.nonNull(account) && Objects.nonNull(productManager)) {
			productManager.remove(account.getStore());
			updateTable(productManager.getAll(account.getStore()));

		}
	}

	private void jButton7ActionPerformed(ActionEvent evt) {
		account = null;
		jLabel2.setText("");
		jTextField2.setText("");
		jTextField3.setText("");
		jTextField1.setText("0");
		jLabel12.setText("");
		jComboBox2.removeAllItems();
		tableModel.getDataVector().removeAllElements();
		tableModel.fireTableDataChanged();
		jButton7.setVisible(false);
		jPanel2.setVisible(true);
	}

	private void updateTable(List<Product> product) {
		tableModel.getDataVector().removeAllElements();
		if (product.size() > 0) {
			for (int i = 0; i < product.size(); i++) {
				tableModel.addRow(new Object[] { product.get(i).getId(), product.get(i).getName(),
						product.get(i).getCategory().getName(), product.get(i).getManufacturers().getName(),
						product.get(i).getPrice() });
			}
		}
		tableModel.fireTableDataChanged();
	}
}
