package redis;

import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataTest {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");

//        testString(jedis);
//        testList(jedis);
//        testMap(jedis);
//        testSet(jedis);
//        testSortedSet(jedis);
    }

    /**
     * 有序，不重复的集合
     * <p>
     * redis正是通过分数来为集合中的成员进行从小到大的排序。
     * 有序集合的成员是唯一的,但分数(score)却可以重复。
     */
    private static void testSortedSet(Jedis jedis) {
        jedis.zadd("name1", 1, "abc");
        jedis.zadd("name1", 2, "abc");
        jedis.zadd("name1", 3, "cab");
        jedis.zadd("name1", 4, "bac");
        Set<String> set = jedis.zrange("name1", 0, Integer.MAX_VALUE);
        System.out.println(set);
    }

    /**
     * 集合（无序，不重复数据）
     */
    private static void testSet(Jedis jedis) {
        jedis.sadd("name", "cyx");
        Set<String> nameSet = jedis.smembers("name");
        System.out.println(nameSet);
    }

    /**
     * 字符串
     */
    public static void testString(Jedis jedis) {
        //设置 redis 字符串数据
        jedis.set("runoobkey", "www.runoob.com");
        // 获取存储的数据并输出
        System.out.println("redis 存储的字符串为: " + jedis.get("runoobkey"));
    }

    /**
     * 哈希（hash）
     * <p>
     * 适合存储对象
     */
    public static void testMap(Jedis jedis) {
        Map<String, String> map = new HashMap<>();
        map.put("name", "cyx");
        map.put("age", "28");
        jedis.hmset("student", map);
        map = jedis.hgetAll("student");
        System.out.println(map);
    }

    /**
     * 列表（list）
     *
     * @param jedis
     */
    public static void testList(Jedis jedis) {
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0, Integer.MAX_VALUE);
        for (int i = 0; i < list.size(); i++) {
            System.out.println("列表项为: " + list.get(i));
        }
    }


}
