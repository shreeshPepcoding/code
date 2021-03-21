import java.util.*;

public class arrDemo {
    
    public static Scanner scn = new Scanner(System.in);

    public static void printArray(int[] students) {
        // 1. without loop
        // System.out.println(students[0]);
        // System.out.println(students[1]);
        // System.out.println(students[2]);
        // System.out.println(students[3]);
        // System.out.println(students[4]);

        // 2. print this array using loop - variable is index
        for(int i = 0; i < students.length; i++) {
            System.out.println("loop : " + students[i]);
        }
    }

    public static void input(int[] arr1, int[] arr2) {
        // arr[0] = 95; // studentA
        // arr[1] = 93; // studentB
        // arr[2] = 90; // studentC
        // arr[3] = 85; // studentD
        // arr[4] = 91; // studentE

        for(int i = 0; i < arr1.length; i++) {
            arr1[i] = scn.nextInt();
        }
    }

    public static void main(String[] args) {
        int[] students;
        students = new int[5];
        

        int[] array;
        array = new int[10];
        input(students, array);
        printArray(students);

    }
}