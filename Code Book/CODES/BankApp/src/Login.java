import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	
	JLabel lblNewLabel = new JLabel("");
	/**
	 * Launch the application.
	 */
	
	
	
	Connectdb db = new Connectdb();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	private  String username ;
	public JLabel lbl_username;
	private JPasswordField passwordField;
	
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlLtHighlight);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setToolTipText("Enter your account number");
		textField.setBounds(130, 11, 163, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				username  = textField.getText().toString();
				String password  = passwordField.getText().toString();
				
				
				
				
				
				if (!username.isEmpty()&&!password.isEmpty()) {
				
				Connection conn = null;
				try {
					conn = (Connection) DriverManager
							.getConnection("jdbc:mysql://192.168.16.171:3306/bank","monty", "monty1234");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

						
						Statement stmt = null;
						try {
							stmt = (Statement) conn.createStatement();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						ResultSet rs = null;
						try {
							rs = stmt.executeQuery("SELECT * FROM customer  WHERE Username = "+username+" AND  Password = "+password);
							

							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						try {
							
							while (rs.next()) {
								  String Username= rs.getString("Username");
								  System.out.println(Username+ "\n");
									Mainpage page = new Mainpage();
									page.setVisible(true);
									lbl_username.setText(Username);
								}
							
						
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							lblNewLabel.setText("username and password not Correct or not found ");
							
						}
						
				
				}
				else {
					lblNewLabel.setText("Please put in your username and password ");
					
				}
			}
		});
		btnNewButton.setBounds(166, 78, 91, 23);
		contentPane.add(btnNewButton);

		lblNewLabel.setBounds(111, 87, 158, 14);
		contentPane.add(lblNewLabel);
		
		lbl_username = new JLabel("");
		lbl_username.setBounds(303, 0, 129, 14);
		contentPane.add(lbl_username);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("Enter your password");
		passwordField.setBounds(130, 42, 163, 20);
		contentPane.add(passwordField);
	}
	
	
	public String getUsername() {
	     return username;
	  }
}
