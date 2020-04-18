package FirstPage;

import java.util.Stack;

/**
 * 题目：
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 *
 * 链接：https://www.nowcoder.com/questionTerminal/54275ddae22f475981afa2244dd448c6?answerType=1&f=discussion
 * 来源：牛客网
 *
 * 题解：
 * 1 当插入时，直接插入 stack1
 * 2 当弹出时，
 * 如果 stack2 不为空，弹出 stack2 栈顶元素，
 * 如果 stack2 为空，将 stack1 中的全部数逐个出栈入栈 stack2，再弹出 stack2 栈顶元素
 */
public class 用两个栈实现队列 {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if(stack2.isEmpty()) {
            while(!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}