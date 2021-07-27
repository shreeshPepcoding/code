import java.util.*;

public class bst {

    public static class Node {
        int data;
        Node left;
        Node right;

        public Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }

        public Node(int data, Node left, Node right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public static Node construct(int[] arr, int lo, int hi) {
        if (lo > hi)
            return null;

        int mid = lo + (hi - lo) / 2;

        Node nn = new Node(arr[mid]);

        nn.left = construct(arr, lo, mid - 1);
        nn.right = construct(arr, mid + 1, hi);

        return nn;
    }

    public static void display(Node root) {
        if (root == null)
            return;

        String str = root.left == null ? " ." : "" + root.left.data;
        str += " <- [" + root.data + "] -> ";
        str += root.right == null ? ". " : root.right.data;
        System.out.println(str);

        display(root.left);
        display(root.right);
    }

    public static int size(Node node) {
        if(node == null) return 0;

        int lsize = size(node.left);
        int rsize = size(node.right);
        return lsize + rsize + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;

        int lsum = sum(node.left);
        int rsum = sum(node.right);
        return lsum + rsum + node.data;
    }

    public static int max(Node node) {
        if(node == null) {
            return Integer.MIN_VALUE;
        } else if(node.right == null) {
            return node.data;
        } else {
            return max(node.right);
        }
    }

    public static int min(Node node) {
        if(node == null) {
            return Integer.MAX_VALUE;
        } else if(node.left == null) {
            return node.data;
        } else {
            return min(node.left);
        }
    }

    public static boolean find(Node node, int data) {
        if(node == null) return false;

        if(data > node.data) {
            return find(node.right, data);
        } else if(data < node.data) {
            return find(node.left, data);
        } else {
            // data found
            return true;
        }
    }

    public static Node add(Node node, int data) {
        if(node == null) {
            Node nn = new Node(data, null, null);
            return nn;
        }

        if(data > node.data) {
            node.right = add(node.right, data);
        } else if(data < node.data) {
            node.left = add(node.left, data);
        } else {

        }
        return node;
    }

    static int sum = 0;
    public static void rwsol(Node node){
        if(node == null) return;
        // right
        rwsol(node.right);
        // inorder is area of work
        int data = node.data;
        node.data = sum;
        sum += data;
        // left
        rwsol(node.left);
    }

    public static int lca(Node node, int d1, int d2) {
        if(d1 > node.data && d2 > node.data) { // right side
            return lca(node.right, d1, d2);
        } else if(d1 < node.data && d2 < node.data) { // left side
            return lca(node.left, d1, d2);
        } else { // answer
            return node.data;
        }
    }

    public static void pir(Node node, int d1, int d2) {
        if(node == null) return;

        if(d1 > node.data && d2 > node.data) { // right side
            pir(node.right, d1, d2);
        } else if(d1 < node.data && d2 < node.data) { // left side
            pir(node.left, d1, d2);
        } else { // answer
            pir(node.left, d1, d2);
            System.out.println(node.data);
            pir(node.right, d1, d2);
        }
    }

    // method 1, time : O(nh), space : O(h), h-> height
    public static void printTargetSumPair1(Node node, Node root, int target) {
        if(node == null) return;

        int n1 = node.data;
        int n2 = target - n1;

        printTargetSumPair1(node.left, root, target);
        // inorder
        if(n1 < n2 && find(root, n2) == true) {
            System.out.println(n1 + " " + n2);
        }
        printTargetSumPair1(node.right, root, target);
    }
    
    // method 2, time : O(n), space : O(n), h-> height
    public static void inorderFiller(Node node, ArrayList<Integer> list) {
        if(node == null) return;

        inorderFiller(node.left, list);
        list.add(node.data);
        inorderFiller(node.right, list);
    }

    public static void printTargetSumPair2(Node node, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        inorderFiller(node, list);

        int left = 0;
        int right = list.size() - 1;

        while(left < right) {
            int sum = list.get(left) + list.get(right);
            if(sum > target) {
                right--;
            } else if(sum < target) {
                left++;
            } else {
                System.out.println(list.get(left) + " " + list.get(right));
                left++;
                right--;
            }
        }
    }
    
    // method 3, time : O(n), space : O(h), h-> height
    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }
    
    public static int inorderItr(Stack<Pair> st) {
        while(st.size() > 0) {
            Pair p = st.peek();

            if(p.state == 0) {
                // left child
                if(p.node.left != null) {
                    st.push(new Pair(p.node.left, 0));
                }
                p.state++;
            } else if(p.state == 1) {
                // right child
                if(p.node.right != null) {
                    st.push(new Pair(p.node.right, 0));
                }
                p.state++;
                return p.node.data;
            } else {
                // pop
                st.pop();
            }
        }
        return -1;
    }

    public static int revInorderItr(Stack<Pair> st) {
        while(st.size() > 0) {
            Pair p = st.peek();

            if(p.state == 0) {
                // right child
                if(p.node.right != null) {
                    st.push(new Pair(p.node.right, 0));
                }
                p.state++;
            } else if(p.state == 1) {
                // left child
                if(p.node.left != null) {
                    st.push(new Pair(p.node.left, 0));
                }
                p.state++;
                return p.node.data;
            } else {
                // pop
                st.pop();
            }
        }
        return -1;
    }


    public static void printTargetSumPair3(Node node, int target) {
        Stack<Pair> ls = new Stack<>();
        Stack<Pair> rs = new Stack<>();

        ls.push(new Pair(node, 0));
        rs.push(new Pair(node, 0));


        int left = inorderItr(ls);
        int right = revInorderItr(rs);

        while(left < right) {
            int sum = left + right;
            if(sum > target) {
                right = revInorderItr(rs);
            } else if(sum < target) {
                left = inorderItr(ls);
            } else {
                System.out.println(left + " " + right);
                left = inorderItr(ls);
                right = revInorderItr(rs);
            }
        }
    }


    public static void fun() {
        int[] data = { 10, 20, 30, 40, 50, 60, 70, 80, 90 };
        Node root = construct(data, 0, data.length - 1);
        display(root);
    }

    public static void main(String[] args) {
        fun();
    }
}