package leetcode.editor.cn;
//给定两个二叉树，编写一个函数来检验它们是否相同。 
//
// 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。 
//
// 示例 1: 
//
// 输入:       1         1
//          / \       / \
//         2   3     2   3
//
//        [1,2,3],   [1,2,3]
//
//输出: true 
//
// 示例 2: 
//
// 输入:      1          1
//          /           \
//         2             2
//
//        [1,2],     [1,null,2]
//
//输出: false
// 
//
// 示例 3: 
//
// 输入:       1         1
//          / \       / \
//         2   1     1   2
//
//        [1,2,1],   [1,1,2]
//
//输出: false
// 
// Related Topics 树 深度优先搜索

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

//相同的树
class SameTree_100{
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String s = "12312312";
        System.out.println("使用库函数转换：" + Integer.valueOf(s));
        int res = StrToInt(s);
        System.out.println("使用自己写的方法转换：" + res);

    }

    public static int StrToInt(String string) {
        char[] chars = string.toCharArray();
        int flag = 0;
        if (chars[0] == '-') {
            flag = 1;
        } else if (chars[0] == '+') {
            flag = 2;
        }
        int start = flag > 0 ? 1 : 0;
        int num = 0;
        for (int i = start; i < chars.length; i++) {
            Character character = chars[i];
            if (Character.isDigit(character)) {
                int tem = character - '0';
                num = num * 10 + tem;
            } else {
                return 0;
            }
        }
        return flag == 1 ? -num : num;
    }
//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}