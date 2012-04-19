import java.util.LinkedList;

public class Node{
	private LinkedList<Node> neighbours;
	private String s;
	
	public Node(String s){
		this.s = s;
		neighbours = new LinkedList<Node>();
	}
	public String toString(){
		return s;
	}
	public LinkedList<Node> neighbours(){
		return neighbours;
	}
	
	public boolean addNeighbour(Node n){
		return neighbours.add(n);
	}
	public Node getNeighbour(){
		return neighbours.element();
	}	
	public Node removeNeighbour(){
		return neighbours.remove();
	}
	public boolean equals(Object o){
		return o.toString().equals(this.toString());
	}
	
	public boolean hasNeighbour(Node n){
		return neighbours.contains(n);
	}
}
