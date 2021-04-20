import java.util.*;

public class ll {
    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode midNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next; // speed => x
            fast = fast.next.next; // speed => 2x
        } 
        return slow;
    }

    public static boolean isPalindrome(ListNode head1) {
        ListNode mid = midNode(head1);
        ListNode head2 = mid.next;
        mid.next = null;

        head2 = reverse(head2);

        while(head1 != null && head2 != null) {
            if(head1.val != head2.val) {
                return false;
            }

            head1 = head1.next;
            head2 = head2.next;
        }
        return true;
    }

    public static void fold(ListNode head1) {
        // 1. get mid
        ListNode mid = midNode(head1);
        // 2. make head2 and unlink mid
        ListNode head2 = mid.next;
        mid.next = null;
        // 3. reverse head2
        head2 = reverse(head2);
        // 4. fold logic
        ListNode temp1 = head1;
        ListNode temp2 = head2;

        while(temp1 != null && temp2 != null) {
            // connection building
            ListNode next = temp2.next;
            temp2.next = temp1.next;
            temp1.next = temp2;
            // shifting
            temp1 = temp2.next;
            temp2 = next;
        }
    }

    public static void unfold(ListNode head1) {
        if(head1 == null || head1.next == null || head1.next.next == null) {
            return;
        }

        ListNode head2 = head1.next;

        ListNode p1 = head1;
        ListNode p2 = head2;

        while(p1 != null && p2 != null) {
            p1.next = p2.next;
            p1 = p1.next;

            if(p1 != null) {
                p2.next = p1.next;
                p2 = p2.next;
            }
        }

        head2 = reverse(head2);
        ListNode tail = head1;
        while(tail.next != null)
            tail = tail.next;

        tail.next = head2;
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(-1);
        ListNode temp = head;

        ListNode t1 = l1;
        ListNode t2 = l2;

        while(t1 != null && t2 != null) {
            if(t1.val < t2.val) {
                temp.next = t1;
                t1 = t1.next;
            } else {
                temp.next = t2;
                t2 = t2.next;
            }
            temp = temp.next;
        }

        if(t1 != null)
            temp.next = t1;

        if(t2 != null)
            temp.next = t2;

        return head.next;
    }

    public static ListNode mergeSort(ListNode head1) {
        if(head1.next == null) {
            return head1;
        }

        ListNode mid = midNode(head1);
        ListNode head2 = mid.next;
        mid.next = null;

        head1 = mergeSort(head1);   
        head2 = mergeSort(head2);

        head1 = mergeTwoLists(head1, head2);

        return head1;
    }

    public static class Pair implements Comparable<Pair>{
        ListNode node;
            
        public Pair(ListNode node) {
            this.node = node;
        }
        
        public int compareTo(Pair other) {
            return this.node.val - other.node.val;
        }
    }
    
    public static ListNode mergeKLists(ListNode[] lists) {
        
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        
        for(int i = 0; i < lists.length; i++) {
            if(lists[i] != null)
                pq.add(new Pair(lists[i]));
        }
        
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        
        while(pq.size() > 0) {
            Pair rem = pq.remove();
            temp.next = rem.node;
            temp = temp.next;
            
            if(rem.node.next != null) 
                pq.add(new Pair(rem.node.next));
        }
        
        return head.next;
    }

    public static boolean isCyclePresentInLL(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        boolean iscyclic = false;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                iscyclic = true;
                break;
            }
        }
        return iscyclic;
    }

    public static ListNode CycleNode(ListNode head) {
        boolean iscyclic = false;
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                iscyclic = true;
                break;
            }
        }

        if(iscyclic == false) return null;

        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        return;
    }
}