import java.util.*;

public class linkedlist {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode random;
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

    
    public static class Node {
        int val;
        Node next;
        Node random;
        Node() {
        }

        Node(int val) {
            this.val = val;
        }

        Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
    
    // leetcode 206, https://leetcode.com/problems/reverse-linked-list/
    public static ListNode reverseList(ListNode head) {
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

    // leetcode 876, https://leetcode.com/problems/middle-of-the-linked-list/
    public ListNode middleNode(ListNode head) {
        // mid 2
        ListNode slow = head;
        ListNode fast = head;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // middle 1, i.e. last node of first half
    public static ListNode midNode1(ListNode head) {
        if(head == null) return head;

        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    // leetcode 234, https://leetcode.com/problems/palindrome-linked-list/
    public boolean isPalindrome(ListNode head) {
        if(head == null) return false;
        boolean isPalindrome = true;

        // step 1 : find middle 1
        ListNode mid = midNode1(head);
        // step 2 : make new head
        ListNode head2 = mid.next;
        mid.next = null;
        // step 3 : reverse head2
        head2 = reverseList(head2);
        // step 4 : Apply logic to check if it is palindrome or not
        ListNode temp1 = head;
        ListNode temp2 = head2;

        while(temp1 != null && temp2 != null) {
            if(temp1.val != temp2.val) {
                isPalindrome = false;
                break;
            }
            temp1 = temp1.next;
            temp2 = temp2.next;
        }

        // if we have to preserve original list
        head2 = reverseList(head2);
        mid.next = head2;

        return isPalindrome;
    }

    // leetcode 143, https://leetcode.com/problems/reorder-list/, portal-> fold a linkedlist
    public void reorderList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        // step 1. Segregate from middle
        ListNode mid = midNode1(head);
        ListNode head2 = mid.next;
        mid.next = null;
        // step 2 : Reverse second half
        head2 = reverseList(head2);
        // step 3. Merge Them according to logic
        ListNode temp1 = head;
        ListNode temp2 = head2;

        while(temp1 != null && temp2 != null) {
            ListNode next1 = temp1.next;
            ListNode next2 = temp2.next;

            temp1.next = temp2;
            temp2.next = next1;

            temp1 = next1;
            temp2 = next2;
        }
    }

    // unfold linkedlist
    public static void unfold(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;

        ListNode oh = new ListNode(-1);
        ListNode eh = new ListNode(-1);

        ListNode t1 = eh;
        ListNode t2 = oh;
        int indx = 0;
        for(ListNode temp = head; temp != null; temp = temp.next, indx++) {
            if(indx % 2 == 0) {
                // even
                t1.next = temp;
                t1 = temp;
            } else {
                // odd
                t2.next = temp;
                t2 = temp;
            }
        }
        t1.next = null;
        t2.next = null;
        oh = reverseList(oh.next);
        t1.next = oh;
    }

    // leetcode 21, https://leetcode.com/problems/merge-two-sorted-lists/
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1 == null || list2 == null) return list1 == null ? list2 : list1;

        ListNode head = new ListNode(-1);
        ListNode temp = head;
        ListNode t1 = list1;
        ListNode t2 = list2;

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

    // leetcode 148, https://leetcode.com/problems/sort-list/
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode mid = midNode1(head);
        ListNode head2 = mid.next;
        mid.next = null;

        head = sortList(head);
        head2 = sortList(head2);

        ListNode res = mergeTwoLists(head, head2);
        return res;
    }

    // leetcode 23, https://leetcode.com/problems/merge-k-sorted-lists/
    private class pair implements Comparable<pair>{
        ListNode node;
        
        pair(ListNode node) {
            this.node = node;
        }

        public int compareTo(pair o) {
            return this.node.val - o.node.val;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length == 0) return null;
        PriorityQueue<pair> pq = new PriorityQueue<>(); // min priority queue

        for(int i = 0; i < lists.length; i++) {
            if(lists[i] != null) {
                pq.add(new pair(lists[i]));
            }
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

    // leetcode 19, https://leetcode.com/problems/remove-nth-node-from-end-of-list/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode fast = head;
        for(int i = 0; i < n; i++) {
            fast = fast.next;
        }
        if(fast == null) {
            // n -> size, remove -> head
            return head.next;
        }
        ListNode slow = head;
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next;
        return head;
    }

    // segregate odd even nodes
    public static ListNode segregateEvenOdd(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode ohead = new ListNode(-1);
        ListNode ehead = new ListNode(-1);
        ListNode t1 = ehead;
        ListNode t2 = ohead;

        for(ListNode temp = head; temp != null; temp = temp.next) {
            if(temp.val % 2 == 0) {
                // even 
                t1.next = temp;
                t1 = temp;
            } else {
                // odd
                t2.next = temp;
                t2 = temp;
            }
        }
        t1.next = null;
        t2.next = null;

        t1.next = ohead.next;
        return ehead.next;
    }

    // segregate 01
    public static ListNode segregate01(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode head1 = new ListNode(-1);
        ListNode head0 = new ListNode(-1);
        ListNode t1 = head0;
        ListNode t2 = head1;

        for(ListNode temp = head; temp != null; temp = temp.next) {
            if(temp.val == 0) {
                t1.next = temp;
                t1 = temp;
            } else {
                t2.next = temp;
                t2 = temp;
            }
        }
        t1.next = null;
        t2.next = null;

        t1.next = head1.next;
        return head0.next;
    }

    // reverse using add first method
    static ListNode thead;
    static ListNode ttail;

    public static void addFirst(ListNode node) {
        if(thead == null) {
            // first node of list
            thead = ttail = node;
        } else {
            node.next = thead;
            thead = node;
        }
    }

    public static ListNode reverseUsingAddFirst(ListNode head) {
        thead = ttail = null;
        ListNode next = head;
        while(next != null) {
            ListNode curr = next;
            next = next.next;
            curr.next = null;
            addFirst(curr);
        }
        return thead;
    }

    public static int size(ListNode head) {
        int count = 0;
        for(; head != null; head = head.next) {
            count++;
        }
        return count;
    }

    // leetcode 25, https://leetcode.com/problems/reverse-nodes-in-k-group/
    public ListNode reverseKGroup(ListNode ohead, int k) {
        if(ohead == null || ohead.next == null || k == 1) return ohead;
        // ohead -> original head
        int sz = size(ohead);
        ListNode head = null;
        ListNode tail = null;
        thead = ttail = null;
        ListNode temp = ohead;
        while(true) {
            if(sz >= k) {
                thead = ttail = null;
                for(int i = 0; i < k; i++) {
                    ListNode curr = temp;
                    temp = temp.next;
                    curr.next = null;
                    addFirst(curr);
                }
                if(head == null) {
                    head = thead;
                    tail = ttail;
                } else {
                    tail.next = thead;
                    tail = ttail;
                }
                sz -= k;
            } else {
                tail.next = temp;
                break;
            }
        }
        return head;
    }

    // reverse in range, leetcode 92, https://leetcode.com/problems/reverse-linked-list-ii/
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(left == right) return head;
        thead = ttail = null;
        ListNode prev = head;

        for(int i = 1; i < left - 1; i++) {
            prev = prev.next;
        }

        ListNode next = left != 1 ? prev.next : head;
        for(int i = left; i <= right; i++) {
            ListNode curr = next;
            next = next.next;
            curr.next = null;
            addFirst(curr);
        }
    
        if(left > 1) {
            prev.next = thead;
        } else {
            head = thead;
        }
        ttail.next = next;
        return head;
    }

    // leetcode 138, https://leetcode.com/problems/copy-list-with-random-pointer/
    public Node copyRandomList(Node ohead) {
        Node chead = new Node(-1);
        Node temp = chead;
        // step 1 : clone linkedlist with next only
        for(Node i = ohead; i != null; i = i.next) {
            Node nn = new Node(i.val);
            temp.next = nn;
            temp = nn;
        }
        chead = chead.next;
        // step 2 : connect node with zigzag pattern
        Node p1 = ohead;
        Node p2 = chead;
        while(p1 != null && p2 != null) {
            Node n1 = p1.next;
            Node n2 = p2.next;

            p1.next = p2;
            p2.next = n1;

            p1 = n1;
            p2 = n2;
        }
        // step 3 : connect random pointer
        p1 = ohead;
        p2 = chead;
        while(p1 != null && p2 != null) {
            p2.random = p1.random == null ? null : p1.random.next;
            p1 = p1.next.next;
            p2 = p2.next == null ? p2.next : p2.next.next;
        }
        // step 4 : retain original list
        Node head1 = new Node(-1); // dummy ohead
        Node head2 = new Node(-1); // dummy chead
        Node t1 = head1, t2 = head2, temp1 = ohead, temp2 = chead;

        while(temp1 != null && temp2 != null) {
            t1.next = temp1;
            t1 = temp1;
            t2.next = temp2;
            t2 = temp2;

            temp1 = temp1.next.next;
            temp2 = temp2.next == null ? temp2.next : temp2.next.next;
        }
        t1.next = null;
        t2.next = null;
        return head2.next;
    }

    // leetcode 83, https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public ListNode deleteDuplicates1(ListNode head) {
        if(head == null) return head;
        ListNode i = head;
        ListNode curr = head.next;

        while(curr != null) {
            if(i.val != curr.val) {
                i.next = curr;
                i = curr;
            }
            curr = curr.next;
        }
        i.next = curr;
        return head;
    }

    // leetcode 82, https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null) return head;
        ListNode head2 = new ListNode(-1);
        ListNode i = head2;
        ListNode curr = head;
        i.next = curr;
        while(curr != null) {
            curr = curr.next;
            boolean flag = false;
            while(curr != null && i.next.val == curr.val) {
                flag = true; // help to identify duplicacy
                curr = curr.next;
            }

            if(flag == true) {
                i.next = curr;
            } else {
                i = i.next;
            }
        }
        return head2.next;
    }

    // leetcode 141, https://leetcode.com/problems/linked-list-cycle/
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if(slow == fast) {
                // same address
                return true;
            }
        }
        return false;
    }
    
    // leetcode 142, https://leetcode.com/problems/linked-list-cycle-ii/
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

    // leetcode 160, https://leetcode.com/problems/intersection-of-two-linked-lists/
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        // step 1 : find Tail and connect tail.next = headA
        ListNode tail = headA;
        while(tail.next != null) 
            tail = tail.next;
        
        tail.next = headA;
        // step 2 : find starting node of cycle linkedlist (if cycle then result is node otherwise null)
        ListNode res = detectCycle(headB);
        // step 3 : retain original structure, tail.next = null
        tail.next = null;
        // step 4 : return result
        return res;
    }

    // method 2, using difference method
    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        int sizeA = size(headA);
        int sizeB = size(headB);
        ListNode tempA = headA, tempB = headB;
        if(sizeA > sizeB) {
            // move tempA
            for(int i = 0; i < sizeA - sizeB; i++)
                tempA = tempA.next;
        } else {
            // move tempB
            for(int i = 0; i < sizeB - sizeA; i++)
                tempB = tempB.next;
        }

        // move tempA and tempB simultaneously
        while(tempA != null) {
            if(tempA == tempB) {
                return tempA;
            }
            tempA = tempA.next;
            tempB = tempB.next;
        }
        return null;
    }

    // leetcode 2, https://leetcode.com/problems/add-two-numbers/
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        ListNode i = l1;
        ListNode j = l2;
        ListNode head = new ListNode(-1);
        ListNode temp = head;
        int carry = 0;
        while(i != null || j != null || carry != 0) {
            int ival = i == null ? 0 : i.val;
            i = i == null ? null : i.next;
            int jval = j == null ? 0 : j.val;
            j = j == null ? null : j.next;
            int sum = ival + jval + carry;
            int val = sum % 10;
            carry = sum / 10;
            ListNode nn = new ListNode(val);
            temp.next = nn;
            temp = nn;
        }
        head = reverseList(head.next);
        l1 = reverseList(l1);
        l2 = reverseList(l2);
        return head;
    }

    public static void main(String[] args) {
        
    }
}