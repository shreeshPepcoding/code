import java.util.*;

public class Main {

    public static List<List<Integer>> res;
    
    public static void solve(int[] arr, int target, int li, List<Integer> list) {
        if(li == arr.length) {
            if(target == 0) {
                List<Integer> bres = new ArrayList<>();
                for(int val : list)
                    bres.add(val);
                
                res.add(bres);
            } 
            return;
        }
        
        if(target - arr[li] >= 0) {
            list.add(arr[li]);
            solve(arr, target - arr[li], li, list);
            list.remove(list.size() - 1);
        }
        solve(arr, target, li + 1, list);
    }
    
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        solve(candidates, target, 0, list);
        return res;
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