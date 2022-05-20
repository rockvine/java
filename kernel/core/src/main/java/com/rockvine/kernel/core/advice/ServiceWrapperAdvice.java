package com.rockvine.kernel.core.advice;

import com.alibaba.fastjson.JSON;
import com.rockvine.kernel.core.enums.ErrorCodeEnum;
import com.rockvine.kernel.core.exception.BusinessException;
import com.rockvine.kernel.core.model.result.BaseResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author rocky
 * @date 2022-05-20 22:03
 * @description 日志增强类
 */

@Component
@Aspect
public class ServiceWrapperAdvice {
    private static final Logger logger = LoggerFactory.getLogger(ServiceWrapperAdvice.class);

    private static final int MAX_LOG_LENGTH = 2000;
    private static final int METHOD_REQUEST_TIME_THRESHOLD = 1000;

    @Pointcut("execution(public * com.rockvine..*.service..*(..))")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object doAround(ProceedingJoinPoint point) {
        final long begin = System.currentTimeMillis();
        Object object = null;
        try {
            object = point.proceed();
            return object;
        } catch (BusinessException e) {
            MethodSignature signature = (MethodSignature) point.getSignature();
            logger.info("{}.{} failed, args:{}, code:{}, msg:{}", signature.getDeclaringTypeName(), signature.getName(),
                    JSON.toJSONString(point.getArgs()), e.getErrorCode(), e.getMessage());
            return BaseResult.buildFailResult(e.getErrorCode(), e.getMessage());
        } catch (Throwable e) {
            MethodSignature signature = (MethodSignature) point.getSignature();
            logger.warn("{}.{} exception, args:{}", signature.getDeclaringTypeName(), signature.getName(),
                    JSON.toJSONString(point.getArgs()), e);
            return BaseResult.buildFailResult(ErrorCodeEnum.SYSTEM_ERROR.getCode(), ErrorCodeEnum.SYSTEM_ERROR.getDesc());
        } finally {
            logMethodInfo(object, point, begin);
        }
    }

    private void logMethodInfo(Object result, ProceedingJoinPoint point, long begin) {
        String resultStr = JSON.toJSONString(result);
        String argsStr = JSON.toJSONString(point.getArgs());
        if (resultStr.length() > MAX_LOG_LENGTH) {
            resultStr = resultStr.substring(0, MAX_LOG_LENGTH);
        }
        final long timeUsed = System.currentTimeMillis() - begin;
        logger.info("ServiceWrapperAdvice|{}|method:{}|cost:{}ms|args:{}|return:{}", timeUsed > METHOD_REQUEST_TIME_THRESHOLD ? "TimeOut" : "",
                point.toLongString(), timeUsed, argsStr, resultStr);
    }
}
