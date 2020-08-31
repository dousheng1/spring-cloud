package m1_simple;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: spring-cloud
 * @description: ������
 * @author: dqs
 * @create: 2020-08-31 10:23
 **/
public class Consumer {
    public static void main(String[] args) throws Exception{
        //����
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.128");
        //f.setPort(5672); //Ĭ�϶˿ڿ���ʡ��
        f.setUsername("admin");
        f.setPassword("admin");

        Channel c = f.newConnection().createChannel();

        //�������
        c.queueDeclare("helloworld",false, false, false,null);
        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                byte[] body = delivery.getBody();
                String msg = new String(body);
                System.out.println("�յ�:"+msg);
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };

        //��helloworld���н�����Ϣ,������Ϣ
        c.basicConsume("helloworld",true,deliverCallback,cancelCallback);
    }

}
