package FirstPage;

import java.util.ArrayList;

public class 顺序打印数组 {
    public ArrayList<Integer> printMatrix(int[][] matrix) {
        if (matrix == null)
            return null;
        ArrayList<Integer> list = new ArrayList<Integer>();
        int row = matrix.length;
        int col = matrix[0].length;
        int left = 0, top = 0, right = col - 1, bottom = row - 1;
        while (left <= right && top <= bottom) {
            //从左向右
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            //从上到下（从下一行开始向下走）
            for (int j = top + 1; j <= bottom; j++) {
                list.add(matrix[j][right]);
            }
            //从右到左
            if (top != bottom) {
                for (int k = right - 1; k >= left; k--) {
                    list.add(matrix[bottom][k]);
                }
            }
            //从下到上
            if (left != right) {
                for (int l = bottom - 1; l > top; l--) {
                    list.add(matrix[l][left]);
                }
            }

            //下一个正方形矩阵
            top++;
            left++;
            right--;
            bottom--;

        }
        return list;
    }
}
