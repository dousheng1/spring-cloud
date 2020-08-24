package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: dqs
 * @create: 2020-08-20 18:55
 **/
@RestController
@Slf4j
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Value("${server.port}")
    private int port;
    //获取订单中的商品
    @GetMapping("/{orderid}")
    public JsonResult getItems(@PathVariable("orderid") String orderId){
        log.info("orderId="+orderId+",port="+port);
        List<Item> items = itemService.getItems(orderId);
        return JsonResult.ok().data(items);
    }
    //减少商品库存
    @PostMapping("/decreasenumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        itemService.decreaseNumbers(items);
        return JsonResult.ok().msg("减少商品库存成功");
    }
}
