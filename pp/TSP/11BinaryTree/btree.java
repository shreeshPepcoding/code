import java.util.*;

public class btree {

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

    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0]);
        Stack<Pair> st = new Stack<>();
        st.push(new Pair(root, 0));

        int indx = 0;
        while (st.size() > 0) {
            Pair p = st.peek();
            if (p.state == 0) {
                // left child processing
                indx++;
                if (arr[indx] != null) {
                    Node nn = new Node(arr[indx]);
                    p.node.left = nn;
                    st.push(new Pair(nn, 0));
                }
                p.state++;
            } else if (p.state == 1) {
                // right child processing
                indx++;
                if (arr[indx] != null) {
                    Node nn = new Node(arr[indx]);
                    p.node.right = nn;
                    st.push(new Pair(nn, 0));
                }
                p.state++;
            } else {
                // pop out node-pair from stack
                st.pop();
            }
        }
        return root;
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

        int ls = size(node.left);
        int rs = size(node.right);

        return ls + rs + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;

        int lsum = sum(node.left);
        int rsum = sum(node.right);

        return lsum + rsum + node.data;
    }

    public static int sum1(Node node) {
        // if(node == null) return 0; // root == null

        if(node.left != null && node.right != null) {
            int lsum = sum1(node.left);
            int rsum = sum1(node.right);
            return lsum + rsum + node.data;
        } else if(node.left != null) {
            int lsum = sum1(node.left);
            return lsum + node.data;
        } else if(node.right != null) {
            int rsum = sum1(node.right);
            return rsum + node.data;
        } else {
            return node.data;
        }
    }

    public static int sum2(Node node) {

        int sum = 0;
        if(node.left != null) {
            sum += sum2(node.left);
        }

        if(node.right != null) {
            sum += sum2(node.right);
        }
        return sum + node.data;
    }

    public static int max(Node node) {
        if(node == null) return Integer.MIN_VALUE;

        int lmax = max(node.left);
        int rmax = max(node.right);

        return Math.max(node.data, Math.max(lmax, rmax));
    }

    public static int height(Node node) {
        if(node == null) return -1;
        
        int lh = height(node.left);
        int rh = height(node.right);

        return Math.max(lh, rh) + 1;
    }

    public static void levelOrder(Node node) {
        Queue<Node> que = new ArrayDeque<>();

        que.add(node);

        while(que.size() > 0) {
            int sz = que.size();

            while(sz-- > 0) {
                // 1. get + remove
                Node rem = que.remove();
                // 2. print
                System.out.print(rem.data + " ");
                // 3. add children
                if(rem.left != null)
                    que.add(rem.left);

                if(rem.right != null) 
                    que.add(rem.right);
            }
            System.out.println();
        }
    }

    // preAre -> area before all calls
    public static void preOrder(Node root) {
        if(root == null) return;
        System.out.print(root.data + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    // inAre -> area between the calls i.e. left and right calls
    public static void inOrder(Node root) {
        if(root == null) return;
        inOrder(root.left);
        System.out.print(root.data + " ");
        inOrder(root.right);
    }

    // postArea -> area after all calls
    public static void postOrder(Node root) {
        if(root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.data + " ");
    }

    static ArrayList<Integer> pre;
    static ArrayList<Integer> in;
    static ArrayList<Integer> post;
    public static void traversal(Node root) {
        if(root == null) return;
        pre.add(root.data);
        traversal(root.left);
        in.add(root.data);
        traversal(root.right);
        post.add(root.data);
    }

    public static void iterativePrePostInTraversal(Node node) {
        Stack<Pair> st = new Stack<>();

        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> in = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        st.push(new Pair(node, 0));

        while(st.size() > 0) {
            Pair p = st.peek();
            if(p.state == 0) {
                pre.add(p.node.data);
                p.state++;
                if(p.node.left != null) {
                    st.push(new Pair(p.node.left, 0));
                }
            } else if(p.state == 1) {
                in.add(p.node.data);
                p.state++;
                if(p.node.right != null) {
                    st.push(new Pair(p.node.right, 0));
                }
            } else {
                post.add(p.node.data);
                st.pop();
            }
        }

        for(int val : pre) {
            System.out.print(val + " ");
        }
        System.out.println();

        for(int val : in) {
            System.out.print(val + " ");
        }
        System.out.println();

        for(int val : post) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static boolean find(Node node, int data) {
        if(node == null) return false;

        if(node.data == data) return true;
        
        // boolean res = false;
        
        // res = find(node.left, data);
        // res = res || find(node.right, data);

        // return res;

        boolean lres = find(node.left, data);
        if(lres == true) return true;

        boolean rres = find(node.right, data);
        if(rres == true) return true;

        return false;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data) {
        if(node == null) return new ArrayList<>();

        if(node.data == data) {
            ArrayList<Integer> bres = new ArrayList<>();
            bres.add(node.data);
            return bres;
        }

        ArrayList<Integer> lres = nodeToRootPath(node.left, data);
        if(lres.size() > 0) {
            lres.add(node.data);
            return lres;
        }

        ArrayList<Integer> rres = nodeToRootPath(node.right, data);
        if(rres.size() > 0) {
            rres.add(node.data);
            return rres;
        }

        return new ArrayList<>();
    }

    public static void printKLevelsDown(Node node, int k){
        if(node == null) return;

        if(k == 0) {
            System.out.println(node.data);
            return;
        }

        printKLevelsDown(node.left, k - 1);
        printKLevelsDown(node.right, k - 1);
    }

    public static ArrayList<Node> nodeToRoot(Node node, int data) {
        if(node == null) {
            return new ArrayList<>();
        }

        if(node.data == data) {
            ArrayList<Node> bres = new ArrayList<>();
            bres.add(node);
            return bres;
        }

        ArrayList<Node> lres = nodeToRoot(node.left, data);
        if(lres.size() > 0) {
            lres.add(node);
            return lres;
        }
        
        ArrayList<Node> rres = nodeToRoot(node.right, data);
        if(rres.size() > 0) {
            rres.add(node);
            return rres;
        }

        return new ArrayList<>();        
    }

    public static void printKDown(Node node, Node blockage, int k) {
        if(node == null || node == blockage || k < 0) return;

        if(k == 0) {
            System.out.println(node.data);
            return;
        }
        
        printKDown(node.left, blockage, k - 1);
        printKDown(node.right, blockage, k - 1);
    }

    public static void printKNodesFar(Node root, int data, int k) {
        ArrayList<Node> n2rp = nodeToRoot(root, data);

        Node blockage = null;
        for(int i = 0; i < n2rp.size() && k >= 0; i++) {
            Node node = n2rp.get(i);
            printKDown(node, blockage, k);
            k--;
            blockage = node;
        }
    }


    public static void pathToLeafFromRoot(Node node, String path, int sum, int lo, int hi) {
        if(node == null) return;

        if(node.left != null && node.right != null) {
            pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
            pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
        } else if(node.left != null) {
            pathToLeafFromRoot(node.left, path + node.data + " ", sum + node.data, lo, hi);
        } else if(node.right != null) {
            pathToLeafFromRoot(node.right, path + node.data + " ", sum + node.data, lo, hi);
        } else {
            // leaf
            sum += node.data;
            path += node.data;
            if(lo <= sum && sum <= hi) {
                // print path
                System.out.println(path);
            }
        }
    }

    public static Node createLeftCloneTree(Node node){
        if(node == null) return null;

        Node lcn = createLeftCloneTree(node.left); // left child node
        Node rcn = createLeftCloneTree(node.right);// right child node

        Node nn = new Node(node.data, lcn, null);
        node.left = nn;
        node.right = rcn;

        return node;
    }

    public static Node transBackFromLeftClonedTree(Node node){
        if(node == null) return null;
        Node lcn = transBackFromLeftClonedTree(node.left.left);
        Node rcn = transBackFromLeftClonedTree(node.right);

        node.left = lcn;
        node.right = rcn;
        return node;
    }
    
    public static void printSingleChildNodes(Node node, Node parent){
        if(node == null) return;

        if(parent != null && parent.left == node && parent.right == null) {
            // i m single left child of my parent
            System.out.println(node.data);
        }

        if(parent != null && parent.right == node && parent.left == null) {
            // i m single right child of my parent
            System.out.println(node.data);
        }

        printSingleChildNodes(node.left, node);
        printSingleChildNodes(node.right, node);
    }

    public static Node removeLeaves(Node node){
        // write your code here
    }

    public static void fun() {
        Integer[] arr = { 50, 25, 12, null, null, 37, 30, null, null, null, 75, 62, null, 70, null, null, 87, null,
                null };
        Node root = construct(arr);

        display(root);

        pathToLeafFromRoot(root, "", 0, 100, 250);
        // iterativePrePostInTraversal(root);

        // levelOrder(root);
        // System.out.print("Pre Order : ");
        // preOrder(root);
        // System.out.print("\nIn Order : ");
        // inOrder(root);
        // System.out.print("\nPost Order : ");
        // postOrder(root);

        // pre = new ArrayList<>();
        // in = new ArrayList<>();
        // post = new ArrayList<>();
        // traversal(root);
    
        // System.out.print("\nPre Order : ");
        // for(int val : pre)
        //     System.out.print(val + " ");
        
        // System.out.print("\nIn Order : ");
        // for(int val : in)
        //     System.out.print(val + " ");
        
        // System.out.print("\nPost Order : ");
        // for(int val : post)
        //     System.out.print(val + " ");

        // System.out.println("Size : " + size(root));
        // System.out.println("Sum : " + sum(root));
        // System.out.println("Max : " + max(root));
        // System.out.println("Height : " + height(root));
        // System.out.println("Sum 1 : " + sum1(root));
        // System.out.println("Sum 2 : " + sum2(root));


    }

    public static void main(String[] args) {
        fun();
    }
}