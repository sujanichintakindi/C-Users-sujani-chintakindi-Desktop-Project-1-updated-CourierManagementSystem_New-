package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class AddTicket {
	public AddTicket(){
		JFrame frame = new JFrame();
		frame.setTitle("Customer Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Add Ticket");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel customerLabel = new JLabel("Pickup Client");
		String[] customers = Main.customerManager.getCustomers();
		JComboBox<String> customerField = new JComboBox<String>(customers);
		JLabel customerLabel2 = new JLabel("Delivery Client");
		JComboBox<String> customerField2 = new JComboBox<String>(customers);
		JLabel courierLabel = new JLabel("Couriers");
		String[] couriers = Main.courierManager.getCouriers();
		JComboBox<String> courierField = new JComboBox<String>(couriers);
		JLabel dateTimeLabel = new JLabel("Pickup Time");
		JSpinner dateTimeField = new JSpinner( new SpinnerDateModel() );
		dateTimeField.setEditor(new JSpinner.DateEditor(dateTimeField, "dd/MM/yyyy HH:mm:ss"));
		dateTimeField.setValue(new Date());
		JLabel dateTimeLabel2 = new JLabel("Delivery Time");
		JSpinner dateTimeField2 = new JSpinner( new SpinnerDateModel() );
		dateTimeField2.setEditor(new JSpinner.DateEditor(dateTimeField2, "dd/MM/yyyy HH:mm:ss"));
		dateTimeField2.setValue(new Date());
		JRadioButton r1=new JRadioButton("Pay at Pickup");
		r1.setActionCommand("1");
		JRadioButton r2=new JRadioButton("Pay at Delivery");
		r2.setActionCommand("2");
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		JLabel packageIdLabel = new JLabel("Package ID");
		JTextField packageIdField = new JTextField(20);
		JLabel packageNameLabel = new JLabel("Package Name");
		JTextField packageNameField = new JTextField(20);
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pickupCustomer = customerField.getSelectedItem().toString();
				String deliveryCustomer = customerField2.getSelectedItem().toString();
		  		String courier = courierField.getSelectedItem().toString();
		  		Date pickupDate = (Date)dateTimeField.getValue();
		  		Date deliveryDate = (Date)dateTimeField2.getValue();
		  		Date ticketDate = new Date();
		  		if(pickupDate.after(deliveryDate) || pickupDate.equals(deliveryDate) || pickupDate.before(ticketDate)){
		  			Main.raiseAlert("Selected times are invalid");
		  			return;
		  		}
		  		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  		String pickupTime = format.format(pickupDate);
		  		String deliveryTime = format.format(deliveryDate);
		  		String ticketTime = format.format(ticketDate);
		  		if(pickupCustomer.equals(deliveryCustomer)){
		  			Main.raiseAlert("Pickup and Delivery cannot be same client");
		  			return;
		  		}
		  		if(!(r1.isSelected() || r2.isSelected())){
		  			Main.raiseAlert("Select Payment Client");
		  			return;
		  		}
		  		String paymentCustomer = bg.getSelection().getActionCommand().equals("1") ? pickupCustomer : deliveryCustomer;
		  		if(courier.equals("")){
		  			Main.raiseAlert("Please select a Courier");
		  			return;
		  		}
		  		String packageId = packageIdField.getText();
		  		if(packageId.equals("")){
		  			Main.raiseAlert("Package ID cannot be empty");
		  			return;
		  		}
		  		String packageName = packageNameField.getText();
		  		if(packageName.equals("")){
		  			Main.raiseAlert("Package Name cannot be empty");
		  			return;
		  		}
		  		if(Main.ticketManager.addTicket(deliveryCustomer,courier,deliveryTime,ticketTime,pickupCustomer,pickupTime,paymentCustomer,packageId, packageName)){
		  			frame.dispose();
		  			new DashBoard();
		  		}
		  		else{
		  			Main.raiseAlert("Ticket Adding Failed");
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
				.addComponent(customerLabel)
				.addGap(20)
				.addComponent(customerField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(dateTimeLabel)
				.addGap(20)
				.addComponent(dateTimeField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(customerLabel2)
				.addGap(20)
				.addComponent(customerField2)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(dateTimeLabel2)
				.addGap(20)
				.addComponent(dateTimeField2)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(courierLabel)
				.addGap(20)
				.addComponent(courierField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(r1)
				.addGap(20)
				.addComponent(r2)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(packageIdLabel)
				.addGap(20)
				.addComponent(packageIdField)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(packageNameLabel)
				.addGap(20)
				.addComponent(packageNameField)
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
				.addComponent(customerLabel)
				.addComponent(customerField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(dateTimeLabel)
				.addComponent(dateTimeField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(customerLabel2)
				.addComponent(customerField2)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(dateTimeLabel2)
				.addComponent(dateTimeField2)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(courierLabel)
				.addComponent(courierField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(r1)
				.addComponent(r2)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(packageIdLabel)
				.addComponent(packageIdField)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(packageNameLabel)
				.addComponent(packageNameField)
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
