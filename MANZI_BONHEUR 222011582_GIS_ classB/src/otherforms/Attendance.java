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
import java.sql.Time;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Attendance extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JLabel lblAttendanceID;
    private JTextField textField_3;
    private JLabel lblCheckInTime;
    private JTextField textField_4;
    private JLabel lblCheckOutTime;
    private JTextField textField_5;
    private JButton btnAdd;
    private JButton btnUpdate;
    private JButton btnView;
    private JButton btnDelete;

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
                    Attendance frame = new Attendance();
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
    public Attendance() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 652, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblAttendance = new JLabel("Attendance ");
        lblAttendance.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lblAttendance.setBounds(185, 26, 163, 27);
        contentPane.add(lblAttendance);
        
        JLabel lblMemberID = new JLabel("Member ID");
        lblMemberID.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblMemberID.setBounds(46, 144, 116, 27);
        contentPane.add(lblMemberID);
        
        textField = new JTextField();
        textField.setBounds(212, 146, 157, 27);
        contentPane.add(textField);
        textField.setColumns(10);
        
        JLabel lblDate = new JLabel("Date");
        lblDate.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblDate.setBounds(56, 188, 106, 19);
        contentPane.add(lblDate);
        
        textField_1 = new JTextField();
        textField_1.setBounds(212, 186, 157, 27);
        contentPane.add(textField_1);
        textField_1.setColumns(10);
        
        JLabel lblTrainerID = new JLabel("Trainer ID");
        lblTrainerID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblTrainerID.setBounds(46, 300, 94, 27);
        contentPane.add(lblTrainerID);
        
        textField_2 = new JTextField();
        textField_2.setBounds(212, 297, 157, 36);
        contentPane.add(textField_2);
        textField_2.setColumns(10);
        
        lblAttendanceID = new JLabel("Attendance ID");
        lblAttendanceID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblAttendanceID.setBounds(48, 95, 124, 27);
        contentPane.add(lblAttendanceID);
        
        textField_3 = new JTextField();
        textField_3.setBounds(212, 100, 157, 19);
        contentPane.add(textField_3);
        textField_3.setColumns(10);
        
        lblCheckInTime = new JLabel("Check in time");
        lblCheckInTime.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblCheckInTime.setBounds(45, 218, 127, 19);
        contentPane.add(lblCheckInTime);
        
        textField_4 = new JTextField();
        textField_4.setBounds(212, 216, 163, 27);
        contentPane.add(textField_4);
        textField_4.setColumns(10);
        
        lblCheckOutTime = new JLabel("Check out time");
        lblCheckOutTime.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblCheckOutTime.setBounds(55, 249, 134, 27);
        contentPane.add(lblCheckOutTime);
        
        textField_5 = new JTextField();
        textField_5.setBounds(212, 254, 163, 20);
        contentPane.add(textField_5);
        textField_5.setColumns(10);
        
        btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 19));
        btnAdd.setBounds(56, 397, 84, 36);
        contentPane.add(btnAdd);
        
        btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        btnUpdate.setBounds(241, 402, 107, 27);
        contentPane.add(btnUpdate);
        
        btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        btnView.setBounds(138, 406, 94, 27);
        contentPane.add(btnView);
        
        btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
        btnDelete.setBounds(368, 406, 148, 27);
        contentPane.add(btnDelete);
        
        // Action listeners for buttons
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addAttendance();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateAttendance();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteAttendance();
            }
        });

        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewAttendance();
            }
        });
    }

    // Method to add attendance record
    private void addAttendance() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO Attendance (MemberID, Date, CheckInTime, CheckOutTime, TrainerID) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textField.getText()));
                statement.setString(2, textField_1.getText());
                statement.setString(3, textField_4.getText());
                statement.setString(4, textField_5.getText());
                statement.setInt(5, Integer.parseInt(textField_2.getText()));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Attendance added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add attendance");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding attendance");
        }
    }

 // Method to view attendance records
    private void viewAttendance() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM Attendance";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("AttendanceID");
                model.addColumn("MemberID");
                model.addColumn("Date");
                model.addColumn("CheckInTime");
                model.addColumn("CheckOutTime");
                model.addColumn("TrainerID");

                while (resultSet.next()) {
                    int attendanceID = resultSet.getInt("AttendanceID");
                    int memberID = resultSet.getInt("MemberID");
                    String date = resultSet.getString("Date");
                    Time checkInTime = resultSet.getTime("CheckInTime");
                    Time checkOutTime = resultSet.getTime("CheckOutTime");
                    int trainerID = resultSet.getInt("TrainerID");

                    // Add data to the table model
                    model.addRow(new Object[]{attendanceID, memberID, date, checkInTime, checkOutTime, trainerID});
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(555, 70, 600, 150);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing attendance");
        }
    }

    // Method to update attendance record
    private void updateAttendance() {
        String attendanceIDString = JOptionPane.showInputDialog("Enter AttendanceID to update:");
        if (attendanceIDString == null || attendanceIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid AttendanceID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE Attendance SET MemberID=?, Date=?, CheckInTime=?, CheckOutTime=?, TrainerID=? WHERE AttendanceID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textField.getText()));
                statement.setString(2, textField_1.getText());
                statement.setString(3, textField_4.getText());
                statement.setString(4, textField_5.getText());
                statement.setInt(5, Integer.parseInt(textField_2.getText()));
                statement.setInt(6, Integer.parseInt(attendanceIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Attendance updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update attendance. AttendanceID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating attendance");
        }
    }

    // Method to delete attendance record
    private void deleteAttendance() {
        String attendanceIDString = JOptionPane.showInputDialog("Enter AttendanceID to delete:");
        if (attendanceIDString == null || attendanceIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid AttendanceID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM Attendance WHERE AttendanceID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(attendanceIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Attendance deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete attendance. AttendanceID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting attendance");
        }
    }
}