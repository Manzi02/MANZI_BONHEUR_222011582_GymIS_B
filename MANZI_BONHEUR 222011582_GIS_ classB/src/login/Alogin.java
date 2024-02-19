package login;

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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JOptionPane;

public class Alogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField textUserName;
    private JTextField textPassword;

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/gym_information_system";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Alogin frame = new Alogin();
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
    public Alogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 554, 367);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lbllogin = new JLabel("Log in");
        lbllogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 29));
        lbllogin.setBounds(155, 52, 136, 32);
        contentPane.add(lbllogin);

        JLabel lbluserName = new JLabel("UserName");
        lbluserName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lbluserName.setBounds(78, 137, 100, 26);
        contentPane.add(lbluserName);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 18));
        lblPassword.setBounds(97, 183, 81, 20);
        contentPane.add(lblPassword);

        textUserName = new JTextField();
        textUserName.setBounds(188, 143, 147, 23);
        contentPane.add(textUserName);
        textUserName.setColumns(10);

        textPassword = new JTextField();
        textPassword.setBounds(188, 185, 147, 20);
        contentPane.add(textPassword);
        textPassword.setColumns(10);

        JButton btnLogin = new JButton("Log in");
        btnLogin.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
        btnLogin.setBounds(61, 232, 100, 25);
        contentPane.add(btnLogin);

        // Action listener for the "Log in" button
        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = textUserName.getText();
                String password = textPassword.getText();

                // Validate user credentials
                if (validateLogin(userName, password)) {
                    // Successful login
                    JOptionPane.showMessageDialog(null, "Login successful!");
                    // You can add code here to navigate to another frame or perform other actions upon successful login
                } else {
                    // Invalid credentials
                    JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                }
            }
        });

        JButton btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 22));
        btnReset.setBounds(188, 232, 100, 25);
        contentPane.add(btnReset);
        btnReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display confirmation dialog
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to reset?", "Reset Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Clear text fields
                    textUserName.setText("");
                    textPassword.setText("");
                }
            }
        });

        JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 21));
        btnExit.setBounds(312, 232, 100, 25);
        contentPane.add(btnExit);
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Display confirmation dialog
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Exit the application
                    System.exit(0);
                }
            }
        });
    }

    // Method to validate user login
    private boolean validateLogin(String userName, String password) {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            String sql = "SELECT * FROM users WHERE UserName = ? AND password = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userName);
                statement.setString(2, password);
                try (ResultSet resultSet = statement.executeQuery()) {
                    return resultSet.next(); // If a row is returned, credentials are valid
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error validating login");
            return false;
        }
    }
}
