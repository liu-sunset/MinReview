package peng.zhi.liu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"peng.zhi.liu.intercepter","peng.zhi.liu.property","peng.zhi.liu.handle","peng.zhi.liu.config","peng.zhi.liu"})
public class MinReviewProjectApplication {
    public static void main(String[] args) {
        SpringApplication.run(MinReviewProjectApplication.class, args);
    }

}
