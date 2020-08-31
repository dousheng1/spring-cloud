package m1_simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: spring-cloud
 * @description: 生产者
 * @author: dqs
 * @create: 2020-08-29 17:10
 **/
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //连接Rabbitmq 服务器
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.128"); //收发消息都是5672
        f.setPort(5672);
        f.setUsername("admin");
        f.setPassword("admin");

        Connection con = f.newConnection();
        Channel c = con.createChannel();
        //定义队列,会通知服务器想使用一个"helloworld"队列
        //服务器会找到这个队列,如果不存在,服务器会新建队列
        // 5.个参数 队列名,是否持久化队列,是否排他(独占)队列,是否自动删除,其他参数属性
        c.queueDeclare("helloworld", false,false,false,null);

        //发送消息
        // 4个参数  空串-后面用到时在介绍,队列名,其他参数属性,消息数据
        c.basicPublish("","helloworld",null,"Hello World".getBytes());
        System.out.println("消息已发送");
        //关闭连接
        f.clone();
        c.close();
        con.close();
    }
}
