public class targetSum {

    // no backtracking ->because we generate all possibility
    public static void printTargetSumSubsets1(int[] arr, int idx, String set, int sum, int tar) {
        if (idx == arr.length) {
            if (tar == sum) {
                System.out.println(set + ".");
            }
            return;
        }

        // yes call
        printTargetSumSubsets1(arr, idx + 1, set + arr[idx] + ", ", sum + arr[idx], tar);
        // no call
        printTargetSumSubsets1(arr, idx + 1, set, sum, tar);
    }

    public static void printTargetSumSubsets(int[] arr, int idx, String set, int sum, int tar) {
        if (idx == arr.length) {
            if (tar == sum)
                System.out.println(set + ".");
            return;
        }

        // yes call
        if (sum + arr[idx] <= tar)
            printTargetSumSubsets(arr, idx + 1, set + arr[idx] + ", ", sum + arr[idx], tar);

        // no call
        printTargetSumSubsets(arr, idx + 1, set, sum, tar);
    }

    public static void main(String[] args) {
        int[] arr = { 10, 20, 30, 40, 50, 0, 0 };
        int tar = 60;
        printTargetSumSubsets1(arr, 0, "", 0, tar);

        System.out.println("================");
        printTargetSumSubsets(arr, 0, "", 0, tar);
    }
}
