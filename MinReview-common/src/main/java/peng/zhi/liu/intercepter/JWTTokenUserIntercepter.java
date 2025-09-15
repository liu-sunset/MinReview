package peng.zhi.liu.intercepter;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import peng.zhi.liu.constant.AdminConstant;
import peng.zhi.liu.constant.UserConstant;
import peng.zhi.liu.property.JWTProperty;
import peng.zhi.liu.utils.BaseContext;
import peng.zhi.liu.utils.BaseContextIndenty;
import peng.zhi.liu.utils.JWTUtils;


@Component
public class JWTTokenUserIntercepter implements HandlerInterceptor {
    private static final Logger log = LoggerFactory.getLogger(JWTTokenUserIntercepter.class);
    @Autowired
    private JWTProperty jwtProperty;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
            return true;
        }

        String tokenTemp = request.getHeader("authorization");
        String token = tokenTemp.substring(7);
        try {
            log.info("用户token是{}",token);
            Claims claims = JWTUtils.parseJWT(jwtProperty.getSecretKey(),token);
            long userId = Long.valueOf( claims.get(UserConstant.USER_ID_INTERCEPTER).toString());
            BaseContext.setId(userId);
            BaseContextIndenty.setThreadLocal("user");
            return true;
        }catch(Exception e)
        {
            response.setStatus(401);
            return false;
        }
    }
}
