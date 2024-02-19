package myUsers;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class ATrainer extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_2;
    private JTextField textField_1;
    private JComboBox<String> comboBox; // Declare comboBox as an instance variable

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_information_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ATrainer frame = new ATrainer();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public ATrainer() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 592, 479);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTrainer = new JLabel("Trainer");
        lblTrainer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
        lblTrainer.setBounds(213, 39, 130, 19);
        contentPane.add(lblTrainer);

        JLabel lblNames = new JLabel("Names");
        lblNames.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblNames.setBounds(155, 174, 55, 28);
        contentPane.add(lblNames);

        textField = new JTextField();
        textField.setBounds(230, 172, 212, 34);
        contentPane.add(textField);
        textField.setColumns(10);

        JLabel lblSpecialization = new JLabel("Specialization");
        lblSpecialization.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblSpecialization.setBounds(92, 227, 118, 28);
        contentPane.add(lblSpecialization);

        JLabel lblContactinfo = new JLabel("Contact info");
        lblContactinfo.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblContactinfo.setBounds(102, 268, 105, 28);
        contentPane.add(lblContactinfo);

        textField_2 = new JTextField();
        textField_2.setBounds(230, 266, 212, 34);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        comboBox = new JComboBox<>(); // Initialize comboBox
        String[] trainerTypes = {"Weight Loss Trainer", "Personal Trainer", "Rehabilitation Trainer", "Group Fitness Trainer", "Strength trainer", "Senior Fitness"};
        comboBox.setModel(new DefaultComboBoxModel<>(trainerTypes));
        comboBox.setSelectedIndex(0);
        comboBox.setBounds(230, 220, 149, 34);
        contentPane.add(comboBox);

        JLabel lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(32, 75, -13, 19);
        contentPane.add(lblNewLabel);

        JLabel lblTrainerID = new JLabel("Trainer ID");
        lblTrainerID.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        lblTrainerID.setBounds(122, 126, 88, 28);
        contentPane.add(lblTrainerID);

        textField_1 = new JTextField();
        textField_1.setBounds(230, 124, 212, 34);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        btnCancel.setBounds(332, 330, 110, 34);
        contentPane.add(btnCancel);

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (JOptionPane.showConfirmDialog(ATrainer.this, "You want to Cancel ?", "Registration Form",
                        JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnRegister.setBounds(173, 331, 110, 34);
        contentPane.add(btnRegister);

        btnRegister.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Call the method to register the trainer
                registerTrainer();
            }
        });
    }

    // Method to register a trainer in the database
    private void registerTrainer() {
        // Retrieve data from the form fields
        String trainerId = textField_1.getText();
        String name = textField.getText();
        String specialization = comboBox.getSelectedItem().toString(); // Corrected line
        String contactInformation = textField_2.getText();

        // Validate the data (you can add more validation if needed)

        // Insert data into the database
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // SQL statement to insert data into the "trainer" table
            String sql = "INSERT INTO trainer (TrainerID, Name, Specialization, ContactInformation) VALUES (?, ?, ?, ?)";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                // Set values for the parameters in the SQL statement
                preparedStatement.setString(1, trainerId);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, specialization);
                preparedStatement.setString(4, contactInformation);

                // Execute the SQL statement
                int rowsAffected = preparedStatement.executeUpdate();

                // Check if the insertion was successful
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Trainer registered successfully!");

                    // Optionally, you can clear the form fields after successful registration
                    textField_1.setText("");
                    textField.setText("");
                    textField_2.setText("");
                    comboBox.setSelectedIndex(0);
                } else {
                    JOptionPane.showMessageDialog(this, "Failed to register the trainer.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Database error: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}
