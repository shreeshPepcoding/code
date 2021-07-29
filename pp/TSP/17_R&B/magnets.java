import java.util.*;
public class magnets {

    public static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
    
    //====================================================
    // Approach 2
    //====================================================
    // magnets using slots as options and starting of cell as level
    // slots -> [+|-], [-|+], [X|X]
    public static class slots {
        char ch1;
        char ch2;

        public slots(char ch1, char ch2) {
            this.ch1 = ch1;
            this.ch2 = ch2;
        }
    }

    public static int signCountInRow(char[][] ans, int row, char sign) {
        int count = 0;
        for(int c = 0; c < ans[0].length; c++) {
            if(ans[row][c] == sign) {
                count++;
            }
        }
        return count;
    }

    public static int signCountInCol(char[][] ans, int col, char sign) {
        int count = 0;
        for(int r = 0; r < ans.length; r++) {
            if(ans[r][col] == sign) {
                count++;
            }
        }
        return count;
    }

    public static boolean isValid(char[][] ans, int[] top, int[] left, int[] right, 
        int[] bottom, int r, int c, char sign) {
            if(sign == 'X') return true;
        // make a check for valid polarity
        int[] xdir = {-1,0,0};
		int[] ydir = {0,1,-1};
        for(int d = 0; d < 3; d++) {
            int rr = r + xdir[d];
            int cc = c + ydir[d];
            if(rr >= 0 && rr < ans.length && cc >= 0 && cc < ans[0].length && ans[rr][cc] == sign) {
                return false;
            }
        }
        // make a check for valid sign count in row and col
        int cir = signCountInRow(ans, r, sign); // cir -> count in row
        int cic = signCountInCol(ans, c, sign); // cic -> count in column

        // top and left -> +ve sign, bottom ans right -> -ve sign
        if(sign == '+') {
            if((top[c] != -1 && cic >= top[c]) || (left[r] != -1 && cir >= left[r])) {
                return false;
            }
        } else {
            if((bottom[c] != -1 && cic >= bottom[c]) || (right[r] != -1 && cir >= right[r])) {
                return false;
            }
        }
        return true;
    }
        
    public static boolean isCorrectResult(char[][] ans, int[] top, int[] left, int[] bottom, int[] right) {
        // check for row
        for(int r = 0; r < ans.length; r++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for(int c = 0; c < ans[0].length; c++) {
                if(ans[r][c] == '+') pcount++;
                else if(ans[r][c] == '-') ncount++;
            }
            if(left[r] != -1 && left[r] != pcount) return false;
            if(right[r] != -1 && right[r] != ncount) return false;
        }
        // check for col
        for(int c = 0; c < ans[0].length; c++) {
            int pcount = 0; // positive count
            int ncount = 0; // negative count
            for(int r = 0; r < ans.length; r++) {
                if(ans[r][c] == '+') pcount++;
                else if(ans[r][c] == '-') ncount++;
            }
            if(top[c] != -1 && top[c] != pcount) return false;
            if(bottom[c] != -1 && bottom[c] != ncount) return false;
        }
        return true;
    }

    public static boolean solve(char[][] ans, char[][] arr, int[] top, int[] left, int[] bottom, int[] right,
        ArrayList<Integer> cells, int indx, slots[] options) {
        if(indx == cells.size()) {
            return isCorrectResult(ans, top, left, bottom, right);
        }

        int bno = cells.get(indx);
        int r = bno / arr[0].length;
        int c = bno % arr[0].length;

        for(slots s : options) {
            if(arr[r][c] == 'L') {
                if(isValid(ans, top, left, right, bottom, r, c, s.ch1) && isValid(ans, top, left, right, bottom, r, c + 1, s.ch2)) {
                    ans[r][c] = s.ch1;
                    ans[r][c + 1] = s.ch2;
                    if(solve(ans, arr, top, left, bottom, right, cells, indx + 1, options)) {
                        return true;
                    }
                    ans[r][c] = 'X';
                    ans[r][c + 1] = 'X';
                }
            } else {
                if(isValid(ans, top, left, right, bottom, r, c, s.ch1) && isValid(ans, top, left, right, bottom, r + 1, c, s.ch2)) {
                    ans[r][c] = s.ch1;
                    ans[r + 1][c] = s.ch2;
                    if(solve(ans, arr, top, left, bottom, right, cells, indx + 1, options)) {
                        return true;
                    }
                    ans[r][c] = 'X';
                    ans[r + 1][c] = 'X';
                }
            }
        }
        return false;
    }


    public static void solution(char[][] arr, int[] top, int[] left, int[] bottom, int[] right) {
        ArrayList<Integer> cells = new ArrayList<>();

        char[][] ans = new char[arr.length][arr[0].length];
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[0].length; j++) {
                if(arr[i][j] == 'L' || arr[i][j] == 'T') {
                    cells.add(i * arr[0].length + j);
                }
                ans[i][j] = 'X';
            }
        }

        slots[] options = new slots[3];
        options[0] = new slots('+', '-');
        options[1] = new slots('-', '+');
        options[2] = new slots('X', 'X');
        
        if(solve(ans, arr, top, left, bottom, right, cells, 0, options)) {
            print(ans);
		} else {
		    System.out.println("No Solution");
		}
    }

    public static void main(String[] args) {

    }
}
