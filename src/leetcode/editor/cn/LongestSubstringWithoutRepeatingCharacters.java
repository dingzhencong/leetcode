// 无重复字符的最长子串
//给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 示例 1: 
//
// 输入: "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 输入: "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 输入: "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
// Related Topics 哈希表 双指针 字符串 Sliding Window

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters{
    public static void main(String[] args){
        Solution solution = new LongestSubstringWithoutRepeatingCharacters().new Solution();
        solution.lengthOfLongestSubstring("abba");
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    @SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
    class Solution {
    public int lengthOfLongestSubstring(String s) {
        /*int max = 0;
        HashMap<Character, Integer> characterIntegerHashMap = new HashMap<>();
        for (int start = 0, end = 0; end < s.length(); end++) {
            char alpa = s.charAt(start);
            if (characterIntegerHashMap.containsKey(alpa)) {
                start = Math.max(start, characterIntegerHashMap.get(alpa));
            }
            max = Math.max(max, end - start + 1);
            characterIntegerHashMap.put(s.charAt(end), end + 1);
        }
        return max;*/
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < n; end++) {
            char alpha = s.charAt(end);
            if (map.containsKey(alpha)) {
                start = Math.max(map.get(alpha), start);
//                start = map.get(alpha);
            }
            ans = Math.max(ans, end - start + 1);
            map.put(s.charAt(end), end + 1);
        }
        return ans;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

