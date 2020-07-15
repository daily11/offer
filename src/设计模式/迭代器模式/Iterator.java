package 设计模式.迭代器模式;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Iterator {
    List<Student> list;
    int cursor = 0;

    public Iterator(Aggregate aggregate) {
        this.list = aggregate.getList();
    }

    public boolean hasNext() {
        return cursor != this.list.size();
    }

    public Student next() {
        return list.get(cursor++);
    }

    public void sort() {
        int len = list.size();
        Student[] arr = list.toArray(new Student[]{});
        // 冒泡排序
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (arr[j].getAge() < arr[j + 1].getAge()) {
                    Student stu = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = stu;
                }
            }
        }
        // 将排序后的数组重新装进List中
        list = Arrays.asList(arr);

//        Collections.sort(list, new Comparator<Student>() {
//            @Override
//            public int compare(Student o1, Student o2) {
//                return o1.getAge() - o2.getAge();
//            }
//        });
    }
}
