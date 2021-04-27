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

    public static int length(ListNode head) {
        int len = 0;
        while(head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    public static ListNode thead = null;
    public static ListNode ttail = null;

    public static void addFirst(ListNode node) {
        if(thead == null) {
            thead = ttail = node; 
        } else {
            node.next = thead;
            thead = node;
        }
    }

    public static ListNode reverseInKGroup(ListNode node, int K) {
        if(node == null || node.next == null || K == 0) return node;

        int len = length(node);

        thead = ttail = null;

        ListNode head = null;
        ListNode tail = null;
        ListNode temp = node;
        int k = K;
        while(len >= k) {
            ListNode next = temp.next;
            addFirst(temp);
            temp = next;
            k--;
            len--;

            if(k == 0) {
                if(head == null) {
                    head = thead;
                    tail = ttail;
                } else {
                    tail.next = thead;
                    tail = ttail;
                }
                thead = ttail = null;
                k = K;
            }
        }

        tail.next = temp;
        return head;
    }

    public static ListNode reverseInRange(ListNode head, int n, int m) {
        if(head == null || head.next == null || n == m) return head;

        thead = thead = null;
        int indx = 1;
        ListNode prev = null;
        ListNode temp = head;

        while(temp != null) {
            while(indx >= n && indx <= m) {
                ListNode next = temp.next;
                addFirst(temp);
                temp = next;
                indx++;
            }

            if(indx > m) {
                if(prev != null) {
                    prev.next = thead;
                    ttail.next = temp;
                } else {
                    head = thead;
                    ttail.next = temp;
                }
                return head;
            }

            indx++;
            prev = temp;
            temp = temp.next;
        }
        return head;
    }

    public static ListNode segregateEvenOdd(ListNode head) {
        if(head == null || head.next == null) return head;
        // dummy nodes
        ListNode ohead = new ListNode(-1);
        ListNode ehead = new ListNode(-1);
    
        // make tail of odd list and even list for iteration
        ListNode otail = ohead, etail = ehead;

        ListNode temp = head;

        while(temp != null) {
            if(temp.val % 2 == 0) {
                // even
                etail.next = temp;
                etail = temp;
            } else {
                // odd
                otail.next = temp;
                otail = temp;
            }
            temp = temp.next;
        }

        etail.next = ohead.next;
        otail.next = null;
        return ehead.next;
    }

    public static ListNode IntersectionNodeInTwoLLDifferenceApproach(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;

        int s1 = length(headA);
        int s2 = length(headB);

        if(s1 > s2) {
            int itr = s1 - s2;
            while(itr > 0) {
                headA = headA.next;
                itr--;
            }
        } else if(s2 > s1) {
            int itr = s2 - s1;
            while(itr > 0) {
                headB = headB.next;
                itr--;
            }
        }

        while(headA != null) {
            if(headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    public static ListNode getTail(ListNode head) {
        while(head.next != null) {
            head = head.next;
        }
        return head;
    }

    public static ListNode IntersectionNodeInTwoLLCyclicApproach(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) return null;
        ListNode tail = getTail(headA);
        tail.next = headA;
        return CycleNode(headB);
    }

    public static ListNode copyRandomList(ListNode head) {
        ListNode t1 = null, t2 = null;
        // 1. Make normal clone list
        ListNode chead = null;
        t1 = head; // helper for movement in original list
        t2 = chead; // helper for movement in clonned list
        while(t1 != null) {
            ListNode nn = new ListNode(t1.val);
            if(chead == null) {
                chead = nn;
            } else {
                t2.next = nn;
            }
            t2 = nn;
            t1 = t1.next;
        }
        // 2. Alternate Arrangement
        t1 = head;
        t2 = chead;

        while(t2 != null) {
            ListNode f1 = t1.next; // forward 1
            ListNode f2 = t2.next; // forward 2

            t1.next = t2;
            t2.next = f1;

            t1 = f1;
            t2 = f2;
        }

        // 3. Set random pointers in clone
        t1 = head; // this is temporary pointer
        while(t1 != null) {
            t1.next.random = t1.random == null ? null : t1.random.next;
            t1 = t1.next.next;
        }

        // 4. Recover original list
        ListNode olist = new Listnode(-1); // dummy node for original list
        ListNode clist = new Listnode(-1); // dummy node for clonned list

        t1 = olist;
        t2 = clist;
        ListNode temp = head;
        while(temp != null) {
            t1.next = temp;
            t1 = temp;

            t2.next = temp.next;
            t2 = temp.next;

            temp = temp.next.next;
        }
        t1.next = null;
        return chead;
    }


    public static ListNode reverse(ListNode head) {
        ListNode prev = null, curr = head;
        
        while(curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        l1 = reverse(l1);
        l2 = reverse(l2);
        
        ListNode i = l1;
        ListNode j = l2;

        ListNode ans = new ListNode(-1);
        ListNode temp = ans;
        int carry = 0;

        while(i != null || j != null || carry == 1) {
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

        ans = reverse(ans.next);
        return ans;
    }

    public static ListNode removeDuplicates(ListNode head) {
        ListNode nhead = new ListNode(-1);
        ListNode temp = nhead, itr = head;

        while(itr != null) {
            if(itr.val != temp.val) {
                temp.next = itr;
                temp = itr;
            }
            itr = itr.next;
        }
        temp.next = null;
        return nhead.next;
    }

    public static ListNode subtractTwoNumbers(ListNode l1, ListNode l2) {
        return null;
    }

    public static ListNode removeDuplicates2(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode nhead = new ListNode(-1);
        ListNode temp = nhead, temp2 = head.next;
        
        temp.next = head;

        while(temp2 != null) {
            boolean flag = false;
            while(temp2 != null && temp.next.val == temp2.val) {
                flag = true;
                temp2 = temp2.next;
            }

            if(flag == true) {
                temp.next = temp2;
            } else {
                temp = temp.next;
            }

            if(temp2 != null)
                temp2 = temp2.next;
        }
        return nhead.next;
    }

    public static void main(String[] args) {
        return;
    }
}