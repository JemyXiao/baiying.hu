package com.baiying.hu.redis;

import org.springframework.data.redis.core.ListOperations;

import java.util.List;
import java.util.Set;
public interface CommonRedisDao {

    /**
     * 添加
     */
    boolean cacheValue(String key, String value, long time);
    boolean cacheValue(String key, String value);

    /**
     * 是否包含
     */
    boolean containsValueKey(String key);
    boolean containsSetKey(String key);
    boolean containsListKey(String key);
    boolean containsKey(String key);

    /**
     * 获取缓存
     */
    String getValue(String key);

    /**
     * 移除缓存
     */
    boolean removeValue(String key);
    boolean removeSet(String key);
    boolean removeList(String key);

    /**
     * 缓存Set
     */
    boolean cacheSet(String key, String value, long time);
    boolean cacheSet(String key, String value);
    boolean cacheSet(String k, Set<String> v, long time);
    boolean cacheSet(String k, Set<String> v);

    /**
     * 获取Set
     */
    Set<String> getSet(String k);

    /**
     * 缓存List
     */
    boolean cacheList(String k, String v, long time);
    boolean cacheList(String k, String v);
    boolean cacheList(String k, List<String> v, long time);
    boolean cacheList(String k, List<String> v);

    /**
     * 获取List
     */
    List<String> getList(String k, long start, long end);

    /**
     * 获取页码
     */
    long getListSize(String key);
    long getListSize(ListOperations<String, String> listOps, String k);

    /**
     * 移除list缓存
     */
    boolean removeOneOfList(String k);
}
