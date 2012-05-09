import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;



public class ClosestPair {
	ArrayList<Point> points = new ArrayList<Point>();

	String name;
	
	public static void main(String[] args) throws IOException{
		ClosestPair cp = new ClosestPair();
		cp.name=args[0];
		cp.parse(cp.name);
		cp.find();
	}
	public void parse(String filename) throws IOException{
		Scanner scanner = null;
		try {
			scanner = new Scanner(new FileReader(filename));
			String line;
			while(scanner.hasNextLine()){
				line = scanner.nextLine();
				if(line.contains("NODE_COORD")){
					break;
				}
			}
			while (scanner.hasNextLine()){
				line = scanner.nextLine();
				line.trim();	
				if(line.contains("EOF")){
					break;
				}
				String[] s = line.split(" ");
				double x = Double.parseDouble(s[1]);
				double y = Double.parseDouble(s[2]);
				points.add(new Point(x,y));
			}		
		}finally {
			scanner.close();
		}
	}
	
	public void find(){
		int pNumber = points.size();
		double dist = closest(points, points.size());
		Point tmp = points.get(points.size()/2);
		for(int i=0;i<points.size();i++){
			if(points.get(i).x > (tmp.x+dist)|| points.get(i).x <(tmp.x-dist)){
				points.remove(i);
			}
		}
		Collections.sort(points);
		for(int i=0;i<points.size();i++){
			Point tmpPoint = points.get(i);
			int n = points.size()-i;
			if (n>11) n=11;
			for(int j=1;j<n;j++){
				double tmpDist = tmpPoint.distance(points.get(i+j));
				if(tmpDist<dist){
					dist = tmpDist;
				}
			}
		}
		System.out.println(pNumber+" "+dist);
	}
	
	public double closest(ArrayList<Point> rec, int n){
		ArrayList<Point> left = new ArrayList<Point>();
		ArrayList<Point> right = new ArrayList<Point>();
		Collections.sort(rec);
		if (n < 3){
			Point p1 = rec.get(0);
			Point p2 = rec.get(1);
			return p1.distance(p2);
		}else if(n == 3){
			Point p1 = rec.get(0);
			Point p2 = rec.get(1);
			Point p3 = rec.get(2);
			return Math.min(Math.min(p1.distance(p2), p1.distance(p3)), p1.distance(p3)); 
		}else{
			left = new ArrayList<Point>();
			right = new ArrayList<Point>();
			for(int i=0;i<n/2;i++){
				left.add(rec.get(i));
			}
			for(int i=n/2; i<n;i++){
				right.add(rec.get(i));
			}
			double minL = closest(left, n/2);
			double minR = closest(right, n/2);
			return Math.min(minL, minR);
		}
	}
}