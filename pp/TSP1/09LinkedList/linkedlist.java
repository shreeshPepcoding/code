import java.util.*;

public class linkedlist {

    private class Node {
        private int data;
        private Node next;
    
        public Node() {
            this.data = 5;
            this.next = null;
        }
    
        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;
        ListNode random;
        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public linkedlist() {
        this.head = this.tail = null;
        this.size = 0;
    }

    private void handleAddWhenSize0(int val) {
        Node nn = new Node(val);
        this.head = nn;
        this.tail = nn;
        this.size++;
    }

    public void addFirst(int val) {
        if(size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node nn = new Node(val);
            nn.next = head;
            this.head = nn;
            this.size++;
        }
    }

    public void addLast(int val) {
        if(this.size == 0) {
            handleAddWhenSize0(val);
        } else {
            Node nn = new Node(val);
            this.tail.next = nn;
            this.tail = nn;
            this.size++;
        }
    }

    private Node getNthNode(int pos) {
        Node temp = this.head;

        for(int i = 0; i < pos; i++) {
            temp = temp.next;
        }

        return temp;
    }

    public void addAt(int val, int indx) {
        if(indx < 0 || indx > this.size) {
            System.out.println("Invalid Position");
        } else if(indx == 0) {
            addFirst(val);
        } else if(indx == this.size) {
            addLast(val);
        } else {
            Node nm1 = getNthNode(indx - 1);
            Node nn = new Node(val);

            nn.next = nm1.next;
            nm1.next = nn;
            this.size++;
        }
    }

    public int getFirst() {
        if(this.size == 0) {
            return -1;
        }

        return this.head.data;
    }

    public int getLast() {
        if(this.size == 0) {
            return -1;
        }

        return this.tail.data;
    }

    public int getAt(int indx) {
        if(indx < 0 || indx >= this.size) {
            return -1;
        }

        Node n = getNthNode(indx);
        return n.data;
    }

    private int handleRemoveWhenSize1() {
        int val = this.head.data;
        this.head = this.tail = null;
        this.size = 0;
        return val;
    }

    public int removeFirst() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int val = this.head.data;
            this.head = this.head.next;
            this.size--;
            return val;
        }
    }

    public int removeLast() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return handleRemoveWhenSize1();
        } else {
            Node nm1 = getNthNode(this.size - 2);
            int val = this.tail.data;

            nm1.next = null;
            this.tail = nm1;
            this.size--;

            return val;
        }
    }

    public int removeAt(int indx) {
        if(indx < 0 || indx >= this.size) {
            return -1;
        } else if(indx == 0) {
            return removeFirst();
        } else if(indx == this.size - 1) {
            return removeLast();
        } else {
            Node nm1 = getNthNode(indx - 1);
            int val = nm1.next.data;
            nm1.next = nm1.next.next;

            this.size--;
            return val;
        }
    }

    public int size() {
        return this.size;
    }

    public void display() {
        Node temp = this.head;
        while(temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
        System.out.println("null");
    }

    public void reverseDI() {
        int left = 0;
        int right = this.size - 1;

        while(left < right) {
            Node lnode = getNthNode(left);
            Node rnode = getNthNode(right);

            int temp = lnode.data;
            lnode.data = rnode.data;
            rnode.data = temp;

            left++;
            right--;
        }
    }

    public void reversePI(){
        Node prev = null;
        Node curr = head;

        while(curr != null) {
            Node next = curr.next;
            // change the pointer
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // swap head and tail pointer to manage head tail
        Node temp = head;
        head = tail;
        tail = head;
    }

    public static class LLToStackAdapter {
        LinkedList<Integer> list;

        public LLToStackAdapter() {
            list = new LinkedList<>();
        }

        int size() {
            return list.size();
        }

        void push(int val) {
            list.addLast(val);
        }

        int pop() {
            if(list.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return list.removeLast();
        }

        int top() {
            if(list.size() == 0) {
                System.out.println("Stack underflow");
                return -1;
            }
            return list.getLast();
        }
    }

    public static class LLToQueueAdapter {
        LinkedList<Integer> list;

        public LLToQueueAdapter() {
            list = new LinkedList<>();
        }

        int size() {
            return list.size();
        }

        void add(int val) {
            list.addLast(val);
        }

        int remove() {
            if(list.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return list.removeFirst();
        }

        int peek() {
            if(list.size() == 0) {
                System.out.println("Queue underflow");
                return -1;
            }
            return list.getFirst();
        }
    }

    public int kthFromLast(int k){
        Node slow = head;
        Node fast = head;

        // 1. move k step to fast ptr
        for(int i = 0; i < k; i++) 
            fast = fast.next;

        // 2. move slow and fast simultaneously till fast not reach at end
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }   

        return slow.data;
    }
    
    public int mid1() {
        if(head == null) return -1;

        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    public int mid2() {
        if(head == null) return -1;

        Node slow = head;
        Node fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.data;
    }

    public linkedlist mergeTwoSortedList1(linkedlist l1, linkedlist l2) {
        // Without changing given linkedlist -> requirement of portal
        Node t1 = l1.head;
        Node t2 = l2.head;

        linkedlist res = new linkedlist();

        while(t1 != null && t2 != null) {
            if(t1.data < t2.data) {
                res.addLast(t1.data);
                t1 = t1.next;
            } else {
                res.addLast(t2.data);
                t2 = t2.next;
            }
        }

        // t1 left over
        while(t1 != null) {
            res.addLast(t1.data);
            t1 = t1.next;
        }

        // t2 left over
        while(t2 != null) {
            res.addLast(t2.data);
            t2 = t2.next;
        }
        return res;
    }

    public linkedlist mergeTwoSortedList2(linkedlist l1, linkedlist l2) {
        // changing in given linkedlist is allowed.
        linkedlist res = new linkedlist();
        while(l1.size() > 0 && l2.size() > 0) {
            if(l1.getFirst() < l2.getFirst()) {
                res.addLast(l1.removeFirst());
            } else {
                res.addLast(l2.removeFirst());
            }
        }

        // l1 left over
        while(l1.size() > 0) {
            res.addLast(l1.removeFirst());
        }

        // l2 left over
        while(l2.size() > 0) {
            res.addLast(l2.removeFirst());
        }
        return res;
    }

    public linkedlist mergeTwoSortedList3(linkedlist l1, linkedlist l2) {
        // inplace change in original linkedlist
        Node head1 = l1.head;
        Node head2 = l2.head;

        Node t1 = head1;
        Node t2 = head2;

        Node dummy = new Node(-1);
        Node temp = dummy;

        while(t1 != null && t2 != null) {
            if(t1.data < t2.data) {
                temp.next = t1;
                temp = temp.next;
                t1 = t1.next;
            } else {
                temp.next = t2;
                temp = temp.next;
                t2 = t2.next;
            }
        }

        if(t1  == null) {
            temp.next = t2;
        } else {
            temp.next = t1;
        }

        linkedlist res = new linkedlist();
        res.head = dummy.next;
        int sz = 0;
        temp = dummy;
        while(temp.next != null) {
            sz++;
            temp = temp.next;
        }
        res.tail = temp;
        res.size = sz;
        return res;
    }

    private Node midForMergeSort(Node head, Node tail) {
        Node slow = head;
        Node fast = head;

        while(fast != tail && fast.next != tail) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public linkedlist mergeSort(Node head, Node tail) {
        if(head == tail) {
            linkedlist bres = new linkedlist();
            bres.addFirst(head.data);
            return bres;
        }

        Node mid = midForMergeSort(head, tail);

        linkedlist l1 = mergeSort(head, mid);
        linkedlist l2 = mergeSort(mid.next, tail);

        linkedlist res = mergeTwoSortedList1(l1, l2);
        return res;
    }

    public void removeDuplicates() {
        Node temp = this.head;
        Node itr = temp.next;

        while(itr != null) {
            if(temp.data == itr.data) {
                itr = itr.next;
            } else {
                temp.next = itr;
                temp = temp.next;
                itr = itr.next;
            }
        }
    }

    public void oddEven(){
        // make dummy nodes
        Node ehead = new Node();
        Node temp1 = ehead;
        Node ohead = new Node();
        Node temp2 = ohead;

        // iterator -> help in movement
        Node itr = this.head;

        while(itr != null) {
            if(itr.data % 2 == 0) {
                // even
                temp1.next = itr;
                temp1 = temp1.next;
            } else {
                // odd
                temp2.next = itr;
                temp2 = temp2.next;
            }
            itr = itr.next;
        }

        // odd -> even
        temp2.next = ehead.next;
        temp1.next = null;

        // management for linkedlist as a class, head, tail and size
        this.head = ohead.next;
        this.tail = temp2.next == null ? temp2 : temp1;
    }
    
    public void kReverse(int k) {
        linkedlist prev = null;

        while(this.size() > 0) {
            linkedlist curr = new linkedlist();
            if(this.size >= k) {
                // removeFirst from this, addfirst in curr
                for(int i = 0; i < k; i++) {
                    int data = this.getFirst();
                    this.removeFirst();
                    curr.addFirst(data);
                }
            } else {
                // removeFirst from this, addLast in curr
                while(this.size() > 0) {
                    int data = this.getFirst();
                    this.removeFirst();
                    curr.addLast(data);
                }
            }

            if(prev == null) {
                // change the reference of prev and curr
                prev = curr;
            } else {
                prev.tail.next = curr.head;
                prev.tail = curr.tail;
                prev.size += curr.size;
            }
        }

        this.head = prev.head;
        this.tail = prev.tail;
        this.size = prev.size;
    }

    private void displayReverseHelper(Node node){
        // write your code here
        if(node == null) return;

        displayReverseHelper(node.next);
        System.out.print(node.data + " ");
    }
    
    private void reversePRHelper(Node node){
        // write your code here
        if(node.next == null) {
            this.head = node;
            return;
        }
        reversePRHelper(node.next);
        node.next.next = node;
    }

    public void reversePR(){
        // write your code here
        Node temp = this.head;
        reversePRHelper(temp);
        temp.next = null;
        this.tail = temp;
    }
    
    public boolean IsPalindrome() {
        // write your code here -> time-O(n^2), space-O(1)
        int left = 0;
        int right = this.size - 1;

        while(left < right) {
            Node lnode = getNthNode(left);
            Node rnode = getNthNode(right);

            if(lnode.data != rnode.data) return false;

            left++;
            right--;
        }
        return true;
    }

    private Node getMidNode(Node node) {
        Node slow = node;
        Node fast = node.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    private Node reversePointer(Node node) {
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

    public boolean IsPalindromeInOn() {
        // write your code here -> time-O(n), space-O(1)
        Node head1 = this.head;

        Node mid = getMidNode(head1);
        Node head2 = mid.next;
        mid.next = null;

        head2 = reversePointer(head2);

        Node t1 = head1;
        Node t2 = head2;

        boolean res = true;

        while(t1 != null && t2 != null) {
            if(t1.data != t2.data) {
                res = false;
                break;
            }

            t1 = t1.next;
            t2 = t2.next;
        }

        // maintain original list
        head2 = reversePointer(head2);
        mid.next = head2;

        return res;
    }

    public void fold() {
        if(this.head == null || this.head.next == null || this.head.next.next == null) return;
        Node head1 = this.head;
        
        Node mid = getMidNode(head1);
        Node head2 = mid.next;
        mid.next = null;
        head2 = reversePointer(head2);

        Node t1 = head1;
        Node t2 = head2;

        Node prev = head1;

        while(t1 != null && t2 != null) {
            Node n1 = t1.next;
            Node n2 = t2.next;

            t1.next = t2;
            t1 = n1;
            prev = t1 == null ? prev : t1;

            t2.next = n1;
            t2 = n2;

            prev = t2 == null ? prev : t2;
        }

        // t1 = head1;

        // while(t1.next != null)
        //     t1= t1.next;

        this.head = head1;
        this.tail = prev;
    }

    public linkedlist addTwoLists(linkedlist one, linkedlist two) {
        // write your code here
        Node head1 = one.head;
        Node head2 = two.head;
        // don't change original list
        // 1. reverse
        head1 = reversePointer(head1);
        head2 = reversePointer(head2);

        // 2. add
        Node i = head1;
        Node j = head2;
        linkedlist res = new linkedlist();
        int carry = 0;
        while(i != null || j != null || carry != 0) {
            int ival = i == null ? 0 : i.data;
            i = i == null ? null : i.next;

            int jval = j == null ? 0 : j.data;
            j = j == null ? null : j.next;

            int sum = ival + jval + carry;

            int val = sum % 10;
            carry = sum / 10;
            res.addFirst(val);
        }

        // 3. make original list again, reverse head1 and head2
        head1 = reversePointer(head1);
        head2 = reversePointer(head2);
        // 4. return result
        return res;
    }

    // addition of linkedlist using recursion
    public static int additionHelper(Node one, int i1, Node two, int i2, linkedlist res) {
        if(one == null && two == null) {
            return 0;
        }

        int d1 = one.data;
        int d2 = two.data;
        int sum = 0;
        if(i1 > i2) {
            int carry = additionHelper(one.next, i1 - 1, two, i2, res);
            sum = d1 + carry;
        } else if(i1 < i2) {
            int carry = additionHelper(one, i1, two.next, i2 - 1, res);
            sum = d2 + carry;
        } else {
            int carry = additionHelper(one.next, i1 - 1, two.next, i2 - 1, res);
            sum = d1 + d2 + carry;
        }

        res.addFirst(sum % 10);
        return sum / 10;
    }

    public linkedlist addTwoLists1(linkedlist one, linkedlist two) {
        linkedlist res = new linkedlist();
        int carry = additionHelper(one.head, one.size, two.head, two.size, res);
        if(carry > 0)
            res.addFirst(carry);

        return res;
    }    

    public static int findIntersection(linkedlist one, linkedlist two){
        // write your code here
        Node t1 = one.head;
        Node t2 = two.head;

        int s1 = one.size();
        int s2 = two.size();

        if(s1 > s2) {
            int diff = s1 - s2;
            for(int i = 0; i < diff; i++)
                t1 = t1.next;
        } else {
            int diff = s2 - s1;
            for(int i = 0; i < diff; i++)
                t2 = t2.next;
        }

        // while(t1 != null && t2 != null && t1 != t2) {
        //     t1 = t1.next;
        //     t2 = t2.next;
        // }
        // return t1 == null || t2 == null ? null : t1;

        while(t1 != t2){
            t1 = t1.next;
            t2 = t2.next;
        }
        return t1.data;
    }


    //Leetcode 160 size + intersection
    public int size(ListNode node) {
        int sz = 0;
        while(node != null) {
            node = node.next;
            sz++;
        }
        return sz;
    }
    
    public ListNode getIntersectionNode(ListNode head1, ListNode head2) {
        if(head1 == null || head2 == null) return null;
        
        ListNode t1 = head1;
        ListNode t2 = head2;

        int s1 = size(t1);
        int s2 = size(t2);

        if(s1 > s2) {
            int diff = s1 - s2;
            for(int i = 0; i < diff; i++)
                t1 = t1.next;
        } else {
            int diff = s2 - s1;
            for(int i = 0; i < diff; i++)
                t2 = t2.next;
        }

        while(t1 != null && t2 != null && t1 != t2) {
            t1 = t1.next;
            t2 = t2.next;
        }
        return t1 == null || t2 == null ? null : t1;
    }

    public static ListNode copyRandomList(ListNode head) {
        ListNode dummy = new ListNode(-1);
        ListNode t1 = dummy;
        ListNode t2 = head;
        // 1. clone without random
        while(t2 != null) {
            ListNode nn = new ListNode(t2.val);
            t1.next = nn;
            t1 = nn;

            t2 = t2.next;
        }

        ListNode head2 = dummy.next;
        // 2. connect in zigzag order
        t1 = head;
        t2 = head2;

        while(t1 != null && t2 != null) {
            ListNode n1 = t1.next;
            ListNode n2 = t2.next;

            t1.next = t2;
            t2.next = n1;

            t1 = n1;
            t2 = n2;
        }

        // 3. set random pointer
        t1 = head;

        while(t1 != null) {
            t1.next.random = t1.random == null ? null : t1.random.next;
            t1 = t1.next.next;
        }

        // 4. rearrange original list
        ListNode d1 = new ListNode(-1);
        t1 = d1;
        ListNode d2 = new ListNode(-1);
        t2 = d2;

        ListNode temp = head;
        while(temp != null) {
            t1.next = temp;
            t2.next = temp.next;

            t1 = t1.next;
            t2 = t2.next;
            temp = temp.next.next;
        }

        t1.next = null;
        t2.next = null;

        // 5. clonned head return
        return d2.next;
    }

    // ~~~~~~~~~~~~~Input Management~~~~~~~~~~~~~~

    public static void demo() {
        linkedlist list = new linkedlist();
        list.addLast(10);
        list.addLast(20);
        list.display();
        list.addLast(30);
        list.addFirst(9);
        list.display();
        list.addFirst(7);
        list.addLast(40);
        list.display();
        System.out.println(list.size());

        System.out.println(list.removeFirst());
        list.addAt(40, 2);
        list.display();

        System.out.println(list.removeAt(3));
        System.out.println(list.getAt(3));
        list.addLast(90);
        list.addLast(85);
        list.addLast(40);
        list.addLast(70);
        list.addLast(60);

        list.display();

        System.out.println(list.size());
        list.removeFirst();
        list.removeFirst();
        list.removeFirst();
        list.removeAt(3);
        list.display();

        list.removeLast();
        list.removeLast();
        list.display();

    }

    public static void main(String[] args) {
        demo();
    }
} 