public class stringDemo {
    public static void main(String[] args) {
        String str = "Welcome";

        // printing of string
        System.out.println(str);
        // for(int i = 0; i < str.length(); i++) {
        //     char ch = str.charAt(i);
        //     System.out.println(ch);
        // }

        // for each loop -> not working for String
        // for(char ch : str) {
        //     System.out.println(ch);
        // }

        // str += " in JAVA";
        // System.out.println(str);

        // substring
        String ss = str.substring(7, 7);
        System.out.println(ss);
    }
}
