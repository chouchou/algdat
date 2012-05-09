
public class Point implements Comparable<Object> {
	double x;
	double y;
	
	public Point(double x, double y){
		this.x = x;
		this.y = y;
	}
	public void print(){
		System.out.println("Point with coord ("+ x+","+y+")");
	}
	
	public double distance(Point p){
		return Math.round(Math.hypot(p.x-x, p.y-y));
	}
	
	public boolean equals(Object o){
		if (o instanceof Point && x==((Point) o).x && y==((Point) o).y ){
			return true;
		}
		return false;
	}
	
	public int compareTo(Object o){
		Point point = (Point) o;
		if(x == point.x ){
			if(y == point.y ){
				return 0;
			}else if (y < point.y){
				return -1;
			}else {
				return 1;
			}
		}else if (x < point.x){
			return -1;
		}else {
			return 1;
		}
	}
}
