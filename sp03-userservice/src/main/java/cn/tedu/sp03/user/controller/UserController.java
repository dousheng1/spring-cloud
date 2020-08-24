package cn.tedu.sp03.user.controller;

import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.UserService;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-23 19:09
 **/
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/{userid}")
    public JsonResult<User> getUser(@PathVariable("userid") Integer userId){
        User user = userService.getUser(userId);
        return JsonResult.ok().data(user);
    }

    @GetMapping("/{userid}/score")
    public JsonResult<?> addScore(@PathVariable("userid") Integer userId, Integer score) {
        userService.addScore(userId, score);
        return JsonResult.ok().msg("增加用户积分成功");
    }
}
