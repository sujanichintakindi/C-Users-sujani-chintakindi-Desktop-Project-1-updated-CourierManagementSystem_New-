package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public final class Main {
	public static String loginUsername;
	public static UserManager userManager;
	public static CourierManager courierManager;
	public static CustomerManager customerManager;
	public static NetworkManager networkManager;
	public static TicketManager ticketManager;
	public static BaseParameters baseParams;
	public static boolean isInteger(String s) {
	    return isInteger(s,10);
	}
	public static boolean isInteger(String s, int radix) {
	    if(s.isEmpty()) return false;
	    for(int i = 0; i < s.length(); i++) {
	        if(i == 0 && s.charAt(i) == '-') {
	            if(s.length() == 1) return false;
	            else continue;
	        }
	        if(Character.digit(s.charAt(i),radix) < 0) return false;
	    }
	    return true;
	}
	public static void main(String[] args){
		baseParams = new BaseParameters();
		userManager = new UserManager();
		courierManager = new CourierManager();
		customerManager = new CustomerManager();
		networkManager = new NetworkManager();
		ticketManager = new TicketManager();
		JFrame frame = new JFrame();
		frame.setTitle("Courier Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		//defining components of page
		JLabel pageHeader = new JLabel("Login");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel usernameLabel = new JLabel("Username");
		JTextField usernameField = new JTextField(20);
		usernameField.setMaximumSize(usernameField.getPreferredSize());
		JPasswordField passwordField = new JPasswordField(20);
		passwordField.setMaximumSize(passwordField.getPreferredSize());
		JLabel passwordLabel = new JLabel("Password");
		JButton submit = new JButton("Submit");
		submit.setMinimumSize(new Dimension(250, 20));
		submit.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
		  		String username = usernameField.getText();
		  		if(username.length() == 0){
		  			raiseAlert("Username Cannot Be Empty");
		  			return;
		  		}
		  		String password = new String(passwordField.getPassword());
		  		if(password.length() == 0){
		  			raiseAlert("Password Cannot Be Empty");
		  			return;
		  		}
		  		if(!userManager.login(username, password)){
		  			raiseAlert("Invalid Login Credentials");
		  			return;
		  		}
		  		else{
		  			frame.dispose();
		  			new DashBoard();
		  		}
			}
		});
		//adding content to group
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(gl.createSequentialGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(usernameLabel)
				.addGap(20)
				.addComponent(usernameField)
				.addGap(75)
				
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(passwordLabel)
				.addGap(20)
				.addComponent(passwordField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(150)
				.addComponent(submit)
				.addGap(75)
			)
		);
		gl.setVerticalGroup(gl.createSequentialGroup()
			.addGroup(gl.createParallelGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(usernameLabel)
				.addComponent(usernameField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(passwordLabel)
				.addComponent(passwordField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(submit)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(100)
			)
		);
		//frame packing and visibility set
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public static void raiseAlert(String s){
		JOptionPane.showMessageDialog(null, s);
    }
}