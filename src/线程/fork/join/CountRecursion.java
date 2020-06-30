package 线程.fork.join;

public class CountRecursion {
    private static final int THRESHOLD = 2;// 阈值
    private int start;
    private int end;
    private static int index = 0;

    public CountRecursion(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public Integer compute() {
        int sum = 0;
        // 如果任务足够小就计算任务
        boolean canCompute = (end - start) <= THRESHOLD;
        if (canCompute) {
            for (int i = start; i <= end; i++) {
                sum += i;
            }
            try {
                Thread.currentThread().sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountRecursion leftTask = new CountRecursion(start, middle);
            CountRecursion rightTask = new CountRecursion(middle + 1, end);
            // 等待子任务执行完，并得到其结果
            int leftResult = leftTask.compute();
            int rightResult = rightTask.compute();
            // 合并子任务
            sum = leftResult + rightResult;
        }
        return sum;
    }
}
