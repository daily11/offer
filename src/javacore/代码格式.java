package javacore;

public class 代码格式 {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 2, 8, 1};
        sort(arr);
    }

    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }

        for (int i : arr) {
            System.out.print(i + " , ");
        }
    }
}
