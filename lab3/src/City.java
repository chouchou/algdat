public class City {
	private int size;
	private String name;
	private City parent = null;
	
	public City(String name) {
		this.name = name;
		size = 1;
	}
	public void setParent(City parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public int getSize() {
		return size;
	}
	public void addToSize(int add) {
		size += add;
	}
	public City getParent() {
		return parent;
	}
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof City  ) {
			City o = (City) obj; 
			return name.equals(o.getName());
		}
		return false;
	}
	
}



