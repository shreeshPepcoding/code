import java.util.*;

class GenericTree implements Iterable<Integer> {
    private class Node {
        int data; 
        ArrayList<Node> children;

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }   

    private class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }
    
    private class GTPreorderIterator implements Iterator<Integer> {
        Integer itr_val;
        Stack<Pair> st;

        public GTPreorderIterator(Node root) {
            st = new Stack<>();
            st.push(new Pair(root, 0));
            next();
        }

        public boolean hasNext() {
            if(itr_val == null) return false;
            else return true;
        }

        public Integer next() {
            Integer val = itr_val;
            itr_val = null;
            while(st.size() > 0) {
                Pair top = st.peek();
                if(top.state == 0) {
                    itr_val = top.node.data;
                    top.state++;
                    break;
                } else if(top.state >= 1 && top.state <= top.node.children.size()) {
                    Node child = top.node.children.get(top.state - 1);
                    st.push(new Pair(child, 0));
                    top.state++;
                } else {
                    st.pop();
                }
            }
            return val;
        }
    }
    
    public Iterator<Integer> iterator() {
        Iterator<Integer> itr = new GTPreorderIterator(root);
        return itr;
    }

    private Node root = null;
    private int size = 0;

    private Node construct(int[] arr) {
        Stack<Node> st = new Stack<>();
        root = new Node(arr[0]);
        this.size = 1;
        st.push(root);
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == -1) {
                st.pop();
            } else {
                Node nn = new Node(arr[i]);
                this.size++;
                st.peek().children.add(nn);
                st.push(nn);
            }
        }
        return root;
    }

    public GenericTree(int[] arr) {
        this.root = construct(arr);
    }

    private void display(Node node) {
        System.out.print("[" + node.data + "] -> ");
        for(Node child : node.children) {
            System.out.print(child.data + ", ");
        }
        System.out.println(".");

        for(Node child : node.children) {
            display(child);
        }
    }

    public void display() {
        display(root);
    }

    public int size() {
        return this.size;
    }
}

public class gtree {

    public static void fun() {
        int[] arr = {10, 20, 50, -1, 60, -1, -1, 30, 70, -1, 80, 110,
             -1, 120, -1, -1, 90, -1, -1, 40, 100, -1, -1, -1};
        GenericTree gt = new GenericTree(arr);
        // gt.display();
        // System.out.println(gt.size());

        for(int val : gt) {
            System.out.print(val + " ");
        }

        System.out.println();
        
        Iterator<Integer> itr = gt.iterator();
        while(itr.hasNext()) {
            System.out.print(itr.next() + " ");
        }
    }

    public static void main(String[] args) {
        fun();
    }
}
