import com.sina.service.TestDubbo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class TestDubboConsumer {
    public static void main(String[] args) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application.xml");
        TestDubbo testService_consumer = (TestDubbo) context.getBean("testDubbo");
        int sum = testService_consumer.sum(1, 2);
        System.out.println(sum);
        System.in.read();
    }
}
