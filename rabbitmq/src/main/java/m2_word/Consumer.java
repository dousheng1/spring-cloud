package m2_word;

import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @program: spring-cloud
 * @description: ������
 * @author: dqs
 * @create: 2020-08-31 14:30
 **/
public class Consumer {
    public static void main(String[] args) throws Exception{
        // ����
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.128");
//        f.setHost("5672");
        f.setUsername("admin");
        f.setPassword("admin");
        Channel c = f.newConnection().createChannel();
        // �������
        c.queueDeclare("task_queue", true, false, false, null);

        DeliverCallback deliverCallback = new DeliverCallback() {

            @Override
            public void handle(String s, Delivery delivery) throws IOException {
                byte[] body = delivery.getBody();
                String msg = new String(body);
                System.out.println("�յ�:"+msg);
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
                System.out.println("��Ϣ�������\n");
            }
        };
        CancelCallback cancelCallback = new CancelCallback() {
            @Override
            public void handle(String s) throws IOException {

            }
        };

        // QOS: Quality of Service
        // ���: ÿ�γ�ȥ����Ϣ����
        // ������ó�1,ÿ��ֻץȡһ����Ϣ,������Ϣ������֮ǰ,�������ץȡ��һ��
        // �������ֶ�ACKģʽ�²���Ч
        c.basicQos(1);

        //��helloworld���н�����Ϣ,������Ϣ
        c.basicConsume("task_queue",true,deliverCallback,cancelCallback);
        System.out.println("��ʼ��������");
    }
}
