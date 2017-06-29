package couriermanagementsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import static java.time.temporal.ChronoUnit.DAYS;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TicketManager {
	HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>();
	ArrayList<HashMap<String, String>> completed = new ArrayList<HashMap<String, String>>();
	public TicketManager(){
		try (BufferedReader br = new BufferedReader(new FileReader("tickets.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] row = line.split(",");
		    	if(row.length == 18){
		    		if(row[3].equals("live")){
		    			HashMap<String, String> entry = new HashMap<String, String>();
			    		entry.put("customer", row[0]);
			    		entry.put("bonus", row[2]);
			    		entry.put("status", row[3]);
			    		entry.put("deliveryTime", row[4]);
			    		entry.put("estimatedCost", row[5]);
			    		entry.put("estimatedDistance", row[6]);
			    		entry.put("instructions", row[7]);
			    		entry.put("deliveryInstructions", row[8]);
			    		entry.put("returnInstructions", row[9]);
			    		entry.put("estimatedTime", row[10]);
			    		entry.put("ticketTime", row[11]);
			    		entry.put("pickupCustomer", row[12]);
			    		entry.put("pickupTime", row[13]);
			    		entry.put("paymentCustomer", row[14]);
			    		entry.put("actualDeliveryTime", row[15]);
			    		entry.put("packageId", row[16]);
			    		entry.put("packageName", row[17]);
			    		data.put(row[1], entry);
		    		}
		    		else{
		    			HashMap<String, String> entry = new HashMap<String, String>();
			    		entry.put("customer", row[0]);
			    		entry.put("courier", row[1]);
			    		entry.put("bonus", row[2]);
			    		entry.put("status", row[3]);
			    		entry.put("deliveryTime", row[4]);
			    		entry.put("estimatedCost", row[5]);
			    		entry.put("estimatedDistance", row[6]);
			    		entry.put("instructions", row[7]);
			    		entry.put("deliveryInstructions", row[8]);
			    		entry.put("returnInstructions", row[9]);
			    		entry.put("estimatedTime", row[10]);
			    		entry.put("ticketTime", row[11]);
			    		entry.put("pickupCustomer", row[12]);
			    		entry.put("pickupTime", row[13]);
			    		entry.put("paymentCustomer", row[14]);
			    		entry.put("actualDeliveryTime", row[15]);
			    		entry.put("packageId", row[16]);
			    		entry.put("packageName", row[17]);
			    		completed.add(entry);
		    		}
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public boolean courierFree(String courier){
		if(data.keySet().contains(courier)){
			return false;
		}
		return true;
	}
	public boolean courierExists(String courier){
		if(data.keySet().contains(courier)){
			return true;
		}
		return false;
	}
	private void writeTicketConfig(){
		try {
			PrintWriter writer = new PrintWriter("tickets.csv", "UTF-8");
			for(String courier : data.keySet()){
				writer.println(data.get(courier).get("customer")+","+courier+","+data.get(courier).get("bonus")+","+data.get(courier).get("status")+","+data.get(courier).get("deliveryTime")+","+data.get(courier).get("estimatedCost")+","+data.get(courier).get("estimatedDistance")+","+data.get(courier).get("instructions")+","+data.get(courier).get("deliveryInstructions")+","+data.get(courier).get("returnInstructions")+","+data.get(courier).get("estimatedTime")+","+data.get(courier).get("ticketTime")+","+data.get(courier).get("pickupCustomer")+","+data.get(courier).get("pickupTime")+","+data.get(courier).get("paymentCustomer")+","+data.get(courier).get("actualDeliveryTime")+","+data.get(courier).get("packageId")+","+data.get(courier).get("packageName"));
			}
			for(int i=0;i<completed.size();i++){
				writer.println(completed.get(i).get("customer")+","+completed.get(i).get("courier")+","+completed.get(i).get("bonus")+","+completed.get(i).get("status")+","+completed.get(i).get("deliveryTime")+","+completed.get(i).get("estimatedCost")+","+completed.get(i).get("estimatedDistance")+","+completed.get(i).get("instructions")+","+completed.get(i).get("deliveryInstructions")+","+completed.get(i).get("returnInstructions")+","+completed.get(i).get("estimatedTime")+","+completed.get(i).get("ticketTime")+","+completed.get(i).get("pickupCustomer")+","+completed.get(i).get("pickupTime")+","+completed.get(i).get("paymentCustomer")+","+completed.get(i).get("actualDeliveryTime")+","+completed.get(i).get("packageId")+","+completed.get(i).get("packageName"));
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}
	public String[][] getTickets(){
		String[][] result = new String[data.keySet().size() + completed.size()][11];
		int iterator = 0;
		for(String courier : data.keySet()){
			result[iterator][0] = data.get(courier).get("packageId");
			result[iterator][1] = data.get(courier).get("packageName");
			result[iterator][2] = data.get(courier).get("pickupCustomer");
			result[iterator][3] = data.get(courier).get("customer");
			result[iterator][4] = data.get(courier).get("paymentCustomer");
			result[iterator][5] = courier;
			result[iterator][6] = data.get(courier).get("pickupTime");
			result[iterator][7] = data.get(courier).get("deliveryTime");
			result[iterator][8] = data.get(courier).get("ticketTime");
			result[iterator][9] = data.get(courier).get("bonus").equals("0") ? "No" : "Yes";
			result[iterator][10] = data.get(courier).get("status");
			iterator++;
		}
		for(int i=0;i<completed.size();i++){
			result[iterator][0] = completed.get(i).get("packageId");
			result[iterator][1] = completed.get(i).get("packageName");
			result[iterator][2] = completed.get(i).get("pickupCustomer");
			result[iterator][3] = completed.get(i).get("customer");
			result[iterator][4] = completed.get(i).get("paymentCustomer");
			result[iterator][5] = completed.get(i).get("courier");
			result[iterator][6] = completed.get(i).get("pickupTime");
			result[iterator][7] = completed.get(i).get("deliveryTime");
			result[iterator][8] = completed.get(i).get("ticketTime");
			result[iterator][9] = completed.get(i).get("bonus").equals("0") ? "No" : "Yes";
			result[iterator][10] = completed.get(i).get("status");
			iterator++;
		}
		return result;
	}
	public HashMap<String, String> getTicket(String courier){
		if(courierExists(courier)){
			return data.get(courier);
		}
		else{
			return null;
		}
	}
	public boolean addTicket(String customer, String courier, String deliveryTime, String ticketTime, String pickupCustomer, String pickupTime, String paymentCustomer,String packageID,String packageName){
		String destination = Main.customerManager.getCustomer(pickupCustomer).get("location");
		String destination2 = Main.customerManager.getCustomer(customer).get("location");
		Estimate estimate = Main.networkManager.getEstimates("D4",destination);
		Estimate estimate2 = Main.networkManager.getEstimates(destination,destination2);
		Estimate estimate3 = Main.networkManager.getEstimates(destination2,"D4");
		if(courierFree(courier) && estimate != null && estimate2 != null && estimate3 != null){
			HashMap<String, String> entry = new HashMap<String, String>();
			entry.put("customer", customer);
			entry.put("deliveryTime", deliveryTime);
			entry.put("bonus", "0");
			entry.put("status", "live");
			entry.put("estimatedCost", String.valueOf(estimate.getCost()+estimate2.getCost()+estimate3.getCost()));
			entry.put("estimatedDistance", String.valueOf(estimate.getDistance()+estimate2.getDistance()+estimate3.getDistance()));
			entry.put("instructions", estimate.getPath());
			entry.put("deliveryInstructions", estimate2.getPath());
			entry.put("returnInstructions", estimate3.getPath());
			entry.put("estimatedTime", String.valueOf(estimate.getTime()+estimate2.getTime()+estimate3.getTime()));
    		entry.put("ticketTime", ticketTime);
    		entry.put("pickupCustomer", pickupCustomer);
    		entry.put("pickupTime", pickupTime);
    		entry.put("paymentCustomer", paymentCustomer);
    		entry.put("actualDeliveryTime", "null");
    		entry.put("packageId", packageID);
    		entry.put("packageName", packageName);
			data.put(courier,entry);
			JOptionPane.showMessageDialog(null, "Estimated Cost :"+entry.get("estimatedCost")+"\nEstimated Distance :"+entry.get("estimatedDistance")+"\nEstimated Time :"+entry.get("estimatedTime")+"\nPickup Instructions :"+entry.get("instructions")+"\nDelivery Instructions :"+entry.get("deliveryInstructions")+"\nReturn Instructions :"+entry.get("returnInstructions"));
			writeTicketConfig();
			return true;
		}
		else{
			return false;
		}
	}
	public boolean editTicket(String customer, String courier, String status, String bonus, String deliveryTime, String oldCourier, String ticketTime, String pickupCustomer, String pickupTime, String paymentCustomer,String actualDeliveryTime,String packageId, String packageName){
		String destination = Main.customerManager.getCustomer(pickupCustomer).get("location");
		String destination2 = Main.customerManager.getCustomer(customer).get("location");
		Estimate estimate = Main.networkManager.getEstimates("D4",destination);
		Estimate estimate2 = Main.networkManager.getEstimates(destination,destination2);
		Estimate estimate3 = Main.networkManager.getEstimates(destination2,"D4");
		if(courierFree(courier) || oldCourier.equals(courier)){
			if(status.equals("complete")){
				data.remove(oldCourier);
				HashMap<String, String> entry = new HashMap<String, String>();
				entry.put("customer", customer);
				entry.put("bonus", bonus);
				entry.put("status", status);
				entry.put("deliveryTime", deliveryTime);
				entry.put("courier", courier);
				int bonusCost = 0;
				if(bonus.equals("1")){
					bonusCost = 2;
				}
				entry.put("estimatedCost", String.valueOf(estimate.getCost()+estimate2.getCost()+estimate3.getCost()+bonusCost));
				entry.put("estimatedDistance", String.valueOf(estimate.getDistance()+estimate2.getDistance()+estimate3.getDistance()));
				entry.put("instructions", estimate.getPath());
				entry.put("deliveryInstructions", estimate2.getPath());
				entry.put("returnInstructions", estimate3.getPath());
				entry.put("estimatedTime", String.valueOf(estimate.getTime()+estimate2.getTime()+estimate3.getTime()));
	    		entry.put("ticketTime", ticketTime);
	    		entry.put("pickupCustomer", pickupCustomer);
	    		entry.put("pickupTime", pickupTime);
	    		entry.put("paymentCustomer", paymentCustomer);
	    		entry.put("actualDeliveryTime", actualDeliveryTime);
	    		entry.put("packageId", packageId);
	    		entry.put("packageName", packageName);
				data.put(courier, entry);
				completed.add(data.get(courier));
				data.remove(courier);
			}
			else{
				data.remove(oldCourier);
				HashMap<String, String> entry = new HashMap<String, String>();
				entry.put("customer", customer);
				entry.put("bonus", bonus);
				int bonusCost = 0;
				if(bonus.equals("1")){
					bonusCost = 2;
				}
				entry.put("status", status);
				entry.put("deliveryTime", deliveryTime);
				entry.put("estimatedCost", String.valueOf(estimate.getCost()+estimate2.getCost()+estimate3.getCost()+bonusCost));
				entry.put("estimatedDistance", String.valueOf(estimate.getDistance()+estimate2.getDistance()+estimate3.getDistance()));
				entry.put("instructions", estimate.getPath());
				entry.put("deliveryInstructions", estimate2.getPath());
				entry.put("returnInstructions", estimate3.getPath());
				entry.put("estimatedTime", String.valueOf(estimate.getTime()+estimate2.getTime()+estimate3.getTime()));
	    		entry.put("ticketTime", ticketTime);
	    		entry.put("pickupCustomer", pickupCustomer);
	    		entry.put("pickupTime", pickupTime);
	    		entry.put("paymentCustomer", paymentCustomer);
	    		entry.put("actualDeliveryTime", actualDeliveryTime);
	    		entry.put("packageId", packageId);
	    		entry.put("packageName", packageName);
				data.put(courier, entry);
			}
			writeTicketConfig();
			return true;
		}
		else{
			return false;
		}
	}
	public boolean deleteTicket(String courier){
		if(courierExists(courier)){
			data.remove(courier);
			writeTicketConfig();
			return true;
		}
		else{
			return false;
		}
	}
	//start editing from reports
	public void ticketReports(){
		String selectedValue = (String)JOptionPane.showInputDialog( null, "Select Client From List Below : ", "Customer List",JOptionPane.QUESTION_MESSAGE, null, Main.customerManager.getCustomers(),Main.customerManager.getCustomers()[0]);
		ArrayList<HashMap<String, String>> finalResult = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<completed.size();i++){
			if(completed.get(i).get("paymentCustomer").equals(selectedValue)){
				Date result = dateConvert(completed.get(i).get("deliveryTime"));
				LocalDate date1 = (new Date()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				LocalDate date2 = result.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				long diffDays = DAYS.between(date2, date1);
				if(diffDays < 7){
					finalResult.add(completed.get(i));
				}
			}
		}
		String[][] tableData = new String[finalResult.size()][11];
		int iterator = 0;
		int total = 0;
		int deliveryAmount = 0;
		int bonusAmount = 0;
		for(HashMap<String, String> ticket : finalResult){
			tableData[iterator][0] = ticket.get("packageId");
			tableData[iterator][1] = ticket.get("pickupCustomer");
			tableData[iterator][2] = ticket.get("customer");
			tableData[iterator][3] = ticket.get("paymentCustomer");
			tableData[iterator][4] = ticket.get("courier");
			tableData[iterator][5] = ticket.get("estimatedCost");
			tableData[iterator][6] = ticket.get("estimatedDistance");
			tableData[iterator][7] = ticket.get("estimatedTime");
			tableData[iterator][8] = ticket.get("actualDeliveryTime");
			tableData[iterator][9] = ticket.get("bonus").equals("0") ? "No" : "Yes";
			tableData[iterator][10] = ticket.get("status");
			iterator++;
			deliveryAmount += ticket.get("bonus").equals("0") ? Integer.valueOf(ticket.get("estimatedCost")) : Integer.valueOf(ticket.get("estimatedCost"))-2;
			bonusAmount += ticket.get("bonus").equals("0") ? 0 : 2;
			total += Integer.valueOf(ticket.get("estimatedCost"));
		}
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		String column[]={"Package ID","Pickup Client","Delivery Client","Payment Client","Courier","Estimate Cost","Estimated Distance","Estimated Time","Actual Delivery Time","Bonus","Status"};
		JTable jt = new JTable(tableData,column);
		JScrollPane ticketTable = new JScrollPane(jt);
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.add(ticketTable);
		panel.add(new JLabel("Delivery Amount : " +deliveryAmount));
		panel.add(new JLabel("Bonus Amount : " +bonusAmount));
		panel.add(new JLabel("Total Amount : " +total));
		panel.add(new JLabel("Number of Tickets  : " +finalResult.size()));
		frame.add(panel);
		frame.setVisible(true);
		frame.setTitle("Ticket Reports");
		frame.pack();
	}
	public void courierReports(){
		String selectedValue = (String)JOptionPane.showInputDialog( null, "Select Courier From List Below : ", "Customer List",JOptionPane.QUESTION_MESSAGE, null, Main.courierManager.getAllCouriers(),Main.courierManager.getAllCouriers()[0]);
		ArrayList<HashMap<String, String>> finalResultOnTime = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> finalResultLateTime = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<completed.size();i++){
			if(completed.get(i).get("courier").equals(selectedValue)){
				if(completed.get(i).get("bonus").equals("1")){
					finalResultOnTime.add(completed.get(i));
				}
				else{
					finalResultLateTime.add(completed.get(i));
				}
			}
		}
		String[][] tableData = new String[finalResultOnTime.size()][10];
		int iterator = 0;
		for(HashMap<String, String> ticket : finalResultOnTime){
			tableData[iterator][0] = ticket.get("pickupCustomer");
			tableData[iterator][1] = ticket.get("customer");
			tableData[iterator][2] = ticket.get("paymentCustomer");
			tableData[iterator][3] = ticket.get("courier");
			tableData[iterator][4] = ticket.get("pickupTime");
			tableData[iterator][5] = ticket.get("deliveryTime");
			tableData[iterator][6] = ticket.get("ticketTime");
			tableData[iterator][7] = ticket.get("actualDeliveryTime");
			tableData[iterator][8] = ticket.get("bonus").equals("0") ? "No" : "Yes";
			tableData[iterator][9] = ticket.get("status");
			iterator++;
		}
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		String column[]={"Pickup Client","Delivery Client","Payment Client","Courier","Pickup Time","Delivery Time","Ticket Time","Actual Delivery Time","Bonus","Status"};
		JTable jt = new JTable(tableData,column);
		JScrollPane ticketTable = new JScrollPane(jt);
		panel.setLayout(new GridLayout(4, 1));
		panel.add(new JLabel("Tickets Delivered On Time"));
		panel.add(ticketTable);
		iterator = 0;
		String[][] tableData2 = new String[finalResultLateTime.size()][10];
		for(HashMap<String, String> ticket : finalResultLateTime){
			tableData2[iterator][0] = ticket.get("pickupCustomer");
			tableData2[iterator][1] = ticket.get("customer");
			tableData2[iterator][2] = ticket.get("paymentCustomer");
			tableData2[iterator][3] = ticket.get("courier");
			tableData2[iterator][4] = ticket.get("pickupTime");
			tableData2[iterator][5] = ticket.get("deliveryTime");
			tableData2[iterator][6] = ticket.get("ticketTime");
			tableData2[iterator][7] = ticket.get("actualDeliveryTime");
			tableData2[iterator][8] = ticket.get("bonus").equals("0") ? "No" : "Yes";
			tableData2[iterator][9] = ticket.get("status");
			iterator++;
		}
		JTable jt2 = new JTable(tableData2,column);
		JScrollPane ticketTable2 = new JScrollPane(jt2);
		panel.add(new JLabel("Tickets Delivered After Time"));
		panel.add(ticketTable2);
		frame.add(panel);
		frame.setVisible(true);
		frame.setTitle("Courier Reports");
		frame.pack();
	}
	public void performanceReports(){
		ArrayList<HashMap<String, String>> finalResultOnTime = new ArrayList<HashMap<String, String>>();
		ArrayList<HashMap<String, String>> finalResultLateTime = new ArrayList<HashMap<String, String>>();
		for(int i=0;i<completed.size();i++){
			System.out.println(completed.get(i).get("bonus"));
			if(completed.get(i).get("bonus").equals("1")){
				finalResultOnTime.add(completed.get(i));
			}
			else{
				finalResultLateTime.add(completed.get(i));
			}
		}
		String[][] tableData = new String[finalResultOnTime.size()][10];
		int iterator = 0;
		for(HashMap<String, String> ticket : finalResultOnTime){
			tableData[iterator][0] = ticket.get("pickupCustomer");
			tableData[iterator][1] = ticket.get("customer");
			tableData[iterator][2] = ticket.get("paymentCustomer");
			tableData[iterator][3] = ticket.get("courier");
			tableData[iterator][4] = ticket.get("pickupTime");
			tableData[iterator][5] = ticket.get("deliveryTime");
			tableData[iterator][6] = ticket.get("ticketTime");
			tableData[iterator][7] = ticket.get("actualDeliveryTime");
			tableData[iterator][8] = ticket.get("bonus").equals("0") ? "No" : "Yes";
			tableData[iterator][9] = ticket.get("status");
			iterator++;
		}
		JFrame frame = new JFrame();
		JPanel panel = new JPanel();
		String column[]={"Pickup Client","Delivery Client","Payment Client","Courier","Pickup Time","Delivery Time","Ticket Time","Actual Delivery Time","Bonus","Status"};
		JTable jt = new JTable(tableData,column);
		JScrollPane ticketTable = new JScrollPane(jt);
		panel.setLayout(new GridLayout(4, 1));
		panel.add(new JLabel("Tickets Delivered On Time"));
		panel.add(ticketTable);
		frame.add(panel);
		iterator = 0;
		String[][] tableData2 = new String[finalResultLateTime.size()][10];
		for(HashMap<String, String> ticket : finalResultLateTime){
			tableData2[iterator][0] = ticket.get("pickupCustomer");
			tableData2[iterator][1] = ticket.get("customer");
			tableData2[iterator][2] = ticket.get("paymentCustomer");
			tableData2[iterator][3] = ticket.get("courier");
			tableData2[iterator][4] = ticket.get("pickupTime");
			tableData2[iterator][5] = ticket.get("deliveryTime");
			tableData2[iterator][6] = ticket.get("ticketTime");
			tableData2[iterator][7] = ticket.get("actualDeliveryTime");
			tableData2[iterator][8] = ticket.get("bonus").equals("0") ? "No" : "Yes";
			tableData2[iterator][9] = ticket.get("status");
			iterator++;
		}
		JTable jt2 = new JTable(tableData2,column);
		JScrollPane ticketTable2 = new JScrollPane(jt2);
		panel.add(new JLabel("Tickets Delivered After Time"));
		panel.add(ticketTable2);
		frame.setVisible(true);
		frame.setTitle("Performance Reports");
		frame.pack();
	}
	public Date dateConvert(String date){
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault());
		try {
			return format.parse(date);
		} catch (ParseException e1) {
			return null;
		}
	}
}
