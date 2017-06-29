package couriermanagementsystem;

public class Edge {
	private Node source;
	private Node destination;
	private int cost;
	private int distance;
	private String direction;
	private int time;
	public Edge(Node source, Node destination, int cost, int distance, int time, String direction){
		this.source = source;
		this.destination = destination;
		this.cost = cost;
		this.distance = distance;
		this.time = time;
		this.direction = direction;
	}
	public Node getSource() {
		return source;
	}
	public Node getDestination() {
		return destination;
	}
	public int getCost() {
		return cost;
	}
	public int getDistance() {
		return distance;
	}
	public String getDirection() {
		return direction;
	}
	public int getTime() {
		return time;
	}
}
