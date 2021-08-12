import java.util.*;

public class QuickSort {

    public static class ListNode {
        int val = 0;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public static class PPair {
        // PPair -> partiton pair 
        ListNode pnm1 = null; // partition node - 1
        ListNode pn; // partition node
        ListNode head2;
        ListNode tail2;

        public PPair(ListNode pnm1, ListNode pn, ListNode head2, ListNode tail2) {
            this.pnm1 = pnm1;
            this.pn = pn;
            this.head2 = head2;
            this.tail2 = tail2;
        }
    }

    public static class QPair {
        // QPair -> quicksort pair
        ListNode nhead; // new head
        ListNode ntail; // new tail

        public QPair(ListNode nhead, ListNode ntail) {
            this.nhead = nhead;
            this.ntail = ntail;
        }   
    }

    public static PPair partition(ListNode head, ListNode pivot) {
        ListNode smaller = new ListNode(-1);
        ListNode greater = new ListNode(-1);
        ListNode t1 = smaller, t2 = greater, temp = head, pnm1 = t1;

        while(temp != null) {
            if(temp.val <= pivot.val) {
                pnm1 = t1;
                t1.next = temp;
                t1 = temp;
            } else {
                t2.next = temp;
                t2 = temp;
            }
            temp = temp.next;
        }
        t2.next = null;
        t1.next = greater.next;
        return new PPair(pnm1 == smaller ? null : pnm1, pivot, smaller.next, t2);
    }

    public static QPair quickSort(ListNode head, ListNode tail) {
        if(head == tail) return new QPair(head, tail);

        ListNode pivot = tail;
        PPair part = partition(head, pivot);

        if(part.pnm1 == null) {
            // only right call
            ListNode head2 = pivot.next;
            pivot.next = null;

            QPair right = quickSort(head2, part.tail2);
            
            pivot.next = right.nhead;
            return new QPair(pivot, right.ntail);
        } else if(pivot.next == null) {
            // only left call
            part.pnm1.next = null;
            
            QPair left = quickSort(part.head2, part.pnm1);
            
            left.ntail.next = pivot;
            return new QPair(left.nhead, pivot);
        } else {
            // both left and right call
            part.pnm1.next = null;
            ListNode head2 = pivot.next;
            pivot.next = null;

            QPair left = quickSort(part.head2, part.pnm1);
            QPair right = quickSort(head2, part.tail2);

            left.ntail.next = pivot;
            pivot.next = right.nhead;

            return new QPair(left.nhead, right.ntail);
        }
    }

    public static ListNode quickSort(ListNode head) {
        if(head == null || head.next == null) return head;
        ListNode tail = head;
        while(tail.next != null)
            tail = tail.next;
        
        QPair res = quickSort(head, tail);
        return res.nhead;
    }
    
    public static void main(String[] args) {
        
    }
}
