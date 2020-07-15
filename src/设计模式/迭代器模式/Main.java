package 设计模式.迭代器模式;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Student> list = new ArrayList<>();
        list.add(new Student("chen", 28));
        list.add(new Student("zou", 26));
        list.add(new Student("yang", 18));

        Aggregate aggregate = new Aggregate();
        aggregate.setList(list);

        Iterator iterator = aggregate.createIterator();
        iterator.sort();
        while (iterator.hasNext()) {
            Student student = iterator.next();
            System.out.println(student);
        }
    }
}
