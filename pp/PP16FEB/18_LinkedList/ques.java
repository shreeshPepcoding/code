import java.util.*;

public class ques {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    // leetcode 206. https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return  prev;
    }

    // leetcode 876. https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // middle 1
    public ListNode getMid1(ListNode head) {
        if(head == null) return null;
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // leetcode 234. https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) return true;
        ListNode mid = getMid1(head);
        ListNode head2 = mid.next;
        mid.next = null;

        head2 = reverseList(head2);

        ListNode t1 = head, t2 = head2;
        while(t1 != null && t2 != null) {
            if(t1.val != t2.val) return false;
            t1 = t1.next;
            t2 = t2.next;
        }
        return true;
    }

    // leetcode 143. https://leetcode.com/problems/reorder-list/
    public void reorderList(ListNode head) {
        // step 1. find middle node
        ListNode mid = getMid1(head);

        // step 2. Reverse second half
        ListNode head2 = mid.next;
        mid.next = null;
        head2 = reverseList(head2);
        // step 3. make a zigzag connection
        ListNode t1 = head, t2 = head2;

        while(t1 != null && t2 != null) {
            ListNode n1 = t1.next;
            ListNode n2 = t2.next;

            t1.next = t2;
            t2.next = n1;
            t1 = n1;
            t2 = n2;
        }
    }

    // unfold of linkedlist -> portal
    public void unfold(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        
        ListNode eihead = head; // even index head
        ListNode oihead = head.next; // odd index head
        
        ListNode t1 = eihead, t2 = oihead, curr = oihead.next;
        int indx = 2;
        while(curr != null) {
            if(indx % 2 == 0) {
                t1.next = curr;
                t1 = curr; 
            } else {
                t2.next = curr;
                t2 = curr;
            }
            curr = curr.next;
            indx++;
        }
        t1.next = null;
        t2.next = null;

        oihead = reverseList(oihead);
        t1.next = oihead;
    }

    // leetcode 21. https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }

        ListNode nhead = new ListNode(-1); // dummy node
        ListNode t1 = l1, t2 = l2, temp = nhead;
        while(t1 != null && t2 != null) {
            if(t1.val < t2.val) {
                temp.next = t1;
                temp = t1;
                t1 = t1.next;
            } else {
                temp.next = t2;
                temp = t2;
                t2 = t2.next;
            }
        }
        temp.next = t1 == null ? t2 : t1;
        return nhead.next;
    }

    // leetcode 23. https://leetcode.com/problems/merge-k-sorted-lists/
    public class Pair implements Comparable<Pair>{
        ListNode node;
        public Pair(ListNode node) {
            this.node = node;
        }
        public int compareTo(Pair o) {
            return this.node.val - o.node.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        for(ListNode node : lists) {
            if(node != null)
                pq.add(new Pair(node));
        }
        ListNode nhead = new ListNode(-1); // dummy head;
        ListNode temp = nhead;
        while(pq.size() > 0) {
            Pair rem = pq.remove();
            temp.next = rem.node;
            temp = rem.node;

            if(rem.node.next != null) {
                pq.add(new Pair(rem.node.next));
            }
        }
        return nhead.next;
    }

    // leetcode 19. https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
    }

    public static void main(String[] args) {

    }
}