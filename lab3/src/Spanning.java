import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Spanning {
	ArrayList<City> cities = new ArrayList<City>();
	ArrayList<Edge> connections = new ArrayList<Edge>();
	
	public static void main(String[] args) throws IOException{
		Spanning sp = new Spanning();
		sp.parse(args[0]);
		//System.out.println("It is "+sp.connections.first().cost+" miles from "+ sp.connections.first().start.getName()+" to "+sp.connections.first().end.getName());
		sp.kruskal();
	}

	private void kruskal() {
		UnionFind uf = new UnionFind();
		uf.makeUnionFind(cities);
		Collections.sort(connections);
		while (!connections.isEmpty()){
			Edge e = connections.get(0);
			System.out.println(e.start.getName()+", " +e.end.getName());
			System.out.println(connections.get(0).start.getName()+", " +connections.get(0).end.getName());
			City c1 = cities.get(cities.indexOf(e.start));
			City c2 = cities.get(cities.indexOf(e.end));
			if(!uf.sameSet(c1, c2)){
				uf.union(c1, c2);
			}
			connections.remove(0);
		}
		System.out.println("done?");
	}

	private void parse(String filename) throws IOException {
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filename));
			while (scanner.hasNextLine()){
				String line = scanner.nextLine();
				if(!line.contains("--")){					
					cities.add(new City(trimMe(line)));
				}else{
					String start;
					String end;
					int cost;
					String[] tmp = line.split("--");
					start=trimMe(tmp[0]);
					if(tmp[1].startsWith("\"")){
						tmp = tmp[1].split("\" ");
						end = trimMe(tmp[0]);
						cost = Integer.parseInt(trimMe(tmp[1]));						
					}else{
						tmp = tmp[1].split(" ");
						end = trimMe(tmp[0]);
						cost = Integer.parseInt(trimMe(tmp[1]));
					}
					connections.add(new Edge(new City(start),new City(end),cost));
				}
			}
		} finally {
			scanner.close();
		}
		
	}
	
	private static String trimMe(String s){
		s = s.trim();
		if((s.startsWith("\"")&&s.endsWith("\""))|| (s.startsWith("[")&&s.endsWith("]"))){
			return s.substring( 1, s.length( ) - 1 );
		}else if (s.startsWith("\"")||s.startsWith("[")){
			return s.substring( 1, s.length( ));
		}else if (s.endsWith("\"")||s.endsWith("]")){
			return s.substring( 0, s.length( )-1);
		}
		return s;
	}
	
}
