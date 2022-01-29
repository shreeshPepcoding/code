public class literals {

    private static void fun() {
        byte b1 = 0b101;
        byte b2 = 0B101;
        System.out.println("---------Binary Literals in Byte--------");
        System.out.println("b1 = " + b1 + "\n" + "b2 = " + b2);
    
        int i1 = 0b1101101;
        int i2 = 0B1101101;
        System.out.println("---------Binary Literals in Integer--------");
        System.out.println("i1 = " + i1 + "\n" + "i2 = " + i2);

        int i3 = -0b1101101;
        int i4 = -0B1101101;
        System.out.println("---------Binary Literals in -ve Integer--------");
        System.out.println("i3 = " + i3 + "\n" + "i4 = " + i4);

        // underscore use
        int num1 = 0b10_1;
        int num2 = -0B11_1;
        System.out.println("---------Binary Literals(Underscore) in Integer--------");
        System.out.println("num1 = " + num1 + "\n" + "num2 = " + num2);

        int num = -0b1101101;
        
        System.out.println(num + " " +Integer.toBinaryString(num));
        num = (num >>> 2);
        System.out.println(num + " " + Integer.toBinaryString(num));

    }

    private static void fun2() {
        byte b1 = -0b1111111;
    }


    public static void main(String[] args) {
        fun();
    }
}
