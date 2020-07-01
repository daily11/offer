package code.SwordFingerOffer.FirstPage;

/**
 * 题目：
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
 *
 * f(n)=f(n-1)+f(n-2)+...+f(n-(n-1))+2+1
 * f(n-1)=f(n-2)+...+2+1
 * 推出下述公式：
 *          | 1       , (n=0 )
 * f(n) =   | 1       , (n=1 )
 *          | 2*f(n-1), (n>=2)
 *
 */
public class 变态跳台阶 {
    public int JumpFloorII(int target) {
        if (target <= 0) {
            return -1;
        } else if (target == 1) {
            return 1;
        } else {
            return 2 * JumpFloorII(target - 1);
        }
    }
}
