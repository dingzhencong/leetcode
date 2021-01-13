package leetcode.editor.cn;
//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。 
//
// 
// push(x) —— 将元素 x 推入栈中。 
// pop() —— 删除栈顶的元素。 
// top() —— 获取栈顶元素。 
// getMin() —— 检索栈中的最小元素。 
// 
//
// 
//
// 示例: 
//
// 输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// pop、top 和 getMin 操作总是在 非空栈 上调用。 
// 
// Related Topics 栈 设计 
// 👍 764 👎 0

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

//最小栈
class MinStack_155{
    public static void main(String[] args) {
        MinStack minStack = new MinStack_155().new MinStack();
        minStack.push(2);
        minStack.push(1);
        minStack.push(3);
        minStack.pop();
        int param_3 = minStack.top();
        int param_4 = minStack.getMin();
    }
//leetcode submit region begin(Prohibit modification and deletion)
class MinStack {
    Stack<Integer> mStack;
    Stack<Integer> stack;

    /** initialize your data structure here. */
    public MinStack() {
        mStack = new Stack<>();
        stack = new Stack<>();
        mStack.push(Integer.MAX_VALUE);
    }

    public void push(int x) {
        mStack.push(Math.min(mStack.peek(), x));
        stack.push(x);
    }

    public void pop() {
        stack.pop();
        mStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return mStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)


}