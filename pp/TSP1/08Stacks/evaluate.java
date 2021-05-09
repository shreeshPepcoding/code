import java.util.*;

public class evaluate {
    // helper
    public static int priority(char op) {
        if(op == '/' || op == '*') return 2;
        else if(op == '+' || op == '-') return 1;
        else return 0;
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
    public static int infixEvaluation(String str) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<Integer> vstack = new Stack<>(); // value stack

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                vstack.push((int)(ch - '0'));
            } else if(ch == '(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();
                    char op = ostack.pop();

                    int res = evaluate(val1, val2, op);
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // solve equal priority now, otherwise after loop we iterate in it with right to left direction
                // because of behaviuour of stack
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    int val2 = vstack.pop();
                    int val1 = vstack.pop();
                    char op = ostack.pop();

                    int res = evaluate(val1, val2, op);
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        // some element may be present in stack
        while(ostack.size() > 0) {
            int val2 = vstack.pop();
            int val1 = vstack.pop();
            char op = ostack.pop();

            int res = evaluate(val1, val2, op);
            vstack.push(res);
        }

        return vstack.pop();
    }

    public static void infixToPrefix(String str) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // value stack

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= 'a' && ch <= 'z') {
                vstack.push("" + ch);
            } else if(ch == '(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = op + val1 + val2;
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // solve equal priority now, otherwise after loop we iterate in it with right to left direction
                // because of behaviuour of stack
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = op + val1 + val2;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        // some element may be present in stack
        while(ostack.size() > 0) {
            String val2 = vstack.pop();
            String val1 = vstack.pop();
            char op = ostack.pop();

            // int res = evaluate(val1, val2, op);
            String res = op + val1 + val2;
            vstack.push(res);
        }
        System.out.println(vstack.pop());
        // return vstack.pop();
    }

    public static void infixToPostfix(String str) {
        Stack<Character> ostack = new Stack<>(); // operator stack
        Stack<String> vstack = new Stack<>(); // value stack

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= 'a' && ch <= 'z') {
                vstack.push("" + ch);
            } else if(ch == '(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = val1 + val2 + op;
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // solve equal priority now, otherwise after loop we iterate in it with right to left direction
                // because of behaviuour of stack
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    String val2 = vstack.pop();
                    String val1 = vstack.pop();
                    char op = ostack.pop();

                    // int res = evaluate(val1, val2, op);
                    String res = val1 + val2 + op;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        // some element may be present in stack
        while(ostack.size() > 0) {
            String val2 = vstack.pop();
            String val1 = vstack.pop();
            char op = ostack.pop();

            // int res = evaluate(val1, val2, op);
            String res = val1 + val2 + op;
            vstack.push(res);
        }
        System.out.println(vstack.pop());
        // return vstack.pop();
    }

    // // postfix
    public static void postfixEvaluation(String str) {
        Stack<Integer> vstack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch >= '0' && ch <= '9') {
                vstack.push(ch - '0');
            } else {
                int val2 = vstack.pop();
                int val1 = vstack.pop();

                int res = evaluate(val1, val2, ch);
                vstack.push(res);
            }
        }
        System.out.println(vstack.pop());
    }

    public static void postfixToPrefix(String str) {
        Stack<String> vstack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch >= '0' && ch <= '9') {
                vstack.push("" + ch);
            } else {
                String val2 = vstack.pop();
                String val1 = vstack.pop();

                // int res = evaluate(val1, val2, ch);
                String res = ch + val1 + val2;
                vstack.push(res);
            }
        }
        System.out.println(vstack.pop());
    }

    public static void postixToinfix(String str) {
        Stack<String> vstack = new Stack<>();

        for(int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if(ch >= '0' && ch <= '9') {
                vstack.push("" + ch);
            } else {
                String val2 = vstack.pop();
                String val1 = vstack.pop();

                // int res = evaluate(val1, val2, ch);
                String res = "(" + val1 + ch + val2 + ")";
                vstack.push(res);
            }
        }
        System.out.println(vstack.pop());
    }

    // prefix
    public static int prefixEvaluation(String str) {
        Stack<Integer> vstack = new Stack<>();

        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);

            if(ch >= '0' && ch <= '9') {
                vstack.push(ch - '0');
            } else {
                int val1 = vstack.pop();
                int val2 = vstack.pop();

                int res = evaluate(val1, val2, ch);
                vstack.push(res);
            }
        }
        System.out.println(vstack.pop());
    }

    public static void prefixToInfix(String str) {
        Stack<String> vstack = new Stack<>();

        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);

            if(ch >= '0' && ch <= '9') {
                vstack.push("" + ch);
            } else {
                String val1 = vstack.pop();
                String val2 = vstack.pop();

                // int res = evaluate(val1, val2, ch);
                String res = "(" + val1 + ch + val2 + ")";
                vstack.push(res);
            }
        }
        System.out.println(vstack.pop());
    }

    public static void prefixToPostfix(String str) {
        Stack<String> vstack = new Stack<>();

        for(int i = str.length() - 1; i >= 0; i--) {
            char ch = str.charAt(i);

            if(ch >= '0' && ch <= '9') {
                vstack.push("" + ch);
            } else {
                String val1 = vstack.pop();
                String val2 = vstack.pop();

                // int res = evaluate(val1, val2, ch);
                String res = val1 + val2 + ch;
                vstack.push(res);
            }
        }
        System.out.println(vstack.pop());
    }
    
    public static void evaluation() {
        String str = "2 + 6 * 4 / 8 - 3";

        // int res = infixEvaluation(str);
        infixToPostfix(str);
        // System.out.println(res);

    }
    
    public static void main(String[] args) {
        evaluation();    
    }
}
