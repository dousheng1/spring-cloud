package cn.tedu.sp04.order.service;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.sp01.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-23 19:29
 **/
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Override
    public Order getOrder(String orderId) {
        // TODO:调用用户,获取用户

        //TODO: 调用商品,获取订单商品列表

        Order order = new Order();
        order.setId(orderId);
        return order;
    }

    @Override
    public void addOrder(Order order) {
        // TODO: 调用用户,增加积分
        // TODO: 调用商品,减少库存

        log.info("保存订单:"+order);
    }
}
