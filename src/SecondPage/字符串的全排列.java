package SecondPage;

import java.util.ArrayList;
import java.util.Collections;

public class 字符串的全排列 {
    public ArrayList<String> Permutation(String str) {
        ArrayList<String> list = new ArrayList<>();
        if (str == null || "".equals(str))
            return list;
        char[] arr = str.toCharArray();
        fullSort(arr, 0, arr.length - 1, list);
        Collections.sort(list);
        return list;
    }

    public static void fullSort(char[] arr, int start, int end, ArrayList<String> list) {
        // 递归终止条件
        if (start == end) {
            String val = String.valueOf(arr);
            if (!list.contains(val)) {
                list.add(val);
            }
            return;
        }
        for (int i = start; i <= end; i++) {
            swap(arr, i, start);
            fullSort(arr, start + 1, end, list);
            swap(arr, i, start);
        }
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}