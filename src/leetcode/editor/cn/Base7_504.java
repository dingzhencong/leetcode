package leetcode.editor.cn;
//给定一个整数，将其转化为7进制，并以字符串形式输出。 
//
// 示例 1: 
//
// 
//输入: 100
//输出: "202"
// 
//
// 示例 2: 
//
// 
//输入: -7
//输出: "-10"
// 
//
// 注意: 输入范围是 [-1e7, 1e7] 。 
// 👍 72 👎 0

//七进制数
class Base7_504{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convertToBase7(int num) {
        char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6'};
        //33是因为有符号int转化为2进制最长为32位，加符号即为33位
        char[] buf = new char[33];
        boolean negative = (num < 0);
        int charPos = 32;
        //同一转换为正数来处理
        if(negative){
            num = -num;
        }
        //这么做是为了解决0的特殊情况
        while(num >= 7){
            buf[charPos--] = digits[num % 7];
            num /= 7;
        }
        buf[charPos] = digits[num];
        if(negative){
            buf[--charPos] = '-';
        }
        //new String(char[], offset, length)
        return new String(buf, charPos, 33 - charPos);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}