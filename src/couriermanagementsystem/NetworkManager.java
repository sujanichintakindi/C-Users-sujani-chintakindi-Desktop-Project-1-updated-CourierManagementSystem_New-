package couriermanagementsystem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class NetworkManager {
	private Graph graph;
	String[] locations = {"A1","A2","A3","A4","A5","A6","A7","B1","B2","B3","B4","B5","B6","B7","C1","C2","C3","C4","C5","C6","C7","D1","D2","D3","D4","D5","D6","D7","E1","E2","E3","E4","E5","E6","E7","F1","F2","F3","F4","F5","F6","F7","G1","G2","G3","G4","G5","G6","G7"};
	HashMap<String, ArrayList<String>> links = new HashMap<String, ArrayList<String>>();
	public String[] getLocations() {
		return locations;
	}
	public NetworkManager(){
		loadNetwork();
	}
	public void loadNetwork(){
		graph = new Graph();
		links.put("blocked", new ArrayList<String>());
		links.put("working", new ArrayList<String>());
		try (BufferedReader br = new BufferedReader(new FileReader("network.csv"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] edgeData = line.split(",");
				if(edgeData.length == 5){
					if(edgeData[3].equals("1")){
						graph.addNode(edgeData[0]);
						graph.addNode(edgeData[1]);
						graph.addEdge(edgeData[0], edgeData[1], Main.baseParams.getCost(), Main.baseParams.getDistance(), Main.baseParams.getTime(), edgeData[4]);
						links.get("working").add(edgeData[0]+"-"+edgeData[1]+"-"+edgeData[4]);
					}
					else{
						links.get("blocked").add(edgeData[0]+"-"+edgeData[1]+"-"+edgeData[4]);
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
	public void unblockEdge(String edge){
		String[] edgeData = edge.split("-");
		graph.addNode(edgeData[0]);
		graph.addNode(edgeData[1]);
		graph.addEdge(edgeData[0], edgeData[1], Main.baseParams.getCost(), Main.baseParams.getDistance(), Main.baseParams.getTime(), edgeData[2]);
		links.get("blocked").remove(edge);
		links.get("working").add(edge);
		writeNetworkConfig();
	}
	public void blockEdge(String edge){
		String[] edgeData = edge.split("-");
		graph.getEdges().remove(graph.getEdge(edgeData[0], edgeData[1]));
		links.get("working").remove(edge);
		links.get("blocked").add(edge);
		writeNetworkConfig();
	}
	public String[] getBlockedEdges(){
		String[] edges = new String[links.get("blocked").size()];
		int iterator = 0;
		for(String edge : links.get("blocked")){
			edges[iterator] = edge;
			iterator++;
		}
		return edges;
	}
	public void writeNetworkConfig(){
		try {
			PrintWriter writer = new PrintWriter("network.csv", "UTF-8");
			ArrayList<Edge> edges = graph.getEdges();
			for(Edge edge : edges){
				writer.println(edge.getSource().getName()+","+edge.getDestination().getName()+","+edge.getCost()+","+edge.getDistance()+","+edge.getDirection());
			}
			for(String link : links.get("blocked")){
				writer.println(link.split("-")[0]+","+link.split("-")[1]+","+Main.baseParams.getCost()+",-"+Main.baseParams.getDistance()+","+link.split("-")[2]);
			}
			writer.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			System.out.println(e);
		}
	}
	public String[] getUnBlockedEdges(){
		String[] edges = new String[links.get("working").size()];
		int iterator = 0;
		for(String edge : links.get("working")){
			edges[iterator] = edge;
			iterator++;
		}
		return edges;
	}
	public Estimate getEstimates(String source, String destination){
		loadNetwork();
		graph.execute(source);
		LinkedList<Node> path = graph.getPath(destination);
		if(path == null){
			return null;
		}
		int totalCost = 0;
		int totalDistance = 0;
		int totalTime = 0;
		String start = null;
		String pathString = "("+graph.getNode(source).getName()+")->";
		for(Node node : path){
			if(start != null){
				totalCost += graph.getEdge(start, node.getName()).getCost();
				totalDistance += graph.getEdge(start, node.getName()).getDistance();
				totalTime += 2;
				pathString += graph.getEdge(start, node.getName()).getDirection()+"("+node.getName()+")->";
			}
			start = node.getName();
		}
		pathString = pathString.substring(0, pathString.length()-2);
		return new Estimate(totalCost, totalDistance, pathString, totalTime);
	}
}
