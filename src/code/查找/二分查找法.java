package code.查找;

/**
 * 前提：待排序的数组已经是有序的！
 */
public class 二分查找法 {

    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6};
        int e = 2;
        int index = sort(data, e);
        System.out.println(index);
    }

    public static int sort(int[] data, int e) {
        int mid = -1, low = 0, high = data.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (data[mid] < e) {
                low = mid + 1;
            } else if (data[mid] > e) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
