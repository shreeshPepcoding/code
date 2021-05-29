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


    public static void fun() {
        Integer[] data = {10, 20, 50, null, 60, null, null, 30, 70, null, 80, 110, null, 120, null,
                                 null, 90, null, null, 40, 100, null, null, null};
        
        Node root = construct(data);
        linearize2(root);
        display(root);

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