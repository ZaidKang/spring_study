package SpringStudy.TheCore.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean(StatefulService.class);
        StatefulService statefulService2 = ac.getBean(StatefulService.class);

        //쓰레드A : A사용자가 10000원 주문
        statefulService1.order("userA", 10000);

        //쓰레드B : B사용자가 20000원 주문
        statefulService2.order("userB", 20000);

        int price = statefulService1.getPrice();
        System.out.println("price = " + price);


        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
        //10000원 나와야 되는데 20000원이 나옴(똑같은 instance니깐 갱신이 되어버린거임)
        //price는 공유되는 field인데 그걸 특정 클라이언트가 바꿔버리니깐 문제가 발생하는 거임.


    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }
}
