package com.ancda.cat.alarm.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations.TypedTuple;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @Title: RedisUtil
 * @Package com.wind.business.shiro.security.util
 * @Description: Redis工具类
 * @author huanghy
 * @date 2018/11/24 9:31
 * @version V1.0
 */
@Component
public class RedisUtil {

    private static Logger logger = LoggerFactory.getLogger(RedisUtil.class);

    private static RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        RedisUtil.redisTemplate = redisTemplate;
    }

    /***********************common*****************************/
    public static Collection<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    public static void delete(String key) {
        redisTemplate.delete(key);
    }

    public static void delete(Collection<String> key) {
        redisTemplate.delete(key);
    }

    /***********************String*****************************/
    public static <T> T get(String key, Class<T> clazz) {
        String value = redisTemplate.opsForValue().get(key);
        return JsonUtil.toBean(value, clazz);
    }

    public static String get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public static List<String> multiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    public static void set(String key, Object obj, Long timeout, TimeUnit unit) {
        if (obj == null) {
            return;
        }

        String value = JsonUtil.toJson(obj);
        if (timeout != null && timeout != 0L) {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }

    public static <T> T getAndSet(String key, Object obj, Class<T> clazz) {
        if (obj == null) {
            return get(key, clazz);
        }

        String value = redisTemplate.opsForValue().getAndSet(key, JsonUtil.toJson(obj));
        return JsonUtil.toBean(value, clazz);
    }

    /**
     * 递减
     * @param key
     * @param delta
     * @return
     */
    public static long decrement(String key, int delta) {
        return redisTemplate.opsForValue().increment(key, -delta);
    }

    /**
     * 递增
     * @param key
     * @param delta
     * @return
     */
    public static long increment(String key, int delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    /***********************list*****************************/

    public static int size(String key) {
        return redisTemplate.opsForList().size(key).intValue();
    }

    public static List<String> range(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public static void rightPushAll(String key, Collection<?> values, Long timeout, TimeUnit unit) {
        if (values == null || values.isEmpty()) {
            return;
        }

        redisTemplate.opsForList().rightPushAll(key, JsonUtil.toJson(values));
        if (timeout != null) {
            redisTemplate.expire(key, timeout, unit);
        }
    }

    public static <T> void leftPush(String key, T obj) {
        if (obj == null) {
            return;
        }

        redisTemplate.opsForList().leftPush(key, JsonUtil.toJson(obj));
    }

    public static <T> T leftPop(String key, Class<T> clazz) {
        String value = redisTemplate.opsForList().leftPop(key);
        return JsonUtil.toBean(value, clazz);
    }

    public static void remove(String key, int count, Object obj) {
        if (obj == null) {
            return;
        }

        redisTemplate.opsForList().remove(key, count, JsonUtil.toJson(obj));
    }

    /***********************set*****************************/

    public static void sadd(String key, String... values) {
        if (values == null) {
            return;
        }

        redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 批量删除
     * @param key
     * @param values
     */
    public static void srem(String key, String... values) {
        if (values == null) {
            return;
        }

        redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 判断确定给定值是否是集合的成员
     * @param key
     * @param value
     * @return
     */
    public static boolean isMember(String key, String value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }


    /***********************zSet*****************************/

    public static int zcard(String key) {
        return redisTemplate.opsForZSet().zCard(key).intValue();
    }

    public static Set<String> zrange(String key, long start, long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    private static List<String> setToList(Set<String> set) {
        if (set == null) {
            return null;
        }
        return new ArrayList<String>(set);
    }

    public static void zadd(String key, Object obj, double score) {
        if (obj == null) {
            return;
        }
        redisTemplate.opsForZSet().add(key, JsonUtil.toJson(obj), score);
    }

    public static void zaddAll(String key, List<TypedTuple<?>> tupleList, Long timeout, TimeUnit unit) {
        if (tupleList == null || tupleList.isEmpty()) {
            return;
        }

        Set<TypedTuple<String>> tupleSet = toTupleSet(tupleList);
        redisTemplate.opsForZSet().add(key, tupleSet);
        if (timeout != null) {
            redisTemplate.expire(key, timeout, unit);
        }
    }

    private static Set<TypedTuple<String>> toTupleSet(List<TypedTuple<?>> tupleList) {
        Set<TypedTuple<String>> tupleSet = new LinkedHashSet<>();
        for (TypedTuple<?> t : tupleList) {
            tupleSet.add(new DefaultTypedTuple<>(JsonUtil.toJson(t.getValue()), t.getScore()));
        }
        return tupleSet;
    }

    public static void zrem(String key, Object obj) {
        if (obj == null) {
            return;
        }
        redisTemplate.opsForZSet().remove(key, JsonUtil.toJson(obj));
    }

    public static void unionStore(String destKey, Collection<String> keys, Long timeout, TimeUnit unit) {
        if (keys == null || keys.isEmpty()) {
            return;
        }

        Object[] keyArr = keys.toArray();
        String key = (String) keyArr[0];

        Collection<String> otherKeys = new ArrayList<String>(keys.size() - 1);
        for (int i = 1; i < keyArr.length; i++) {
            otherKeys.add((String) keyArr[i]);
        }

        redisTemplate.opsForZSet().unionAndStore(key, otherKeys, destKey);
        if (timeout != null) {
            redisTemplate.expire(destKey, timeout, unit);
        }
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean exist(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 设置key的存活时间，单位为秒
     * @param key
     * @param timeout
     */
    public static void expire(String key, long timeout) {
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 设置key的存活时间
     * @param key
     * @param timeout
     * @param timeUnit
     */
    public static void expire(String key, long timeout, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeout, timeUnit);
    }
}
