import java.util.*;

public class Main {
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int target = scn.nextInt();
        List<List<Integer>> res = combinationSum(arr, target);
        System.out.println(res);
    }
}