package m2_word;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.util.Scanner;

/**
 * @program: spring-cloud
 * @description: ������ ����ģʽ
 * @author: dqs
 * @create: 2020-08-31 11:24
 **/
public class Producer {
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

        // ����Ϣ
        while (true) {
            System.out.print("������Ϣ:");
            String msg = new Scanner(System.in).nextLine();
            c.basicPublish("","task_queue", MessageProperties.PERSISTENT_TEXT_PLAIN, msg.getBytes());
        }
    }
}
