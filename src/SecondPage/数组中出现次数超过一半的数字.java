package SecondPage;

import java.util.Arrays;

/**
 * 数组排序后，如果符合条件的数存在，则一定是数组中间那个数
 * https://www.nowcoder.com/profile/519263/codeBookDetail?submissionId=1520973g
 */
public class 数组中出现次数超过一半的数字 {
    public int MoreThanHalfNum_Solution(int[] array) {
        if (array == null)
            return 0;

        int len = array.length;
        int count = 0;
        Arrays.sort(array);

        int target = array[len / 2];
        for (int i = 0; i < len; i++) {
            if (target == array[i])
                count++;
        }
        if (count <= (len / 2)) {
            target = 0;
        }
        return target;
    }
}
