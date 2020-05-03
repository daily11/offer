package code.FirstPage;

/**
 * 定义：
 * F(1)=1，F(2)=1, F(n)=F(n - 1)+F(n - 2)（n ≥ 3，n ∈ N*）
 * <p>
 * 题目：
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。n<=39
 */
public class 斐波那契数列 {
    public int Fibonacci(int n) {
        if (n > 39 || n <= 0) {
            return n;
        } else if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 1;
        } else {
            return Fibonacci(n - 1) + Fibonacci(n - 2);
        }
    }
}
