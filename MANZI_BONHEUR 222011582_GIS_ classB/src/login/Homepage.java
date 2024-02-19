package login;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import myUsers.ATrainer; // Assuming ATrainer exists
import myUsers.BMemberForm;
import otherforms.Attendance;
import otherforms.ClassSchedule;
import otherforms.Payment;
import otherforms.Equipment;
public class Homepage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Homepage frame = new Homepage();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Homepage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 611, 539);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblHome = new JLabel("Home ");
        lblHome.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 23));
        lblHome.setBounds(169, 11, 65, 28);
        contentPane.add(lblHome);

        JLabel lblNewLabel = new JLabel("Welcome ");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 23));
        lblNewLabel.setBounds(149, 89, 135, 59);
        contentPane.add(lblNewLabel);

        JButton btnTrainer = new JButton("Trainer");
        btnTrainer.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnTrainer.setBounds(35, 215, 150, 40);
        contentPane.add(btnTrainer);

        JButton btnmember = new JButton("Member");
        btnmember.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnmember.setBounds(256, 215, 143, 40);
        contentPane.add(btnmember);

        JButton btnClassschedule = new JButton("Class schedule");
        btnClassschedule.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnClassschedule.setBounds(33, 266, 165, 40);
        contentPane.add(btnClassschedule);

        JButton btnAttendance = new JButton("Attendance");
        btnAttendance.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnAttendance.setBounds(256, 266, 143, 40);
        contentPane.add(btnAttendance);

        JButton btnPayment = new JButton("Payment");
        btnPayment.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnPayment.setBounds(35, 317, 150, 40);
        contentPane.add(btnPayment);

        JButton btnEquipment = new JButton("Equipment");
        btnEquipment.setFont(new Font("Times New Roman", Font.BOLD, 20));
        btnEquipment.setBounds(256, 317, 143, 40);
        contentPane.add(btnEquipment);

           

        // Logic for Trainer button
        btnTrainer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ATrainer trainerForm = new ATrainer();
                trainerForm.setVisible(true);
                dispose(); // Close the current form
            }
        });
        // Logic for Member button
        btnmember.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BMemberForm memberForm = new BMemberForm();
                memberForm.setVisible(true);
                dispose(); // Close the current form
            }
        });
     // Logic for Class Schedule button
        btnClassschedule.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ClassSchedule classScheduleForm = new ClassSchedule();
                classScheduleForm.setVisible(true);
                dispose(); // Close the current form
            }
        });

        // Logic for Attendance button
        btnAttendance.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Attendance attendanceForm = new Attendance();
                attendanceForm.setVisible(true);
                dispose(); // Close the current form
            }
        });

        // Logic for Payment button
        btnPayment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Payment paymentForm = new Payment();
                paymentForm.setVisible(true);
                dispose(); // Close the current form
            }
        });

        // Logic for Equipment button
        btnEquipment.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Equipment equipmentForm = new Equipment();
                equipmentForm.setVisible(true);
                dispose(); // Close the current form
            }
        });
}
}