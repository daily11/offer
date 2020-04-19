package FirstPage;

/**
 * 题目：
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
 * 输入一个---非递减-排序---的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
 * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/9f3231a991af4f55b95579b44b7a01ba?answerType=1&f=discussion
 * 参考案例：
 * 1、 4 5 6 7 8 1 2 3
 * 2、 1 1 1 0 1
 */
public class 旋转数组的最小值 {
    int minNumberInRotateArray(int[] rotateArray) {
        if (rotateArray.length == 0)
            return 0;
        int low = 0;
        int high = rotateArray.length - 1;
        int mid = 0;
        while (low < high) {
            if (rotateArray[low] < rotateArray[high])
                return rotateArray[low];
            mid = low + (high - low) / 2;
            if (rotateArray[mid] > rotateArray[low])
                low = mid + 1;
            else if (rotateArray[mid] < rotateArray[high])
                high = mid;
            else low++;
        }
        return rotateArray[low];
    }
}

