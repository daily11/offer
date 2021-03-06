package code.SwordFingerOffer.SecondPage;

import java.util.Stack;

/**
 * 题目：
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。（注意：这两个序列的长度是相等的）
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/d77d11405cc7470d82554cb392585106
 * 来源：牛客网
 * <p>
 * 假设有一串数字要将他们压栈: 1 2 3 4 5 如果这个栈是很大很大，那么一次性全部压进去，再出栈：5 4 3 2 1
 * 但是，如果这个栈高度为4，会发生什么？
 * 1 2 3 4都顺利入栈，但是满了，那么要先出栈一个，才能入栈，那么就是先出4，然后压入5，随后再全部出栈：4 5 3 2 1
 * 那么我总结了所有可能的出栈情况:
 * 5 4 3 2 1//栈高度为5
 * 4 5 3 2 1//栈高度为4
 * 3 4 5 2 1//栈高度为3
 * 2 3 4 5 1//栈高度为2
 * 1 2 3 4 5//栈高度为1
 * 借助一个辅助的栈，遍历压栈的顺序，依次放进辅助栈中。
 * 对于每一个放进栈中的元素，栈顶元素都与出栈的popIndex对应位置的元素进行比较，是否相等，相等则popIndex++，再判断，直到为空或者不相等为止。
 */
public class 栈的压入弹出序列 {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        //数组为空的情况
        if (pushA.length == 0 || popA.length == 0) {
            return false;
        }
        //弹出序列的下表索引
        int popIndex = 0;
        //辅助栈
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < pushA.length; i++) {
            //不停地将pushA中的元素压入栈中，一旦栈顶元素与popA相等了，则开始出栈
            // 不相等则继续入栈
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[popIndex]) {
                stack.pop();
                popIndex++;
            }
        }
        //栈中没有元素了说明元素全部一致，并且符合弹出顺序，那么返回true
        return stack.isEmpty();
    }
}
