package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AddCustomer {
	public AddCustomer(){
		JFrame frame = new JFrame();
		frame.setTitle("Client Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Add Client");
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
		JLabel locationLabel = new JLabel("Location");
		String[] locations = Main.networkManager.getLocations(); 
		JComboBox<String> locationField = new JComboBox<String>(locations);
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
		  		String location = locationField.getSelectedItem().toString();
		  		if(Main.customerManager.addCustomer(location, name, contact)){
		  			Main.raiseAlert("Client Added Successfully");
		  			frame.dispose();
		  			new CustomerManagment();
		  		}
		  		else{
		  			Main.raiseAlert("Client Name already exists");
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
				.addComponent(locationLabel)
				.addGap(20)
				.addComponent(locationField)
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
				.addComponent(locationLabel)
				.addComponent(locationField)
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
