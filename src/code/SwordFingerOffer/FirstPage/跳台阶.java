package code.SwordFingerOffer.FirstPage;

/**
 * 题目：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * <p>
 * 题解：
 * 由于它可以跳1级台阶或者2级台阶，所以它上一步必定在第n-1,或者第n-2级台阶，也就是说它跳上n级台阶的跳法数是跳上n-1和跳上n-2级台阶的跳法数之和。
 */
public class 跳台阶 {
    public static int JumpFloor(int target) {
        if (target == 1) {
            return 1;
        } else if (target == 2) {
            return 2;
        } else {
            return JumpFloor(target - 1) + JumpFloor(target - 2);
        }
    }

    public static void main(String[] args) {
        System.out.println(JumpFloor(11));
    }
}
