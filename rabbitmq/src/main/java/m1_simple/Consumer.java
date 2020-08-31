package m1_simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: spring-cloud
 * @description: 消费者
 * @author: dqs
 * @create: 2020-08-31 10:23
 **/
public class Consumer {
    public static void main(String[] args) throws Exception{
        //连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.128");
        //f.setPort(5672); //默认端口可以省略
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c = f.newConnection().createChannel();

        //定义队列
        c.queueDeclare("helloworld",false, false, false,null);
        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                byte[] body = delivery.getBody();
                String msg = new String(body);
                System.out.println("收到:"+msg);
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };

        //从helloworld队列接受信息,消费信息
        c.basicConsume("helloworld",true,deliverCallback,cancelCallback);
    }

}
