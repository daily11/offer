package 设计模式.策略模式;

public class VIPDiscount implements Discount {
    private final static double VIP_DISCOUNT = 0.5;

    /**
     * @param price
     */
    public double calculate(double price) {
        System.out.println("vip半折后票价：" + price * VIP_DISCOUNT);
        return price * VIP_DISCOUNT;
    }

}