package leetcode.editor.cn;
//编写一个程序，找到两个单链表相交的起始节点。 
//
// 如下面的两个链表： 
//
// 
//
// 在节点 c1 开始相交。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, s
//kipB = 3
//输出：Reference of the node with value = 8
//输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1
//,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
// 
//
// 
//
// 示例 2： 
//
// 
//
// 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB =
// 1
//输出：Reference of the node with value = 2
//输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4
//]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
// 
//
// 
//
// 示例 3： 
//
// 
//
// 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
//输出：null
//输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而
// skipA 和 skipB 可以是任意值。
//解释：这两个链表不相交，因此返回 null。
// 
//
// 
//
// 注意： 
//
// 
// 如果两个链表没有交点，返回 null. 
// 在返回结果后，两个链表仍须保持原有的结构。 
// 可假定整个链表结构中没有循环。 
// 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。 
// 
// Related Topics 链表 
// 👍 924 👎 0

//相交链表
class IntersectionOfTwoLinkedLists_160{
    public static void main(String[] args) {
        //listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
        Solution solution = new IntersectionOfTwoLinkedLists_160().new Solution();
        ListNode listNodeA1 = new ListNode(4);
        ListNode listNodeA2 = new ListNode(1);
        ListNode listNodeA3 = new ListNode(8);
        ListNode listNodeA4 = new ListNode(4);
        ListNode listNodeA5 = new ListNode(5);
        listNodeA1.next = listNodeA2;
        listNodeA2.next = listNodeA3;
        listNodeA3.next = listNodeA4;
        listNodeA4.next = listNodeA5;
        ListNode listNodeB1 = new ListNode(5);
        ListNode listNodeB2 = new ListNode(0);
        listNodeB1.next = listNodeB2;
        listNodeB2.next = listNodeA2;
        ListNode node = solution.getIntersectionNode(listNodeA1, listNodeB1);
        System.out.println(node.val);

    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        //listA = [4,1,8,4,5],
        //listB = [5,0,1,8,4,5]
        while (nodeA != nodeB) {
            System.out.println("nodeA.val=" + (nodeA == null ? ("nodeA == null  "+headB.val) : nodeA.val));
            System.out.println("nodeB.val=" + (nodeB == null ? ("nodeB == null  "+headA.val) : nodeB.val));
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
            System.out.println("");
        }
        return nodeA;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}