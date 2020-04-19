package FirstPage;

/**
 * 第二种：
 *     public double Power(double base, int exponent) {
 *         if(base == 0 && exponent == 0)
 *             return -1;
 *         boolean flag = exponent<0?true:false;
 *         exponent = flag==true?-exponent:exponent;
 *         double sum = 1;
 *         for(int i=0; i<exponent; i++){
 *             sum*=base;
 *         }
 *         if(flag)
 *             sum = 1.0/sum;
 *         return sum;
 *     }
 *
 *  第三种：
 *  底层实现
 */
public class 数值的整数次方 {
    public static double Power(double base, int exponent) {
        return Math.pow(base,exponent);
    }
}
