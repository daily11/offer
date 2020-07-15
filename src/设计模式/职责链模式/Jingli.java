package 设计模式.职责链模式;

public class Jingli extends People {
    public Jingli(String name) {
        super(name);
    }

    @Override
    public void handleRequest(int day) {
        if (day < 10 && day >= 3) {
            System.out.println(this.name + "审批通过！");
        } else {
            this.next.handleRequest(day);
        }
    }
}
