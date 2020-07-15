package 设计模式.迭代器模式;
import java.util.*;

public class Aggregate {
    List<Student> list;

    public List<Student> getList() {
        return list;
    }

    public void setList(List<Student> list) {
        this.list = list;
    }

    public Iterator createIterator() {
        return new Iterator(this);
    }
}
