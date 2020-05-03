import util.ArrayUtil;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,2,2,2,5,4,2};

        String str = "A,B,C";
        int len = str.length()-str.replace(",","").length();
        System.out.println(len);

        char[] ch = str.toCharArray();
        int count = 0;
        for(char c : ch) {
            if(c == ','){
                count++;
            }
        }
        System.out.println(count);


    }
}
