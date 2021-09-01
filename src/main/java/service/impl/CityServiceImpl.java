package service.impl;

import dao.CityDao;
import dao.impl.CityDaoImpl;
import domain.City;
import service.CityService;

import java.util.List;

public class CityServiceImpl implements CityService {
    CityDao dao = new CityDaoImpl();
    @Override
    public List<City> findAll() {
        return dao.findAll();
    }
}
