import java.util.*;

public class UnionFind {
	
	/* data member - ArrayList containing all the records */	
	private ArrayList<City> records = new ArrayList<City>();
	
	/* Initalizes all sets, one for every element in list set */
	public void makeUnionFind(List<City> set) {
		for(City c : set)
			records.add(c);
	}
	
	public ArrayList<City> getRecords() {
		return records;
	}
	
	/* "Unionizes two sets */
	public void union(City c1, City c2) {
			City xroot = find(c1);
			City yroot = find(c2);
		
			if(xroot.getSize() > yroot.getSize()) {
				yroot.setParent(xroot);
				xroot.addToSize(yroot.getSize());
			}
			else {
				xroot.setParent(yroot);
				yroot.addToSize(xroot.getSize());
			}
	}
	
	/* Given a records returns the top-record that represents the set 
	 * containing that record. Re-links the given record to the top-record (Path compression,
	 * the key to gain the amortized running time).
	 **/
	public City find(City c) {
		if(c.getParent() == null)
			return c;
		else {
			c.setParent(find(c.getParent()));
			return c.getParent();
		
		}
	}
	
	/* Checks if to records are in the same set. */
	public boolean sameSet(City c1, City c2) {
		return find(c1).equals(find(c2));
	}

}
