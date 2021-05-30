import java.util.*;

class linkedlist {
    private class Node {
        private int data;
        private Node next;

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

    private Node head = null;
    private Node tail = null;
    private int size = 0;


    private void handleAddWhenSize0(int data) {
        Node nn = new Node(data);
        head = tail = nn;
        size = 1;
    }

    public void addFirst(int data) {
        if(size == 0) {
            handleAddWhenSize0(data);
        } else {
            Node nn = new Node(data);
            nn.next = head;
            head = nn;
            size++;
        }
    }

    public void addLast(int data) {
        if(size == 0) {
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
        while(pos > 0) {
            temp = temp.next;
            pos--;
        }
        return temp;
    }

    public void addAt(int data, int indx) {
        if(indx < 0 || indx > size) {
            return;
        } else if(indx == 0) {
            addFirst(data);
        } else if(indx == size) {
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
        if(size == 0 ) {
            return -1;
        } else if(size == 1) {
            return handleRemoveWhenSize1();
        } else {
            int data = head.data;
            head = head.next;
            size--;
            return data;
        }
    }

    public int removeLast() {
        if(size == 0) {
            return -1;
        } else if(size == 1) {
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
        if(indx < 0 || indx >= size) {
            return -1;
        } else if(indx == 0) {
            return removeFirst();
        } else if(indx == size - 1) {
            return removeLast();
        } else {
            Node nm1 =  getNodeAt(indx - 1);
            Node n = nm1.next;
            int data = n.data;

            // connection making
            nm1.next = n.next;

            size--;
            return data;
        }
    }

    public int getFirst() {
        if(size == 0) {
            return -1;
        }
        return head.data;
    }

    public int getLast() {
        if(size == 0) {
            return -1;
        }
        return tail.data;
    }

    public int getAt(int indx) {
        if(indx < 0 && indx >= size) {
            return -1;
        } else if(indx == 0){
            return getFirst();
        } else if(indx == size - 1) {
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

        while(temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }

        System.out.println("null");
    }

    @Override
    public String toString() {
        String str = "";

        Node temp = head;

        while(temp != null) {
            str += temp.data + "->";
            temp = temp.next;
        }

        str += "null";

        return str;
    }
}

public class myLinkedlist {
    
    public static void demo() {
        linkedlist list = new linkedlist();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        list.addLast(50);
        list.addLast(60);
        System.out.println(list);
        System.out.println(list.removeFirst());
        System.out.println(list);
        System.out.println(list.removeLast());
        System.out.println(list);
        System.out.println(list.removeAt(2));
        System.out.println(list);
        System.out.println(list.size());
        System.out.println(list.getFirst());
        System.out.println(list.getLast());
        System.out.println(list.getAt(1));


        // list.addAt(35, 3);
        // System.out.println(list + "\n" + list.size());
        // list.addFirst(70);
        // System.out.println(list);
        // list.addFirst(80);
        // System.out.println(list);
        // System.out.println(list.size());
        // list.display();
    }

    public static void main(String[] args) {
        demo();
    }
}
