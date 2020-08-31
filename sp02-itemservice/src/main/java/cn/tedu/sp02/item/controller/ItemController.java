package cn.tedu.sp02.item.controller;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import cn.tedu.web.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

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
    public JsonResult getItems(@PathVariable("orderid") String orderId) throws InterruptedException {
        log.info("orderId="+orderId+",port="+port);
        List<Item> items = itemService.getItems(orderId);

        if (Math.random() <0.6) {
            // 60%概率会执行延迟代码
            int t = new Random().nextInt(5000);
            System.out.println("延迟:" + t);
            Thread.sleep(t);
        }
        return JsonResult.ok().data(items).msg("port="+port);
    }
    //减少商品库存
    @PostMapping("/decreaseNumber")
    public JsonResult<?> decreaseNumber(@RequestBody List<Item> items) {
        itemService.decreaseNumbers(items);
        return JsonResult.ok().msg("减少商品库存成功");
    }
}
