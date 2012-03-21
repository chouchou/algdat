import java.util.LinkedList;
import java.util.Scanner;

public class StableMarriage {
	int n,w; // = 3;
	String[] mNames; // = { "Ross", "Chandler", "Joey" };
	String[] wNames; // = { "Monica", "Phoebe", "Rachel" };
	int[][] mPref; // = { { 5, 3, 1 }, { 1, 5, 3 }, { 5, 3, 1 } };
	int[][] wPref; // = { { 2, 4, 0 }, { 4, 0, 2 }, { 0, 4, 2 } };
	LinkedList<Integer> freeMen = new LinkedList<Integer>();
	int[] nextWoman; // = { 0, 0, 0 };
	int[] currentMan; // = { -1, -1, -1 };
	int[][] ranking;

	public static void main(String[] args) {
		StableMarriage sm = new StableMarriage();
		sm.parse();
		sm.solve();
		sm.print();
	}

	public void parse() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextLine()) {					
			String line = sc.nextLine();
			if (line.startsWith("n=")) {
				String[] s = line.split("n=");
				n = Integer.valueOf(s[1]);
				mNames = new String[n];
				wNames = new String[n];
				mPref = new int[n][n];
				wPref = new int[n][n];
				line = sc.nextLine();
				for (int i = 0; i < 2 * n; i++) {
					s = line.split(" ");
					if (i % 2 != 0) {
						wNames[i / 2] = s[1];
					} else {
						mNames[i / 2] = s[1];
					}
					line = sc.nextLine();
				}
				for(int i=0;i<2*n;i++){
					line = sc.nextLine();
					s = line.split(" ");
					if (i % 2 != 0) {
						for(int j=0;j<n;j++){
							wPref[i/2][j] = Integer.valueOf(s[j+1])-1;
						}
					} else {
						for(int k=0;k<n;k++){
							mPref[i/2][k] = Integer.valueOf(s[k+1])-1;
						}
					}
				}
				ranking = new int[n][n];
				currentMan = new int[n];
				nextWoman = new int[n];
				for (int i = 0; i < n; i++) {
					freeMen.add(new Integer(i));
					currentMan[i]=-1;
					nextWoman[i]=0;
					for (int j = 0; j < n; j++) {
						ranking[i][(wPref[i][j] / 2)] = j;
					}
				}
			}
		}
	}
		
	public void solve() {
		Integer m = freeMen.element(); 
		while (!freeMen.isEmpty() && nextWoman[m] < n) {
			w = mPref[m][nextWoman[m]] / 2; 
			if (currentMan[w] == -1) { 
				currentMan[w] = m;
				nextWoman[m]++;
				freeMen.remove(m);
				m = freeMen.peek();
			} else {
				if (ranking[w][m] < ranking[w][currentMan[w]]) {
					freeMen.addFirst(new Integer(currentMan[w]));
					currentMan[w] = m;
					nextWoman[m]++;
					freeMen.remove(m);
					m = freeMen.peek();
				} else {
					nextWoman[m]++;
				}
			}
		}
	}

	public void print() {
		int[] p = new int[n];
		for (int i = 0; i < n; i++) {
			p[currentMan[i]] = i;
		}
		for (int i = 0; i < n; i++) {
			System.out.println(mNames[i] + " -- " + wNames[p[i]]);
		}
	}
}