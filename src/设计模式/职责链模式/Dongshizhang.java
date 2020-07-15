package 设计模式.职责链模式;

public class Dongshizhang extends People {
    public Dongshizhang(String name) {
        super(name);
    }

    @Override
    public void handleRequest(int day) {
        if (day < 30 && day >= 10) {
            System.out.println(this.name + "审批通过！");
        } else {
            System.out.println(this.name + "拒绝请假！");
        }
    }
}
