import java.util.*;

public class pattern9 {
    
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        
        int n = scn.nextInt();

        int ospace = 0;
        int ispace = n - 2;

        for(int r = 0; r < n; r++) {
            // 1. print ospace
            for(int osp = 1; osp <= ospace; osp++) {
                System.out.print("\t");
            }
            // 2. print single star
            System.out.print("*\t");
            // 3. print inner space
            for(int isp = 1; isp <= ispace; isp++) {
                System.out.print("\t");
            }
            // 4. print single star*
            if(r != n / 2)
                System.out.print("*\t");
            // 5. hit enter
            System.out.println();
            // 6. count manage
            if(r < n / 2) {
                ospace++;
                ispace -= 2;
            } else {
                ospace--;
                ispace += 2;
            }
        }



        // for(int r = 0; r < n; r++) {
        //     for(int c = 0; c < n; c++) {
        //         if(r < n / 2 && r <= c && r + c <= n - 1) {
        //             // print star
        //             System.out.print("*\t");
        //         } else if(r == c || r + c == n -1) {
        //             System.out.print("*\t");
        //         }else {
        //             // print space
        //             System.out.print("\t");
        //         }
        //     }
        //     // hit enter
        //     System.out.println();
        // }
    }
}