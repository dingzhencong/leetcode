package leetcode.editor.cn;
//给定一个整数，编写一个函数来判断它是否是 2 的幂次方。 
//
// 示例 1: 
//
// 输入: 1
//输出: true
//解释: 20 = 1 
//
// 示例 2: 
//
// 输入: 16
//输出: true
//解释: 24 = 16 
//
// 示例 3: 
//
// 输入: 218
//输出: false 
// Related Topics 位运算 数学

//2的幂
class PowerOfTwo_231{
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) { return false; }    // 负数
//        int count = 0;      // 计数器
//        while (n != 0) {
//            if ((n & 0x1) == 1) { count++; }    // 每次取一位判断
//            n >>>= 1;       // 注意使用逻辑右移，不过对于正数，算术右移和逻辑右移效果是一样的
//        }
//        return count == 1 ? true : false;
        return (n & (n - 1)) == 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}