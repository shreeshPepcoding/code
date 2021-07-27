import java.util.*;

public class evaluation {

    public static int priority(char op) {
        if(op == '*' || op == '/') {
            return 2;
        } else if(op == '+' || op == '-') {
            return 1;
        } else {
            return 0;
        }
    }

    public static int evaluate(int val1, int val2, char op) {
        if(op == '*') {
            return val1 * val2;
        } else if(op == '/') {
            return val1 / val2;
        } else if(op == '+') {
            return val1 + val2;
        } else if(op == '-') {
            return val1 - val2;
        } else {
            return 0;
        }
    }

    // infix
    public static int infixEvaluation(String exp) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<Integer> vstack = new Stack<>(); // value stack

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                vstack.push((int)(ch - '0'));
            } else if(ch == '(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();

                    int res = evaluate(val1, val2, op);
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                while(ostack.size() > 0 && ostack.peek() != '(' && 
                            priority(ostack.peek()) >= priority(ch)) {
                    // process
                    char op = ostack.pop();
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();

                    int res = evaluate(val1, val2, op);
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            int val2 = vstack.pop();
            int val1 = vstack.pop();

            int res = evaluate(val1, val2, op);
            vstack.push(res);
        }

        return vstack.peek();
    }

    public static void infixToPrefix(String exp) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // value stack

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == ' ') {
                continue;
            } else if(ch >= 'a' && ch <= 'z') {
                vstack.push("" + ch);
            } else if(ch == '(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = op + val1 + val2;
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                while(ostack.size() > 0 && ostack.peek() != '(' && 
                            priority(ostack.peek()) >= priority(ch)) {
                    // process
                    char op = ostack.pop();
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = op + val1 + val2;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            String val2 = vstack.pop();
            String val1 = vstack.pop();

            // int res = evaluate(val1, val2, op);
            String res = op + val1 + val2;
            vstack.push(res);
        }

        System.out.println(vstack.peek());
    }

    public static void infixToPostfix(String exp) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // value stack

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch == ' ') {
                continue;
            } else if(ch >= 'a' && ch <= 'z') {
                vstack.push("" + ch);
            } else if(ch == '(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = val1 + val2 + op;
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                while(ostack.size() > 0 && ostack.peek() != '(' && 
                            priority(ostack.peek()) >= priority(ch)) {
                    // process
                    char op = ostack.pop();
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = val1 + val2 + op;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            String val2 = vstack.pop();
            String val1 = vstack.pop();

            // int res = evaluate(val1, val2, op);
            String res = val1 + val2 + op;
            vstack.push(res);
        }

        System.out.println(vstack.peek());
    }

    // prefix
    public static void prefixEvaluation(String exp) {
        Stack<Integer> st = new Stack<>();
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9') {
                st.push((int)(ch - '0'));
            } else {
                int val1 = st.pop();
                int val2 = st.pop();

                int res = evaluate(val1, val2, ch);
                st.push(res);
            }
        }

        System.out.println(st.peek());
    }

    public static void prefixToPostfix(String exp) {
        Stack<String> st = new Stack<>();
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9') {
                st.push("" + ch);
            } else {
                String val1 = st.pop();
                String val2 = st.pop();

                // int res = evaluate(val1, val2, ch);
                String res = val1 + val2 + ch;
                st.push(res);
            }
        }

        System.out.println(st.peek());
    }

    public static void prefixToInfix(String exp) {
        Stack<String> st = new Stack<>();
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9') {
                st.push("" + ch);
            } else {
                String val1 = st.pop();
                String val2 = st.pop();

                // int res = evaluate(val1, val2, ch);
                String res = "(" + val1 + ch + val2 + ")";
                st.push(res);
            }
        }

        System.out.println(st.peek());
    }

    // postfix
    public static void postfixEvaluation(String exp) {
        Stack<Integer> st = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9') {
                st.push((int)(ch - '0'));
            } else {
                int val2 = st.pop();
                int val1 = st.pop();

                int res = evaluate(val1, val2, ch);
                st.push(res);
            }
        }
        System.out.println(st.peek());
    }

    public static void postfixToPrefix(String exp) {
        Stack<String> st = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9') {
                st.push("" + ch);
            } else {
                String val2 = st.pop();
                String val1 = st.pop();

                // int res = evaluate(val1, val2, ch);
                String res = ch + val1 + val2;
                st.push(res);
            }
        }
        System.out.println(st.peek());
    }

    public static void postfixToInfix(String exp) {
        Stack<String> st = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);

            if(ch >= '0' && ch <= '9') {
                st.push("" + ch);
            } else {
                String val2 = st.pop();
                String val1 = st.pop();

                // int res = evaluate(val1, val2, ch);
                String res = "(" + val1 + ch + val2 + ")";
                st.push(res);
            }
        }
        System.out.println(st.peek());   
    }

    public static void fun() {
        // String exp = "264*8/+3-";
        // String exp = "-+2/*6483";
        // ((2+((6*4)/8))-3)

        prefixEvaluation(exp);
        prefixToInfix(exp);
        prefixToPostfix(exp);

        // postfixEvaluation(exp);
        // postfixToInfix(exp);
        // postfixToPrefix(exp);


        // int res = infixEvaluation(exp);
        // System.out.println(res);

        // infixToPostfix(exp);
        // infixToPrefix(exp);
    }

    public static void main(String[] args) {
        fun();
    }
}
