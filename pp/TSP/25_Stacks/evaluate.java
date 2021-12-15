import java.io.*;
import java.util.*;

public class evaluate{

    private static int evaluate(int val1, int val2, char op) {
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

    private static int priority(char op) {
        if(op == '/' || op =='*') {
            return 2; 
        } else if(op == '+' || op =='-') {
            return 1;
        } else {
            return 0;
        }
    }
    
    // infix evaluation
    private static int infixEval(String exp) {
        Stack<Integer> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); // operator stack
  
        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= '0' && ch <= '9') {
                vstack.push((int)(ch - '0'));
            } else if(ch =='(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    int v2 = vstack.pop();
                    int v1 = vstack.pop();

                    // res = val1 operator val2
                    int res = evaluate(v1, v2, op);
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // ch = operators
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    char op = ostack.pop();
                    int v2 = vstack.pop();
                    int v1 = vstack.pop();

                    // res = val1 operator val2
                    int res = evaluate(v1, v2, op);
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            int v2 = vstack.pop();
            int v1 = vstack.pop();

            // res = val1 operator val2
            int res = evaluate(v1, v2, op);
            vstack.push(res);
        }
        return vstack.peek();
    }

    // infix to prefix
    private static String infixToPrefix(String exp) {
        Stack<String> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); // operator stack
  
        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= 'a' && ch <= 'z') {
                vstack.push(ch + "");
            } else if(ch =='(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();

                    // res = val1 operator val2
                    // int res = evaluate(v1, v2, op);
                    String res = op + v1 + v2;
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // ch = operators
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();

                    // res = val1 operator val2
                    // int res = evaluate(v1, v2, op);
                    String res = op + v1 + v2;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            String v2 = vstack.pop();
            String v1 = vstack.pop();

            // res = val1 operator val2
            // int res = evaluate(v1, v2, op);
            String res = op + v1 + v2;
            vstack.push(res);
        }
        return vstack.peek();
    }

    // infix to postfix
    private static String infixToPostfix(String exp) {
        Stack<String> vstack = new Stack<>(); // value stack
        Stack<Character> ostack = new Stack<>(); // operator stack
  
        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch == ' ') {
                continue;
            } else if(ch >= 'a' && ch <= 'z') {
                vstack.push(ch + "");
            } else if(ch =='(') {
                ostack.push(ch);
            } else if(ch == ')') {
                while(ostack.peek() != '(') {
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();

                    // res = val1 operator val2
                    // int res = evaluate(v1, v2, op);
                    String res = v1 + v2 + op;
                    vstack.push(res);
                }
                ostack.pop(); // this pop is for opening bracket
            } else {
                // ch = operators
                while(ostack.size() > 0 && ostack.peek() != '(' && priority(ostack.peek()) >= priority(ch)) {
                    char op = ostack.pop();
                    String v2 = vstack.pop();
                    String v1 = vstack.pop();

                    // res = val1 operator val2
                    // int res = evaluate(v1, v2, op);
                    String res = v1 + v2 + op;
                    vstack.push(res);
                }
                ostack.push(ch);
            }
        }

        while(ostack.size() > 0) {
            char op = ostack.pop();
            String v2 = vstack.pop();
            String v1 = vstack.pop();

            // res = val1 operator val2
            // int res = evaluate(v1, v2, op);
            String res = v1 + v2 + op;
            vstack.push(res);
        }
        return vstack.peek();
    }
    
    // postfix evaluation
    private static int postfixEvaluate(String exp) {
        Stack<Integer> vstack = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch >= '1' && ch <= '9') {
                vstack.push((int)(ch - '0'));
            } else {
                // ch == operator
                int v2 = vstack.pop();
                int v1 = vstack.pop();
                int res = evaluate(v1, v2, ch);
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // postfix to infix
    private static String postfixToInfix(String exp) {
        Stack<String> vstack = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch >= '1' && ch <= '9') {
                vstack.push(ch + "");
            } else {
                // ch == operator
                String v2 = vstack.pop();
                String v1 = vstack.pop();
                // int res = evaluate(v1, v2, ch);
                String res = "(" + v1 + ch + v2 + ")";
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // postfix to prefix
    private static String postfixToPrefix(String exp) {
        Stack<String> vstack = new Stack<>();

        for(int i = 0; i < exp.length(); i++) {
            char ch = exp.charAt(i);
            if(ch >= '1' && ch <= '9') {
                vstack.push(ch + "");
            } else {
                // ch == operator
                String v2 = vstack.pop();
                String v1 = vstack.pop();
                // int res = evaluate(v1, v2, ch);
                String res = ch + v1 + v2;
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // prefix
    private static int prefixEvaluation(String exp) {
        Stack<Integer> vstack = new Stack<>();
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);
            if(ch >= '0' && ch <= '9') {
                vstack.push((int)(ch - '0'));
            } else {
                int val1 = vstack.pop();
                int val2 = vstack.pop();
                int res = evaluate(val1, val2, ch);
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    private static String prefixPostfix(String exp) {
        Stack<String> vstack = new Stack<>();
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);
            if(ch >= '0' && ch <= '9') {
                vstack.push(ch + "");
            } else {
                String val1 = vstack.pop();
                String val2 = vstack.pop();
                // int res = evaluate(val1, val2, ch);
                String res = val1 + val2 + ch;
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    private static String prefixInfix(String exp) {
        Stack<String> vstack = new Stack<>();
        for(int i = exp.length() - 1; i >= 0; i--) {
            char ch = exp.charAt(i);
            if(ch >= '0' && ch <= '9') {
                vstack.push(ch + "");
            } else {
                String val1 = vstack.pop();
                String val2 = vstack.pop();
                // int res = evaluate(val1, val2, ch);
                String res = "(" + val1 + ch + val2 + ")";
                vstack.push(res);
            }
        }
        return vstack.pop();
    }

    // leetcode 636, https://leetcode.com/problems/exclusive-time-of-functions/
    private class EThelper {
        int id;
        int stime;
        int cet; // child execution time

        public EThelper(int id, int stime, int cet) {
            this.id = id;
            this.stime = stime;
            this.cet = cet;
        }
    }
    public int[] exclusiveTime(int n, List<String> logs) {
        // n-> number of IDs
        int[] res = new int[n];
        Stack<EThelper> st = new Stack<>();
        
        for(String str : logs) {
            String[] info = str.split(":");
            int id = Integer.parseInt(info[0]);
            String status = info[1];
            int timeStamp = Integer.parseInt(info[2]);

            if(status.equals("start")) {
                st.push(new EThelper(id, timeStamp, 0));
            } else {
                int fn_diff = timeStamp - st.peek().stime + 1; // function difference time
                int etime = fn_diff - st.peek().cet;  // child execution time

                res[id] += etime;
                st.pop();
                if(st.size() > 0) {
                    st.peek().cet += fn_diff;
                }
            }
        }
        return res;
    }

    // leetcode 456, https://leetcode.com/problems/132-pattern/
    public boolean find132pattern(int[] nums) {
        
    }

    // leetcode 735, https://leetcode.com/problems/asteroid-collision/
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> st = new Stack<>();
        for(int val : asteroids) {
            if(val > 0) {
                st.push(val);
                continue;
            }
            // val is -ve
            // peek is positive but smaller in temrs of size then pop untile this condition will not break
            while(st.size() > 0 && st.peek() < -val && st.peek() > 0) {
                st.pop();
            }
            if(st.size() > 0 && st.peek() == -val) {
                st.pop(); // equal in size but opposite in direction
            } else if(st.size() == 0 || st.peek() < 0) {
                st.push(val);
            } else {
                // nothing to do
            }
        }
        int[] res = new int[st.size()];
        for(int i = res.length - 1; i >= 0; i--) {
            res[i] = st.pop();
        }
        return res;
    }

    // leetcode 402, https://leetcode.com/problems/remove-k-digits/
    public String removeKdigits(String num, int k) {
        ArrayList<Character> 
    }

    // leetcode 316, https://leetcode.com/problems/remove-duplicate-letters/
    public String removeDuplicateLetters(String s) {
        
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        int res = infixEval(exp);
        System.out.println(res);
    }
}