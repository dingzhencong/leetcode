// 电话号码的字母组合
//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 字符串 回溯算法 
// 👍 997 👎 0

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfAPhoneNumber{
    public static void main(String[] args){
        Solution solution = new LetterCombinationsOfAPhoneNumber().new Solution();
        
    }
    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> letterCombinations(String digits) {
            List<String> resultList = new ArrayList<>();
            HashMap<Character, String> numberMap = new HashMap<>();
            numberMap.put('2', "abc");


            return resultList;
        }

        private void backTrack(List<String> resultList, HashMap<Character, String> numberMap, String digits,int index, StringBuffer stringBuffer) {
            if (index == digits.length()) {
                resultList.add(stringBuffer.toString());
            } else {
                char digit = digits.charAt(index);
                String chars = numberMap.get(digit);
            }
        }

}
//leetcode submit region end(Prohibit modification and deletion)

}

