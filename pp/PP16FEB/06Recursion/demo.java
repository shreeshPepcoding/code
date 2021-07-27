import java.util.*;

public class demo {
    public static Scanner scn = new Scanner(System.in);

    public static void demoAL() {
        // create
        ArrayList<Integer> list = new ArrayList<>();

        // add 
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);

        System.out.println(list + ", size : " + list.size());
        // Add At
        list.add(3, 35);
        System.out.println(list + ", size : " + list.size());


        // get At
        int val = list.get(3);
        System.out.println("After GET at 3rd index : " + val);
        // get Last
        int last = list.get(list.size() - 1);
        System.out.println("After GET at last index : " + last);

        // set At
        list.set(3, 39);
        System.out.println(list + ", size : " + list.size());

        // remove 
        int rmVal = list.remove(3);
        System.out.println("Removal value is : " + rmVal);

        // printing arrayList using loop -> normal loop
        for(int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            System.out.print(value + " ");
        }
        System.out.println();
        // for each loop
        for(int ele : list) {
            System.out.print(ele + " ");
        }
        System.out.println();


    }

    public static void demoString() {
        // declare , initialise
        String s = "Hello World";
        // input
        // String s1 = scn.next();
        // String s2 = scn.nextLine();
        // System.out.println(s2);

        // char At, set is not allowed in string of java
        char ch = s.charAt(4);
        System.out.println(ch);

        // ouput with loop
        for(int i = 0; i < s.length(); i++) {
            char chr = s.charAt(i);
            System.out.print(chr);
        }
        System.out.println();

        // Substring
        // String str = s.substring(6, 11);
        // System.out.println(str);

        // str = s.substring(6, 8);
        // System.out.println(str);

        // str = s.substring(6);
        // System.out.println(str);

        // cancatenation
        s = "Hello";
        s += ' ';
        s += 'W';
        s += 'o';
        System.out.println(s);
        s += "rld";
        System.out.println(s);

        System.out.println(s + 13 + 7);
        System.out.println(13 + 7 + s);
        System.out.println(13 + 7 + s + 10 + 2);
        System.out.println(13 + 7 + s + (10 + 2));

    }

    public static void printSubstring() {
        String str = "abcde";

        System.out.println(str);
        // string index of substring
        for(int i = 0; i < str.length(); i++) {
            // System.out.println("si = " + i);
            for(int j = i; j < str.length(); j++) {
                System.out.println("\t" + str.substring(i, j + 1));
            }
        }
    }

    public static void splitTest() {
        String str = "abc,def,ghi,jkl";
        String[] stArr = str.split(",");

        String delimiter = ",";
        String nstr = String.join(delimiter, stArr);

        // int[] arr = {10, 20, 30, 40, 50};

        // print
        // System.out.println(Arrays.toString(arr));

        // System.out.println(Arrays.toString(stArr));
        System.out.println(nstr);
    }

    public static  void ASCIIdemo() {
        // char ch = 'a';

        // System.out.println(ch);
        // System.out.println((int)ch);

        // ch = '0';
        // System.out.println(ch);
        // System.out.println((int)ch);

        // method 1 to covert char into int for indexing
        char ch_indx = '7';
        int indx = ch_indx - '0';
        System.out.println(indx);
        // method 2 to covert char into int for indexing
        
        ch_indx = '7';
        indx = ch_indx - 48;
        System.out.println(indx);
    }

    public static void main(String[] args) {
        // demoAL();
        // demoString();
        // printSubstring();
        // splitTest();
        ASCIIdemo();
    }
}