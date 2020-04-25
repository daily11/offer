import util.ArrayUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,2,2,2,5,4,2};
        Arrays.sort(arr);
        ArrayUtil.sop(arr);
        System.out.println(arr[arr.length/2]);
    }
}
