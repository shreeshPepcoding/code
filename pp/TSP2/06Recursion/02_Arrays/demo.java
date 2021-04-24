public class demo {
    
    public static void fun(int i) {
        if(i == 10) return;
        fun(i++);
        System.out.println(i);
    }

    public static void main(String[] args) {
        int i = 0;
        // fun(0);
        int b = ++i;
        System.out.println(b + " " + i);
    }
}
