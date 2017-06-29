package couriermanagementsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;

public class CourierManager {
	ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>(); 
	public CourierManager(){
		try (BufferedReader br = new BufferedReader(new FileReader("couriers.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] row = line.split(",");
		    	if(row.length == 2){
		    		HashMap<String, String> rowMap = new HashMap<String, String>();
		    		rowMap.put("name", row[0]);
		    		rowMap.put("contact", row[1]);
		    		data.add(rowMap);
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public boolean indexExists(int index){
		if(data.size() > index){
			return true;
		}
		return false;
	}
	public boolean addCourier(String contact, String name){
		for (int i = 0; i < data.size(); i++) {
			if(data.get(i).get("name").equals(name)){
				return false;
			}
		}
		HashMap<String, String> rowMap = new HashMap<String, String>();
		rowMap.put("name", name);
		rowMap.put("contact", contact);
		data.add(rowMap);
		writeCourierConfig();
		return true;
	}
	public boolean updateCourier(String name, String contact, int index){
		if(indexExists(index)){
			HashMap<String, String> rowMap = data.get(index);
			rowMap.put("name", name);
			rowMap.put("contact", contact);
			writeCourierConfig();
			return true;
		}
		return true;
	}
	public boolean deleteCourier(int index){
		if(indexExists(index)){
			data.remove(index);
		}
		writeCourierConfig();
		return true;
	}
	private void writeCourierConfig() {
		try {
			PrintWriter writer = new PrintWriter("couriers.csv", "UTF-8");
			for(HashMap<String, String> courier : data){
				writer.println(courier.get("name")+","+courier.get("contact"));
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}
	public String[] getCouriers(){
		String couriers = "";
		for(HashMap<String, String> courier : data){
			if(Main.ticketManager.courierFree(courier.get("name"))){
				couriers += courier.get("name") + ","; 
			}
		}
		if(couriers.length() > 0){
			couriers = couriers.substring(0, couriers.length()-1);
		}
		return couriers.split(",");
	}
	public String[] getAllCouriers(){
		String couriers = "";
		for(HashMap<String, String> courier : data){
			couriers += courier.get("name") + ",";
		}
		if(couriers.length() > 0){
			couriers = couriers.substring(0, couriers.length()-1);
		}
		return couriers.split(",");
	}
	public HashMap<String, String> getCourier(int index){
		return data.get(index);
	}
}