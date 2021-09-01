import domain.City;
import example.Dao.UserDao;
import example.domain.User;
import org.junit.Test;
import service.CityService;
import service.impl.CityServiceImpl;

import java.util.List;


public class UserDaoTest {
    @Test
    public void testLogin(){
        User loginUser = new User();
        loginUser.setUsername("lisi");
        loginUser.setPassword("123");

        UserDao dao = new UserDao();
        User user = dao.login(loginUser);
        System.out.println(user);
    }

    @Test
    public void testDB(){
        CityService service = new CityServiceImpl();
        List<City> cities = service.findAll();
        System.out.println(cities);
    }
}
