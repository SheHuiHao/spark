import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class StartProvoide {
    public static void main(String[] args) throws IOException {
        //启动工厂时  就已经把服务注册到zookeeper中
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        System.in.read();
    }
}
