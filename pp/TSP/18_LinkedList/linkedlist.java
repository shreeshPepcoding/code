import java.util.*;

public class linkedlist {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    // leetcode 206 https://leetcode.com/problems/reverse-linked-list/
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    // leetcode 876. https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {
        // for middle 2
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    public ListNode middleNode1(ListNode head) {
        if(head == null) return head;
        // for middle 2
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
        if(head == null) return false;
        boolean isPalindrome = true;
        // step 1. find middle 1
        ListNode mid = middleNode1(head);
        // step 2. reverse second half of linkedlist
        ListNode head2 = mid.next;
        mid.next = null;
        head2 = reverseList(head2);
        // step 3. check is data is equal or not
        ListNode temp1 = head, temp2 = head2; 
        while(temp1 != null && temp2 != null) {
            if(temp1.val != temp2.val) {
                isPalindrome = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }
        // step 4. preserve original one
        head2 = reverseList(head2);
        mid.next = head2;
        return isPalindrome;
    }

    // leetcode 143. https://leetcode.com/problems/reorder-list/
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        // step 1. Segregate first half and second half
        ListNode mid = middleNode1(head);
        ListNode head2 = mid.next;
        mid.next = null;
        // step 2. Reverse second half
        head2 = reverseList(head2);
        // step 3. Apply logic for proper connection to match output
        ListNode temp1 = head, temp2 = head2;
        while(temp1 != null && temp2 != null) {
            ListNode next1 = temp1.next;
            ListNode next2 = temp2.next;

            temp1.next = temp2;
            temp2.next = next1;

            temp1 = next1;
            temp2 = next2;
        }
    }

    // unfold-> from portal
    public void unfold(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        // step 1. Make two dummy nodes
        ListNode oh = new ListNode(-1); // odd index head
        ListNode eh = new ListNode(-1); // even index head
        ListNode t1 = oh, t2 = eh;
        // step 2. iterate on list and make connections according to odd and even index
        int indx = 0;
        for(ListNode i = head; i != null; indx++, i = i.next) {
            if(indx % 2 == 0) {
                t2.next = i;
                t2 = i;
            } else {
                t1.next = i;
                t1 = i;
            }
        }
        t1.next = null;
        t2.next = null;

        oh = reverseList(oh.next);
        t2.next = oh;
    }

    // leetcode 21. https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) return l1 == null ? l2 : l1;
        
        ListNode head = new ListNode(-1);
        ListNode temp = head, t1 = l1, t2 = l2;

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
        return head.next;
    }

    // leetcode 23. https://leetcode.com/problems/merge-k-sorted-lists/
    public class pair implements Comparable<pair>{
        ListNode node;

        public pair(ListNode node) {
            this.node = node;
        }

        public int compareTo(pair o) {
            return this.node.val - o.node.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<pair> pq = new PriorityQueue<>();
    
        for(int i = 0; i < lists.length; i++) {
            if(lists[i] != null)
                pq.add(new pair(lists[i]));
        }

        ListNode head = new ListNode(-1);
        ListNode temp = head;

        while(pq.size() > 0) {
            pair rem = pq.remove();
            temp.next = rem.node;
            temp = temp.next;

            if(rem.node.next != null) {
                pq.add(new pair(rem.node.next));
            }
        }

        return head.next;
    }

    // leetcode 148. https://leetcode.com/problems/sort-list/
    public ListNode sortList(ListNode head1) {
        if(head1 == null || head1.next == null) return head1;
        ListNode mid = middleNode1(head1);
        ListNode head2 = mid.next;
        mid.next = null;

        head1 = sortList(head1);
        head2 = sortList(head2);
        ListNode res = mergeTwoLists(head1, head2);

        return res;
    }

    // leetcode 19. https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // step 1. make a forward pointer and move it by n
        ListNode forw = head; // forward
        for(int i = 0; i < n; i++) 
            forw = forw.next;
        
        if(forw == null) return head.next; // in that case n is equal to size
        // step 2. point head with current pointer
        ListNode curr = head;
        // step 3. move current and forward pointer simultaneously
        while(forw.next != null) {
            curr = curr.next;
            forw = forw.next;
        }
        // step 4. disconnect nth node using current.next
        curr.next = curr.next.next;
        return head;
    }

    // leetcode 141. https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) return true;
        }

        return false;
    }

    // leetcode 142. https://leetcode.com/problems/linked-list-cycle-ii/
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
                break;
            }
        }
        if(fast == null || fast.next == null) return null;
        slow = head;
        while(slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    // leetcode 160. https://leetcode.com/problems/intersection-of-two-linked-lists/
    // method 1 -> using floyd cycle detection
    public ListNode getIntersectionNode1(ListNode head1, ListNode head2) {
        ListNode tail = head1;
        while(tail.next != null)
            tail = tail.next;

        tail.next = head1;

        ListNode res = detectCycle(head2);
        tail.next = null;
        return res;
    }

    // method 2 -> usinf difference method
    private int size(ListNode head) {
        int s = 0;
        ListNode t1 = head;
        while(t1 != null) {
            t1 = t1.next;
            s++;
        }
        return s;
    }

    public ListNode getIntersectionNode2(ListNode head1, ListNode head2) {
        int s1 = size(head1);
        int s2 = size(head2);

        ListNode t1 = head1;
        ListNode t2 = head2;

        if(s1 > s2) {
            for(int i = 0; i < s1 - s2; i++)
                t1 = t1.next;
        } else {
            for(int i = 0; i < s2 - s1; i++)
                t2 = t2.next;
        }

        while(t1 != null) {
            if(t1 == t2) return t1;
            t1 = t1.next;
            t2 = t2.next;
        }
        return null;
    }

    // leetcode 328. https://leetcode.com/problems/odd-even-linked-list/
    public ListNode oddEvenList(ListNode head) {
        // write your code here
    }   
    // addition
    // subtraction
    // multiplication


    public static void ques() {
        // lists
    }

    public static void main(String[] args) {
        ques();
    }
}