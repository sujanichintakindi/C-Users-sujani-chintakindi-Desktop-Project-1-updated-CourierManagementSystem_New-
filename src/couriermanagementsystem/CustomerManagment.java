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

public class CustomerManagment {
	public CustomerManagment(){
		JFrame frame = new JFrame();
		frame.setTitle("Client Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Manage Clients");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel listLabel = new JLabel("Clients List");
		String[] customers = Main.customerManager.getCustomers();
		JComboBox<String> cb=new JComboBox<String>(customers);
		cb.setMinimumSize(new Dimension(200, 20));
		JButton editCustomer = new JButton("Edit Client");
		editCustomer.setMinimumSize(new Dimension(200, 30));
		editCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = customers[cb.getSelectedIndex()];
				frame.dispose();
				new EditCustomer(name);
			}
		});
		JButton deleteCustomer = new JButton("Delete Client");
		deleteCustomer.setMinimumSize(new Dimension(200, 30));
		deleteCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = customers[cb.getSelectedIndex()];
				if(Main.customerManager.deleteCustomer(name)){
					Main.raiseAlert("Client Deleted Successfully");
					frame.dispose();
			  		new UserManagement();
				}
				else{
					Main.raiseAlert("Error Deleting Client");
				}
			}
		});
		JButton addCustomer = new JButton("Add Client");
		addCustomer.setMinimumSize(new Dimension(200, 30));
		addCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
		  		new AddCustomer();
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
				.addComponent(editCustomer)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(deleteCustomer)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(addCustomer)
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
				.addComponent(editCustomer)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(deleteCustomer)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(addCustomer)
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
