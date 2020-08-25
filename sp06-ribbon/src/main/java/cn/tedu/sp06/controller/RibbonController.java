package cn.tedu.sp06.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.web.util.JsonResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
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
    @HystrixCommand(fallbackMethod = "getItemsFB")
    public JsonResult<List<Item>> getItems(@PathVariable Integer orderId) {
        return restTemplate.getForObject("http://item-service/{1}",JsonResult.class,orderId);
    }
    @PostMapping("/item-service/decreaseNumber")
    @HystrixCommand(fallbackMethod = "decreaseNumberFB")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        return restTemplate.postForObject("http://item-service/decreaseNumber", items, JsonResult.class);
    }

    @GetMapping("/user-service/{userId}")
    @HystrixCommand(fallbackMethod = "getUserFB")
    public JsonResult<User> getUser(@PathVariable Integer userId) {
        return restTemplate.getForObject("http://user-service/{1}", JsonResult.class, userId);
    }
    @GetMapping("/user-service/{userId}/score")
    @HystrixCommand(fallbackMethod = "addScoreFB")
    public JsonResult<?> addScore(@PathVariable Integer userId, Integer score) {
        return restTemplate.getForObject("http://user-service/{1}/score?score={2}",
                JsonResult.class,userId, score);
    }

    @GetMapping("/order-service/{orderId}")
    @HystrixCommand(fallbackMethod = "getOrderFB")
    public JsonResult<Order> getOrder(@PathVariable String orderId) {
        return restTemplate.getForObject("http://order-service/{1}", JsonResult.class,orderId);
    }
    @GetMapping("/order-service")
    @HystrixCommand(fallbackMethod = "addOrderFB")
    public JsonResult addOrder() {
        return restTemplate.getForObject("http://order-service:8201/", JsonResult.class);
    }


    ////////////////
    public JsonResult<List<Item>> getItemsFB(@PathVariable Integer orderId) {
        return JsonResult.err().msg("获取订单的商品列表失败");
    }
    public JsonResult<?> decreaseNumberFB(@RequestBody List<Item> items) {
        return JsonResult.err().msg("减少商品库存失败");
    }

    public JsonResult<User> getUserFB(@PathVariable Integer userId) {
        return JsonResult.err().msg("获取用户失败");
    }
    public JsonResult<?> addScoreFB(@PathVariable Integer userId, Integer score) {
        return JsonResult.err().msg("增加用户积分失败");
    }

    public JsonResult<Order> getOrderFB(@PathVariable String orderId) {
        return JsonResult.err().msg("获取订单失败");
    }
    public JsonResult addOrderFB() {
        return JsonResult.err().msg("添加订单失败");
    }
}
