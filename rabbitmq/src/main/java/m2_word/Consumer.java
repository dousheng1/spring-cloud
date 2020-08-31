package m2_word;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: spring-cloud
 * @description: 消费者
 * @author: dqs
 * @create: 2020-08-31 14:30
 **/
public class Consumer {
    public static void main(String[] args) throws Exception{
        // 连接
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.128");
//        f.setHost("5672");
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();
        // 定义队列
        c.queueDeclare("task_queue", true, false, false, null);

        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                byte[] body = delivery.getBody();
                String msg = new String(body);
                System.out.println("收到:"+msg);
                for (int i = 0; i < msg.length() ; i++) {
                    if (msg.charAt(i) == '.') {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                c.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
                System.out.println("消息处理完成\n");
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };

        // QOS: Quality of Service
        // 理解: 每次出去的消息数量
        // 如果设置成1,每次只抓取一条消息,这条消息处理完之前,不会继续抓取下一条
        // 必须在手动ACK模式下才有效
        c.basicQos(1);

        //从helloworld队列接受信息,消费信息
        c.basicConsume("task_queue",true,deliverCallback,cancelCallback);
        System.out.println("开始消费数据");
    }
}
