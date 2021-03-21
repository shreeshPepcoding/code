import java.util.*;

public class evaluate {
    
    // extra
    public static int priority(char op) {
        if(op == '/' || op == '*') {
            return 2;
        } else if(op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }
    
    public static int evaluate(int val1, int val2, char op) {
        if(op == '/') {
            return val1 / val2;
        } else if(op == '*') {
            return val1 * val2;
        } else if(op == '+') {
            return val1 + val2;
        } else if(op == '-') {
            return val1 - val2;
        } else {
            return 0;
        }
    }
    
    // Infix
    public static void infixEval(String str) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<Integer> vstack = new Stack<>(); // value stack
        
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                vstack.push((int)(ch - '0'));
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();
                    char op = ostack.pop();

                    int res = evaluate(val1, val2, op);
                    vstack.push(res);
                }
                ostack.pop();
            } else if(ch == '(') {
                ostack.push(ch);
            } else {
                while(ostack.size() > 0 && ostack.peek() != '(' &&
                    priority(ostack.peek()) >= priority(ch)) {
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();
                    char op = ostack.pop();

                    int res = evaluate(val1, val2, op);
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }
        System.out.println(ostack + "\n" + vstack);
        while(ostack.size() > 0) {
            int val2 = vstack.pop();
            int val1 = vstack.pop();
            char op = ostack.pop();

            int res = evaluate(val1, val2, op);
            vstack.push(res);
        }
        System.out.println(vstack.peek());
    }
    
    public static void infixToPre(String str) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // value stack
        
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                vstack.push("" + ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    vstack.push("" + op + val1 + val2);
                }
                ostack.pop();
            } else if(ch == '(') {
                ostack.push(ch);
            } else {
                while(ostack.size() > 0 && ostack.peek() != '(' &&
                    priority(ostack.peek()) >= priority(ch)) {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    // vstack.push(res);
                    vstack.push("" + op + val1 + val2);
                }
                ostack.push(ch);
            }
        }
        System.out.println(ostack + "\n" + vstack);
        while(ostack.size() > 0) {
            String val2 = vstack.pop();
            String val1 = vstack.pop();
            char op = ostack.pop();

            // int res = evaluate(val1, val2, op);
            // vstack.push(res);
            vstack.push("" + op + val1 + val2);
        }
        System.out.println(vstack.peek());
    }
    
    public static void infixToPost(String str) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // value stack
        
        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                vstack.push("" + ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    vstack.push("" + val1 + val2 + op);
                }
                ostack.pop();
            } else if(ch == '(') {
                ostack.push(ch);
            } else {
                while(ostack.size() > 0 && ostack.peek() != '(' &&
                    priority(ostack.peek()) >= priority(ch)) {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    // vstack.push(res);
                    vstack.push("" + val1 + val2 + op);
                }
                ostack.push(ch);
            }
        }
        System.out.println(ostack + "\n" + vstack);
        while(ostack.size() > 0) {
            String val2 = vstack.pop();
            String val1 = vstack.pop();
            char op = ostack.pop();

            // int res = evaluate(val1, val2, op);
            // vstack.push(res);
            vstack.push("" + val1 + val2 + op);
        }
        System.out.println(vstack.peek());
    }
    
    // prefix
    public static void prefixEval(String str) {
        Stack<Integer> st = new Stack<>();

        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // number
                st.push((int)(ch-'0'));
            } else {
                // operator
                int val1 = st.pop();
                int val2 = st.pop();

                int res = evaluate(val1, val2, ch);
                st.push(res);
            }
        }
        System.out.println(st.peek());
    }
    
    public static void prefixToPost(String str) {
        Stack<String> st = new Stack<>();

        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // number
                st.push("" + ch);
            } else {
                // operator
                String val1 = st.pop();
                String val2 = st.pop();

                // int res = evaluate(val1, val2, ch);
                st.push(val1 + val2 + ch);
            }
        }
        System.out.println(st.peek());
    }
    
    public static void prefixToIn(String str) {
        Stack<String> st = new Stack<>();

        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // number
                st.push("" + ch);
            } else {
                // operator
                String val1 = st.pop();
                String val2 = st.pop();

                // int res = evaluate(val1, val2, ch);
                st.push("(" + val1 + ch + val2 + ")");
            }
        }
        System.out.println(st.peek());
    }
    
    // postfix
    public static void postfixEval(String str) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // number
                st.push((int)(ch - '0'));
            } else {
                // operator
                int val2 = st.pop();
                int val1 = st.pop();

                int res = evaluate(val1, val2, ch);
                st.push(res);
            }
        }
        System.out.println(st.peek());
    }
    
    public static void postfixToIn(String str) {
        Stack<String> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // number
                st.push("" + ch);
            } else {
                // operator
                String val2 = st.pop();
                String val1 = st.pop();

                // int res = evaluate(val1, val2, ch);
                st.push("(" + val1 + ch + val2 + ")");
            }
        }
        System.out.println(st.peek());
    }
    
    public static void postfixToPre(String str) {
        Stack<String> st = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch >= '0' && ch <= '9') {
                // number
                st.push("" + ch);
            } else {
                // operator
                String val2 = st.pop();
                String val1 = st.pop();

                // int res = evaluate(val1, val2, ch);
                st.push("" + ch + val1 + val2);
            }
        }
        System.out.println(st.peek());
    }
    
    public static void main(String[] args) {
        // String str = "2+6*4/8-3";
        // infixEval(str);
        // infixToPre(str);
        // infixToPost(str);

        // String str = "264*8/+3-";
        // postfixEval(str);
        // postfixToIn(str);
        // postfixToPre(str);

        String str = "-+2/*6483";
        prefixEval(str);
        prefixToIn(str);
        prefixToPost(str);
    }
}