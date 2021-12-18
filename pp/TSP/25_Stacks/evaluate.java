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

    // leetcode 641, https://leetcode.com/problems/design-circular-deque/
    class MyCircularDeque {
        private class Node {
            int data;
            Node next;
        
            public Node(int data) {
                this.data = data;
                this.next = null;
            }
        }

        private Node head = null;
        private Node tail = null;
        private int size = 0;
        private int limit = 0;

        public MyCircularDeque(int k) {
            this.limit = k;
        }
        
        private void handleAddWhenSize0(int val) {
            Node nn = new Node(val);
            head = tail = nn;
            size = 1;
        }

        public boolean insertFront(int value) {
            if(this.size == limit) {
                return false;
            } else if(this.size == 0) {
                handleAddWhenSize0(value);
            } else {
                Node nn = new Node(value);
                nn.next = head;
                head = nn;
                this.size++;
            }
            return true;
        }
        
        public boolean insertLast(int value) {
            if(this.size == limit) {
                return false;
            } else if(this.size == 0) {
                handleAddWhenSize0(value);
            } else {
                Node nn = new Node(value);
                tail.next = nn;
                tail = nn;
                this.size++;
            }
            return true;
        }

        private void handleRemoveWhenSize1() {
            head = tail = null;
            this.size = 0;
        }

        public boolean deleteFront() {
            if(this.size == 0) {
                return false;
            } else if(this.size == 1) {
                handleRemoveWhenSize1();
            } else {
                this.head = this.head.next;
                this.size--;
            }
            return true;
        }
        
        public boolean deleteLast() {
            if(this.size == 0) {
                return false;
            } else if(this.size == 1) {
                handleRemoveWhenSize1();
            } else {
                Node nn = head;
                int indx = 0;
                while(indx < size - 1) {
                    nn = nn.next;
                    indx++;
                }
                nn.next = null;
                this.tail = nn;
                this.size--;
            }
            return true;
        }
        
        public int getFront() {
            if(this.size == 0) return -1;
            return head.data;
        }
        
        public int getRear() {
            if(this.size == 0) return -1;
            return tail.data;
        }
        
        public boolean isEmpty() {
            return this.size == 0;
        }
        
        public boolean isFull() {
            return this.size == this.limit;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String exp = br.readLine();
        int res = infixEval(exp);
        System.out.println(res);
    }
}