package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCourier {
	public AddCourier(){
		JFrame frame = new JFrame();
		frame.setTitle("Courier Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Add Courier");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel nameLabel = new JLabel("Name");
		JTextField nameField = new JTextField(20);
		nameField.setMaximumSize(nameField.getPreferredSize());
		JLabel contactLabel = new JLabel("Contact Info");
		JTextField contactField = new JTextField(20);
		contactField.addKeyListener(new KeyAdapter() {
		    public void keyTyped(KeyEvent e) { 
		        if (contactField.getText().length() >= 10 )
		            e.consume(); 
		    }  
		});
		contactField.setMaximumSize(contactField.getPreferredSize());
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
		  		if(name.length() == 0){
		  			Main.raiseAlert("Name Cannot Be Empty");
		  			return;
		  		}
		  		String contact = contactField.getText();
		  		if(contact.length() == 0 || contact.length() != 10 || !Main.isInteger(contact)){
		  			Main.raiseAlert("Invalid Contact Details");
		  			return;
		  		}
		  		if(Main.courierManager.addCourier(contact, name)){
		  			Main.raiseAlert("Courier Added Successfully");
		  			frame.dispose();
		  			new CourierManagment();
		  		}
		  		else{
		  			Main.raiseAlert("Username already exists");
		  		}
			}
		});
		ok.setMinimumSize(new Dimension(250, 20));
		//adding content to group
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(gl.createSequentialGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(nameLabel)
				.addGap(20)
				.addComponent(nameField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(contactLabel)
				.addGap(20)
				.addComponent(contactField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(ok)
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
				.addComponent(nameLabel)
				.addComponent(nameField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(contactLabel)
				.addComponent(contactField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(ok)
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
