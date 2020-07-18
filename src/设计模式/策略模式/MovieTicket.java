package 设计模式.策略模式;

public class MovieTicket {

    private double price;
    private Discount discount;

    public double getPrice() {
        return this.discount.calculate(this.price);
    }

    /**
     * @param discount
     */
    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    /**
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

}