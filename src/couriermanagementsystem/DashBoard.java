package couriermanagementsystem;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DashBoard {
	public DashBoard(){
		JFrame frame = new JFrame();
		frame.setTitle("Courier Management System");
		JPanel panel = (JPanel) frame.getContentPane();
		GroupLayout gl = new GroupLayout(panel);
		panel.setLayout(gl);
		gl.setAutoCreateContainerGaps(true);
		JLabel pageHeader = new JLabel("Dash Board");
		pageHeader.setFont(new Font("", Font.BOLD, 16));
		JButton userManagmentButton = new JButton("Manage Users");
		userManagmentButton.setMinimumSize(new Dimension(200, 30));
		userManagmentButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
		  		new UserManagement();
			}
		});
		JButton courierManagmentButton = new JButton("Manage Couriers");
		courierManagmentButton.setMinimumSize(new Dimension(200, 30));
		courierManagmentButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
		  		new CourierManagment();
			}
		});
		JButton customerManagmentButton = new JButton("Manage Clients");
		customerManagmentButton.setMinimumSize(new Dimension(200, 30));
		customerManagmentButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
		  		new CustomerManagment();
			}
		});
		JButton networkManagmentButton = new JButton("Manage Intersection");
		networkManagmentButton.setMinimumSize(new Dimension(200, 30));
		networkManagmentButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				new NetworkManagement();
			}
		});
		JButton updateBaseParameters = new JButton("Update Base Parameters");
		updateBaseParameters.setMinimumSize(new Dimension(200, 30));
		updateBaseParameters.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) { 
				new BaseParametersManagement();
			}
		});
		String tableData[][] = Main.ticketManager.getTickets();
		String column[]={"Package ID","Package Name","Pickup Client","Delivery Client","Payment Client","Courier","Pickup Time","Delivery Time","Ticket Time","Bonus","Status"};
		JTable jt = new JTable(tableData,column);
		jt.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		jt.setPreferredScrollableViewportSize(new Dimension(450,63));
		jt.setFillsViewportHeight(true);
		JScrollPane ticketTable = new JScrollPane(jt);
		JButton addTicket = new JButton("Generate Ticket");
		addTicket.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
		  		new AddTicket();
			}
		});
		JButton editTicket = new JButton("Edit Ticket");
		editTicket.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int rowNumer = jt.getSelectedRow();
				if(rowNumer == -1){
					Main.raiseAlert("Select A Ticket to Edit");
					return;
				}
				String status = jt.getValueAt(rowNumer, 10).toString();
				if(status.equals("complete")){
					Main.raiseAlert("Ticket Already Complete");
				}
				else{
					String courier = jt.getValueAt(rowNumer, 5).toString();
			  		new EditTicket(courier);
				}
			}
		});
		JButton deleteTicket = new JButton("Delete Ticket");
		deleteTicket.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				int rowNumer = jt.getSelectedRow();
				if(rowNumer == -1){
					Main.raiseAlert("Select A Ticket to Delete");
					return;
				}
				String courier = jt.getValueAt(rowNumer, 5).toString();
				if(Main.ticketManager.deleteTicket(courier)){
					frame.dispose();
					Main.raiseAlert("Ticket Delete Successfully");
					new DashBoard();
				}
				else{
					Main.raiseAlert("Unable to Delete Ticket");
				}
			}
		});
		JButton ticketReports = new JButton("Invoice Generation");
		ticketReports.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Main.ticketManager.ticketReports();
			}
		});
		JButton courierReports = new JButton("Courier Performance Reports");
		courierReports.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				Main.ticketManager.courierReports();
			}
		});
		JButton performanceReports = new JButton("Company Performance Reports");
		performanceReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.ticketManager.performanceReports();
			}
		});
		//adding content to group
		gl.setHorizontalGroup(gl.createParallelGroup(GroupLayout.Alignment.CENTER)
			.addGroup(gl.createSequentialGroup()
				.addComponent(pageHeader)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(userManagmentButton)
				.addGap(20)
				.addComponent(courierManagmentButton)
				.addGap(20)
				.addComponent(customerManagmentButton)
				.addGap(20)
				.addComponent(networkManagmentButton)
				.addGap(20)
				.addComponent(updateBaseParameters)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(ticketTable)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(addTicket)
				.addGap(20)
				.addComponent(editTicket)
				.addGap(20)
				.addComponent(deleteTicket)
				.addGap(75)
			)
			.addGroup(gl.createSequentialGroup()
				.addGap(75)
				.addComponent(ticketReports)
				.addGap(20)
				.addComponent(courierReports)
				.addGap(20)
				.addComponent(performanceReports)
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
				.addComponent(userManagmentButton)	
				.addComponent(courierManagmentButton)
				.addComponent(customerManagmentButton)
				.addComponent(networkManagmentButton)
				.addComponent(updateBaseParameters)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(ticketTable)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(addTicket)
				.addComponent(editTicket)
				.addComponent(deleteTicket)
			)
			.addGroup(gl.createParallelGroup()
				.addGap(20)
			)
			.addGroup(gl.createParallelGroup()
				.addComponent(ticketReports)
				.addComponent(courierReports)
				.addComponent(performanceReports)
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
}
