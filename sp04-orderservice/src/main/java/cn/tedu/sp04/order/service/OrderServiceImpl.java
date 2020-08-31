package cn.tedu.sp04.order.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.pojo.User;
import cn.tedu.sp01.service.OrderService;
import cn.tedu.sp04.order.feign.ItemFeignClient;
import cn.tedu.sp04.order.feign.UserFeignClient;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-23 19:29
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired
    private ItemFeignClient itemFeignClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @Override
    public Order getOrder(String orderId) {
        // 调用用户,获取用户
        JsonResult<User> user = userFeignClient.getUser(8);
        // 调用商品,获取订单商品列表
        JsonResult<List<Item>> items = itemFeignClient.getItems(orderId);
        Order order = new Order();
        order.setId(orderId);
        order.setUser(user.getData());
        order.setItems(items.getData());
        return order;
    }

    @Override
    public void addOrder(Order order) {
        //  调用用户,增加积分
        userFeignClient.addScore(order.getUser().getId(),1000);
        //  调用商品,减少库存
        itemFeignClient.decreaseNumber(order.getItems());
        log.info("保存订单:"+order);
    }
}
