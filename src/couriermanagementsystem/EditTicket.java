package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;

public class EditTicket {
	public EditTicket(String courier){
		JFrame frame = new JFrame();
		HashMap<String, String> entry = Main.ticketManager.getTicket(courier);
		frame.setTitle("Customer Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Edit Ticket");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JLabel customerLabel = new JLabel("Pickup Client");
		JLabel customerLabel2 = new JLabel("Pickup Client");
		String[] customers = Main.customerManager.getCustomers(); 
		JComboBox<String> customerField = new JComboBox<String>(customers);
		JComboBox<String> customerField2 = new JComboBox<String>(customers);
		JLabel courierLabel = new JLabel("Couriers");
		String[] couriers = Main.courierManager.getCouriers();  
		JComboBox<String> courierField = new JComboBox<String>(couriers);
		if(couriers[0].equals("")){
			courierField.removeAllItems();
		}
		courierField.addItem(courier);
		JLabel dateTimeLabel = new JLabel("Pickup Time");
		JSpinner dateTimeField = new JSpinner(new SpinnerDateModel());
		dateTimeField.setEditor(new JSpinner.DateEditor(dateTimeField, "dd/MM/yyyy HH:mm:ss"));
		String time = entry.get("pickupTime");
		JLabel dateTimeLabel2 = new JLabel("Delivery Time");
		JSpinner dateTimeField2 = new JSpinner(new SpinnerDateModel());
		dateTimeField2.setEditor(new JSpinner.DateEditor(dateTimeField2, "dd/MM/yyyy HH:mm:ss"));
		String time2 = entry.get("deliveryTime");
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
		Date date;
		try {
			date = format.parse(time);
			dateTimeField.setValue(date);
			date = format.parse(time2);
			dateTimeField2.setValue(date);
		} catch (ParseException e1) {
			System.out.println(e1);
		}
		JLabel actualDelivery = new JLabel("Actual Delivery Time");
		JSpinner dateTimeField3 = new JSpinner(new SpinnerDateModel());
		dateTimeField3.setEditor(new JSpinner.DateEditor(dateTimeField3, "dd/MM/yyyy HH:mm:ss"));
		JRadioButton r1=new JRadioButton("Pay at Pickup");
		r1.setActionCommand("1");
		JRadioButton r2=new JRadioButton("Pay at Delivery");
		r2.setActionCommand("2");
		ButtonGroup bg=new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		JLabel delivery = new JLabel("Delivery Complete");
		JRadioButton n =new JRadioButton("No");
		n.setActionCommand("1");
		JRadioButton y =new JRadioButton("Yes");
		y.setActionCommand("2");
		ButtonGroup bg2=new ButtonGroup();
		bg2.add(n);
		bg2.add(y);
		JLabel packageIdLabel = new JLabel("Package ID");
		JTextField packageIdField = new JTextField(20);
		JLabel packageNameLabel = new JLabel("Package Name");
		JTextField packageNameField = new JTextField(20);
		JButton getEstimates = new JButton("Get Estimates, Instructions");
		getEstimates.setMinimumSize(new Dimension(250, 20));
		getEstimates.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				JOptionPane.showMessageDialog(null, "Estimated Cost :"+entry.get("estimatedCost")+"\nEstimated Distance :"+entry.get("estimatedDistance")+"\nEstimated Time :"+entry.get("estimatedTime")+"\nPickup Instructions :"+entry.get("instructions")+"\nDelivery Instructions :"+entry.get("deliveryInstructions")+"\nReturn Instructions :"+entry.get("returnInstructions"));
			}
		});
		JButton ok = new JButton("ok");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pickupCustomer = customerField.getSelectedItem().toString();
				String deliveryCustomer = customerField2.getSelectedItem().toString();
				if(pickupCustomer.equals(deliveryCustomer)){
		  			Main.raiseAlert("Pickup and Delivery cannot be same client");
		  			return;
		  		}
		  		String newCourier = courierField.getSelectedItem().toString();
		  		String status = "live";
		  		String bonus = "0";
		  		Date pickupDate = (Date)dateTimeField.getValue();
		  		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		  		String pickupTime = format.format(pickupDate);
		  		Date deliveryDate = (Date)dateTimeField2.getValue();
		  		String deliveryTime = format.format(deliveryDate);
		  		Date ticketDate = null;
				try {
					ticketDate = format.parse(entry.get("ticketTime")); 
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
		  		if(pickupDate.after(deliveryDate) || pickupDate.equals(deliveryDate) || pickupDate.before(ticketDate)){
		  			Main.raiseAlert("Selected times are Invalid");
		  			return;
		  		}
		  		if(newCourier.equals("")){
		  			Main.raiseAlert("Please select a Courier");
		  			return;
		  		}
		  		String actualTime = "null";
		  		if(y.isSelected()){
		  			if(((Date)dateTimeField3.getValue()).before(deliveryDate)){
		  				actualTime = format.format((Date)dateTimeField3.getValue());
		  				bonus = "1";
		  			}
		  			status = "complete";
		  		}
		  		String paymentCustomer = bg.getSelection().getActionCommand().equals("1") ? pickupCustomer : deliveryCustomer;
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
		  		if(Main.ticketManager.editTicket(deliveryCustomer, newCourier, status, bonus, deliveryTime, courier, entry.get("ticketTime"),pickupCustomer,pickupTime,paymentCustomer,actualTime, packageId, packageName)){
		  			frame.dispose();
		  			new DashBoard();
		  		}
		  		else{
		  			Main.raiseAlert("Ticket Update Failed");
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
				.addComponent(actualDelivery)
				.addGap(20)
				.addComponent(dateTimeField3)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(delivery)
				.addGap(20)
				.addComponent(n)
				.addGap(20)
				.addComponent(y)
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
				.addComponent(getEstimates)
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
				.addComponent(actualDelivery)
				.addComponent(dateTimeField3)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(delivery)
				.addComponent(n)
				.addComponent(y)
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
				.addComponent(getEstimates)
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
		n.setSelected(true);
		courierField.setSelectedItem(courier);
		customerField.setSelectedItem(entry.get("pickupCustomer"));
		customerField2.setSelectedItem(entry.get("customer"));
		if(entry.get("paymentCustomer").equals(entry.get("customer"))){
			r2.setSelected(true);
		}
		else{
			r1.setSelected(true);
		}
		packageIdField.setText(entry.get("packageId"));
		packageNameField.setText(entry.get("packageName"));
		frame.pack();
		frame.setVisible(true);
	}
}