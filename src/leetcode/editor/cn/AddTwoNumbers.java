// 两数相加
//给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。 
//
// 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 
//
// 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。 
//
// 示例： 
//
// 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
//输出：7 -> 0 -> 8
//原因：342 + 465 = 807
// 
// Related Topics 链表 数学

package leetcode.editor.cn;
public class AddTwoNumbers{
    public static void main(String[] args){
        Solution solution = new AddTwoNumbers().new Solution();
        
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int isTen = 0;
        ListNode firstNode = new ListNode(0);
        ListNode nextNode = firstNode;
        while (l1 != null || l2 != null) {
            int sum;
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            sum = x + y + isTen;
            if (sum > 9) {
                isTen = 1;
                sum = sum % 10;
            }
            ListNode newNode = new ListNode(sum % 10);
            nextNode.next = newNode;
            nextNode = newNode;
            if(l1 != null)
                l1 = l1.next;
            if(l2 != null)
                l2 = l2.next;

        }
        if (isTen == 1) {
            nextNode = new ListNode(1);
        }
        return firstNode.next;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

