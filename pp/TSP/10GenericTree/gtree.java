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
        Node root = null;
        Stack<Node> st = new Stack<>();

        for(int i = 0; i < arr.length; i++) {
            Integer data = arr[i];
            if(data != null) {
                Node nn = new Node(data);
                if(st.size() == 0) {
                    root = nn;
                    st.push(nn);
                } else {
                    st.peek().children.add(nn);
                    st.push(nn);
                }
            } else {
                st.pop();
            }
        }

        return root;
    }

    public static void display(Node root) {
        
        String str = "[" + root.data + "] -> ";
        for(Node child : root.children) {
            str += child.data + ", ";
        }
        System.out.println(str + " .");

        for(int i = 0; i < root.children.size(); i++) {
            Node child = root.children.get(i);
            display(child);
        }
    }

    public static int size(Node root) {
        int sz = 0;
        for(Node child : root.children) {
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

    public static int height(Node root) {
        int ht = -1; // on the basis of edge

        for(Node child : root.children) {
            ht = Math.max(ht, height(child));
        }

        return ht + 1;
    }

    public static void traversals(Node node){
        // node pre
        System.out.println("Node Pre " + node.data);
        for(Node child : node.children) {
            // before call -> edge pre
            System.out.println("Edge Pre " + node.data + "--" + child.data);
            // call
            traversals(child);
            // after call -> edge post
            System.out.println("Edge Post " + node.data + "--" + child.data);
        }
        // node post
        System.out.println("Node Post " + node.data);
    }

    public static void levelOrder(Node node){
        Queue<Node> qu = new ArrayDeque<>();

        qu.add(node);

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

    // level order using 2 queues
    public static void levelOrderLinewise1(Node node){
        Queue<Node> mainQ = new ArrayDeque<>();
        Queue<Node> childQ = new ArrayDeque<>();
        
        mainQ.add(node);
        int level = 1;
        System.out.print("level - " + level + " : ");
        while(mainQ.size() > 0) {
            Node rem = mainQ.remove();
            System.out.print(rem.data + " ");

            for(Node child : rem.children) {
                childQ.add(child);
            }

            if(mainQ.size() == 0) {
                // hit enter
                System.out.println();
                level++;
                // swap mainQ and childQ
                Queue<Node> temp = mainQ;
                mainQ = childQ;
                childQ = temp;
                if(mainQ.size() != 0)
                    System.out.print("level - " + level + " : ");
            }
        }
    }

    // level order using delimiter
    public static void levelOrderLinewise2(Node node){
        // use linkedlist as a queue, because arrayDeque is not allow us to add null
        Queue<Node> qu = new LinkedList<>();

        qu.add(node);
        qu.add(null);

        while(qu.size() > 0) {
            // 1. get + remove
            Node rem = qu.remove();

            if(rem == null) {
                // 2. hit enter
                System.out.println();
                // 3. if size is more than 0 then again add null
                if(qu.size() > 0)
                    qu.add(null);

            } else {
                // 2. print
                System.out.print(rem.data + " ");
                // 3. add children
                for(Node child : rem.children) {
                    qu.add(child);
                }
            }
        }
    }

    // level order line wise, using single que, count approach
    public static void levelOrderLinewise3(Node node){
        // use linkedlist as a queue, because arrayDeque is not allow us to add null
        Queue<Node> qu = new LinkedList<>();
        qu.add(node);

        while(qu.size() > 0) {
            int sz = qu.size();
            while(sz-- > 0) {
                // 1. get + remove
                Node rem = qu.remove();
                // 2. print
                System.out.print(rem.data + " ");
                // 3. add children
                for(Node child : rem.children) {
                    qu.add(child);
                }
            }
            System.out.println();
        }
    }

    // using double stack
    public static void levelOrderLinewiseZZ(Node node){
        Stack<Node> mainS = new Stack<>();
        Stack<Node> childS = new Stack<>();
        mainS.push(node);
        int lvl = 1;
        while(mainS.size() > 0) {
            while(mainS.size() > 0) {
                Node rem = mainS.pop();
                System.out.print(rem.data + " ");
                
                if(lvl % 2 == 1) {
                    // odd level -> left to right
                    for(int i = 0; i < rem.children.size(); i++) {
                        Node child = rem.children.get(i);
                        childS.push(child);
                    }
                } else {
                    // even level -> right to left
                    for(int i = rem.children.size() - 1; i >= 0; i--) {
                        Node child = rem.children.get(i);
                        childS.push(child);
                    }
                }
            }
            System.out.println();
            lvl++;
            Stack<Node> temp = mainS;
            mainS = childS;
            childS = temp;
        }
    }

    public static void levelOrderLinewiseZZ2(Node node){
        Stack<Node> mainS = new Stack<>();
        Stack<Node> childS = new Stack<>();
        mainS.push(node);
        int lvl = 1;
        while(mainS.size() > 0) {
            Node rem = mainS.pop();
            System.out.print(rem.data + " ");
            
            if(lvl % 2 == 1) {
                // odd level -> left to right
                for(int i = 0; i < rem.children.size(); i++) {
                    Node child = rem.children.get(i);
                    childS.push(child);
                }
            } else {
                // even level -> right to left
                for(int i = rem.children.size() - 1; i >= 0; i--) {
                    Node child = rem.children.get(i);
                    childS.push(child);
                }
            }

            if(mainS.size() == 0) {
                System.out.println();
                lvl++;
                Stack<Node> temp = mainS;
                mainS = childS;
                childS = temp;
            }
        }
    }
    
    public static void mirror(Node node){
        for(Node child : node.children) {
            mirror(child);
        }

        // reverse children of current node
        int left = 0;
        int right = node.children.size() - 1;

        while(left < right) {
            Node temp = node.children.get(left);
            node.children.set(left, node.children.get(right));
            node.children.set(right, temp);
            left++;
            right--;
        }
    }

    public static void removeLeaves(Node node) {
        // preorder -> removal of leaves

        // for(int i = 0; i < node.children.size(); i++) {
        //     Node child = node.children.get(i);
        //     if(child.children.size() == 0) {
        //         node.children.remove(child);
        //         i--;
        //     }
        // }

        // for(Node child : node.children) {
        //     removeLeaves(child);
        // }
        
        // for(int i = node.children.size() - 1; i >= 0; i--) {
        //     Node child = node.children.get(i);
        //     if(child.children.size() == 0) {
        //         node.children.remove(i);
        //     }
        // }

        // for(Node child : node.children) {
        //     removeLeaves(child);
        // }
        
        ArrayList<Node> st = new ArrayList<>();
        for(Node child : node.children) {
            // Node child = node.children.get(i);
            if(child.children.size() != 0) {
                st.add(child);
            }
        }
        node.children = st;

        for(Node child : node.children) {
            removeLeaves(child);
        }
    }

    public static Node getTail(Node node) {
        Node tail = node;

        while(tail.children.size() != 0) {
            tail = tail.children.get(0);
        }

        return tail;
    }

    public static void linearize(Node node){
        for(Node child : node.children) {
            linearize(child);
        }

        for(int i = node.children.size() - 2; i >= 0; i--) {
            Node last = node.children.get(i + 1);  // last 
            Node slast = node.children.get(i);     // second last

            node.children.remove(i + 1);
            Node tail = getTail(slast);

            tail.children.add(last);
        }
    }

    // linearize with tail -> efficient version
    public static Node linearize2(Node node) {
        if(node.children.size() == 0) return node;

        Node lastNode = node.children.get(node.children.size() - 1);
        Node tail = linearize2(lastNode);

        for(int i = node.children.size() - 2 ; i >= 0; i--) {
            Node rem = node.children.remove(i  + 1);

            Node stail = linearize2(node.children.get(i));
            stail.children.add(rem);
        }

        return tail;
    }
    
    public static boolean find(Node node, int data) {
        if(node.data == data) return true;

        boolean res = false;
        for(Node child : node.children) {
            res = find(child, data);
            if(res == true) return true;

            // res = res || find(child, data);
        }

        return res;
    }

    public static ArrayList<Integer> nodeToRootPath(Node node, int data){
        if(node.data == data) {
            ArrayList<Integer> bres = new ArrayList<>();
            bres.add(node.data);
            return bres;
        }

        for(Node child : node.children) {
            ArrayList<Integer> rres = nodeToRootPath(child, data);
            if(rres.size() > 0) {
                rres.add(node.data);
                return rres;
            }

            // if(rres != null) {
            //     rres.add(node.data, data);
            //     return rres;
            // }
        }

        return new ArrayList<>();
        // return null;
    }

    public static int lca(Node node, int d1, int d2) {
        // write your code here
        ArrayList<Integer> n2rp1 = nodeToRootPath(node, d1);
        ArrayList<Integer> n2rp2 = nodeToRootPath(node, d2);

        int i = n2rp1.size() - 1;
        int j = n2rp2.size() - 1;
        int res = -1;
        while(i >= 0 && j >= 0 && n2rp1.get(i) == n2rp2.get(j)) {
            res = n2rp1.get(i);
            i--;
            j--;
        }

        return res;
    }

    public static int distanceBetweenNodes(Node node, int d1, int d2){
        ArrayList<Integer> n2rp1 = nodeToRootPath(node, d1);
        ArrayList<Integer> n2rp2 = nodeToRootPath(node, d2);

        int i = n2rp1.size() - 1;
        int j = n2rp2.size() - 1;

        while(i >= 0 && j >= 0 && n2rp1.get(i) == n2rp2.get(j)) {
            i--;
            j--;
        }

        // distance = (i + 1) + (j + 1)
        return i + j + 2;
    }

    public static boolean areSimilar(Node n1, Node n2) {
        if(n1.children.size() != n2.children.size()) return false;

        boolean res = true;
        for(int i = 0; i < n1.children.size(); i++) {
            Node child1 = n1.children.get(i);
            Node child2 = n2.children.get(i);

            res = areSimilar(child1, child2);
            if(res == false) return false;
        }
        return res;
    }

    public static boolean areMirror(Node n1, Node n2) {
        if(n1.children.size() != n2.children.size()) return false;

        int sz = n1.children.size();
        boolean res = true;
        for(int i = 0; i < sz; i++) {
            Node child1 = n1.children.get(i);
            Node child2 = n2.children.get(sz - i - 1);

            res = areMirror(child1, child2);
            if(res == false) return false;
        }
        return res;
    }

    public static boolean IsSymmetric(Node node) {
        boolean res = areMirror(node, node);
        return res;
    }

    //~~~~~~~~~~~~~~~~~~~~Multi Solver1~~~~~~~~~~~~~~~~~~~~
    // using global variable
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    static int ht = 0;
    static int size = 0;

    public static void multiSolver1(Node node, int depth) {
        min = Math.min(min, node.data);
        max = Math.max(max, node.data);
        ht = Math.max(ht, depth);
        size++;

        for(Node child : node.children) {
            multiSolver1(child, depth + 1);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~Multi Solver2~~~~~~~~~~~~~~~~~~~~
    // using return type
    public static class multiSolver {
        int min;
        int max;
        int ht;
        int sz;

        public multiSolver(int min,int max, int ht, int sz) {
            this.min = min;
            this.max = max;
            this.ht = ht;
            this.sz = sz;
        }
        
        public multiSolver() {
            this.min = Integer.MAX_VALUE;
            this.max = Integer.MIN_VALUE;
            this.ht = -1;
            this.sz = 0;
        }
    }

    public static multiSolver multiSolver2(Node node) {
        multiSolver mres = new multiSolver(node.data, node.data, -1, 1);

        for(Node child : node.children) {
            multiSolver rres = multiSolver2(child);
            mres.min = Math.min(mres.min, rres.min);
            mres.max = Math.max(mres.max, rres.max);
            mres.ht = Math.max(mres.ht, rres.ht);
            mres.sz += rres.sz;
        }
        
        mres.ht += 1;

        return mres;
    }

    public static void multiSolution(Node root) {

        multiSolver res = multiSolver2(root);
        System.out.println("Min : " + res.min);
        System.out.println("Max : " + res.max);
        System.out.println("Height : " + res.ht);
        System.out.println("Size : " + res.sz);



        // min = Integer.MAX_VALUE;
        // max = Integer.MIN_VALUE;
        // ht = -1;
        // size = 0;

        // multiSolver1(root, 0);

        // System.out.println("Min : " + min);
        // System.out.println("Max : " + max);
        // System.out.println("Height : " + ht);
        // System.out.println("Size : " + size);
    }

    
    static Node predecessor;
    static Node successor;
    static int state = 0;
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
            return;
        }

        for(Node child : node.children) {
            if(state < 2) {
                predecessorAndSuccessor(child, data);
            } else {
                return;
            }
        }
    }

    static int ceil = Integer.MAX_VALUE; // qualified min
    static int floor = Integer.MIN_VALUE; // qualified max
    public static void ceilAndFloor(Node node, int data) {
        if(node.data > data) {
            // ceil
            if(node.data < ceil) {
                ceil = node.data;
            }
        }

        if(node.data < data) {
            // floor
            if(node.data > floor) {
                floor = node.data;
            }
        }

        for(Node child : node.children) {
            ceilAndFloor(child, data);
        }
    }

    public static int kthLargest(Node node, int k){
        int data = Integer.MAX_VALUE;
        for(int i = 0; i < k; i++) {
            floor = Integer.MIN_VALUE;
            ceilAndFloor(node, data);
            data = floor;
        }
        return data;
    }

    static int nodeData = 0;
    static int maxSum =Integer.MIN_VALUE; // 
    public static int treeSum(Node node) {
        int sum = 0;
        for(Node child : node.children) {
            sum += treeSum(child);
        }

        sum += node.data;
        // if(sum > maxSum) {
        //     nodeData = node.data;
        //     maxSum = sum;
        // }

        System.out.println(node.data + " @ " + sum);
        return sum;
    }


    public static int diameter1(Node node) {
        int mh = -1; // max height;
        int smh = -1; // second max height

        // finding height from child
        for(Node child : node.children) {
            int ht = height(child);
            if(ht >= mh) {
                smh = mh;
                mh = ht;
            } else if(ht > smh) {
                smh = ht;
            }
        }

        int dfc = 0; // diameter from child
        for(Node child : node.children) {
            dfc = Math.max(diameter1(child), dfc);
        }
        return Math.max(dfc, mh + smh + 2);
    }


    public static int diameter = 0;
    public static int heightForDiamter(Node node) {
        int maxHt = -1; // maximum height
        int smaxHt = -1; // second maximum height

        for(Node child : node.children) {
            int ht = heightForDiamter(child);

            if(ht >= maxHt) {
                smaxHt = maxHt;
                maxHt = ht;
            } else if(ht > smaxHt) {
                smaxHt = ht;
            } 
        }
        
        diameter = Math.max(diameter, maxHt + smaxHt + 2);

        // for height
        return maxHt + 1;
    }

    public static class Pair {
        Node node;
        int state;

        public Pair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    }

    public static void IterativePreandPostOrder(Node node) {
        Stack<Pair> st = new Stack<>();

        st.push(new Pair(node, 0));

        ArrayList<Integer> pre = new ArrayList<>();
        ArrayList<Integer> post = new ArrayList<>();

        while(!st.empty()) {
            Pair p = st.peek();
            if(p.state == 0) {
                pre.add(p.node.data);
                p.state++;
            } else if(p.state <= p.node.children.size()) {
                Node child = p.node.children.get(p.state - 1);
                p.state++;
                st.push(new Pair(child, 0));
            } else {
                post.add(p.node.data);
                st.pop();
            }
        }

        for(int val : pre) {
            System.out.print(val + " ");
        }
        System.out.println();

        for(int val : post) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static class SSPair {
        String ques;
        int state;
        String ans;

        public SSPair(String ques, int state, String ans) {
            this.ques = ques;
            this.state = state;
            this.ans = ans;
        }
    }

    public static void printSubseq(String str) {

        Stack<SSPair> st = new Stack<>();

        st.push(new SSPair(str, 0, ""));
        ArrayList<String> res = new ArrayList<>();
        while(st.size() > 0) {
            SSPair p = st.peek();

            if(p.ques.length() == 0) {
                res.add(p.ans);
                System.out.println(p.ans);
                st.pop();
                continue;
            }

            String roq = p.ques.substring(1);
            char ch = p.ques.charAt(0);
            if(p.state == 0) {
                p.state++;
                st.push(new SSPair(roq, 0, p.ans + ch + " "));
            } else if(p.state == 1) {
                p.state++;
                st.push(new SSPair(roq, 0, p.ans + "- "));
            } else {
                st.pop();
            }
        }

        for(String val : res) {
            System.out.println(val);
        }
    }


    public static void fun() {
        Integer[] data = {10, 20, -50, null, -60, null, null, 30, -70, null, 80, -110, null, 120, null,
                                 null, 90, null, null, -40, 100, null, null, null};
            

        // Integer[] data = {10, 20, null, null};
        Node root = construct(data);

        printSubseq("abc");

        // IterativePreandPostOrder(root);
        // treeSum(root);
        // multiSolution(root);
        // multiSolution(root);

        // linearize2(root);
        // display(root);

        // levelOrderLinewiseZZ(root);
        // display(root);
        // System.out.println();
        // mirror(root);
        // removeLeaves(root);
        // display(root);
        // levelOrderLinewise3(root);
        // levelOrderLinewise2(root);
        // levelOrderLinewise1(root);
        // levelOrder(root);
        // System.out.println("Size : " + size(root));
        // System.out.println("Max : " + max(root));
        // System.out.println("Min : " + min(root));
    }

    public static void main(String[] args) {
        fun();
    }
}