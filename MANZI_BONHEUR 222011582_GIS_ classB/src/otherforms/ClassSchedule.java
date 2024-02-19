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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class ClassSchedule extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textClassType;
    private JTextField textTime;
    private JTextField textDuration;
    private JTextField textMemberID;
    private JTextField textTrainerID;
    private JTextField textClassScheduleDate;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_information_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ClassSchedule frame = new ClassSchedule();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ClassSchedule() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 613, 510);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblClassSchedule = new JLabel("Class Schedule");
        lblClassSchedule.setFont(new Font("Times New Roman", Font.BOLD, 28));
        lblClassSchedule.setBounds(205, 30, 185, 39);
        contentPane.add(lblClassSchedule);
        
        JLabel lblClassType = new JLabel("Class Type");
        lblClassType.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblClassType.setBounds(25, 237, 133, 23);
        contentPane.add(lblClassType);
        
        textClassType = new JTextField();
        textClassType.setBounds(214, 239, 146, 22);
        contentPane.add(textClassType);
        textClassType.setColumns(10);
        
        JLabel lblTime = new JLabel("Time");
        lblTime.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblTime.setBounds(25, 212, 91, 14);
        contentPane.add(lblTime);
        
        textTime = new JTextField();
        textTime.setBounds(202, 208, 158, 20);
        contentPane.add(textTime);
        textTime.setColumns(10);
        
        JLabel lblDuration = new JLabel("Duration");
        lblDuration.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblDuration.setBounds(25, 271, 80, 23);
        contentPane.add(lblDuration);
        
        textDuration = new JTextField();
        textDuration.setBounds(202, 275, 158, 17);
        contentPane.add(textDuration);
        textDuration.setColumns(10);
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setFont(new Font("Times New Roman", Font.BOLD, 22));
        btnAdd.setBounds(37, 339, 89, 23);
        contentPane.add(btnAdd);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addClass();
            }
        });
        
        JButton btnView = new JButton("View");
        btnView.setFont(new Font("Times New Roman", Font.BOLD, 22));
        btnView.setBounds(159, 342, 89, 23);
        contentPane.add(btnView);
        btnView.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                viewClass();
            }
        });
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 21));
        btnUpdate.setBounds(259, 342, 101, 23);
        contentPane.add(btnUpdate);
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateClass();
            }
        });
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 21));
        btnDelete.setBounds(370, 342, 97, 23);
        contentPane.add(btnDelete);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteClass();
            }
        });
        
        JLabel lblClassID = new JLabel("Class ID");
        lblClassID.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblClassID.setBounds(24, 96, 158, 23);
        contentPane.add(lblClassID);
        
        textMemberID = new JTextField();
        textMemberID.setBounds(212, 99, 151, 23);
        contentPane.add(textMemberID);
        textMemberID.setColumns(10);
        
        textTrainerID = new JTextField();
        textTrainerID.setBounds(214, 133, 146, 21);
        contentPane.add(textTrainerID);
        textTrainerID.setColumns(10);
        
        JLabel lblScheduleDatel = new JLabel("Schedule Date");
        lblScheduleDatel.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblScheduleDatel.setBounds(25, 177, 119, 14);
        contentPane.add(lblScheduleDatel);
        
        textClassScheduleDate = new JTextField();
        textClassScheduleDate.setBounds(204, 165, 147, 30);
        contentPane.add(textClassScheduleDate);
        textClassScheduleDate.setColumns(10);
        
        JLabel lblTrainerID = new JLabel("Trainer ID");
        lblTrainerID.setFont(new Font("Times New Roman", Font.ITALIC, 19));
        lblTrainerID.setBounds(25, 136, 101, 18);
        contentPane.add(lblTrainerID);
    }

    private void addClass() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "INSERT INTO classschedule (TrainerID, Time, ClassType, Duration) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {              
                statement.setInt(1, Integer.parseInt(textTrainerID.getText()));
                statement.setString(2, textTime.getText());
                statement.setString(3, textClassType.getText());
                statement.setInt(4, Integer.parseInt(textDuration.getText()));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Class added successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add class");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error adding class");
        }
    }

    private void viewClass() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM classschedule";
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {

                DefaultTableModel model = new DefaultTableModel();
                model.addColumn("ClassID");
                model.addColumn("TrainerID");
                model.addColumn("Time");
                model.addColumn("ClassType");
                model.addColumn("Duration");

                while (resultSet.next()) {
                    int classID = resultSet.getInt("ClassID");
                    int trainerID = resultSet.getInt("TrainerID");
                    String time = resultSet.getString("Time");
                    String classType = resultSet.getString("ClassType");
                    int duration = resultSet.getInt("Duration");

                    model.addRow(new Object[]{classID, trainerID,  time, classType, duration});
                }

                JTable dataTable = new JTable();
                JScrollPane scrollPane = new JScrollPane(dataTable);
                scrollPane.setBounds(500, 70, 740, 250);
                contentPane.add(scrollPane);

                dataTable.setModel(model);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error viewing class schedule");
        }
    }

    private void updateClass() {
        String classIDString = JOptionPane.showInputDialog("Enter ClassID to update:");
        if (classIDString == null || classIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid ClassID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "UPDATE classschedule SET TrainerID=?, Time=?, ClassType=?, Duration=? WHERE ClassID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(textTrainerID.getText()));
                statement.setString(2, textTime.getText());
                statement.setString(3, textClassType.getText());
                statement.setInt(4, Integer.parseInt(textDuration.getText()));
                statement.setInt(5, Integer.parseInt(classIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Class updated successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to update class. ClassID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error updating class");
        }
    }

    private void deleteClass() {
        String classIDString = JOptionPane.showInputDialog("Enter ClassID to delete:");
        if (classIDString == null || classIDString.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Invalid ClassID");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "DELETE FROM classschedule WHERE ClassID=?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, Integer.parseInt(classIDString));

                int rowsAffected = statement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Class deleted successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete class. ClassID not found.");
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error deleting class");
        }
    }
}
