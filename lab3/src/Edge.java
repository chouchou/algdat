
public class Edge implements Comparable<Edge>{
	City start;
	City end;
	int cost;
	
	public Edge(City start, City end, int cost){
		this.start = start;
		this.end = end;
		this.cost = cost;
	}
	
	public boolean equals(Edge e){
		
		if ((e.start==this.start && e.end == this.end)||(e.start==this.end && e.end ==this.start)){
				return true;
			}	
		return false;
	}
	public int compareTo(Edge e){
		
		if(cost == e.cost && this.equals(e)){
			return 0;
		}else if (cost < e.cost){
			return -1;
		}else {
			return 1;
		}

	}

}
