package 设计模式.职责链模式;

public class Client {
    public static void main(String[] args) {
        People zhuren = new Zhuren("zhuren");
        People jingli = new Jingli("jingli");
        People dongshizhang = new Dongshizhang("dongshizhang");

        zhuren.setNext(jingli);
        jingli.setNext(dongshizhang);

        int day = 1;
        zhuren.handleRequest(day);

    }
}
