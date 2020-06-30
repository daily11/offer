package 线程.fork.join;

import 线程.fork.join.CountRecursion;
import 线程.fork.join.CountTask;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws Exception {
        run();

    }

    public static void run() throws Exception {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        // 生成一个计算任务，负责计算1+2+3+4
        CountTask task = new CountTask(1, 50);
        // 执行一个任务

        long start = System.currentTimeMillis();
        Future<Integer> future = forkJoinPool.submit(task);
        System.out.println(future.get());
        long end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) / 1000.0 + "秒");

        start = System.currentTimeMillis();
        CountRecursion countRecursion = new CountRecursion(1, 50);
        System.out.println(countRecursion.compute());
        end = System.currentTimeMillis();
        System.out.println("用时：" + (end - start) / 1000.0 + "秒");

    }


}
