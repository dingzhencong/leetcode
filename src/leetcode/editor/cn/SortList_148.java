package leetcode.editor.cn;
//给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。 
//
// 进阶： 
//
// 
// 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？ 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：head = [4,2,1,3]
//输出：[1,2,3,4]
// 
//
// 示例 2： 
//
// 
//输入：head = [-1,5,3,4,0]
//输出：[-1,0,3,4,5]
// 
//
// 示例 3： 
//
// 
//输入：head = []
//输出：[]
// 
//
// 
//
// 提示： 
//
// 
// 链表中节点的数目在范围 [0, 5 * 104] 内 
// -105 <= Node.val <= 105 
// 
// Related Topics 排序 链表 
// 👍 953 👎 0

import java.util.List;

//排序链表
class SortList_148{
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    /**
     * public ListNode sortList(ListNode head) {
     *         if(head==null || head.next==null) return head;
     *         ListNode slow = head; //慢指针
     *         ListNode fast = head.next; //快指针
     *
     *         while(fast!=null && fast.next!=null){ //快慢指针找到链表中点
     *             slow = slow.next; //慢指针走一步
     *             fast = fast.next.next; //快指针走两步
     *         }
     *         ListNode rightHead = slow.next; //链表第二部分的头节点
     *         slow.next = null; //cut 链表
     *
     *         ListNode left = sortList(head); //递归排序前一段链表
     *         ListNode right = sortList(rightHead); //递归排序后一段链表
     *         return merge(left,right);
     *     }
     *     public ListNode merge(ListNode h1,ListNode h2){ //合并两个有序链表
     *         ListNode dummy = new ListNode(-1);
     *         ListNode p = dummy;
     *         while(h1!=null && h2!=null){
     *             if(h1.val < h2.val){
     *                 p.next = h1;
     *                 h1 = h1.next;
     *             }else{
     *                 p.next = h2;
     *                 h2 = h2.next;
     *             }
     *             p = p.next;
     *         }
     *         if(h1!=null)    p.next = h1;
     *         else if(h2!=null) p.next = h2;
     *         return dummy.next;
     *     }
     */
    public ListNode sortList(ListNode head) {
        if(head==null || head.next==null) return head;
        ListNode slow = head;
        ListNode fast = head.next;
        if (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode right = slow.next;
        slow.next = null;
        ListNode left = sortList(head);
        ListNode rightListNode = sortList(right);
        return mergeList(left, rightListNode);
    }

    public ListNode mergeList(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;

        return head.next;
    }


}
//leetcode submit region end(Prohibit modification and deletion)


}