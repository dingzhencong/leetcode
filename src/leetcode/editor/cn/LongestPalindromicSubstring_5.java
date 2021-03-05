package leetcode.editor.cn;
//给你一个字符串 s，找到 s 中最长的回文子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 
// 👍 3101 👎 0

//最长回文子串
class LongestPalindromicSubstring_5{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public String longestPalindrome(String s) {
        String longest = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = longest(s, i, i);
            String s2 = longest(s, i, i + 1);
            longest = s1.length() > longest.length() ? s1 : longest;
            longest = s2.length() > longest.length() ? s2 : longest;
        }
        return longest;
    }

    public String longest(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(right) == s.charAt(left)) {
            left--;
            right++;
        }
        return s.substring(++left, right);
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}