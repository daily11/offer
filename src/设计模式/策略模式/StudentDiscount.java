package 设计模式.策略模式;

public class StudentDiscount implements Discount {
    private final static double STUDENT_DISCOUNT = 0.8;

    /**
     * @param price
     */
    public double calculate(double price) {
        System.out.println("学生8折后票价：" + price * STUDENT_DISCOUNT);
        return price * STUDENT_DISCOUNT;
    }

}