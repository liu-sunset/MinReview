package peng.zhi.liu.handle;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import peng.zhi.liu.result.Result;
import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /*
    * 处理所有异常
    * mybatis会将SQLIntegrityConstraintViolationException封装成runtimeexception向上抛出
    * */
    @ExceptionHandler
    public Result exceptionHandler(RuntimeException e){
        if (e.getCause() instanceof SQLIntegrityConstraintViolationException && e.getMessage().contains("Duplicate entry")){
            SQLIntegrityConstraintViolationException se = (SQLIntegrityConstraintViolationException) e.getCause();
            String[] splits = se.getMessage().split(" ");
            log.error("SQL异常信息:{}","已存在"+splits[2]);
            return Result.error("已存在"+splits[2]);
        }
        else{
            // 获取异常堆栈信息中的第一个元素（即异常发生的类和方法）
            StackTraceElement[] stackTrace = e.getStackTrace();
            if (stackTrace.length > 0) {
                StackTraceElement element = stackTrace[0];
                String className = element.getClassName();
                String methodName = element.getMethodName();
                int lineNumber = element.getLineNumber();
                log.error("业务异常信息: {}，异常发生位置: {}#{}:{}", e.getMessage(), className, methodName, lineNumber);
            } else {
                log.error("业务异常信息:{}", e.getMessage());
            }
            return Result.error(e.getMessage());
        }
    }
}
