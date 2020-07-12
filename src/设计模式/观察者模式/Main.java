package 设计模式.观察者模式;

public class Main {
    public static void main(String[] args) {
        Publish<String> publish = new Publish();
        Subscribe subscribe = new Subscribe();
        publish.addObserver(subscribe);

        publish.setData("hello world!");
    }
}
