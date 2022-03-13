public class doublyLinkedList {
    
    public class ListNode {
        int val;
        ListNode next;
        ListNode prev;

        ListNode() {
            this.val = 0;
            this.next = this.prev = null;
        }

        ListNode(int val) {
            this.val = val;
            this.next = this.prev = null;
        }

        ListNode(int val, ListNode next, ListNode prev) {
            this.val = val;
            this.next = next;
            this.prev = prev;
        }
    }

    public ListNode head = null;
    public ListNode tail = null;
    public int size = 0;

    public int size() {
        return this.size;
    }

    public void display() {
        System.out.print("NULL");
        ListNode temp = this.head;
        while(temp != null) {
            System.out.print(" <--> " + temp.val);
            temp = temp.next;
        }
        System.out.print(" <--> NULL");
    }

    private void handleAddWhenSize0(int val) {
        ListNode nn = new ListNode(val);
        this.head = this.tail = nn;
        this.size = 1;
    }

    public void addFirst(int val) {
        if(this.size == 0) {
            this.handleAddWhenSize0(val);
        } else {
            ListNode nn = new ListNode(val);
            nn.next = this.head;
            nn.prev = null;
            this.head.prev = nn;
            this.head = nn;
            this.size++;
        }
    }

    public void addLast(int val) {
        if(this.size == 0) {
            this.handleAddWhenSize0(val);
        } else {
            ListNode nn = new ListNode(val);
            nn.prev = this.tail;
            nn.next = null;
            this.tail.next = nn;
            this.tail = nn;
            this.size++;
        }
    }

    private ListNode getNthNode(int indx) {
        ListNode temp = this.head;
        for(int i = 0; i < indx; i++) {
            temp = temp.next;
        }
        return temp;
    }

    public void addAt(int val, int pos) {
        if(pos < 0 || pos > this.size) {
            System.out.println("Invalid Index");
        } else if(pos == 0) {
            this.addFirst(val);
        } else if(pos == this.size) {
            this.addLast(val);
        } else {
            ListNode n1 = this.getNthNode(pos - 1); // (n-1)th node
            ListNode n2 = n1.next;

            ListNode nn = new ListNode(val);
            n1.next = nn;
            nn.prev = n1;
            nn.next = n2;
            n2.prev = nn;
            this.size++;
        }
    }

    public int getFirst() {
        if(this.size == 0) {
            return -1;
        } else {
            return this.head.val;
        }
    }

    public int getLast() {
        if(this.size == 0) {
            return -1;
        } else {
            return this.tail.val;
        }
    }

    public int getAt(int pos) {
        if(pos < 0 || pos >= this.size) {
            return -1;
        } else if(pos == 0) {
            return this.getFirst();
        } else if(pos == this.size - 1) {
            return this.getLast();
        } else {
            ListNode nn = this.getNthNode(pos);
            return nn.val;
        }
    }

    private int handleRemoveWhenSize1() {
        int data = this.head.val;
        this.head = this.tail = null;
        this.size = 0;
        return data;
    }

    public int removeFirst() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return this.handleRemoveWhenSize1();
        } else {
            int data = this.head.val;
            this.head = this.head.next;
            this.head.prev = null;
            this.size--;
            return data;
        }
    }

    public int removeLast() {
        if(this.size == 0) {
            return -1;
        } else if(this.size == 1) {
            return this.handleRemoveWhenSize1();
        } else {
            int data = this.tail.val;
            this.tail = this.tail.prev;
            this.tail.next = null;
            this.size--;
            return data;
        }
    }

    public int removeAt(int pos) {
        if(this.size == 0 || pos < 0 || pos >= this.size) {
            return -1;
        } else if(pos == 0) {
            return this.removeFirst();
        } else if(pos == this.size - 1) {
            return this.removeLast();
        } else {
            ListNode n1 = getNthNode(pos - 1);
            ListNode n = n1.next;
            ListNode n2 = n.next;

            n1.next = n2;
            n2.prev = n1;
            int data = n.val;
            n.next = n.prev = null;
            this.size--;
            return data;
        }
    }

 
    public static void fun() {

    }
    public static void main(String[] args) {
        fun();    
    }
}
