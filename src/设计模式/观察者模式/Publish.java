package 设计模式.观察者模式;

import java.util.Observable;

public class Publish<T> extends Observable {
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
        setChanged();
        notifyObservers();
    }
}
