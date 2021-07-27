import java.util.*;

public class gtree {

    public static class Node {
        int data;
        ArrayList<Node> children;

        public Node() {
            this.data = 0;
            this.children = new ArrayList<>();
        }

        public Node(int data) {
            this.data = data;
            this.children = new ArrayList<>();
        }
    }

    public static Node construct(Integer[] arr) {
        Node root = new Node(arr[0]);
        Stack<Node> st = new Stack<>();
        st.push(root);

        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == null) {
                st.pop(); // wipe out from recursive stack
            } else {
                // 1. Make a new Node
                Node nn = new Node(arr[i]);
                // 2. make connection to its parent
                st.peek().children.add(nn);
                // 3. push it in the stack
                st.push(nn);
            }
        }
        return root;
    }

    public static void display(Node node) {
        if(node == null) return;
        // self work
        System.out.print(node.data + " -> ");
        for(Node child : node.children) {
            System.out.print(child.data + " ");   
        }
        System.out.println(".");

        // faith work
        for(Node child : node.children) {
            display(child);
        }
    }

    public static int size(Node node){
        if(node == null) return 0;
        int sz = 0;
        for(int i = 0; i < node.children.size(); i++) {
            Node child = node.children.get(i);
            sz += size(child);
        }
        return sz + 1;
    }

    public static int max(Node node) {
        int mx = Integer.MIN_VALUE;

        for(Node child : node.children) {
            mx = Math.max(mx, max(child));
        }
        return Math.max(mx, node.data);
    }

    public static int min(Node node) {
        int mn = Integer.MAX_VALUE;

        for(Node child : node.children) {
            mn = Math.min(mn, min(child));
        }
        return Math.min(mn, node.data);
    }

    public static int height(Node node) {
        int ht = -1; // for edge basis, ht = 0, it is for node basis

        for(Node child : node.children) {
            ht = Math.max(ht, height(child));
        }
        return ht + 1;
    }

    public static void traversals(Node node){
        // pre
        System.out.println("Node Pre " + node.data);
        for(Node child : node.children) {
            // edge pre
            System.out.println("Edge Pre " + node.data + "--" + child.data);
            // call
            traversals(child);
            // edge post
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }
 
        // post
        System.out.println("Node Post " + node.data);
    }


    public static void levelOrder(Node root){
        Queue<Node> qu = new ArrayDeque<>();
        qu.add(root);

        while(qu.size() > 0) {
            // 1. get + remove
            Node rem = qu.remove();
            // 2. print
            System.out.print(rem.data + " ");
            // 3. add children
            for(Node child : rem.children) {
                qu.add(child);
            }
        }
        System.out.println(".");
    }

    // level order using delimiter
    public static void levelOrderLineWise1(Node root) {
        Queue<Node> qu = new LinkedList<>();
        qu.add(root);
        qu.add(null);

        while(qu.size() > 0) {
            // 1. get + remove
            Node rem = qu.remove();

            if(rem == null) {
                System.out.println();
                if(qu.size() > 0) 
                    qu.add(null);
                
                continue;
            }
            // 2. print 
            System.out.print(rem.data + " ");

            // 3. add children
            for(Node child : rem.children) {
                qu.add(child);
            }
        }

    }

    // level order using two queues
    public static void levelOrderLineWise2(Node root) {
        Queue<Node> mainQ = new LinkedList<>();
        Queue<Node> helperQ = new LinkedList<>();
        mainQ.add(root);
        while(mainQ.size() > 0) {
            Node rem = mainQ.remove();
            System.out.print(rem.data + " ");
            for(Node child : rem.children) {
                helperQ.add(child);
            }
            if(mainQ.size() == 0) {
                System.out.println();
                // swapping of queues
                Queue<Node> temp = mainQ;
                mainQ = helperQ;
                helperQ = temp;
            }
        }
    }

    public static void levelOrderLineWise3(Node root) {
        Queue<Node> qu = new LinkedList<>();
        qu.add(root);
        while(qu.size() > 0) {
            int size = qu.size();
            while(size-- > 0) {
                // get + remove
                Node rem = qu.remove();
                // print
                System.out.print(rem.data + " ");
                // add children
                for(Node child : rem.children) {
                    qu.add(child);
                }
            }
            // level is end
            System.out.println();
        }
    }

    public static void levelOrderLinewiseZZ(Node root) {
        Stack<Node> mainS = new Stack<>();
        Stack<Node> helperS = new Stack<>();

        mainS.push(root);

        int level = 0;
        // level -> odd => right to left addition of child
        // level -> even => left to right addition of child
        while(mainS.size() > 0) {
            while(mainS.size() > 0) {
                Node rem = mainS.pop();
                System.out.print(rem.data + " ");
                if(level % 2 == 0) {
                    // left to right
                    for(Node child : rem.children) {
                        helperS.push(child);
                    }
                } else {
                    // right to left, helping from reverse loop
                    for(int i = rem.children.size() - 1; i >= 0; i--) {
                        Node child = rem.children.get(i);
                        helperS.push(child);
                    }
                }
            }
            System.out.println();
            Stack<Node> temp = mainS;
            mainS = helperS;
            helperS = temp;

            level++;
        }
    }

    public static void mirror(Node node){
        // faith
        for(Node child : node.children) {
            mirror(child);
        }
        // merging -> reverse nodde.children
        int left = 0;
        int right = node.children.size() - 1;

        while(left < right) {
            Node data1 = node.children.get(left);
            Node data2 = node.children.get(right);

            node.children.set(left, data2);
            node.children.set(right, data1);

            left++;
            right--;
        }
    }

    public static Node getTail(Node node) {
        Node tail = node;
        while(tail.children.size() != 0) {
            tail = tail.children.get(0);
        }
        return tail;
    }

    public static void linearize(Node root) {
        for(Node child : root.children) {
            linearize(child);
        }
        // make connection
        for(int i = root.children.size() - 2; i >= 0; i--) {
            Node rem = root.children.remove(i + 1);
            Node tail = getTail(root.children.get(i));
            tail.children.add(rem);
        }
    }

    public static Node lineariseBtr(Node root) {
        if(root.children.size() == 0) return root;
        int n = root.children.size();
        Node tail = lineariseBtr(root.children.get(n - 1));
        for(int i = n - 2; i >= 0; i--) {
            Node rem = root.children.remove(i + 1);
            Node ntail = lineariseBtr(root.children.get(i));
            ntail.children.add(rem);
        }
        return tail;
    }

    public static boolean find(Node node, int data) {
        //self check
        if(node.data == data) return true;
        // children check

        for(Node child : node.children) {
            boolean res = find(child, data);
            if(res == true) return true;
        }
        return false;
    }
    
    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if(node.data == data) {
            ArrayList<Integer> bres = new ArrayList<>();
            bres.add(data);
            return bres;
        }

        for(Node child : node.children) {
            ArrayList<Integer> rres = nodeToRootPath(child, data);
            if(rres.size() > 0) {
                rres.add(node.data);
                return rres;    
            }
        }
        return new ArrayList<>();
    }

    public static int lca(Node node, int d1, int d2) {
        ArrayList<Integer> path1 = nodeToRootPath(node, d1);
        ArrayList<Integer> path2 = nodeToRootPath(node, d2);

        int i = path1.size() - 1;
        int j = path2.size() - 1;

        int prev = 0;

        while(i >= 0 && j >= 0) {
            if(path1.get(i) != path2.get(j)) {
                break;
            }
            prev = path1.get(i);
            i--;
            j--;
        }
        return prev;
    }  

    public static int distanceBetweenNodes(Node node, int d1, int d2){
        ArrayList<Integer> path1 = nodeToRootPath(node, d1);
        ArrayList<Integer> path2 = nodeToRootPath(node, d2);

        int i = path1.size() - 1;
        int j = path2.size() - 1;

        while(i >= 0 && j >= 0) {
            if(path1.get(i) != path2.get(j)) {
                break;
            }
            i--;
            j--;
        }
        return i + j + 2;
    }

    public static boolean areSimilar(Node root1, Node root2) {
        if(root1.children.size() != root2.children.size()) return false;
        
        // both have left to right diurection for children
        for(int i = 0; i < root1.children.size(); i++) {
            Node child1 = root1.children.get(i);
            Node child2 = root2.children.get(i);

            boolean res = areSimilar(child1, child2);
            if(res == false)
                return false;
        }
        return true;
    }

    public static boolean areMirror(Node root1, Node root2) {
        if(root1.children.size() != root2.children.size()) return false;
        
        // both have left to right diurection for children
        int n = root1.children.size();
        for(int i = 0; i < n; i++) {
            Node child1 = root1.children.get(i);
            Node child2 = root2.children.get(n - i - 1);

            boolean res = areMirror(child1, child2);
            if(res == false)
                return false;
        }
        return true;
    }

    public static boolean IsSymmetric(Node root) {
        return areMirror(root, root);
    }

    // multi solver pair
    public static class MSPair {
        int min;
        int max;
        int ht;
        int size;

        public MSPair() {
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
            this.ht = -1;
            this.size = 0;
        }
    }

    public static MSPair multiSolver(Node root) {
        MSPair mres = new MSPair();
        mres.min = mres.max = root.data;
        for(Node child : root.children) {
            MSPair rres = multiSolver(child);
            // min
            mres.min = Math.min(mres.min, rres.min);
            // max
            mres.max = Math.max(mres.max, rres.max);
            // ht
            mres.ht = Math.max(mres.ht, rres.ht);
            // size
            mres.size += rres.size;
        }

        mres.ht += 1;
        mres.size += 1;
        return mres;
    }

    static int state = 0;    
    static Node predecessor;
    static Node successor;
    public static void predecessorAndSuccessor(Node node, int data) {
        if(state == 0) {
            if(node.data == data) {
                state++;
            } else {
                predecessor = node;
            }
        } else if(state == 1) {
            successor = node;
            state++;
        } else {
            return;
        }

        for(Node child : node.children) {
            if(state > 1) return;
            predecessorAndSuccessor(child, data);
        }
    }

    static int ceil = Integer.MAX_VALUE;
    static int floor = Integer.MIN_VALUE;
    public static void ceilAndFloor(Node node, int factor) {
        if(node.data > factor) {
            // valid elements for ceil
            if(node.data < ceil) {
                ceil = node.data;
            }
        } else if(node.data < factor) {
            // valid elements for floor
            if(node.data > floor) {
                floor = node.data;
            }
        }

        for(Node child : node.children) {
            ceilAndFloor(child, factor);
        }
    }

    public static int kthLargest(Node node, int k){
        int factor = Integer.MAX_VALUE;

        for(int i = 0; i < k; i++) {
            floor = Integer.MIN_VALUE;
            ceilAndFloor(node, factor);
            factor = floor;
        }

        return factor;
    }

    static Node maxNode = null;
    static int maxSum = Integer.MIN_VALUE;
    public static int sum(Node node) {
        int s = node.data;

        for(Node child : node.children) {
            s += sum(child);
        }

        // maximise maxSum 
        if(s > maxSum) {
            maxNode = node;
            maxSum = s;
        }
        return s;
    }


    static int diameter = 0;
    public static void diameter(Node node) {

        for(Node child : node.children) {
            diameter(child);
        }
        // preparing my diameter
        int max = -1;
        int smax = -1; // second max
        for(Node child : node.children) {
            int ht = height(child);

            if(ht > max) {
                smax = max;
                max = ht;
            } else if(ht > smax) {
                smax = ht;
            }
        }

        diameter = Math.max(diameter, max + smax + 2);
    }


    public static int heightForDia(Node node) {
        int max = -1;
        int smax = -1;

        for(Node child : node.children) {
            int ht = heightForDia(child);

            if(ht > max) {
                smax = max;
                max = ht;
            } else if(ht > smax) {
                smax = ht;
            }
        }

        diameter = Math.max(diameter, max + smax + 2);
        return max + 1;
    }


    public static class IPair {
        Node node;
        int state;

        public IPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void iterativePrePost(Node root) {
        Stack<IPair> st = new Stack<>();

        st.push(new IPair(root, -1));
        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();


        while(st.size() > 0) {
            IPair p = st.peek();
            if(p.state == -1) {
                // pre area
                pre.add(p.node.data);
                p.state++;
            } else if(0 <= p.state && p.state < p.node.children.size()) {
                // in area
                Node child = p.node.children.get(p.state);
                st.push(new IPair(child, -1));
                p.state++;
            } else { 
                // post area
                post.add(p.node.data);
                st.pop();
            }
        }

        // print pre order
        for(int val : pre) {
            System.out.print(val + " ");
        }
        System.out.println();
        // print post order
        for(int val : post) {
            System.out.print(val + " ");
        }
        System.out.println();
    }


    public static void fun() {
        Integer[] data = {-100, 20, -50, null, 60, null, null, 30, -70, null, 80, 110, null,
             -120, null, null, 90, null, null, -40, 100, null, null, null};

        Node root = construct(data);
        iterativePrePost(root);




        // sum(root);
        // System.out.println(maxNode.data + "@" + maxSum);

        // ceilAndFloor(root, 60);
        // System.out.println("Floor : " + floor);
        // System.out.println("Ceil : " + ceil);

        // predecessorAndSuccessor(root, 120);
        // System.out.println("Succ : " + successor.data);
        // System.out.println("Pred : " + predecessor.data);

        // MSPair res = multiSolver(root);
        // System.out.println("Min : " + res.min);
        // System.out.println("Max : " + res.max);
        // System.out.println("Height : " + res.ht);
        // System.out.println("Size : " + res.size);

        // display(root);
        // System.out.println();
        // System.out.println(lca(root, 80, 110));
        // ArrayList<Integer> path = nodeToRootPath(root, 110);
        // System.out.println(path);
        // linearize(root);
        // lineariseBtr(root);
        // display(root);
        
        // mirror(root);
        // levelOrderLinewiseZZ(root);

        // levelOrderLineWise3(root);
        // levelOrder(root);

        // traversals(root);

        // display(root);
        // System.out.println("Min : " + min(root));
    }

    public static void test() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);
        list.add(50);


        for(int i = list.size() - 1; i >= 0; i--) {
            int val = list.remove(i);
            System.out.println(val);
        }
    }


    public static void main(String[] args) {
        fun();
    }
}