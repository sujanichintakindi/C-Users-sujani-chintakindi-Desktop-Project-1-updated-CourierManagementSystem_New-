package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class UserManagement {
	public UserManagement(){
		JFrame frame = new JFrame();
		frame.setTitle("Courier Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Manage Users");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel listLabel = new JLabel("Users List");
		String[] users = Main.userManager.getUsers();
		JComboBox<String> cb=new JComboBox<String>(users);
		cb.setMinimumSize(new Dimension(200, 20));
		JButton editUser = new JButton("Edit User");
		editUser.setMinimumSize(new Dimension(200, 30));
		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = users[cb.getSelectedIndex()];
				frame.dispose();
				new EditUser(username);
			}
		});
		JButton deleteUser = new JButton("Delete User");
		deleteUser.setMinimumSize(new Dimension(200, 30));
		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = users[cb.getSelectedIndex()];
				if(Main.userManager.deleteUser(username)){
					Main.raiseAlert("User Deleted Successfully");
					frame.dispose();
			  		new UserManagement();
				}
				else{
					Main.raiseAlert("Error Deleting User.");
				}
			}
		});
		JButton addUser = new JButton("Add User");
		addUser.setMinimumSize(new Dimension(200, 30));
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
		  		new AddUser(); 
			}
		});
		//adding content to group
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(gl.createSequentialGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(listLabel)
				.addGap(20)
				.addComponent(cb)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(editUser)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(deleteUser)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(addUser)
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
				.addComponent(listLabel)
				.addComponent(cb)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(editUser)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(deleteUser)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(addUser)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(100)
			)
		);
		//frame packing and visibility set
		frame.pack();
		frame.setVisible(true);
	}
}
