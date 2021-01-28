package leetcode.editor.cn;
//请你仅使用两个栈实现先入先出队列。队列应当支持一般队列的支持的所有操作（push、pop、peek、empty）： 
//
// 实现 MyQueue 类： 
//
// 
// void push(int x) 将元素 x 推到队列的末尾 
// int pop() 从队列的开头移除并返回元素 
// int peek() 返回队列开头的元素 
// boolean empty() 如果队列为空，返回 true ；否则，返回 false 
// 
//
// 
//
// 说明： 
//
// 
// 你只能使用标准的栈操作 —— 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
// 
// 你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。 
// 
//
// 
//
// 进阶： 
//
// 
// 你能否实现每个操作均摊时间复杂度为 O(1) 的队列？换句话说，执行 n 个操作的总时间复杂度为 O(n) ，即使其中一个操作可能花费较长时间。 
// 
//
// 
//
// 示例： 
//
// 
//输入：
//["MyQueue", "push", "push", "peek", "pop", "empty"]
//[[], [1], [2], [], [], []]
//输出：
//[null, null, null, 1, 1, false]
//
//解释：
//MyQueue myQueue = new MyQueue();
//myQueue.push(1); // queue is: [1]
//myQueue.push(2); // queue is: [1, 2] (leftmost is front of the queue)
//myQueue.peek(); // return 1
//myQueue.pop(); // return 1, queue is [2]
//myQueue.empty(); // return false
// 
// 提示：
//
// 
// 1 <= x <= 9 
// 最多调用 100 次 push、pop、peek 和 empty 
// 假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作） 
// 
// Related Topics 栈 设计

import java.util.Stack;

//用栈实现队列
class ImplementQueueUsingStacks_232{
//leetcode submit region begin(Prohibit modification and deletion)
class MyQueue {
    private Stack<Integer> s1;//入栈
    private Stack<Integer> s2;//出栈
    /** Initialize your data structure here. */
    //构造方法
    public MyQueue(){
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    /** Push element x to the back of queue. */
    //入栈
    public void push(int x) {
        /*
        1.首先给s1入栈
         */
        s1.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        /*
        1.如果s2为空，则将s1(是否为空)全部的值先移动到s2
        2.如果s2有值，则直接弹出
         */
        if (s2.empty()){
            while(!s1.empty()){
                s2.push(s1.pop());
            }
        }
        //这个判断条件就是去除s1为空，从而导致s2也为空的情况
        if (!s2.empty()){
            return s2.pop();
        }
        return -1;
    }

    /** Get the front element. */
    public int peek() {
        if (s2.empty()){
            while(!s1.empty()){
                s2.push(s1.pop());
            }
        }
        if (!s2.empty()){
            return s2.peek();
        }
        return -1;

    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        if (s1.empty() && s2.empty()){
            return true;
        }
        return false;
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
//leetcode submit region end(Prohibit modification and deletion)


}