package m1_simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @program: spring-cloud
 * @description: ������
 * @author: dqs
 * @create: 2020-08-29 17:10
 **/
public class Producer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //����Rabbitmq ������
        ConnectionFactory f = new ConnectionFactory();
        f.setHost("192.168.126.128"); //�շ���Ϣ����5672
        f.setPort(5672);
        f.setUsername("admin");
        f.setPassword("admin");

        Connection con = f.newConnection();
        Channel c = con.createChannel();
        //�������,��֪ͨ��������ʹ��һ��"helloworld"����
        //���������ҵ��������,���������,���������½�����
        // 5.������ ������,�Ƿ�־û�����,�Ƿ�����(��ռ)����,�Ƿ��Զ�ɾ��,������������
        c.queueDeclare("helloworld", false,false,false,null);

        //������Ϣ
        // 4������  �մ�-�����õ�ʱ�ڽ���,������,������������,��Ϣ����
        c.basicPublish("","helloworld",null,"Hello World".getBytes());
        System.out.println("��Ϣ�ѷ���");
        //�ر�����
        f.clone();
        c.close();
        con.close();
    }
}
