class linkedlist {
    private class Node {
        int data;
        Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }

        public Node() {

        }
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    private void HandleAddWhenSize0(int data) {
        Node nn = new Node(data);
        this.head = this.tail = nn;
    }

    public void addFirst(int data) {
        if(this.size == 0) {
            HandleAddWhenSize0(data);
        } else {
            Node nn = new Node();

            // nn.data = data;
            nn.data = data;
            nn.next = head;
            head = nn;
        }
        this.size++;
    }

    public void addLast(int data) {
        if(this.size == 0) {
            HandleAddWhenSize0(data);
        } else {
            // 1. creation of node
            Node nn = new Node(data);
            // 2. make connection
            tail.next = nn;
            tail = nn;
        }
        size++;
    }

    public int size() {
        return this.size;
    }

    public void display() {
        Node temp = head;
        while(temp != null) {
            // print temp
            System.out.print(temp.data + " -> ");
            // move temp
            temp = temp.next;
        }
        System.out.println(".");
    }

    public void reverse() {
        Node[] arr = new Node[this.size];

        // fill arr
        Node temp = head;
        int indx = 0;
        while(temp != null) {
            arr[indx] = temp;
            temp = temp.next;
            indx++; 
        }

        // reverse data;
        int left = 0;
        int right = this.size - 1;

        while(left < right) {
            Node leftNode = arr[left];
            Node rightNode = arr[right];

            int val = leftNode.data;
            leftNode.data = rightNode.data;
            rightNode.data = val;

            left++;
            right--;
        }

    }

    private void displayRec(Node temp) {
        if(temp == null) return;
        // print yourself
        // call  with faith
        displayRec(temp.next);
        System.out.print(temp.data + " ");
    }

    public void DisplayRec() {
        displayRec(head);
    }

    private void display4fold(Node node) {
        while(node != null) {
            System.out.print(node.data + "->");
            node = node.next;
        }
        System.out.println(".");
    }

    private Node getNewMid(Node node) {
        Node slow = head;
        Node fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private Node reverse4fold(Node node){
        Node prev = null;
        Node curr = node;
        Node next = curr.next;

        while(next != null) {
            curr.next = prev;
            prev = curr;
            curr = next;
            next = next.next;
        }
        curr.next = prev;

        return curr;
    }

    public void fold() {
        if(head == null || head.next == null) {
            return;
        }

        // get mid
        Node mid = getNewMid(this.head);
        // make head1 and head2
        Node head1 = this.head;
        Node head2 = mid.next;
        mid.next = null;
        // reverse head2 and get new head
        head2 = reverse4fold(head2);

        // display4fold(head1);
        // display4fold(head2);

        Node temp1 = head1;
        Node temp2 = head2;
        while(temp1 != null && temp2 != null) {
            Node n2 = temp2.next;
            temp2.next = temp1.next;
            temp1.next = temp2;
            temp1 = temp2.next;
            temp2 = n2;
        }
        
        this.head = head1;
        Node t = head;
        while(t.next != null)
            t = t.next;

        this.tail = t;
    } 

    public linkedlist res = null;

    private int carry = 0;

    private void recLsum(Node p1, Node p2, int indx1, int indx2) {
        if(indx1 == 0 && indx2 == 0) {
            res = new linkedlist();
            return;
        }
        int v1 = 0;
        int v2 = 0;
        if(indx1 == indx2) {
            recLsum(p1.next, p2.next, indx1 - 1, indx2 - 1);
            v1 = p1.data;
            v2 = p2.data;
        } else if(indx1 > indx2) {
            recLsum(p1.next, p2, indx1 - 1, indx2);
            v1 = p1.data;
        } else {
            recLsum(p1, p2.next, indx1, indx2 - 1);
            v2 = p2.data;
        }
        int sum = v1 + v2 + carry;
        res.addFirst(sum % 10);
        carry = sum / 10;
    }

    // add two linked list l1 and l2 and store 
    public linkedlist lSum(linkedlist l1, linkedlist l2) {
        int s1 = l1.size();
        int s2 = l2.size();
        Node p1 = l1.head;
        Node p2 = l2.head;
        recLsum(p1, p2, s1, s2);
        if(carry != 0) {
            res.addFirst(carry);
        }
        return res; 
    }


}

public class list {

    public static class Demo2 {
        public int data = 17;
        public String name = "A";
    }

    public static void main(String[] args) {
        // linkedlist list = new linkedlist();

        // list.addLast(10);
        // list.addLast(20);
        // list.addLast(30);
        // list.addLast(40);
        // list.addLast(50);
        // list.addLast(60);
        // list.addLast(70);
        // list.addLast(80);

        // list.display();
        // list.reverse();
        // list.display();

        // list.DisplayRec();
        
        // list.display();
        // list.fold();
        // list.display();
        // Demo2 d = new Demo2();

        linkedlist l1 = new linkedlist();
        l1.addLast(9);
        l1.addLast(8);
        l1.addLast(7);
        l1.addLast(6);

        linkedlist l2 = new linkedlist();
        l2.addLast(9);
        l2.addLast(8);

        linkedlist l3 = l1.lSum(l1, l2);
        l3.display();
    }
}
