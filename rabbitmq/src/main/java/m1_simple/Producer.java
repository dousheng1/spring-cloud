package m1_simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: spring-cloud
 * @description:
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
        c.queueDeclare("helloworld", false,false,false,null);

        //发送消息
        c.basicPublish("","helloworld",null,"Hello World".getBytes());
        System.out.println("消息已发送");
        //关闭连接
        f.clone();
        c.close();
        con.close();
    }
}
