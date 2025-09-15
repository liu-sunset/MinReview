package peng.zhi.liu.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import peng.zhi.liu.entity.OperationLog;
import peng.zhi.liu.mapper.OperationLogMapper;
import peng.zhi.liu.utils.BaseContext;
import peng.zhi.liu.utils.BaseContextIndenty;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Autowired
    private OperationLogMapper operationLogMapper;
    @Around("@annotation(peng.zhi.liu.annotation.OperationLog)")
    public Object OperationLog(ProceedingJoinPoint joinPoint) throws Throwable {
        Long start = System.currentTimeMillis();
        Object result = null;
        Integer flag = 1;
        String errorMsg = null;
        try {
            result = joinPoint.proceed();
        }catch (Exception e){
            flag = 0;
            errorMsg = e.getMessage();
            throw e;
        } finally{
            Long end = System.currentTimeMillis();
            Long during = end-start;
            //确认操作者是管理员还是用户
            String indenty = BaseContextIndenty.getThreadLocal();
            //获取注解中的操作类型
            MethodSignature signature = (MethodSignature)joinPoint.getSignature();
            Method method = signature.getMethod();
            peng.zhi.liu.annotation.OperationLog annotation = method.getAnnotation(peng.zhi.liu.annotation.OperationLog.class);
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
            log.info("开始构建日志实体");
            if("admin".equals(indenty)){
                OperationLog operationLog = OperationLog.builder()
                        .adminId(BaseContext.getId())
                        .operationType(annotation.value().toString())
                        .requestUrl(request.getRequestURI())
                        .requestMethod(method.getName())
                        .requestParams(Arrays.toString(joinPoint.getArgs()))
                        .responseData(result != null ? result.toString() : "Null")
                        .status(flag)
                        .errorMsg(errorMsg)
                        .executeTime(during)
                        .createTime(LocalDateTime.now())
                        .build();
                operationLogMapper.addOperationLogMapper(operationLog);
            }else {
                OperationLog operationLog = OperationLog.builder()
                        .userId(BaseContext.getId())
                        .operationType(annotation.value().toString())
                        .requestUrl(request.getRequestURI())
                        .requestMethod(method.getName())
                        .requestParams(Arrays.toString(joinPoint.getArgs()))
                        .responseData(result != null ? result.toString() : "Null")
                        .status(flag)
                        .errorMsg(errorMsg)
                        .executeTime(during)
                        .createTime(LocalDateTime.now())
                        .build();
                operationLogMapper.addOperationLogMapper(operationLog);
            }
        }
        return result;
    }
}
