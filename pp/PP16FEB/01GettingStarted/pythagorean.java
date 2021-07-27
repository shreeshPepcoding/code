import java.util.Scanner;

public class pythagorean {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int a = scn.nextInt();
        int b = scn.nextInt();
        int c = scn.nextInt();
        boolean ans = false;
        if(((a*a) + (b*b)) == c*c) {
            ans = true;
        } else if(((a*a) + (c*c)) == b*b) {
            ans = true;
        } else if(((c*c) + (b*b)) == a*a) {
            ans = true;
        } else {
            ans = false;
        }

        System.out.println(ans);
    }
}()
