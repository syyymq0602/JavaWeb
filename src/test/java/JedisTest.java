import org.junit.Test;
import redis.clients.jedis.Jedis;
import util.JedisPoolUtil;

public class JedisTest {

    @Test
    public void jedis_test(){
        Jedis jedis = JedisPoolUtil.getJedis();
        jedis.set("he","is a man");
        String s = jedis.get("he");
        System.out.println(s);
    }
}
