package 设计模式.策略模式;

public class Client {

    public static void main(String[] args) {
        Discount student = new StudentDiscount();
        Discount vip = new VIPDiscount();

        MovieTicket movieTicket = new MovieTicket();
        movieTicket.setPrice(100);
        System.out.println("票价原价：" + 100);

        movieTicket.setDiscount(student);
        movieTicket.getPrice();

        movieTicket.setDiscount(vip);
        movieTicket.getPrice();

    }
}
