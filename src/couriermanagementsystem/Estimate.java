package couriermanagementsystem;

public class Estimate {
	private int cost;
	private int distance;
	private String path;
	private int time;
	public int getTime() {
		return time;
	}
	public int getCost() {
		return cost;
	}
	public int getDistance() {
		return distance;
	}
	public String getPath() {
		return path;
	}
	public Estimate(int cost, int distance, String path, int time) {
		this.cost = cost;
		this.distance = distance;
		this.path = path;
		this.time = time;
	}
}
