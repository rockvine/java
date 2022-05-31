package com.rockvine.kernel.core.util;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.*;

/**
 * @author rocky
 * @date 2022-05-29 15:50
 * @description 重试工具类测试类
 */
public class RetryUtilsTest {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 4, 3, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10), new ThreadPoolExecutor.AbortPolicy());


        MyRunnable runnable = new MyRunnable();

        RetryUtils.RetryPolicy runnablePolicy = RetryUtils.RetryPolicyBuilder.newRetryPolicy()
                .retryTimes(2)
                .sleepTimeInMillis(1000)
                .exponential(false)
                .build();

        RetryUtils.executeWithRetry(runnable, runnablePolicy);

        RetryUtils.asyncExecuteWithRetry(runnable, runnablePolicy, 2000, threadPoolExecutor);


        MyCallable callable = new MyCallable();

        // noinspection unchecked
        RetryUtils.RetryPolicy callablePolicy = RetryUtils.RetryPolicyBuilder.newRetryPolicy()
                .retryTimes(2)
                .sleepTimeInMillis(100)
                .exponential(true)
                .retryExceptionClassList(Lists.newArrayList(ArithmeticException.class))
                .build();

        String result1 = RetryUtils.executeWithRetry(callable, runnablePolicy, StringUtils::isBlank);
        System.out.println(result1);

        String result2 = RetryUtils.asyncExecuteWithRetry(callable, callablePolicy, null, 1000, threadPoolExecutor);
        System.out.println(result2);
    }

    static class MyRunnable implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello World");
        }
    }

    static class MyCallable implements Callable<String> {
        @Override
        public String call() {
            return "HelloWord";
        }
    }
}
