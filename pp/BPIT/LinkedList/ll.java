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

    public static void main(String[] args) {
        return;
    }
}