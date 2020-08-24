package cn.tedu.sp04.order.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.web.util.JsonResult;
import com.sun.org.apache.xpath.internal.operations.Or;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-23 19:38
 **/
@RestController
@Slf4j
public class OrderController {
    @Autowired
    private OrderService orderService;

    //获取订单
    @GetMapping("/{orderid}")
    public JsonResult<Order> getOrder(@PathVariable("orderid") String orderId){
        log.info("获取订单: orderId=" + orderId);
        Order order = orderService.getOrder(orderId);
        return JsonResult.ok().data(order);
    }
    // 添加订单
    @GetMapping("/")
    public JsonResult<?> addOrder() {
        Order order = new Order();
        order.setId("12312321");
        order.setUser(new User(9,null,null));
        order.setItems(Arrays.asList(new Item[]{
                new Item(1,"商品1", 1),
                new Item(1,"商品1", 2),
                new Item(1,"商品1", 3),
                new Item(1,"商品1", 6),
        }));
        orderService.addOrder(order);
        return JsonResult.ok().msg("添加订单成功");
    }
}
