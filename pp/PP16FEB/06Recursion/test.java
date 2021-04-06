/* package codechef; // don't place package name! */

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
public class test {
    public static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws java.lang.Exception {
        int t = scn.nextInt();
        for (int j = 0; j < t; j++) {
            // System.out.println(j);
            int r = 1;
            int n = scn.nextInt();
            int k = scn.nextInt();
            scn.nextLine();
            String str = scn.nextLine();

            // print to check if input is working or not
            System.out.println(n + " " + k + " " + str);

            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '*') {

                    for (int o = i + 1; o < str.length(); o++) {
                        if (c == str.charAt(o)) {
                            r++;
                        }
                    }
                }
            }
            if (r == k) {
                // System.out.println("yes");
            } else {
                // System.out.println("no");
            }
        }
        // your code goes here

    }
}