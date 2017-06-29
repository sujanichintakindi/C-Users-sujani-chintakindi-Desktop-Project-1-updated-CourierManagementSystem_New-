package couriermanagementsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class CustomerManager {
	HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>(); 
	public CustomerManager(){
		try (BufferedReader br = new BufferedReader(new FileReader("customers.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] row = line.split(",");
		    	if(row.length == 3){
		    		data.put(row[0], new HashMap<String, String>());
		    		data.get(row[0]).put("contact", row[1]);
		    		data.get(row[0]).put("location", row[2]);
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public boolean contactExists(String name){
		if(this.data.containsKey(name)){
			return true;
		}
		return false;
	}
	private void writeCustomerConfig(){
		try {
			PrintWriter writer = new PrintWriter("customers.csv", "UTF-8");
			for(String name : data.keySet()){
				writer.println(name+","+data.get(name).get("contact")+","+data.get(name).get("location"));
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}
	public String[] getCustomers(){
		return data.keySet().toArray(new String[0]);
	}
	public HashMap<String, String> getCustomer(String name){
		if(contactExists(name)){
			return data.get(name);
		}
		else{
			return null;
		}
	}
	public boolean addCustomer(String location, String name, String contact){
		if(!contactExists(contact)){
			data.put(name, new HashMap<String, String>());
			data.get(name).put("location", location);
			data.get(name).put("contact", contact);
			writeCustomerConfig();
			return true;
		}
		else{
			return false;
		}
	}
	public boolean editCustomer(String location, String name, String contact, String oldName){
		if(!contactExists(name) || name.equals(oldName)){
			data.remove(oldName);
			data.put(name, new HashMap<String, String>());
			data.get(name).put("location", location);
			data.get(name).put("contact", contact);
			writeCustomerConfig();
			return true;
		}
		else{
			return false;
		}
	}
	public boolean deleteCustomer(String name){
		if(contactExists(name)){
			data.remove(name);
			writeCustomerConfig();
			return true;
		}
		else{
			return false;
		}
	}
}
