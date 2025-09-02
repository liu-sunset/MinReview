package peng.zhi.liu.config;

import lombok.ToString;
import peng.zhi.liu.intercepter.JWTTokenAdminIntercepter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import peng.zhi.liu.intercepter.JWTTokenUserIntercepter;

@Slf4j
@Configuration
public class WebMvcConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JWTTokenAdminIntercepter jwtTokenAdminIntercepter;
    @Autowired
    private JWTTokenUserIntercepter jwtTokenUserIntercepter;

    @Override
    //添加拦截器
    public void addInterceptors(InterceptorRegistry interceptorRegistry){
        log.info("开始注册管理员拦截器");
        interceptorRegistry.addInterceptor(jwtTokenAdminIntercepter)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login")
                        .excludePathPatterns("/admin/file/upload/image");

        interceptorRegistry.addInterceptor(jwtTokenUserIntercepter)
                .addPathPatterns("/user/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register");
    }
}
