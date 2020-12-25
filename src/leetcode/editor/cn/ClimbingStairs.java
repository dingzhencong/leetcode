// 爬楼梯
//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1341 👎 0

package leetcode.editor.cn;
public class ClimbingStairs{
    public static void main(String[] args){
        Solution solution = new ClimbingStairs().new Solution();
        
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int climbStairs(int n) {
        /*
        * f(x) = f(x-1) + f(x-2);
        * f(1) = 1;
        * f(2) = 2;
        *
        * */

        int f = 1, s = 2, sum = 0;
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        for (int i = 3; i <= n; i++) {
            sum = f + s;
            f = s;
            s = sum;
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}

