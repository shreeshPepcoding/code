import java.io.*;
import java.util.*;

public class magnets {

    static int[] xdir = {-1, 0, 1, 0};
    static int[] ydir = {0, -1, 0, 1};

    private static int countVertical(char[][] ans, char sign, int col) {
        int count = 0;
        for(int r = 0; r < ans.length; r++) {
            if(ans[r][col] == sign) {
                count++;
            }
        }
        return count;
    }

    private static int countHorizontal(char[][] ans, char sign, int row) {
        int count = 0;
        for(int c = 0; c < ans[0].length; c++) {
            if(ans[row][c] == sign) {
                count++;
            }
        }
        return count;
    }

    private static boolean isSafeToPlace(char[][] ans, int[] top, int[] left, int[] right,
        int[] bottom, char sign, int row, int col) {
        // sign check
        for(int d = 0; d < 4; d++) {
            int r = row + xdir[d];
            int c = col + ydir[d];

            if(r >= 0 && r < ans.length && c >= 0 && c < ans[0].length && ans[r][c] == sign) {
                return false;
            }
        }
        // count check based on sign
        if(sign == '+') {
            // find count for top and left
            int topCount = countVertical(ans, sign, col);
            if(top[col] != -1 && topCount >= top[col]) {
                return false;
            }
            int leftCount = countHorizontal(ans, sign, row);
            if(left[row] != -1 && leftCount >= left[row]) {
                return false;
            }
        } else {
            // find count for right and bottom
            int bottomCount = countVertical(ans, sign, col);
            if(bottom[col] != -1 && bottomCount >= bottom[col]) {
                return false;
            }
            int rightCount = countHorizontal(ans, sign, row);
            if(right[row] != -1 && rightCount >= right[row]) {
                return false;
            }
        }
        return true;
    }

    private static boolean validAnswer(char[][] ans, int[] top, int[] left, int[] right, int[] bottom) {
        // check +ve, -ve counts in row
        for(int r = 0; r < ans.length; r++) {
            int lcount = countHorizontal(ans, '+', r);
            int rcount = countHorizontal(ans, '-', r);

            if((left[r] != -1 && lcount != left[r]) || (right[r] != -1 && rcount != right[r])) {
                return false;
            }
        }

        // check +ve, -ve count in cols
        for(int c = 0; c < ans[0].length; c++) {
            int tcount = countVertical(ans, '+', c);
            int bcount = countVertical(ans, '-', c);

            if((top[c] != -1 && tcount != top[c]) || (bottom[c] != -1 && bcount != bottom[c])) {
                return false;
            }
        }
        return true;
    }

	public static boolean solution(char[][] arr, int[] top, int[] left, 
        int[] right, int[] bottom, char[][] ans, int row, int col) {
        
        // row and col. management
        if(col == arr[0].length) {
            col = 0;
            row++;
        }

        // base case
        if(row == arr.length) {
            // check number of sign count for a valid configuration,
            // why needed, because of no call it is possible that we have less sign counts

            if(validAnswer(ans, top, left, right, bottom) == true) {
                return true;
            }
            return false;
        }

        // yes call
        if(arr[row][col] == 'L') {
            // Horizontal alignment
            // place as |+|-|
            if(isSafeToPlace(ans, top, left, right, bottom, '+', row, col) == true &&
                isSafeToPlace(ans, top, left, right, bottom, '-', row, col + 1) == true) {
                
                // place
                ans[row][col] = '+';
                ans[row][col + 1] = '-';
                if(solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }
            // place as |-|+|
            if(isSafeToPlace(ans, top, left, right, bottom, '-', row, col) == true &&
                isSafeToPlace(ans, top, left, right, bottom, '+', row, col + 1) == true) {
                
                // place
                ans[row][col] = '-';
                ans[row][col + 1] = '+';
                if(solution(arr, top, left, right, bottom, ans, row, col + 2) == true) {
                    return true;
                }
                // unplace
                ans[row][col] = 'X';
                ans[row][col + 1] = 'X';
            }

        } else if(arr[row][col] == 'T') {
            // vertical alignment
            // place as |+|-|
            if(isSafeToPlace(ans, top, left, right, bottom, '+', row, col) == true &&
                isSafeToPlace(ans, top, left, right, bottom, '-', row + 1, col) == true) {
                
                // place
                ans[row][col] = '+';
                ans[row + 1][col] = '-';
                if(solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
            // place as |-|+|
            if(isSafeToPlace(ans, top, left, right, bottom, '-', row, col) == true &&
                isSafeToPlace(ans, top, left, right, bottom, '+', row + 1 , col) == true) {
                
                // place
                ans[row][col] = '-';
                ans[row + 1][col] = '+';
                if(solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
                    return true;
                }
                // unplace
                ans[row][col] = 'X';
                ans[row + 1][col] = 'X';
            }
        }

        // no call
        if(solution(arr, top, left, right, bottom, ans, row, col + 1) == true) {
            return true;
        }
        return false;
	}

	public static void print(char[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int m = scn.nextInt();
		int n = scn.nextInt();
		char[][] arr = new char[m][n];
		for (int i = 0; i < arr.length; i++) {
			String str = scn.next();
			arr[i] = str.toCharArray();
		}
		int[] top = new int[n];
		for (int i = 0; i < n; i++) {
			top[i] = scn.nextInt();
		}
		int[] left = new int[m];
		for (int i = 0; i < m; i++) {
			left[i] = scn.nextInt();
		}
		int[] right = new int[m];
		for (int i = 0; i < m; i++) {
			right[i] = scn.nextInt();
		}
		int[] bottom = new int[n];
		for (int i = 0; i < n; i++) {
			bottom[i] = scn.nextInt();
		}

		char[][] ans = new char[arr.length][arr[0].length];
		for(int i = 0; i < ans.length; i++) {
		    for(int j = 0; j < ans[0].length; j++) {
		        ans[i][j] = 'X';
		    }
		}
		
		if(solution(arr, top, left, right, bottom, ans, 0, 0))
		    print(ans);
		else 
		    System.out.println("No Solution");
	}

	
}