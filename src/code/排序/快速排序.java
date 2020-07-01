package code.排序;

public class 快速排序 {

    public static void main(String[] args) {
        int[] data = new int[]{3, 2, 5, 12};
        sort(data, 0, data.length - 1);
        for (int i : data) {
            System.out.print(i + " , ");
        }
    }

    public static void sort(int[] data, int lf, int rg) {
        int lf_idx, rg_idx;
        if (lf < rg) {
            lf_idx = lf + 1;
            rg_idx = rg;
            while (true) {
                // 从左到右找到第一个大于等于d[lf]的值
                for (int i = lf + 1; i <= rg; i++) {
                    if (data[i] > data[lf]) {
                        lf_idx = i;
                        break;
                    }
                    lf_idx++;
                }
                // 从右到左找到第一个小于等于d[lf]的值
                // 不可以写成j>=lf_idx,因lf_idx在上面for中已经变了
                for (int i = rg; i >= lf + 1; i--) {
                    if (data[i] <= data[lf]) {
                        rg_idx = i;
                        break;
                    }
                    rg_idx--;
                }
                // 转换d[lf_idx]与d[rg_idx]的值
                if (lf_idx < rg_idx) {
                    int tmp = data[lf_idx];
                    data[lf_idx] = data[rg_idx];
                    data[rg_idx] = tmp;
                } else {
                    break;
                }
            }
            if (lf_idx >= rg_idx) {
                // 转换d[lf]与d[rg_idx]的值
                int tmp = data[lf];
                data[lf] = data[rg_idx];
                data[rg_idx] = tmp;

                // 递归左侧快速排序
                sort(data, lf, rg_idx - 1);
                // 递归右侧快速排序
                sort(data, rg_idx + 1, rg);
            }
        }
    }
}
