package com.sjw;

import com.sjw.component.Start;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by Administrator on 2018/12/21.
 */
@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext run = SpringApplication.run(Main.class, args);
            Start bean = run.getBean(Start.class);
            bean.getStart();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ;
}
