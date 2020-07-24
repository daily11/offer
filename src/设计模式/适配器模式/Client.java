package 设计模式.适配器模式;

public class Client {
    public static void main(String[] args) {
        Subject subject = new Adapter(new Adaptee());
        subject.doEncrypt(123456789, "cyx");
    }
}
