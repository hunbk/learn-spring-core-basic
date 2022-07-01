package hello.core.singleton;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.assertj.core.api.Assertions.assertThat;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);

        StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService", StatefulService.class);

        // threadA: userA 10000원 주문
        statefulService1.order("userA", 10000);
        // threadB: userB 20000원 주문
        statefulService2.order("userB", 20000);

        // threadA: userA 주문 가격 조회
        int price = statefulService1.getPrice();
        // threadA: userA는 10000원을 주문했지만 20000원이 출력됨
        System.out.println("price = " + price);

        assertThat(price).isEqualTo(20000);

    }

    static class TestConfig {

        @Bean
        public StatefulService statefulService() {
            return new StatefulService();
        }
    }
}