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
            size++;
        }
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

}

public class list {

    public static class Demo2 {
        public int data = 17;
        public String name = "A";
    }

    public static void main(String[] args) {
        linkedlist list = new linkedlist();

        list.addFirst(10);
        list.addFirst(20);
        list.addFirst(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);
        list.addLast(70);
        list.addLast(80);

        // list.display();
        // list.reverse();
        list.display();

        list.DisplayRec();

        // Demo2 d = new Demo2();


    
    }
}
