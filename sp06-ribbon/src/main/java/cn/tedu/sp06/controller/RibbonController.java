package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-24 17:47
 **/
@RestController
public class RibbonController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/item-service/{orderId}")
    public JsonResult<List<Item>> getItems(@PathVariable Integer orderId) {
        return restTemplate.getForObject("http://localhost:8001/{1}",JsonResult.class,orderId);
    }
    @PostMapping("/item-service/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        return restTemplate.postForObject("http://localhost:8001/decreaseNumber", items, JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        return restTemplate.getForObject("http://localhost:8101/{1}", JsonResult.class, userId);
    }
    @GetMapping("/user-service/{userId}/score")
    public JsonResult<?> addScore(@PathVariable Integer userId, Integer score) {
        return restTemplate.getForObject("http://localhost:8101/{1}/score?score={2}",
                JsonResult.class,userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        return restTemplate.getForObject("http://localhost:8201/{1}", JsonResult.class,orderId);
    }
    @GetMapping("/order-service")
    public JsonResult addOrder() {
        return restTemplate.getForObject("http://localhost:8201/", JsonResult.class);
    }
}
