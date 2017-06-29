package couriermanagementsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
	private ArrayList<Node> nodes = new ArrayList<>();
	private ArrayList<Edge> edges = new ArrayList<>();
	private ArrayList<Node> settledNodes = new ArrayList<Node>();
	private ArrayList<Node> unSettledNodes = new ArrayList<Node>();
	private HashMap<Node, Integer> distances = new HashMap<Node, Integer>();
	private HashMap<Node, Node> predecessors = new HashMap<Node, Node>();
	public ArrayList<Node> getNodes() {
		return nodes;
	}
	public ArrayList<Edge> getEdges() {
		return edges;
	}
	public boolean nodeExists(String name){
		for (int i = 0; i < getNodes().size(); i++) {
			if(getNodes().get(i).getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	public boolean addNode(String name){
		if(!nodeExists(name)){
			Node node = new Node(name);
			getNodes().add(node);
			return true;
		}
		else{
			return false;
		}
	}
	public Node getNode(String name){
		if(nodeExists(name)){
			for (int i = 0; i < getNodes().size(); i++) {
				if(getNodes().get(i).getName().equals(name)){
					return getNodes().get(i);
				}
			}
			return null;
		}
		else{
			return null;
		}
	}
	public boolean edgeExists(String source, String destination){
		for (int i = 0; i < getEdges().size(); i++) {
			if(getEdges().get(i).getSource().getName().equals(source) && getEdges().get(i).getDestination().getName().equals(destination)){
				return true;
			}
		}
		return false;
	}
	public Edge getEdge(String source, String destination){
		for (int i = 0; i < getEdges().size(); i++) {
			if(getEdges().get(i).getSource().getName().equals(source) && getEdges().get(i).getDestination().getName().equals(destination)){
				return getEdges().get(i);
			}
		}
		return null;
	}
	public boolean addEdge(String source, String destination, int cost, int distance, int time, String direction){
		if(!edgeExists(source, destination) && nodeExists(source) && nodeExists(destination)){
			Edge edge = new Edge(getNode(source), getNode(destination), cost, distance, time, direction);
			getEdges().add(edge);
			return true;
		}
		else{
			return false;
		}
	}
	public void execute(String algoSource){
		distances.put(getNode(algoSource), 0);
		unSettledNodes.add(getNode(algoSource));
		while(unSettledNodes.size() > 0){
			Node node = getMinimum(unSettledNodes);
			settledNodes.add(node);
			unSettledNodes.remove(node);
			findMinimalDistances(node);
		}
	}
	private Node getMinimum(ArrayList<Node> nodes) {
        Node minimum = null;
        for (Node node : nodes) {
            if (minimum == null) {
                minimum = node;
            } else {
                if (getShortestDistance(node) < getShortestDistance(node)) {
                    minimum = node;
                }
            }
        }
        return minimum;
    }
	private boolean isSettled(Node node) {
        return settledNodes.contains(node);
    }
	private int getShortestDistance(Node destination) {
        Integer d = distances.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }
	private void findMinimalDistances(Node node) {
        ArrayList<Node> adjacentNodes = getNeighbors(node);
        for (Node target : adjacentNodes) {
            if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
                distances.put(target, getShortestDistance(node)+getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }
	private int getDistance(Node node, Node target) {
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && edge.getDestination().equals(target)) {
                return edge.getDistance();
            }
        }
        throw new RuntimeException("Should not happen");
    }
	private ArrayList<Node> getNeighbors(Node node) {
		ArrayList<Node> neighbors = new ArrayList<Node>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node) && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }
	public LinkedList<Node> getPath(String destination) {
		Node target = getNode(destination);
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }
}