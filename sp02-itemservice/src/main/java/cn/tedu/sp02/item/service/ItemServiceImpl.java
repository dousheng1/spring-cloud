package cn.tedu.sp02.item.service;

import cn.tedu.sp01.pojo.Item;
import cn.tedu.sp01.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: springcloud
 * @description:
 * @author: dqs
 * @create: 2020-08-20 18:40
 **/
@Service
@Slf4j
public class ItemServiceImpl implements ItemService {
    @Override
    public List<Item> getItems(String orderId) {
        ArrayList<Item> items = new ArrayList<>();
            items.add(new Item(1,"商品1",1));
            items.add(new Item(2,"商品2",2));
            items.add(new Item(3,"商品3",4));
            items.add(new Item(4,"商品4",2));
            items.add(new Item(5,"商品5",1));
            return items;
    }

    @Override
    public void decreaseNumbers(List<Item> list) {
        for (Item item : list) {
            log.info("减少商品库存:"+item);
        }
    }
}
