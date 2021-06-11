import java.util.*;

public class ques {

    public static class Node {
        public int data;
        public Node next;

        public Node() {
            this.data = 0;
            this.next = null;
        }

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node(Node next, int data) {
            this.next = next;
            this.data = data;
        }
    }

    public static class LinkedList {
        private Node head = null;
        private Node tail = null;
        private int size = 0;

        private void handleAddWhenSize0(int data) {
            Node nn = new Node(data);
            head = tail = nn;
            size = 1;
        }

        public void addFirst(int data) {
            if (size == 0) {
                handleAddWhenSize0(data);
            } else {
                Node nn = new Node(data);
                nn.next = head;
                head = nn;
                size++;
            }
        }

        public void addLast(int data) {
            if (size == 0) {
                handleAddWhenSize0(data);
            } else {
                Node nn = new Node(data);
                tail.next = nn;
                tail = nn;
                size++;
            }
        }

        private Node getNodeAt(int pos) {
            Node temp = this.head;
            while (pos > 0) {
                temp = temp.next;
                pos--;
            }
            return temp;
        }

        public void addAt(int data, int indx) {
            if (indx < 0 || indx > size) {
                return;
            } else if (indx == 0) {
                addFirst(data);
            } else if (indx == size) {
                addLast(data);
            } else {
                Node nm1 = getNodeAt(indx - 1);
                Node nn = new Node(data);
                nn.next = nm1.next;
                nm1.next = nn;
                size++;
            }
        }

        private int handleRemoveWhenSize1() {
            int data = head.data;
            head = tail = null;
            size = 0;
            return data;
        }

        public int removeFirst() {
            if (size == 0) {
                return -1;
            } else if (size == 1) {
                return handleRemoveWhenSize1();
            } else {
                int data = head.data;
                head = head.next;
                size--;
                return data;
            }
        }

        public int removeLast() {
            if (size == 0) {
                return -1;
            } else if (size == 1) {
                return handleRemoveWhenSize1();
            } else {
                Node nm1 = getNodeAt(size - 2); // second last node
                int data = tail.data;
                nm1.next = null;
                tail = nm1;
                size--;
                return data;
            }
        }

        public int removeAt(int indx) {
            if (indx < 0 || indx >= size) {
                return -1;
            } else if (indx == 0) {
                return removeFirst();
            } else if (indx == size - 1) {
                return removeLast();
            } else {
                Node nm1 = getNodeAt(indx - 1);
                Node n = nm1.next;
                int data = n.data;

                // connection making
                nm1.next = n.next;

                size--;
                return data;
            }
        }

        public int getFirst() {
            if (size == 0) {
                return -1;
            }
            return head.data;
        }

        public int getLast() {
            if (size == 0) {
                return -1;
            }
            return tail.data;
        }

        public int getAt(int indx) {
            if (indx < 0 && indx >= size) {
                return -1;
            } else if (indx == 0) {
                return getFirst();
            } else if (indx == size - 1) {
                return getLast();
            } else {
                Node n = getNodeAt(indx);
                return n.data;
            }
        }

        public int size() {
            return size;
        }

        public void display() {
            Node temp = head;

            while (temp != null) {
                System.out.print(temp.data + "->");
                temp = temp.next;
            }

            System.out.println("null");
        }

        @Override
        public String toString() {
            String str = "";

            Node temp = head;

            while (temp != null) {
                str += temp.data + "->";
                temp = temp.next;
            }

            str += "null";

            return str;
        }

        public void reverseDI() {
            int left = 0;
            int right = this.size - 1;

            while (left < right) {
                Node lnode = getNodeAt(left);
                Node rnode = getNodeAt(right);

                // data swapping
                int temp = lnode.data;
                lnode.data = rnode.data;
                rnode.data = temp;

                left++;
                right--;
            }
        }

        private Node left = null;
        private int sz = 0;

        private void reverseDataRecursiveHelper(Node node, int level) {
            if (node == null) {
                this.sz = level;
                return;
            }

            reverseDataRecursiveHelper(node.next, level + 1);

            if (level >= this.size / 2) {
                // swap data
                int temp = left.data;
                left.data = node.data;
                node.data = temp;

                left = left.next;
            }
        }

        public void reverseDataRecursive() {
            this.left = head;
            sz = 0;
            reverseDataRecursiveHelper(head, 0);
        }

        public void reversePI() {
            Node prev = null;
            Node curr = head;

            while (curr != null) {
                Node next = curr.next;

                curr.next = prev;
                prev = curr;
                curr = next;
            }

            // swapping of head tail pointer
            Node temp = head;
            head = tail;
            tail = temp;
        }

        public int kthFromLast(int k) {
            Node slow = head;
            Node fast = head;

            // 1. move fast at k step away from head
            for (int i = 0; i < k; i++) {
                fast = fast.next;
            }

            // 2. move slow and fast simultaneously until fast.next != null
            while (fast.next != null) {
                slow = slow.next;
                fast = fast.next;
            }

            return slow.data;
        }

        public int mid1() {
            if (head == null)
                return -1;
            Node slow = head;
            Node fast = head.next;

            while (fast != null && fast.next != null) {
                slow = slow.next; // speed x
                fast = fast.next.next; // speed 2x
            }

            return slow.data;
        }

        public int mid2() {
            if (head == null)
                return -1;
            Node slow = head;
            Node fast = head;

            while (fast != null && fast.next != null) {
                slow = slow.next; // speed x
                fast = fast.next.next; // speed 2x
            }

            return slow.data;
        }

        public static LinkedList mergeTwoSortedLists(LinkedList l1, LinkedList l2) {
            LinkedList res = new LinkedList();

            Node p1 = l1.head;
            Node p2 = l2.head;

            while (p1 != null && p2 != null) {
                int val1 = p1.data;
                int val2 = p2.data;

                if (val1 < val2) {
                    res.addLast(val1);
                    p1 = p1.next;
                } else {
                    res.addLast(val2);
                    p2 = p2.next;
                }
            }

            while (p1 != null) {
                res.addLast(p1.data);
                p1 = p1.next;
            }
            while (p2 != null) {
                res.addLast(p2.data);
                p2 = p2.next;
            }
            return res;
        }

        public static Node getMidNode(Node lo, Node hi) {
            // return mid1
            Node slow = lo;
            Node fast = lo.next;

            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }

            return slow;
        }

        public static LinkedList mergeSort(Node lo, Node hi) {
            if (lo == hi) {
                LinkedList bres = new LinkedList();
                bres.addFirst(lo.data);
                return bres;
            }
            // 1. get mid node and break the connection of middle
            Node mid = getMidNode(lo, hi);
            Node midNext = mid.next;
            mid.next = null;

            LinkedList l1 = mergeSort(lo, mid);
            LinkedList l2 = mergeSort(midNext, hi);

            LinkedList res = mergeTwoSortedLists(l1, l2);

            // 2. make the connection of mid
            mid.next = midNext;
            return res;
        }

        public void removeDuplicates() {
            Node prev = this.head;
            Node next = prev.next;

            while (next != null) {
                if (prev.data == next.data) {
                    next = next.next;
                } else {
                    prev.next = next;
                    prev = next;
                    next = next.next;
                }
            }
            prev.next = null;

            // set original head and tail
            this.tail = prev;
        }

        public void oddEven() {
            Node ohead = new Node();
            Node ehead = new Node();

            Node t1 = ohead;
            Node t2 = ehead;

            Node i = this.head;
            while (i != null) {
                if (i.data % 2 == 0) {
                    // even
                    t2.next = i;
                    t2 = i;
                } else {
                    // odd
                    t1.next = i;
                    t1 = i;
                }
                i = i.next;
            }

            // odd -> even
            t1.next = ehead.next;
            t2.next = null;

            this.head = ohead.next;
            this.tail = t2 == ehead ? t1 : t2;
        }

        public void kReverse(int k) {
            LinkedList prev = null;

            while (this.size() > 0) {
                LinkedList curr = new LinkedList();
                if (this.size() >= k) {
                    // remove first from list and add first in curr
                    for (int i = 0; i < k; i++) {
                        int data = this.getFirst();
                        this.removeFirst();
                        curr.addFirst(data);
                    }
                } else {
                    // remove first in list and add last in curr
                    // remove first from list and add first in curr
                    int sz = this.size();
                    for (int i = 0; i < sz; i++) {
                        int data = this.getFirst();
                        this.removeFirst();
                        curr.addLast(data);
                    }
                }

                if (prev == null) {
                    prev = curr;
                } else {
                    prev.tail.next = curr.head;
                    prev.tail = curr.tail;
                    prev.size += curr.size;
                }
            }

            this.size = prev.size;
            this.head = prev.head;
            this.tail = prev.tail;
        }

        private void displayReverseHelper(Node node) {
            if(node == null) return;
            
            displayReverseHelper(node.next);
            System.out.print(node.data + " ");
        }

        public void displayReverse() {
            displayReverseHelper(head);
            System.out.println();
        }

        public Node getMiddle(Node node) {
            Node slow = node;
            Node fast = node.next;

            while(fast != null && fast.next != null)  {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }

        public Node reverseNode(Node node) {
            Node prev = null;
            Node curr = node;

            while(curr != null) {
                Node next = curr.next;
                curr.next = prev;
                prev = curr;
                curr = next;
            }

            return prev;
        }

        public boolean IsPalindrome() {
            // 1. get mid1 node
            Node mid = getMiddle(head);

            // 2. make head2
            Node head2 = mid.next;
            mid.next = null;
        
            // 3. reverse head2
            head2 = reverseNode(head2);

            // 4. check for palindrome
            Node p1 = head;
            Node p2 = head2;

            boolean ispalindrome = true;
            while(p1 != null && p2 != null) {
                if(p1.data != p2.data) {
                    ispalindrome = false;
                    break;
                }
                p1 = p1.next;
                p2 = p2.next;
            }

            // 5. maintain original list
            head2 = reverseNode(head2);
            mid.next = head2;

            return ispalindrome;
        }

        public void fold() {
            if(head == null || head.next == null || head.next.next == null) return;
            // 1. get mid
            Node mid = getMiddle(head);
            // 2. make head2 + reverse head2
            Node head2 = mid.next;
            mid.next = null;
            head2 = reverseNode(head2);

            // 3. make connections
            Node p1 = head;
            Node p2 = head2;

            Node prev = head;
        
            while(p1 != null && p2 != null) {
                Node n1 = p1.next;
                Node n2 = p2.next;

                p1.next = p2;
                p2.next = n1;
                p1 = n1;
                p2 = n2;
                if(p1 != null) prev = p1;
                if(p2 != null) prev = p2;
            }
            // 4. manage head and tail
            this.tail = prev;
        }
        
        private static int additionHelper(Node h1, int i1, Node h2, int i2, LinkedList res) {
            if(h1 == null || h2 == null) {
                return 0;
            }

            int carry = 0;
            int sum = 0;
            if(i1 > i2) {
                carry = additionHelper(h1.next, i1 - 1, h2, i2, res);
                sum = h1.data + carry;
            } else if(i1 < i2) {
                carry = additionHelper(h1, i1, h2.next, i2 - 1, res);
                sum = h2.data + carry;
            } else {
                carry = additionHelper(h1.next, i1 - 1, h2.next, i2 - 1, res);
                sum = h1.data + h2.data + carry;
            }
            res.addFirst(sum % 10);
            return sum / 10;
        }

        public static LinkedList addTwoLists(LinkedList one, LinkedList two) {
            LinkedList res = new LinkedList();

            int carry = additionHelper(one.head, one.size, two.head, two.size, res);
            if(carry != 0)
                res.addFirst(carry);
            return res;
        }

    }

    public static void fun() {
        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);
        list.addLast(70);

        System.out.println(list);
        System.out.println(list.mid1());
        System.out.println(list.mid2());
        // System.out.println(list.kthFromLast(3));
        // list.reverseDI();
        // System.out.println(list);
    }

    public static void main(String[] args) {
        fun();
    }
}