package com.rockvine.kernel.core.cache;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;
import com.rockvine.kernel.core.exception.BusinessException;
import com.rockvine.kernel.core.model.BaseModel;
import com.rockvine.kernel.core.util.RetryUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Function;

/**
 * @author rocky
 * @date 2022-05-30 17:51
 * @description 抽象缓存管理类
 */
@Component
public abstract class AbstractCacheManager {
    private static final Logger logger = LoggerFactory.getLogger(AbstractCacheManager.class);
    /**
     * 最大缓存Lock数量
     */
    private static final long MAX_CACHE_LOCK = 1000;
    /**
     * 默认缓存过期时间
     */
    private static final long DEFAULT_CACHE_EXPIRE_TIME = TimeUnit.MINUTES.toSeconds(2);

    private final ConcurrentLinkedHashMap<String, ReentrantLock> lockMap = new ConcurrentLinkedHashMap.Builder<String, ReentrantLock>()
            .maximumWeightedCapacity(MAX_CACHE_LOCK)
            .weigher(Weighers.singleton())
            .build();

    /**
     * 获取数据
     *
     * @param cacheKey 缓存Key
     * @param function 获取源数据方法
     * @param <T>      返回结果类型
     * @return 返回结果
     */
    public <T extends BaseModel> T getData(String cacheKey, Function<String, T> function) {
        return this.getData(cacheKey, function, DEFAULT_CACHE_EXPIRE_TIME, true);
    }

    /**
     * 获取数据，指定是否缓存空值
     *
     * @param cacheKey    缓存Key
     * @param function    获取源数据方法
     * @param isCacheNull 是否缓存空值
     * @param <T>         返回结果类型
     * @return 返回结果
     */
    public <T extends BaseModel> T getData(String cacheKey, Function<String, T> function, boolean isCacheNull) {
        return this.getData(cacheKey, function, DEFAULT_CACHE_EXPIRE_TIME, isCacheNull);
    }

    /**
     * 获取数据，指定缓存过期时间及是否缓存空值
     *
     * @param cacheKey    缓存Key
     * @param function    获取源数据方法
     * @param expireTime  过期时间
     * @param isCacheNull 是否缓存空值
     * @param <T>         返回结果类型
     * @return 返回结果
     */
    public <T extends BaseModel> T getData(String cacheKey, Function<String, T> function, long expireTime, boolean isCacheNull) {
        // 从缓存中获取数据
        T data = null;
        try {
            data = this.getCache(cacheKey);
        } catch (Exception e) {
            logger.warn("get data from cache failed");
        }

        // 执行function获取数据并缓存
        if (data == null) {
            data = this.doGetData(cacheKey, function, expireTime, isCacheNull);
        }
        return data;
    }

    /**
     * 批量获取数据，指定是否缓存空值
     *
     * @param cacheKeyList 缓存Key集合
     * @param function     获取源数据方法
     * @param <T>          返回结果类型
     * @return 返回结果
     */
    public <T extends BaseModel> Map<String, T> listData(List<String> cacheKeyList, Function<String, T> function) {
        return this.listData(cacheKeyList, function, DEFAULT_CACHE_EXPIRE_TIME, true);
    }

    /**
     * 批量获取数据
     *
     * @param cacheKeyList 缓存Key集合
     * @param function     获取源数据方法
     * @param isCacheNull  是否缓存空值
     * @param <T>          返回结果类型
     * @return 返回结果
     */
    public <T extends BaseModel> Map<String, T> listData(List<String> cacheKeyList, Function<String, T> function, boolean isCacheNull) {
        return this.listData(cacheKeyList, function, DEFAULT_CACHE_EXPIRE_TIME, isCacheNull);
    }

    /**
     * 批量获取数据，指定缓存过期时间及是否缓存空值
     *
     * @param cacheKeyList 缓存Key集合
     * @param function     获取源数据方法
     * @param expireTime   过期时间
     * @param isCacheNull  是否缓存空值
     * @param <T>          返回结果类型
     * @return 返回结果
     */
    public <T extends BaseModel> Map<String, T> listData(List<String> cacheKeyList, Function<String, T> function, long expireTime, boolean isCacheNull) {
        // 从缓存中获取数据
        Map<String, T> dataMap = new HashMap<>();
        try {
            dataMap = this.listCache(cacheKeyList);
        } catch (Exception e) {
            logger.warn("get data from cache failed");
        }

        if (dataMap.size() == cacheKeyList.size()) {
            return dataMap;
        }

        // 查找缺失数据
        for (String cacheKey : cacheKeyList) {
            T data = dataMap.get(cacheKey);
            if (data != null) {
                continue;
            }

            data = this.doGetData(cacheKey, function, expireTime, isCacheNull);
            dataMap.put(cacheKey, data);
        }
        return dataMap;
    }

    /**
     * 从缓存中获取数据
     *
     * @param cacheKey 缓存Key
     * @param <T>      缓存数据类型
     * @return 返回结果
     */
    protected abstract <T extends BaseModel> T getCache(String cacheKey);

    /**
     * 批量从缓存中获取数据
     *
     * @param cacheKeyList 缓存Key集合
     * @param <T>          缓存数据类型
     * @return 返回结果
     */
    protected abstract <T extends BaseModel> Map<String, T> listCache(List<String> cacheKeyList);

    /**
     * 添加缓存
     *
     * @param cacheKey   缓存Key
     * @param data       缓存数据
     * @param expireTime 过期时间
     * @param <T>        缓存数据类型
     */
    protected abstract <T extends BaseModel> void addCache(String cacheKey, T data, long expireTime);

    private <T extends BaseModel> T doGetData(String cacheKey, Function<String, T> function, long expireTime, boolean isCacheNull) {
        // 加锁排队
        acquireLock(cacheKey);
        try {
            // 双重校验，避免缓存击穿
            T data = this.getCache(cacheKey);
            if (data != null) {
                return data;
            }
            // 由function函数查询数据
            data = RetryUtils.executeWithRetry(() -> function.apply(cacheKey), BusinessException.class);
            if (data != null || isCacheNull) {
                this.addCache(cacheKey, data, expireTime);
            }
            return data;
        } catch (Exception e) {
            logger.warn("get data failed");
            return null;
        } finally {
            // 解锁
            releaseLock(cacheKey);
        }
    }

    private ReentrantLock getLockForKey(String key) {
        ReentrantLock lock = new ReentrantLock();
        ReentrantLock previous = lockMap.putIfAbsent(key, lock);
        return previous == null ? lock : previous;
    }

    private void acquireLock(String key) {
        Lock lock = getLockForKey(key);
        lock.lock();
    }

    private void releaseLock(String key) {
        ReentrantLock lock = lockMap.get(key);
        if (lock.isHeldByCurrentThread()) {
            lock.unlock();
        }
    }
}
