package cn.finwood.demo.common.util;

import cn.finwood.demo.common.exception.RedisCacheException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 * created by haoyanbing on 2018/11/9 11:17
 */
@Component
public class RedisUtil {

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 写入键值
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, String value) {
        try {
            stringRedisTemplate.opsForValue().set(key, value);

        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("key: %1$s value:%2$s", key, value), e);
        }
    }

    /**
     * 写入键值
     *
     * @param key
     * @param value
     * @param timeout 秒
     */
    public void set(String key, String value, long timeout) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("key: %1$s value:%2$s", key, value), e);
        }
    }


    /**
     * 获指定的value
     *
     * @param key
     * @return
     */
    public String get(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("key: %1$s ", key), e);
        }
    }

    public boolean setExpire(String key, long timeout) {
        try {
            return stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("key: %1$s ", key), e);
        }
    }

    /**
     * redisTemplate 过期
     *
     * @param key
     * @return
     */
    public long expire(String key) {
        try {
            return redisTemplate.getExpire(key, TimeUnit.MILLISECONDS);//毫秒
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("getExpire key: %1$s ", key), e);
        }
    }


    /**
     * 取过期时间
     *
     * @param key
     * @return 毫秒
     */
    public long getExpire(String key) {
        try {
            return stringRedisTemplate.getExpire(key, TimeUnit.MILLISECONDS);//毫秒
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("getExpire key: %1$s ", key), e);
        }
    }

    /**
     * 获取多个keys
     *
     * @param keyList
     */
    public List<String> get(List<String> keyList) {
        try {
            return stringRedisTemplate.opsForValue().multiGet(keyList);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("get keys: %1$s ", keyList), e);
        }
    }

    /**
     * 移除
     *
     * @param key
     */
    public void delete(String key) {

        delete(key, true);
    }

    public void delete(String key, boolean isStr) {
        try {
            if (isStr) {
                stringRedisTemplate.delete(key);
            } else {
                redisTemplate.delete(key);

            }
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("del key: %1$s ", key), e);
        }
    }

    /**
     * 自增
     *
     * @param key
     * @return
     */
    public long increment(String key) {
        return increment(key, 1);
    }

    /**
     * 自增带步长
     *
     * @param key
     * @param step
     * @return
     */
    public long increment(String key, int step) {

        try {
            return redisTemplate.opsForValue().increment(key, step);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("del key: %1$s ", key), e);
        }
    }

    /**
     * 设置一个list
     *
     * @param key
     * @param list
     */
    public void set(String key, List list) {
        try {
            redisTemplate.opsForList().leftPushAll(key, list);
        } catch (Exception ex) {
            throw new RedisCacheException(String.format("setList key: %1$s", key), ex);

        }
    }

    /**
     * 取List
     *
     * @param key
     * @return
     */
    public List<?> getList(String key) {
        try {
            return (List<?>) redisTemplate.opsForList().leftPop(key);
        } catch (Exception ex) {
            throw new RedisCacheException(String.format("getList key: %1$s ", key), ex);
        }
    }


    /**
     * 写入map
     *
     * @param key
     * @param value
     * @return
     */
    public void set(String key, Map value) {
        try {
            redisTemplate.opsForHash().putAll(key, value);

        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("set key: %1$s map:%2$s", key, value), e);
        }
    }

    /**
     * 根据key获取整个map
     *
     * @param key
     * @return
     */
    public Map getMap(String key) {
        try {
            return redisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            throw new RedisCacheException(String.format(" getMap key: %1$s ", key), e);
        }
    }

    /**
     * 取map 中的指定key 的值
     *
     * @param key
     * @param itemKey
     * @return
     */
    public Object getMapItem(String key, String itemKey) {

        try {
            return redisTemplate.opsForHash().get(key, itemKey);

        } catch (Exception ex) {
            throw new RedisCacheException(String.format("getMapItem key: %1$s  itemkey:%2$s", key, itemKey), ex);
        }

    }

    /**
     * 删除指定的key
     *
     * @param key
     * @param itemkeys
     */
    public void deleteMap(String key, Object... itemkeys) {
        try {

            redisTemplate.opsForHash().delete(key, itemkeys);
        } catch (Exception ex) {
            throw new RedisCacheException(String.format("deleteMap key: %1$s ", key), ex);
        }
    }


    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        try {
            return redisTemplate.hasKey(key);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("exists key: %1$s ", key), e);
        }

    }

    /**
     * 判断Key是否存在
     *
     * @param key
     * @return
     */
    public boolean stringExists(String key) {
        try {
            return stringRedisTemplate.hasKey(key);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("stringExists  key: %1$s ", key), e);
        }

    }


    // =============================hash============================

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hget(String key, String item) {
        try {
            return stringRedisTemplate.opsForHash().get(key, item);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("hget key: %1$s ", key), e);
        }
    }

    /**
     * 167
     * 获取hashKey对应的所有键值
     * 168
     *
     * @param key 键
     *            169
     * @return 对应的多个键值
     * 170
     */
    public Map<Object, Object> hmget(String key) {
        try {
            return stringRedisTemplate.opsForHash().entries(key);
        } catch (Exception e) {
            // TODO: handle exception
            throw new RedisCacheException(String.format("hmget key: %1$s ", key), e);
        }
    }

    /**
     * HashSet     *
     *
     * @param key 键
     * @param map 对应多个键值
     * @return true 成功 false 失败
     */
    public boolean hmset(String key, Map<String, Object> map) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            return true;
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hmset key: %1$s ", key), e);
        }
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     * @return true成功 false失败
     */
    public boolean hmset(String key, Map<String, Object> map, long time) {
        try {
            stringRedisTemplate.opsForHash().putAll(key, map);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hmset key: %1$s ", key), e);
        }
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            return true;
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hset key: %1$s ", key), e);
        }
    }


    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒) 注意:如果已存在的hash表有时间,这里将会替换原有的时间
     * @return true 成功 false失败
     */
    public boolean hset(String key, String item, Object value, long time) {
        try {
            stringRedisTemplate.opsForHash().put(key, item, value);
            if (time > 0) {
                expire(key, time);
            }
            return true;
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hset key: %1$s ", key), e);
        }
    }


    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hdel(String key, Object... item) {
        try {
            stringRedisTemplate.opsForHash().delete(key, item);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hdel key: %1$s ", key), e);
        }
    }


    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public boolean hHasKey(String key, String item) {
        try {
            return stringRedisTemplate.opsForHash().hasKey(key, item);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hHasKey key: %1$s ", key), e);
        }
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @return true 存在 false不存在
     */
    public Set<Object> hKeys(String key) {
        try {
            return stringRedisTemplate.opsForHash().keys(key);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hHasKey key: %1$s ", key), e);
        }
    }


    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public double hincr(String key, String item, double by) {
        try {
            return stringRedisTemplate.opsForHash().increment(key, item, by);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hincr key: %1$s ", key), e);
        }
    }


    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public double hdecr(String key, String item, double by) {
        try {
            return stringRedisTemplate.opsForHash().increment(key, item, -by);
        } catch (Exception e) {
            throw new RedisCacheException(String.format("hdecr key: %1$s ", key), e);
        }
    }

    // =============================common============================

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     * @return
     */
    public boolean expire(String key, long time) {
        try {
            if (time > 0) {
                stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            throw new RedisCacheException(String.format("expire key: %1$s ", key), e);
        }
    }
}
