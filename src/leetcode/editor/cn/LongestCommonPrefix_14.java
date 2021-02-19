package leetcode.editor.cn;
//编写一个函数来查找字符串数组中的最长公共前缀。 
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 0 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 
// 👍 1461 👎 0

//最长公共前缀
class LongestCommonPrefix_14{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if(strs.length == 0)
            return "";
        String str1 = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str2 = strs[i];
            int j = str1.length();
            for (; j > 0; j--) {
                if (str2.startsWith(str1)) {
                    break;
                }
            }
            str1 = str1.substring(0, j);
            if(str1.equals(""))
                return str1;
        }
        return str1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}