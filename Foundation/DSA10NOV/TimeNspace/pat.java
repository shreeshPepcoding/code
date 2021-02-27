public class pat {

    public static void print(int n) {
        if(n == 0) return;
        
        print(n - 1);

        System.out.print(n + " -> ");
        for(int j = 1; j <= n; j++)
            System.out.print("* ");
        System.out.println();
    }

    public static void main(String[] args) {
        print(8);
    }
}
