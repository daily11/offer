package 设计模式.观察者模式;

import java.util.Observable;
import java.util.Observer;

public class Subscribe implements Observer {

    @Override
    public void update(Observable o, Object arg) {
        Publish publish = (Publish) o;
        System.out.println("订阅者接收信息--->" + publish.getData());
    }
}
