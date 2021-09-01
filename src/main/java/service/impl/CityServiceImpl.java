package service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dao.CityDao;
import dao.impl.CityDaoImpl;
import domain.City;
import redis.clients.jedis.Jedis;
import service.CityService;
import util.JedisPoolUtil;

import java.util.List;

public class CityServiceImpl implements CityService {
    CityDao dao = new CityDaoImpl();

    @Override
    public List<City> findAll() {
        return dao.findAll();
    }

    // 使用redis缓存
    @Override
    public String findAllJson() {
        // 先从redis查询数据
        // 获取对应连接
        Jedis jedis = JedisPoolUtil.getJedis();
        String city = jedis.get("city");
        // 判断city是否为null
        if (city == null || city.length() == 0) {
            System.out.println("redis数据库中没有数据.....");
            List<City> cities = dao.findAll();
            try {
                city = new ObjectMapper().writeValueAsString(cities);
                jedis.set("city", city);
                jedis.close();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("redis有数据，查询缓存......");
        }
        return city;
    }
}
