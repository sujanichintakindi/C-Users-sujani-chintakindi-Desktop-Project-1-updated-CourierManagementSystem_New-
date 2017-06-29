package couriermanagementsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class UserManager {
	HashMap<String, HashMap<String, String>> data = new HashMap<String, HashMap<String, String>>(); 
	public UserManager(){
		try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] row = line.split(",");
		    	if(row.length == 5){
		    		data.put(row[2], new HashMap<String, String>());
		    		data.get(row[2]).put("name", row[0]);
		    		data.get(row[2]).put("contact", row[1]);
		    		data.get(row[2]).put("password", row[3]);
		    		data.get(row[2]).put("role", row[4]);
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	public boolean userNameExists(String username){
		if(this.data.containsKey(username)){
			return true;
		}
		return false;
	}
	public boolean addUser(String username, String password, String contact, String name, String role){
		if(userNameExists(username)){
			return false;
		}
		data.put(username, new HashMap<String, String>());
		data.get(username).put("contact", contact);
		data.get(username).put("password", password);
		data.get(username).put("name", name);
		data.get(username).put("role", role);
		writeUserConfig();
		return true;
	}
	public boolean updateUser(String username, String contact, String name, String role, String password){
		data.get(username).put("contact", contact);
		data.get(username).put("password", password);
		data.get(username).put("name", name);
		data.get(username).put("role", role);
		writeUserConfig();
		return true;
	}
	public boolean deleteUser(String username){
		if(userNameExists(username)){
			data.remove(username);
		}
		writeUserConfig();
		return true;
	}
	private void writeUserConfig(){
		try {
			PrintWriter writer = new PrintWriter("users.csv", "UTF-8");
			for(String username : data.keySet()){
				writer.println(data.get(username).get("name")+","+data.get(username).get("contact")+","+username+","+data.get(username).get("password")+","+data.get(username).get("role"));
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}
	public boolean login(String username, String password){
		if(userNameExists(username)){
			if(data.get(username).get("password").equals(password)){
				Main.loginUsername = username;
				return true;
			}
		}
		return false;
	}
	public String[] getUsers(){
		return data.keySet().toArray(new String[0]);
	}
	public HashMap<String, String> getUser(String username){
		return data.get(username);
	}
}