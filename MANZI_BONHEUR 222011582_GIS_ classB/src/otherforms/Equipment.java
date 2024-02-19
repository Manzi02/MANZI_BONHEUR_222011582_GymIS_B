package otherforms;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;



public class Equipment extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
    private JTextField textName;
    private JTextField textQuantity;
    private JTextField textPurchasedate;
    private JTextField textEquipmentID;
    private JComboBox<String> comboBox;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_information_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Equipment frame = new Equipment();
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
    public Equipment() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 625, 562);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblEquipmentsl = new JLabel("Equipments");
        lblEquipmentsl.setFont(new Font("Times New Roman", Font.BOLD, 29));
        lblEquipmentsl.setBounds(214, 26, 173, 30);
        contentPane.add(lblEquipmentsl);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblName.setBounds(52, 128, 107, 22);
        contentPane.add(lblName);

        textName = new JTextField();
        textName.setBounds(214, 129, 156, 21);
        contentPane.add(textName);
        textName.setColumns(10);

        JLabel lblType = new JLabel("Type");
        lblType.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblType.setBounds(51, 170, 63, 22);
        contentPane.add(lblType);

        JLabel lblQuantity = new JLabel("Quantity");
        lblQuantity.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblQuantity.setBounds(49, 211, 98, 22);
        contentPane.add(lblQuantity);

        textQuantity = new JTextField();
        textQuantity.setBounds(214, 210, 156, 14);
        contentPane.add(textQuantity);
        textQuantity.setColumns(10);

        JLabel lblPurchasedate = new JLabel("Purchase date");
        lblPurchasedate.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblPurchasedate.setBounds(52, 255, 112, 22);
        contentPane.add(lblPurchasedate);

        textPurchasedate = new JTextField();
        textPurchasedate.setBounds(214, 256, 156, 20);
        contentPane.add(textPurchasedate);
        textPurchasedate.setColumns(10);

        comboBox = new JComboBox<>();
        comboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Strength", "fitness", "Weight Loss", "Rehabilitation" }));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(214, 172, 156, 20);
        contentPane.add(comboBox);

        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 24));
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addEquipment();
            }
        });
        btnAdd.setBounds(10, 353, 98, 22);
        contentPane.add(btnAdd);

        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.BOLD, 24));
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewEquipment();
            }
        });
        btnView.setBounds(141, 353, 89, 22);
        contentPane.add(btnView);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 24));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateEquipment();
            }
        });
        btnUpdate.setBounds(266, 349, 121, 30);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 24));
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteEquipment();
            }
        });
        btnDelete.setBounds(400, 353, 98, 22);
        contentPane.add(btnDelete);

        JLabel lblEquipmentID = new JLabel("Equipment ID");
        lblEquipmentID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblEquipmentID.setBounds(52, 87, 121, 21);
        contentPane.add(lblEquipmentID);

        textEquipmentID = new JTextField();
        textEquipmentID.setBounds(212, 89, 158, 22);
        contentPane.add(textEquipmentID);
        textEquipmentID.setColumns(10);
    }

    private void addEquipment() {
        String name = textName.getText();
        String quantity = textQuantity.getText();
        String purchaseDate = textPurchasedate.getText();

        if (name.isEmpty() || quantity.isEmpty() || purchaseDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO equipment (Name, Type, Quantity, PurchaseDate) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, comboBox.getSelectedItem().toString());
                statement.setInt(3, Integer.parseInt(quantity));
                statement.setDate(4, java.sql.Date.valueOf(purchaseDate));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Equipment added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add equipment");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding equipment");
        }
    }

    private void updateEquipment() {
        String equipmentID = textEquipmentID.getText();
        String name = textName.getText();
        String quantity = textQuantity.getText();
        String purchaseDate = textPurchasedate.getText();

        if (equipmentID.isEmpty() || name.isEmpty() || quantity.isEmpty() || purchaseDate.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required!");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE equipment SET Name=?, Type=?, Quantity=?, PurchaseDate=? WHERE EquipmentID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, name);
                statement.setString(2, comboBox.getSelectedItem().toString());
                statement.setInt(3, Integer.parseInt(quantity));
                statement.setDate(4, java.sql.Date.valueOf(purchaseDate));
                statement.setInt(5, Integer.parseInt(equipmentID));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Equipment updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update equipment. EquipmentID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating equipment");
        }
    }

    private void deleteEquipment() {
        String equipmentID = textEquipmentID.getText();

        if (equipmentID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Equipment ID is required!");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM equipment WHERE EquipmentID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(equipmentID));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Equipment deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete equipment. EquipmentID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting equipment");
        }
    }

    private void viewEquipment() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM equipment";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("EquipmentID");
                model.addColumn("Name");
                model.addColumn("Type");
                model.addColumn("Quantity");
                model.addColumn("PurchaseDate");

                while (resultSet.next()) {
                    int equipmentID = resultSet.getInt("EquipmentID");
                    String name = resultSet.getString("Name");
                    String type = resultSet.getString("Type");
                    int quantity = resultSet.getInt("Quantity");
                    Date purchaseDate = resultSet.getDate("PurchaseDate");

                    model.addRow(new Object[]{equipmentID, name, type, quantity, purchaseDate});
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(500, 70, 740, 250);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing equipment records");
        }
    }

}
