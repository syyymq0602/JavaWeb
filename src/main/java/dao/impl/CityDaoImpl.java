package dao.impl;

import dao.CityDao;
import domain.City;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtil;

import java.util.List;

public class CityDaoImpl implements CityDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    @Override
    public List<City> findAll() {
        String sql = "select * from city";
        List<City> cities = template.query(sql, new BeanPropertyRowMapper<>(City.class));
        return cities;
    }
}
