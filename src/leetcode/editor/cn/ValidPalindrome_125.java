package leetcode.editor.cn;
//给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。 
//
// 说明：本题中，我们将空字符串定义为有效的回文串。 
//
// 示例 1: 
//
// 输入: "A man, a plan, a canal: Panama"
//输出: true
// 
//
// 示例 2: 
//
// 输入: "race a car"
//输出: false
// 
// Related Topics 双指针 字符串 
// 👍 330 👎 0

//验证回文串
class ValidPalindrome_125{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPalindrome(String s) {
        for (int i = 0, j = s.length() - 1; i < j; ) {
            Character first = Character.toLowerCase(s.charAt(i));
            Character second = Character.toLowerCase(s.charAt(j));
            if (first == ' ') {
                i++;
                continue;
            }
            if (second == ' ') {
                j--;
                continue;
            }
            if (first != second) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}