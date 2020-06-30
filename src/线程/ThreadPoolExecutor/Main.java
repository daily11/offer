package 线程.ThreadPoolExecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                2,
                4,
                1,
                TimeUnit.HOURS,
                new LinkedBlockingDeque<>(8)
        );

        for (int i = 0; i < 1; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.currentThread().sleep(6 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };

            threadPoolExecutor.execute(runnable);
        }


    }
}
