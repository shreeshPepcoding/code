public class cryptoArithmatic {

    public static String getUnique(String str) {
        boolean[] availability = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            availability[ch - 'A'] = true;
        }

        String uStr = "";
        for (int i = 0; i < availability.length; i++) {
            if (availability[i] == true) {
                char ch = (char) (i + 'A');
                uStr += ch;
            }
        }
        return uStr;
    }

    static String str1 = "SEND";
    static String str2 = "MORE";
    static String str3 = "MONEY";

    static char ch1 = 'S';
    static char ch2 = 'M';
    static char ch3 = 'M';

    public static int getNumber(String str, int[] map) {
        int num = 0;
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            num = (num * 10) + map[ch - 'A'];
        }
        return num;
    }

    public static void printmap(String str, int[] map) {
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            System.out.println(ch + " -> " + map[ch - 'A']);
        }
    }

    public static int crypto(String str, int indx, boolean[] digitUsed, int[] map) {
        if (indx == str.length()) {
            if(map[ch1 - 'A'] == 0 || map[ch2 - 'A'] == 0 || map[ch3 - 'A'] == 0) {
                return 0;
            }

            int first = getNumber(str1, map);
            int second = getNumber(str2, map);
            int third = getNumber(str3, map);

            if(first + second == third) {
                printmap(str, map);
                System.out.println("\t" + first);
                System.out.println("+\t" + second);
                System.out.println("---------------");
                System.out.println("\t" + third);
                System.out.println("---------------");
                return 1;
            }
            return 0;
        }
        
        int count = 0;
        char charLevel = str.charAt(indx);

        for (int d = 0; d < 10; d++) {
            if (digitUsed[d] == false) {
                digitUsed[d] = true;
                map[charLevel - 'A'] = d;
                count += crypto(str, indx + 1, digitUsed, map);
                map[charLevel - 'A'] = 0;
                digitUsed[d] = false;
            }
        }

        return count;
    }

    public static void solve() {

        String uniqueString = getUnique(str1 + str2 + str3);
        int[] map = new int[26];
        boolean[] digitUsed = new boolean[10];

        System.out.println(crypto(uniqueString, 0, digitUsed, map));
    }

    public static void main(String[] args) {
        solve();
    }
}
