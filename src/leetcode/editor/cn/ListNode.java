package leetcode.editor.cn;

public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }

    ListNode(int x, ListNode next) {
        this.next = next;
        val = x;
    }
}
