package com.rockvine.kernel.core.util;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Predicate;

/**
 * @author rocky
 * @date 2022-05-28 19:43
 * @description 重试工具类
 */
public class RetryUtils {
    private static final Logger logger = LoggerFactory.getLogger(RetryUtils.class);
    /**
     * 默认重试次数
     */
    private static final int DEFAULT_RETRY_TIMES = 2;
    /**
     * 默认休眠时间（毫秒）
     */
    private static final long DEFAULT_SLEEP_TIME_IN_MILLISECOND = 500;
    /**
     * 最大休眠时间（毫秒）
     */
    private static final long MAX_SLEEP_TIME_IN_MILLISECOND = TimeUnit.MINUTES.toMillis(5);

    /**
     * 调用runnable方法失败重试
     *
     * @param runnable 实际业务逻辑
     * @throws Exception 抛出异常
     */
    public static void executeWithRetry(Runnable runnable) throws Exception {
        executeWithRetry(runnable, RetryPolicyBuilder.newRetryPolicy().build());
    }

    /**
     * 调用runnable方法失败重试，需指定重试策略
     *
     * @param runnable          实际业务逻辑
     * @param retryTimes        最大重试次数（强制>=1）
     * @param sleepTimeInMillis 调用失败后休眠对应时间再进行重试
     * @param exponential       休眠时间是否指数递增
     * @throws Exception 抛出异常
     */
    public static void executeWithRetry(Runnable runnable, int retryTimes, long sleepTimeInMillis, boolean exponential) throws Exception {
        RetryPolicy policy = RetryPolicyBuilder.newRetryPolicy()
                .retryTimes(retryTimes)
                .sleepTimeInMillis(sleepTimeInMillis)
                .exponential(exponential)
                .build();
        executeWithRetry(runnable, policy);
    }

    /**
     * 调用runnable方法失败重试，需指定无需重试异常
     *
     * @param runnable       实际业务逻辑
     * @param abortException 无需重试异常
     * @throws Exception 抛出异常
     */
    @SuppressWarnings("unchecked")
    public static void executeWithRetry(Runnable runnable, Class<? extends Exception> abortException) throws Exception {
        RetryPolicy policy = RetryPolicyBuilder.newRetryPolicy()
                .abortExceptionClassList(Lists.newArrayList(abortException))
                .build();
        executeWithRetry(runnable, policy);
    }

    /**
     * 调用runnable方法失败重试，需指定重试策略RetryPolicy
     * RetryUtil.RetryPolicy policy = RetryUtil.RetryPolicyBuilder.newRetryPolicy()
     * .xxx // 函数式指定重试策略
     * .build();
     *
     * @param runnable 实际业务逻辑
     * @param policy   重试策略
     * @param <T>      返回结果类型（runnable无需关心）
     * @throws Exception 抛出异常
     */
    public static <T> void executeWithRetry(Runnable runnable, RetryPolicy policy) throws Exception {
        new Retry<T>().doRetry(runnable, policy);
    }

    /**
     * 使用线程池调用runnable方法失败重试，指定重试策略RetryPolicy、线程池、方法执行超时时间，超时即失败重试
     * RetryUtil.RetryPolicy policy = RetryUtil.RetryPolicyBuilder.newRetryPolicy()
     * .xxx // 函数式指定重试策略
     * .build();
     *
     * @param runnable           实际业务逻辑
     * @param policy             重试策略
     * @param timeoutMillis      超时时间
     * @param threadPoolExecutor 线程池
     * @param <T>                返回结果类型（runnable无需关心）
     * @throws Exception 抛出异常
     */
    public static <T> void asyncExecuteWithRetry(Runnable runnable, RetryPolicy policy, long timeoutMillis, ThreadPoolExecutor threadPoolExecutor) throws Exception {
        AsyncRetry<T> retry = new AsyncRetry<>(timeoutMillis, threadPoolExecutor);
        retry.doRetry(runnable, policy);
    }

    /**
     * 调用callable方法失败重试
     *
     * @param callable 实际业务逻辑
     * @param <T>      返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable) throws Exception {
        return executeWithRetry(callable, RetryPolicyBuilder.newRetryPolicy().build());
    }

    /**
     * 调用callable方法失败重试，需指定重试策略
     *
     * @param callable          实际业务逻辑
     * @param retryTimes        最大重试次数（强制>=1）
     * @param sleepTimeInMillis 调用失败后休眠对应时间再进行重试
     * @param exponential       休眠时间是否指数递增
     * @param <T>               返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable, int retryTimes, long sleepTimeInMillis, boolean exponential) throws Exception {
        RetryPolicy policy = RetryPolicyBuilder.newRetryPolicy()
                .retryTimes(retryTimes)
                .sleepTimeInMillis(sleepTimeInMillis)
                .exponential(exponential)
                .build();
        return executeWithRetry(callable, policy);
    }

    /**
     * 调用callable方法失败重试，需指定无需重试异常
     *
     * @param callable       实际业务逻辑
     * @param abortException 无需重试异常
     * @param <T>            返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    @SuppressWarnings("unchecked")
    public static <T> T executeWithRetry(Callable<T> callable, Class<? extends Exception> abortException) throws Exception {
        RetryPolicy policy = RetryPolicyBuilder.newRetryPolicy()
                .abortExceptionClassList(Lists.newArrayList(abortException))
                .build();
        return executeWithRetry(callable, policy);
    }

    /**
     * 调用callable方法失败重试，需指定重试策略RetryPolicy
     * RetryUtil.RetryPolicy policy = RetryUtil.RetryPolicyBuilder.newRetryPolicy()
     * .xxx // 函数式指定重试策略
     * .build();
     *
     * @param callable 实际业务逻辑
     * @param policy   重试策略
     * @param <T>      返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable, RetryPolicy policy) throws Exception {
        return new Retry<T>().doRetry(callable, policy);
    }

    /**
     * 调用callable方法失败重试，需指定返回结果重试断言
     *
     * @param callable  实际业务逻辑
     * @param predicate 返回结果重试断言
     * @param <T>       返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable, Predicate<T> predicate) throws Exception {
        return executeWithRetry(callable, RetryPolicyBuilder.newRetryPolicy().build(), predicate);
    }

    /**
     * 调用callable方法失败重试，需指定返回结果重试断言集合
     *
     * @param callable      实际业务逻辑
     * @param predicateList 返回结果重试断言
     * @param <T>           返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable, List<Predicate<T>> predicateList) throws Exception {
        return executeWithRetry(callable, RetryPolicyBuilder.newRetryPolicy().build(), predicateList);
    }

    /**
     * 调用callable方法失败重试，需指定重试策略RetryPolicy，及返回结果重试断言
     * RetryUtil.RetryPolicy policy = RetryUtil.RetryPolicyBuilder.newRetryPolicy()
     * .xxx // 函数式指定重试策略
     * .build();
     *
     * @param callable  实际业务逻辑
     * @param policy    重试策略
     * @param predicate 返回结果重试断言
     * @param <T>       返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable, RetryPolicy policy, Predicate<T> predicate) throws Exception {
        return new Retry<T>().addPredicateList(predicate).doRetry(callable, policy);
    }

    /**
     * 调用callable方法失败重试，需指定重试策略RetryPolicy，及返回结果重试断言集合
     * RetryUtil.RetryPolicy policy = RetryUtil.RetryPolicyBuilder.newRetryPolicy()
     * .xxx // 函数式指定重试策略
     * .build();
     *
     * @param callable      实际业务逻辑
     * @param policy        重试策略
     * @param predicateList 返回结果重试断言集合
     * @param <T>           返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T executeWithRetry(Callable<T> callable, RetryPolicy policy, List<Predicate<T>> predicateList) throws Exception {
        return new Retry<T>().setPredicateList(predicateList).doRetry(callable, policy);
    }

    /**
     * 使用线程池调用callable方法失败重试，指定方法执行超时时间，超时即失败重试
     *
     * @param callable           实际业务逻辑
     * @param policy             重试策略
     * @param predicateList      返回结果重试断言集合
     * @param timeoutMillis      超时时间
     * @param threadPoolExecutor 线程池
     * @param <T>                返回结果类型
     * @return 返回结果
     * @throws Exception 抛出异常
     */
    public static <T> T asyncExecuteWithRetry(Callable<T> callable, RetryPolicy policy, List<Predicate<T>> predicateList,
                                              long timeoutMillis, ThreadPoolExecutor threadPoolExecutor) throws Exception {
        AsyncRetry<T> retry = new AsyncRetry<>(timeoutMillis, threadPoolExecutor);
        retry.setPredicateList(predicateList);
        return retry.doRetry(callable, policy);
    }

    /**
     * 重试工具类具体实现逻辑
     */
    static class Retry<T> {
        /**
         * 返回结果重试断言集合
         */
        private List<Predicate<T>> predicateList = null;

        /**
         * 赋值返回结果重试断言集合
         *
         * @param predicateList 返回结果重试断言集合
         * @return 重试类Retry
         */
        protected Retry<T> setPredicateList(List<Predicate<T>> predicateList) {
            this.predicateList = predicateList;
            return this;
        }

        /**
         * 添加返回结果重试断言
         *
         * @param predicate 返回结果重试断言
         * @return 重试类Retry
         */
        protected Retry<T> addPredicateList(Predicate<T> predicate) {
            if (predicate == null) {
                return this;
            }
            if (CollectionUtils.isEmpty(predicateList)) {
                predicateList = new ArrayList<>();
            }
            predicateList.add(predicate);
            return this;
        }

        /**
         * 无返回值方法重试
         *
         * @param runnable 实际业务逻辑
         * @param policy   重试策略
         * @throws Exception 抛出异常
         */
        public void doRetry(Runnable runnable, RetryPolicy policy) throws Exception {
            if (runnable == null) {
                throw new IllegalArgumentException("runnable can not be null");
            }
            doRetry(null, runnable, policy);
        }

        /**
         * 有返回值方法重试
         *
         * @param callable 实际业务逻辑
         * @param policy   重试策略
         * @return 返回结果
         * @throws Exception 抛出异常
         */
        public T doRetry(Callable<T> callable, RetryPolicy policy) throws Exception {
            if (callable == null) {
                throw new IllegalArgumentException("callable can not be null");
            }
            return doRetry(callable, null, policy);
        }

        /**
         * 方法重试处理逻辑
         *
         * @param callable 实际业务逻辑（有返回值）
         * @param runnable 实际业务逻辑（无返回值）
         * @param policy   重试策略
         * @return 返回结果
         * @throws Exception 抛出异常
         */
        @SuppressWarnings({"BusyWait", "ConstantConditions"})
        private T doRetry(Callable<T> callable, Runnable runnable, RetryPolicy policy) throws Exception {
            Exception exception = null;
            for (int i = 0; i <= policy.retryTimes; i++) {
                try {
                    if (callable != null) {
                        T result = call(callable);
                        if (isInRetryResultConditionList(result, predicateList)) {
                            throw new RuntimeException("方法返回结果存在返回结果重试断言集合中，本次结果作废，返回结果：" + JSON.toJSONString(result));
                        }
                        return result;
                    } else {
                        run(runnable);
                        return null;
                    }
                } catch (Exception e) {
                    exception = e;
                    logger.warn("throw exception, message:{}", e.getMessage(), e);

                    // 判断抛出的异常是否存在重试异常列表中
                    if (!isInRetryExceptionClassList(exception, policy.retryExceptionClassList)) {
                        throw exception;
                    }
                    // 判断抛出的异常是否存在无需重试异常列表中
                    if (isInAbortExceptionClassList(exception, policy.abortExceptionClassList)) {
                        throw exception;
                    }
                    // 重试休眠时间处理
                    long sleepTimeInMillis = policy.sleepTimeInMillis;
                    if (policy.exponential) {
                        sleepTimeInMillis = Math.min(sleepTimeInMillis * (long) Math.pow(2, i), MAX_SLEEP_TIME_IN_MILLISECOND);
                    }

                    if (i < policy.retryTimes) {
                        logger.warn("call throw exception, 即将尝试第{}次重试，本次重试等待{}ms, 异常信息：{}", i + 1, sleepTimeInMillis, e.getMessage());
                        Thread.sleep(sleepTimeInMillis);
                    }
                }
            }
            throw exception;
        }

        /**
         * 执行无返回值方法
         *
         * @param runnable 实际业务逻辑
         * @throws Exception 抛出异常
         */
        protected void run(Runnable runnable) throws Exception {
            runnable.run();
        }

        /**
         * 执行有返回值方法
         *
         * @param callable 实际业务逻辑
         * @return 返回结果
         * @throws Exception 抛出异常
         */
        protected T call(Callable<T> callable) throws Exception {
            return callable.call();
        }

        /**
         * 判断抛出的异常是否存在重试异常列表中
         * true:重试; false:不重试
         *
         * @param exception               抛出的异常
         * @param retryExceptionClassList 重试异常列表
         * @return 判断结果
         */
        private static boolean isInRetryExceptionClassList(Exception exception, List<Class<? extends Exception>> retryExceptionClassList) {
            if (CollectionUtils.isEmpty(retryExceptionClassList)) {
                return true;
            }
            return retryExceptionClassList.stream().anyMatch(retryException -> retryException == exception.getClass());
        }

        /**
         * 判断抛出的异常是否存在无需重试异常列表中
         * true:不重试; false:重试
         *
         * @param exception               抛出的异常
         * @param abortExceptionClassList 无需重试异常列表
         * @return 判断结果
         */
        private static boolean isInAbortExceptionClassList(Exception exception, List<Class<? extends Exception>> abortExceptionClassList) {
            if (CollectionUtils.isEmpty(abortExceptionClassList)) {
                return false;
            }
            return abortExceptionClassList.stream().anyMatch(abortException -> abortException == exception.getClass());
        }

        /**
         * 判断方法返回结果是否存在返回结果重试断言集合中
         * true:重试; false:不重试
         *
         * @param result                   返回结果
         * @param retryResultConditionList 返回结果重试断言集合
         * @param <T>                      返回结果类型
         * @return 判断结果
         */
        private static <T> boolean isInRetryResultConditionList(T result, List<Predicate<T>> retryResultConditionList) {
            if (CollectionUtils.isEmpty(retryResultConditionList)) {
                return false;
            }
            return retryResultConditionList.stream().anyMatch(retryResultCondition -> retryResultCondition.test(result));
        }
    }

    static class AsyncRetry<T> extends Retry<T> {
        /**
         * 方法执行等待指定时间，超过指定时间即失败
         */
        private final long timeoutMillis;
        /**
         * 指定任务执行的线程池
         */
        private final ThreadPoolExecutor threadPoolExecutor;

        private AsyncRetry(long timeoutMillis, ThreadPoolExecutor threadPoolExecutor) {
            this.timeoutMillis = timeoutMillis;
            this.threadPoolExecutor = threadPoolExecutor;
        }

        /**
         * 使用传入的线程池异步执行任务，等待指定时间获取结果
         * <p/>
         * 使用Future.get()方法，等待指定的时间，单位毫秒
         * 如果任务在指定时间内结果，则正常返回，否则抛出异常
         *
         * @param callable 实际业务逻辑
         * @return 返回结果
         * @throws Exception 抛出异常
         */
        @Override
        protected T call(Callable<T> callable) throws Exception {
            Future<T> future = threadPoolExecutor.submit(callable);
            try {
                return future.get(timeoutMillis, TimeUnit.MILLISECONDS);
            } finally {
                if (!future.isDone()) {
                    future.cancel(true);
                    logger.warn("callable任务执行超时，取消任务");
                }
            }
        }

        /**
         * 使用传入的线程池异步执行任务，等待指定时间
         * <p/>
         * 使用Future.get()方法，等待指定的时间，单位毫秒
         * 如果任务在指定时间内结果，则正常返回，否则抛出异常
         *
         * @param runnable 实际业务逻辑
         * @throws Exception 抛出异常
         */
        @Override
        protected void run(Runnable runnable) throws Exception {
            Future<?> future = threadPoolExecutor.submit(runnable);
            try {
                future.get(timeoutMillis, TimeUnit.MILLISECONDS);
            } finally {
                if (!future.isDone()) {
                    future.cancel(true);
                    logger.warn("runnable任务执行超时，取消任务");
                }
            }
        }
    }

    /**
     * 重试策略
     */
    public static final class RetryPolicy {
        /**
         * 重试次数，默认重试2次
         */
        private int retryTimes = DEFAULT_RETRY_TIMES;
        /**
         * 休眠时间，单位毫秒，默认500ms
         */
        private long sleepTimeInMillis = DEFAULT_SLEEP_TIME_IN_MILLISECOND;
        /**
         * 休眠时间是否指数递增
         * 根据Math.pow(2, retryTimes)计算，默认false，即不指数递增
         */
        private boolean exponential = false;
        /**
         * 重试异常列表，优先级低
         * 当列表为空时，表示全部重试
         * 当列表不为空时，且指定异常在该列表中重试，否则不重试
         */
        private List<Class<? extends Exception>> retryExceptionClassList = new ArrayList<>();
        /**
         * 无需重试异常列表，优先级高
         * 当列表为空时，表示全部重试
         * 当列表不为空时，且指定异常在该列表中不重试，否则重试
         */
        private List<Class<? extends Exception>> abortExceptionClassList = new ArrayList<>();

        private RetryPolicy() {
        }
    }

    /**
     * 重试策略建造者
     */
    public static final class RetryPolicyBuilder {
        private final RetryPolicy retryPolicy;

        private RetryPolicyBuilder() {
            retryPolicy = new RetryPolicy();
        }

        public static RetryPolicyBuilder newRetryPolicy() {
            return new RetryPolicyBuilder();
        }

        public RetryPolicyBuilder retryTimes(int retryTimes) {
            retryPolicy.retryTimes = Math.max(retryTimes, 1);
            return this;
        }

        public RetryPolicyBuilder sleepTimeInMillis(long sleepTimeInMillis) {
            retryPolicy.sleepTimeInMillis = Math.min(MAX_SLEEP_TIME_IN_MILLISECOND,
                    sleepTimeInMillis < 0 ? DEFAULT_SLEEP_TIME_IN_MILLISECOND : sleepTimeInMillis);
            return this;
        }

        public RetryPolicyBuilder exponential(boolean exponential) {
            retryPolicy.exponential = exponential;
            return this;
        }

        public RetryPolicyBuilder retryExceptionClassList(List<Class<? extends Exception>> retryExceptionClassList) {
            retryPolicy.retryExceptionClassList = retryExceptionClassList;
            return this;
        }

        public RetryPolicyBuilder abortExceptionClassList(List<Class<? extends Exception>> abortExceptionClassList) {
            retryPolicy.abortExceptionClassList = abortExceptionClassList;
            return this;
        }

        public RetryPolicy build() {
            return retryPolicy;
        }
    }
}