package couriermanagementsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class BaseParameters {
	private int cost;
	private int distance;
	private int time;
	public int getCost() {
		return cost;
	}
	public int getDistance() {
		return distance;
	}
	public int getTime() {
		return time;
	}
	public BaseParameters() {
		try (BufferedReader br = new BufferedReader(new FileReader("baseparams.csv"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	String[] row = line.split(",");
		    	if(row.length == 3){
		    		this.cost = Integer.valueOf(row[0]);
		    		this.distance = Integer.valueOf(row[1]);
		    		this.time = Integer.valueOf(row[2]);
		    	}
		    }
		    br.close();
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
