package peng.zhi.liu.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import peng.zhi.liu.constant.UserConstant;
import peng.zhi.liu.exception.UserException;

@Slf4j
@Aspect
@Component
public class JwtAspect {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Pointcut("@annotation(peng.zhi.liu.annotation.JwtInspect)")
    public void jwtInspectPointCut(){}

    @Before("jwtInspectPointCut()")
    public void jwtInspectMethod(JoinPoint joinPoint){
        log.info("开始检查token是否已经失效（用户登出）");
        //获取用户token
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String authorization = request.getHeader("Authorization");
        String token = authorization.substring(7);
        Boolean flag = stringRedisTemplate.hasKey(token);
        if(flag){
            throw new UserException(UserConstant.TOKEN_INVALID);
        }
    }
}
