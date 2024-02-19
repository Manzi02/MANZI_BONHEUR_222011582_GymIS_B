package otherforms;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Payment extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textAmount;
	private JTextField textPaymentDate;
	private JTextField textPaymentID;
	private JTextField textMemberID;
	private JTextField textTransactionID;
	
	  // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/gym_information_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment frame = new Payment();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Payment() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 632, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPayment = new JLabel("Payment");
		lblPayment.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblPayment.setBounds(195, 11, 164, 45);
		contentPane.add(lblPayment);
		
		JLabel lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblPaymentMethod.setBounds(22, 234, 146, 26);
		contentPane.add(lblPaymentMethod);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.setModel(new DefaultComboBoxModel<>(new String[] {"MTN mobile money", "Paypal", "cash", "Airtel money"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(213, 230, 184, 38);
		contentPane.add(comboBox);

		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblAmount.setBounds(22, 279, 103, 23);
		contentPane.add(lblAmount);
		
		textAmount = new JTextField();
		textAmount.setBounds(213, 281, 184, 22);
		contentPane.add(textAmount);
		textAmount.setColumns(10);
		
		JLabel lblPaymentDate = new JLabel("Payment Date");
		lblPaymentDate.setFont(new Font("Times New Roman", Font.ITALIC, 20));
		lblPaymentDate.setBounds(22, 182, 133, 23);
		contentPane.add(lblPaymentDate);
		
		textPaymentDate = new JTextField();
		textPaymentDate.setBounds(213, 184, 197, 22);
		contentPane.add(textPaymentDate);
		textPaymentDate.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnAdd.setBounds(22, 401, 96, 26);
		contentPane.add(btnAdd);
		
		JButton btnview = new JButton("view");
		btnview.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnview.setBounds(151, 402, 89, 24);
		contentPane.add(btnview);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnUpdate.setBounds(282, 403, 103, 23);
		contentPane.add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 22));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnDelete.setBounds(433, 402, 96, 24);
		contentPane.add(btnDelete);
		
		JLabel lblPaymentID = new JLabel("Payment ID");
		lblPaymentID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblPaymentID.setBounds(18, 115, 150, 26);
		contentPane.add(lblPaymentID);
		
		JLabel lblMemberID = new JLabel("Member ID");
		lblMemberID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblMemberID.setBounds(22, 151, 151, 23);
		contentPane.add(lblMemberID);
		
		textPaymentID = new JTextField();
		textPaymentID.setBounds(213, 119, 164, 23);
		contentPane.add(textPaymentID);
		textPaymentID.setColumns(10);
		
		textMemberID = new JTextField();
		textMemberID.setBounds(213, 153, 164, 20);
		contentPane.add(textMemberID);
		textMemberID.setColumns(10);
		
		JLabel lblTransactionID = new JLabel("Transaction ID");
		lblTransactionID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
		lblTransactionID.setBounds(22, 329, 139, 20);
		contentPane.add(lblTransactionID);
		
		textTransactionID = new JTextField();
		textTransactionID.setBounds(213, 331, 188, 20);
		contentPane.add(textTransactionID);
		textTransactionID.setColumns(10);
		
		btnAdd.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        addPayment();
		    }
		});

		btnUpdate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        updatePayment();
		    }
		});

		btnDelete.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        deletePayment();
		    }
		});

		btnview.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        viewPayment();
		    }
		});
	}
	private void addPayment() {
	    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
	        String sql = "INSERT INTO payment (MemberID, Date, Amount, PaymentMethod, TransactionID) VALUES (?, ?, ?, ?, ?)";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, Integer.parseInt(textMemberID.getText()));
	            statement.setDate(2, java.sql.Date.valueOf(textPaymentDate.getText())); // Assuming textDate contains the date in yyyy-MM-dd format
	            statement.setBigDecimal(3, new BigDecimal(textAmount.getText()));
	            statement.setString(4, textPaymentID.getText());
	            statement.setString(5, textTransactionID.getText());

	            int rowsAffected = statement.executeUpdate();

	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(null, "Payment added successfully");
	            } else {
	                JOptionPane.showMessageDialog(null, "Failed to add payment");
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error adding payment");
	    }
	}

	private void updatePayment() {
	    String paymentIDString = JOptionPane.showInputDialog("Enter PaymentID to update:");
	    if (paymentIDString == null || paymentIDString.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Invalid PaymentID");
	        return;
	    }

	    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
	        String sql = "UPDATE payment SET MemberID=?, Date=?, Amount=?, PaymentMethod=?, TransactionID=? WHERE PaymentID=?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, Integer.parseInt(textMemberID.getText()));
	            statement.setDate(2, java.sql.Date.valueOf(textPaymentDate.getText())); // Assuming textDate contains the date in yyyy-MM-dd format
	            statement.setBigDecimal(3, new BigDecimal(textAmount.getText()));
	            statement.setString(4, textPaymentID.getText());
	            statement.setString(5, textTransactionID.getText());
	            statement.setInt(6, Integer.parseInt(paymentIDString));

	            int rowsAffected = statement.executeUpdate();

	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(null, "Payment updated successfully");
	            } else {
	                JOptionPane.showMessageDialog(null, "Failed to update payment. PaymentID not found.");
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error updating payment");
	    }
	}

	private void deletePayment() {
	    String paymentIDString = JOptionPane.showInputDialog("Enter PaymentID to delete:");
	    if (paymentIDString == null || paymentIDString.trim().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Invalid PaymentID");
	        return;
	    }

	    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
	        String sql = "DELETE FROM payment WHERE PaymentID=?";
	        try (PreparedStatement statement = connection.prepareStatement(sql)) {
	            statement.setInt(1, Integer.parseInt(paymentIDString));

	            int rowsAffected = statement.executeUpdate();

	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(null, "Payment deleted successfully");
	            } else {
	                JOptionPane.showMessageDialog(null, "Failed to delete payment. PaymentID not found.");
	            }
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error deleting payment");
	    }
	}

	private void viewPayment() {
	    try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
	        String sql = "SELECT * FROM payment";
	        try (PreparedStatement statement = connection.prepareStatement(sql);
	             ResultSet resultSet = statement.executeQuery()) {

	            DefaultTableModel model = new DefaultTableModel();
	            model.addColumn("PaymentID");
	            model.addColumn("MemberID");
	            model.addColumn("Date");
	            model.addColumn("Amount");
	            model.addColumn("PaymentMethod");
	            model.addColumn("TransactionID");

	            while (resultSet.next()) {
	                int paymentID = resultSet.getInt("PaymentID");
	                int memberID = resultSet.getInt("MemberID");
	                Date date = resultSet.getDate("Date");
	                BigDecimal amount = resultSet.getBigDecimal("Amount");
	                String paymentMethod = resultSet.getString("PaymentMethod");
	                String transactionID = resultSet.getString("TransactionID");

	                model.addRow(new Object[]{paymentID, memberID, date, amount, paymentMethod, transactionID});
	            }

	            JTable dataTable = new JTable();
	            JScrollPane scrollPane = new JScrollPane(dataTable);
	            scrollPane.setBounds(500, 70, 740, 250);
	            contentPane.add(scrollPane);

	            dataTable.setModel(model);
	        }
	    } catch (SQLException ex) {
	        ex.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Error viewing payment records");
	    }
	}
}