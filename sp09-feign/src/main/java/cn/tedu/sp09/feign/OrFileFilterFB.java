package cn.tedu.sp09.feign;

import cn.tedu.sp01.pojo.Order;
import cn.tedu.web.util.JsonResult;
import org.springframework.stereotype.Component;

/**
 * @program: spring-cloud
 * @description:
 * @author: dqs
 * @create: 2020-08-28 20:34
 **/
@Component
public class OrFileFilterFB implements OrderFeignClient{
    @Override
    public JsonResult<Order> getOrder(String orderId) {
        return JsonResult.err().msg("获取订单失败");
    }

    @Override
    public JsonResult<?> addOrder() {
        return JsonResult.err().msg("保存订单失败");
    }
}
