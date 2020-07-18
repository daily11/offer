package 设计模式.策略模式;

public interface Discount {

    /**
     * @param price
     */
    double calculate(double price);

}