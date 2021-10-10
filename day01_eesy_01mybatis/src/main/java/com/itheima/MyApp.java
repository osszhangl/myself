package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Created by zhanglin on 2021/7/22.
 */
@SpringBootApplication
@EnableSwagger2
public class MyApp {

    public static void main(String[] args) {
        SpringApplication.run(MyApp.class,args );
    }

}
