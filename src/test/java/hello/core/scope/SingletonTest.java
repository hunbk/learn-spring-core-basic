package hello.core.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    void singletonBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);
        System.out.println("find SingletonBean1");
        SingletonBean SingletonBean1 = ac.getBean(SingletonBean.class);
        System.out.println("find SingletonBean2");
        SingletonBean SingletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("SingletonBean1 = " + SingletonBean1);
        System.out.println("SingletonBean2 = " + SingletonBean2);
        assertThat(SingletonBean1).isSameAs(SingletonBean2);
        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean {

        @PostConstruct
        public void init() {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("SingletonBean.destroy");
        }
    }
}
