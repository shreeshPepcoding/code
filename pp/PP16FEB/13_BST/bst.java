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

    public static Node construction(int[] arr, int lo, int hi) {
        if (lo > hi)
            return null;

        int mid = lo + (hi - lo) / 2;

        Node root = new Node(arr[mid]);

        root.left = construction(arr, lo, mid - 1);
        root.right = construction(arr, mid + 1, hi);

        return root;
    }

    public static void display(Node node) {
        if (node == null)
            return;

        String str = "";
        str += node.left == null ? "." : node.left.data;
        str += " <- [" + node.data + "] -> ";
        str += node.right == null ? "." : node.right.data;

        System.out.println(str);
        display(node.left);
        display(node.right);
    }

    public static int size(Node node) {
        if(node == null) return 0;

        return size(node.left) + size(node.right) + 1;
    }

    public static int sum(Node node) {
        if(node == null) return 0;
        return sum(node.left) + sum(node.right) + node.data;
    }

    public static int max(Node node) {
        if(node.right == null) return node.data;
        return max(node.right);
    }

    public static int min(Node node) {
        if(node.left == null) return node.data;
        return min(node.left);
    }

    public static boolean find(Node node, int data) {
        if(node == null) {
            return false;
        }

        boolean res = false;
        if(node.data == data) {
            // data found
            res = true;
        } else if(node.data > data) {
            // left side
            res = find(node.left, data);
        } else {
            // right side
            res = find(node.right, data);
        }
        return res;
    }

    public static Node add(Node node, int data) {
        if(node == null) {
            return new Node(data, null, null);
        }

        if(node.data < data) {
            node.right = add(node.right, data);
        } else if(node.data > data){
            node.left = add(node.left, data);
        }
        
        return node;
    }

    public static Node remove(Node node, int data) {
        if(node == null) return null;

        if(node.data == data) {
            if(node.left != null && node.right != null) {
                // have both child
                int lmax = max(node.left);
                node.data = lmax;
                node.left = remove(node.left, lmax);
            } else if(node.left != null) {
                // have left child
                return node.left;
            } else if(node.right != null) {
                // have right child
                return node.right;
            } else {    
                // itself a root
                return null;
            }
        } else if(node.data > data) {
            node.left = remove(node.left, data);
        } else {
            node.right = remove(node.right, data);
        }
        return node;
    }

    public static void fun() {
        int[] data = { 12, 25, 30, 37, 50, 62, 70, 75, 87 };
        Node root = construction(data, 0, data.length - 1);
        display(root);
    }

    static int sum = 0;
    public static void rwsol(Node node){
        if(node == null) return;

        rwsol(node.right);
        int data = node.data;
        node.data = sum;
        sum += data;
        rwsol(node.left);
    }

    public static int lca(Node node, int d1, int d2) {
        if(node == null) return -1;

        if(d1 < node.data && d2 < node.data) {
            // left side
            return lca(node.left, d1, d2);
        } else if(node.data < d1 && node.data  < d2) {
            // right side
            return lca(node.right, d1, d2);
        } else {
            // lca found
            return node.data;
        }
    }

    public static void pir(Node node, int d1, int d2) {
        if(node == null) return;

        if(d1 < node.data && d2 < node.data) {
            // left side
            pir(node.left, d1, d2);
        } else if(node.data < d1 && node.data  < d2) {
            // right side
            pir(node.right, d1, d2);
        } else {
            // lca found
            pir(node.left, d1, d2);
            System.out.println(node.data);
            pir(node.right, d1, d2);
        }
    }

    public static void targetSumPair1(Node node, Node root, int target) {
        if(node == null) return;

        targetSumPair1(node.left, root, target);
        int val1 = node.data;
        int val2 = target - val1;
        if(val1 < val2 && find(root, val2)) {
            System.out.println(val1 + " " + val2);
        }
        targetSumPair1(node.right, root, target);
    }

    public static void fillInOrder(Node root, ArrayList<Integer> list) {
        if(root == null) return;
        fillInOrder(root.left, list);
        list.add(root.data);
        fillInOrder(root.right, list);
    }

    public static void targetSumPair2(Node node, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        fillInOrder(node, list);

        int left = 0;
        int right = list.size() - 1;
        while(left < right) {
            int sum = list.get(left) + list.get(right);
            if(sum == target) { 
                System.out.println(list.get(left) + " " + list.get(right));
                left++;
                right--;
            } else if(sum > target) {
                right--;
            } else {
                left++;
            }
        }
    }

    public static class IPair {
        Node node;
        int state;

        public IPair(Node node, int state) {
            this.node = node;
            this.state = state;
        }
    } 

    public static Node itrInOrder(Stack<IPair> st) {
        while(st.size() > 0) {
            IPair p = st.peek();

            if(p.state == 0) {
                if(p.node.left != null) {
                    st.push(new IPair(p.node.left, 0));
                }
                p.state++;
            } else if(p.state == 1) {
                if(p.node.right != null) {
                    st.push(new IPair(p.node.right, 0));
                }
                p.state++;
                return p.node;
            } else {
                st.pop();
            }
        }
        return null;
    }

    public static Node revItrInOrder(Stack<IPair> st) {
        while(st.size() > 0) {
            IPair p = st.peek();

            if(p.state == 0) {
                if(p.node.right != null) {
                    st.push(new IPair(p.node.right, 0));
                }
                p.state++;
            } else if(p.state == 1) {
                if(p.node.left != null) {
                    st.push(new IPair(p.node.left, 0));
                }
                p.state++;
                return p.node;
            } else {
                st.pop();
            }
        }
        return null;
    }

    public static void targetSumPair3(Node node, int target) {
        Stack<IPair> lstack = new Stack<>();
        Stack<IPair> rstack = new Stack<>();

        lstack.push(new IPair(node, 0));
        rstack.push(new IPair(node, 0));

        Node left = itrInOrder(lstack);
        Node right = revItrInOrder(rstack);

        while(left.data < right.data) {
            int sum = left.data + right.data;
            if(sum == target) {
                System.out.println(left.data + " " + right.data);
                left = itrInOrder(lstack);
                right = revItrInOrder(rstack);
            } else if(sum > target) {
                right = revItrInOrder(rstack);
            } else {
                left = itrInOrder(lstack);
            }
        }
    }

    public static void main(String[] args) {
        fun();
    }
}