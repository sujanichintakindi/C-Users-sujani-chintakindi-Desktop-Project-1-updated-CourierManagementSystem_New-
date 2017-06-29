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

public class CourierManagment {
	public CourierManagment(){
		JFrame frame = new JFrame();
		frame.setTitle("Courier Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Manage Couriers");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel listLabel = new JLabel("Couriers List");
		String[] couriers = Main.courierManager.getAllCouriers();
		JComboBox<String> cb=new JComboBox<String>(couriers);
		cb.setMinimumSize(new Dimension(200, 20));
		JButton editUser = new JButton("Edit Courier");
		editUser.setMinimumSize(new Dimension(200, 30));
		editUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = cb.getSelectedIndex();
				frame.dispose();
				new EditCourier(index);
			}
		});
		JButton deleteUser = new JButton("Delete Courier");
		deleteUser.setMinimumSize(new Dimension(200, 30));
		deleteUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = cb.getSelectedIndex();
				if(Main.courierManager.deleteCourier(index)){
					Main.raiseAlert("Courier Deleted Successfully");
					frame.dispose();
			  		new CourierManagment();
				}
				else{
					Main.raiseAlert("Error Deleting Courier");
				}
			}
		});
		JButton addUser = new JButton("Add Courier");
		addUser.setMinimumSize(new Dimension(200, 30));
		addUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
		  		new AddCourier(); 
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
