package util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.InputStream;
import java.util.Properties;


public class JedisPoolUtil {
    private static JedisPool jedisPool;

    static {
        try {
            Properties pro = new Properties();
            InputStream stream = JedisPoolUtil.class.getClassLoader().getResourceAsStream("jedis.properties");
            pro.load(stream);
            // 配置redis
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.parseInt(pro.getProperty("maxTotal")));
            config.setMaxIdle(Integer.parseInt(pro.getProperty("maxIdle")));
            // 初始化Jedis
            jedisPool = new JedisPool(config,pro.getProperty("host"),Integer.parseInt(pro.getProperty("port")));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    // 获取连接方法
    public static Jedis getJedis(){
        return jedisPool.getResource();
    }
}
