package cn.tedu.sp03.user.service;

import com.fasterxml.jackson.core.type.TypeReference;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-23 18:53
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Value("${sp.user-service.users}")
    private String userJson;
    @Override
    public User getUser(Integer id) {
        List<User> users = JsonUtil.from(userJson, new TypeReference<List<User>>() {
        });
        for (User u : users) {
            if (id.equals(u.getId())) {
                return u;
            }
        }
        return new User(id,"name"+id, "pwd"+id);
    }

    @Override
    public void addScore(Integer id, Integer score) {
        //这里增加积分
        log.info("user "+id+" - 增加积分 "+score);
    }
}
