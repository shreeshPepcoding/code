import java.util.*;

public class test {
	public static Scanner scn = new Scanner(System.in);

	public static void main(String[] args) throws Exception {

		int n = scn.nextInt();
		int m = scn.nextInt();

		int sr = 1;
		int sc = 1;
		int dr = n;
		int dc = m;
		ArrayList<String> result = getMazePaths(sr, sc, dr, dc);
		System.out.println(result);
	}

	// sr - source row
	// sc - source column
	// dr - destination row
	// dc - destination column
	public static ArrayList<String> getMazePaths(int sr, int sc, int dr, int dc) {
		if (sr > dr || sc > dc) {
			System.out.println("Return because of Invalid index " + sr + " " + sc + " " + dr + " " + dc);
			ArrayList<String> temp = new ArrayList<>();
			return temp;
		}
		if (sr == dr && sc == dc) {
			System.out.println("Return because of destination " + sr + " " + sc + " " + dr + " " + dc);
			ArrayList<String> temp = new ArrayList<>();
			temp.add("");
			return temp;
		}

		ArrayList<String> mypath = new ArrayList<>();

		System.out.println(sr + " " + sc + " " + dr + " " + dc);

		for (int i = 1; sc + i <= dc; i++) {
			ArrayList<String> pathh = getMazePaths(sr, sc + i, dr, dc);
			for (String s : pathh) {
				mypath.add("h" + i + s);
			}
		}

		for (int j = 1; sr + j <= dr; j++) {
			ArrayList<String> pathv = getMazePaths(sr + j, sc, dr, dc);
			for (String s : pathv) {
				mypath.add("v" + j + s);
			}
		}

		for (int j = 1; sr + j <= dr && sc + j <= dc; j++) {
			ArrayList<String> pathd = getMazePaths(sr + j, sc + j, dr, dc);
			for (String s : pathd) {
				mypath.add("d" + j + s);
			}

		}

		return mypath;
	}

}