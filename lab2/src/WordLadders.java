import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class WordLadders {
	
	public static void main(String [] args) throws IOException{

		//VARIABLES
		ArrayList<String> words = new ArrayList<String>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		HashMap<String,ArrayList<String>> hm = new HashMap<String,ArrayList<String>>();
		ArrayList<String> testcases = new ArrayList<String>();
		ArrayList<String> values;
		String key;
		
		
		// READING FILES
		String datfile = args[0];
		String testfile = args[1];

		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(datfile));
			while (scanner.hasNextLine()){
				words.add(scanner.nextLine());
		    }
		} finally {
			scanner.close();
		}
		try {
			scanner = new Scanner(new FileReader(testfile));
			while (scanner.hasNextLine()){
				testcases.add(scanner.nextLine());
		    }
		} finally {
			scanner.close();
		}
		
		// CREATE HASHMAP
		for(int i=0;i<words.size();i++){
			key = sortString(words.get(i).substring(1));
			values = hm.get(key);
			if (values == null){
				values = new ArrayList<String>();
				hm.put(key,values);
			}
			values.add(words.get(i));
		}
		//SEARCH FOR NEIGHBOURS AND BUILD GRAPH
		for(int i=0;i<words.size();i++){
			Node n = new Node(words.get(i));
			String[] comb = new String[5];
			char[] ca = n.toString().toCharArray();
			char[] c1 = {ca[1],ca[2],ca[3],ca[4]};
			char[] c2 = {ca[0],ca[2],ca[3],ca[4]};
			char[] c3 = {ca[0],ca[1],ca[3],ca[4]};
			char[] c4 = {ca[0],ca[1],ca[2],ca[4]};
			char[] c5 = {ca[0],ca[1],ca[2],ca[3]};
			comb[0] = new String(c1);
			comb[1] = new String(c2);
			comb[2] = new String(c3);
			comb[3] = new String(c4);
			comb[4] = new String(c5);
			for(int j=0;j<5;j++){
				comb[j]=sortString(comb[j]);
				if(hm.containsKey(comb[j])){
					values = hm.get(comb[j]);
					for(String s:values){
						if(!s.equals(n.toString())){
				
							//System.out.println(s+" ---> "+n);
							Node temp = new Node(s);
							if(!nodes.contains(temp)){
								temp.addNeighbour(n);
								nodes.add(temp);
							}else{
								int index = nodes.indexOf(temp);
								temp = nodes.get(index);
								if (!temp.hasNeighbour(n))
								temp.addNeighbour(n);
							}
						}
					}
				} else {
					if(!nodes.contains(n)){
						nodes.add(n);
					}
				}
			}
		}
		for(Node n : nodes){
			System.out.println(n.toString()+" ->" + n.neighbours());
		}
		// BFS
		String[] pair;
		for (String s : testcases){
			pair = s.split(" ");
			//int ind;
			Node root = nodes.get(nodes.indexOf(new Node(pair[0])));
			//System.out.println(root.toString());
			Node target = nodes.get(nodes.indexOf(new Node(pair[1])));
			//System.out.println(target.toString());
			//boolean[] visited = new boolean[nodes.size()];
			Queue<Node> q = new LinkedList<Node>();
			Queue<Integer> d = new LinkedList<Integer>();
			ArrayList<Node> vis = new ArrayList<Node>();
			q.offer(root);
			d.offer(0);
			boolean flag = false;
			while(!q.isEmpty()){
				Node tmp = q.poll();
				System.out.println(tmp);
				int depth = d.poll();
				//ind = nodes.indexOf(tmp);
				//visited[ind]=true;
				vis.add(tmp);
				if(tmp.equals(target)){
					System.out.println(depth);
					flag= true;
					break;
				}else{
					for (Node n : tmp.neighbours()){
						//ind = nodes.indexOf(n);
						
						if(!vis.contains(n)){//!visited[ind]
							q.offer(n);
							d.offer(depth+1);
							System.out.println("-->"+n.toString());
							//visited[ind]=true;
						}
					}
				}
			}
			if(!flag)System.out.println("-1");
		}
	}

	// SORT STRING IN ALPHABETIC ORDER
	public static String sortString(String s){
		char[] c = s.toCharArray();
		java.util.Arrays.sort(c);
		return s = new String(c);
	}
	
}
