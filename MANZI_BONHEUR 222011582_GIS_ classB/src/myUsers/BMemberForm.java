package myUsers;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

public class BMemberForm extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textMemberID;
    private JTextField textNames;
    private JTextField textAddress;
    private JTextField textPhone;
    private JTextField textEmail;
    private JTextField textDateOfBirth;
    private JComboBox<String> comboBox;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_information_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    BMemberForm frame = new BMemberForm();
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
    public BMemberForm() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 588);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Label and text field for Member ID
        JLabel lblMemberID = new JLabel("Member ID");
        lblMemberID.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblMemberID.setBounds(117, 109, 93, 28);
        contentPane.add(lblMemberID);

        // Label and text field for Names
        JLabel lblNames = new JLabel("Names");
        lblNames.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblNames.setBounds(154, 141, 55, 28);
        contentPane.add(lblNames);

        textNames = new JTextField();
        textNames.setBounds(230, 141, 212, 32);
        contentPane.add(textNames);
        textNames.setColumns(10);

        // Label and text field for Address
        JLabel lblAddress = new JLabel("Address");
        lblAddress.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblAddress.setBounds(147, 184, 68, 28);
        contentPane.add(lblAddress);

        textAddress = new JTextField();
        textAddress.setBounds(230, 184, 212, 32);
        contentPane.add(textAddress);
        textAddress.setColumns(10);

        // Label and text field for Phone
        JLabel lblPhone = new JLabel("Phone ");
        lblPhone.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblPhone.setBounds(154, 227, 55, 28);
        contentPane.add(lblPhone);

        textPhone = new JTextField();
        textPhone.setBounds(230, 227, 212, 32);
        contentPane.add(textPhone);
        textPhone.setColumns(10);

        // Label and text field for Email
        JLabel lblEmail = new JLabel("E-mail");
        lblEmail.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblEmail.setBounds(147, 268, 62, 28);
        contentPane.add(lblEmail);

        textEmail = new JTextField();
        textEmail.setBounds(230, 268, 212, 32);
        contentPane.add(textEmail);
        textEmail.setColumns(10);

        // Label and text field for Date of Birth
        JLabel lblDateofBirth = new JLabel("Date of Birth");
        lblDateofBirth.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblDateofBirth.setBounds(97, 311, 111, 28);
        contentPane.add(lblDateofBirth);

        textDateOfBirth = new JTextField();
        textDateOfBirth.setBounds(230, 311, 212, 32);
        contentPane.add(textDateOfBirth);
        textDateOfBirth.setColumns(10);

        // Label and combo box for Payment method
        JLabel lblPaymentmethod = new JLabel("Payment method");
        lblPaymentmethod.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblPaymentmethod.setBounds(75, 354, 140, 28);
        contentPane.add(lblPaymentmethod);

        comboBox = new JComboBox<>(new String[] {"MTN Mobile money", "Cash", "Paypal", "Airtel money"}); // Initialize class-level comboBox
        comboBox.setSelectedIndex(0);
        comboBox.setEditable(true);
        comboBox.setBounds(230, 354, 140, 32);
        contentPane.add(comboBox);

        // Register and cancel buttons
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 22));
        btnAdd.setBounds(46, 439, 120, 40);
        contentPane.add(btnAdd);

        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 22));
        btnDelete.setBounds(455, 439, 120, 40);
        contentPane.add(btnDelete);
        
        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
        btnView.setBounds(176, 448, 93, 28);
        contentPane.add(btnView);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 28));
        btnUpdate.setBounds(297, 448, 145, 28);
        contentPane.add(btnUpdate);
        
        JLabel lblMember = new JLabel("Member");
        lblMember.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        lblMember.setBounds(220, 11, 201, 43);
        contentPane.add(lblMember);
        
        textField = new JTextField();
        textField.setBounds(228, 115, 214, 20);
        contentPane.add(textField);
        textField.setColumns(10);
        
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMember();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateMember();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteMember();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewMember();
            }
        });
    }

    // Method to add member record
    private void addMember() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Member (MemberID, Name, Address, Phone, Email, DateOfBirth, PaymentInformation) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textField.getText()));
                statement.setString(2, textNames.getText());
                statement.setString(3, textAddress.getText());
                statement.setString(4, textPhone.getText());
                statement.setString(5, textEmail.getText());
                statement.setString(6, textDateOfBirth.getText());
                statement.setString(7, comboBox.getSelectedItem().toString()); // Use 7 instead of 6 for PaymentInformation

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Member added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add member");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding member");
        }
    }

    // Method to view member records
    private void viewMember() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Member";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("MemberID");
                model.addColumn("Name");
                model.addColumn("Address");
                model.addColumn("Phone");
                model.addColumn("Email");
                model.addColumn("DateOfBirth");
                model.addColumn("PaymentInformation");

                while (resultSet.next()) {
                    int memberID = resultSet.getInt("MemberID");
                    String name = resultSet.getString("Name");
                    String address = resultSet.getString("Address");
                    String phone = resultSet.getString("Phone");
                    String email = resultSet.getString("Email");
                    String dateOfBirth = resultSet.getString("DateOfBirth");
                    String paymentInformation = resultSet.getString("PaymentInformation");

                    // Add data to the table model
                    model.addRow(new Object[]{memberID, name, address, phone, email, dateOfBirth, paymentInformation});
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(555, 70, 600, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing member");
        }
    }

    // Method to update member record
    private void updateMember() {
        String memberIDString = JOptionPane.showInputDialog("Enter MemberID to update:");
        if (memberIDString == null || memberIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid MemberID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Member SET Name=?, Address=?, Phone=?, Email=?, DateOfBirth=?, PaymentInformation=? WHERE MemberID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, textNames.getText());
                statement.setString(2, textAddress.getText());
                statement.setString(3, textPhone.getText());
                statement.setString(4, textEmail.getText());
                statement.setString(5, textDateOfBirth.getText());
                statement.setString(6, comboBox.getSelectedItem().toString());
                statement.setInt(7, Integer.parseInt(memberIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Member updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update member. MemberID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating member");
        }
    }

    // Method to delete member record
    private void deleteMember() {
        String memberIDString = JOptionPane.showInputDialog("Enter MemberID to delete:");
        if (memberIDString == null || memberIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid MemberID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Member WHERE MemberID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(memberIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Member deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete member. MemberID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting member");
        }
    }
}
