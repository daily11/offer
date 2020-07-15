package 设计模式.职责链模式;

public abstract class People {
    protected People next;
    protected String name;

    public People(String name) {
        this.name = name;
    }

    public void setNext(People next) {
        this.next = next;
    }

    public abstract void handleRequest(int day);
}
