import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;

public class Mainpage extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JLabel lblBalance;
	private JLabel lblNewLabel;
	private JLabel lblEnterYourAccount;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainpage frame = new Mainpage();
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
	
	
	
	
	
	
	
	public Mainpage() {
		setTitle("OMG Bank ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlShadow);
		contentPane.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Login Lg = new Login();
		String User=Lg.lbl_username.getText();
		
		
		System.out.println("This is the session "+User);
		Connection conn;
		String balance = null;
		try {
			 conn = (Connection) DriverManager
					.getConnection("jdbc:mysql://192.168.16.171:3306/bank","monty", "monty1234");

			
			Statement stmt = (Statement) conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM account  WHERE account_number = "+ User);
		
			
			while (rs.next()) {
				
				
				  balance= rs.getString("balance");
				  lblNewLabel.setText(balance);
					System.out.println("This is the Balance "+balance);
				}
				
		}
		catch(SQLException e) {
			
		}
	
		
		
		
		
		textField = new JTextField();
		textField.setToolTipText("Enter your account number");
		textField.setBounds(10, 53, 204, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setText(User);
		
		textField_1 = new JTextField();
		textField_1.setToolTipText("Enter Value");
		textField_1.setBounds(10, 84, 204, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setToolTipText("Enter the account you want to send to");
		textField_2.setBounds(10, 130, 204, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String AN =textField.getText();
				String MTS=textField_1.getText();
				String SAN=textField_2.getText();
				 String balance2= null;
				
				String balance = null;
				try {
					Connection  conn = (Connection) DriverManager
								.getConnection("jdbc:mysql://192.168.16.171:3306/bank","monty", "monty1234");

					
					Statement stmt = (Statement) conn.createStatement();
					ResultSet rs = stmt.executeQuery("SELECT * FROM account  WHERE account_number = "+AN);
					
					
					
					while (rs.next()) {
						  balance= rs.getString("balance");
						  lblNewLabel.setText(balance);
					
						}
					
				    Date date = new Date();
					
					
					String insert= "INSERT INTO transaction_log (account_number, t_time_date, receiver_number,amount_sent)" +
					        "VALUES (?, ?, ?,?)";
					
					
					PreparedStatement preparedStatement = (PreparedStatement) conn.prepareStatement(insert);
					preparedStatement.setString(1,AN );
					preparedStatement.setString(2,date.toString());
					preparedStatement.setString(3, SAN);
					preparedStatement.setString(4, MTS);
					
					preparedStatement.executeUpdate(); 
					
					
				
				int mts =Integer.parseInt(MTS); 		int bal =Integer.parseInt(balance);
			
				
			int rb=bal - mts ;
				
	String RB = String.valueOf(rb);
	

	Statement stmt2 = (Statement) conn.createStatement();
	rs = stmt2.executeQuery("SELECT * FROM account  WHERE account_number = "+SAN);
	
	
	while (rs.next()) {
		 balance2 = rs.getString("balance");
		}
	
	int bal2 =Integer.parseInt(balance2);
	
	int mtbs=mts+bal2;
	
	String MBS= String.valueOf(mtbs);
	
	
	
					
	

    // create the java mysql update preparedstatement
    String query = "update account set balance = ? where account_number = ?";
    PreparedStatement preparedStmt = (PreparedStatement) conn.prepareStatement(query);
    preparedStmt.setString  (1, RB);
    preparedStmt.setString(2, AN);

    // execute the java preparedstatement
    preparedStmt.executeUpdate();
    
    
   	
    
    // create the java mysql update preparedstatement
    String sql= "update account set balance = ? where account_number = ?";
   preparedStmt = (PreparedStatement) conn.prepareStatement(sql);
    preparedStmt.setString  (1, MBS);
    preparedStmt.setString(2, SAN);

    // execute the java preparedstatement
    preparedStmt.executeUpdate();
    
    Component frame = null;
	JOptionPane.showMessageDialog(frame, "Successfully sent.");
	lblNewLabel_2.setText("Sent !") ;

					
				} catch (SQLException e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
					return;
				}
				
				
				
				
			}
		});
		btnSend.setBounds(10, 174, 91, 23);
		contentPane.add(btnSend);
		
		lblBalance = new JLabel("Balance:");
		lblBalance.setBounds(10, 0, 60, 14);
		contentPane.add(lblBalance);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(309, 0, 123, 14);
		contentPane.add(lblNewLabel);
		
		lblEnterYourAccount = new JLabel("Enter your Account Number");
		lblEnterYourAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblEnterYourAccount.setBounds(10, 37, 173, 14);
		contentPane.add(lblEnterYourAccount);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(224, 161, 149, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblEnterAmountTo = new JLabel("Enter Amount to be sent");
		lblEnterAmountTo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblEnterAmountTo.setBounds(10, 72, 149, 14);
		contentPane.add(lblEnterAmountTo);
		
		JLabel lblReceiversAccount = new JLabel("Receivers Account");
		lblReceiversAccount.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		lblReceiversAccount.setBounds(10, 115, 149, 14);
		contentPane.add(lblReceiversAccount);
		
		lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 246, 212, 14);
		contentPane.add(lblNewLabel_2);
	}
}
