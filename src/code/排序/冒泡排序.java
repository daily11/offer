package code.排序;

/**
 * 冒泡排序法
 * 思路：
 * 	（1）外层进行n-1轮排序
 *  （2）内层进行n-1-i轮排序
 *  	2.1）因为内层每一次排序，最大的数都下沉到底部，因此倒数i个元素不要再排序
 *  	2.2）内层从头到尾两两排序，前者大于后者就交换二者值
 */
public class 冒泡排序 {
    public static void main(String[] args) {
        int[] data = new int[]{3, 2, 5, 12};
        sort(data);
    }

    public static void sort(int[] data) {
        for (int i = 0; i < data.length - 1; i++) {
            for (int j = 0; j < data.length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int tmp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = tmp;
                }
            }
        }
        for (int i : data) {
            System.out.print(i + " , ");
        }
    }

}
