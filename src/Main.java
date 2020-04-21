import util.ArrayUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.remove(list.size()-1);
        System.out.println(list);
    }
}
