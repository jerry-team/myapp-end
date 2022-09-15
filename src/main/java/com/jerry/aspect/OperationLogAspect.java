package com.jerry.aspect;

import com.jerry.annotation.OperationLogAnnotation;
import com.jerry.entity.*;
import com.jerry.mapper.SysOperLogMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Map;

@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    SysOperLogMapper logMapper;

//    HttpServletRequest request;

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 设置操作日志切入点   在注解的位置切入代码
     */
    @Pointcut("@annotation(com.jerry.annotation.OperationLogAnnotation)")
    public void operLogPoinCut() {
    }

    @AfterReturning(returning = "methodResult",value = "operLogPoinCut()")
    public void saveOperLog(JoinPoint joinPoint, Object methodResult) throws Throwable {
        try {
            // 获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            // 从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            //将返回值转换成Result
            Result result = (Result) methodResult;
            // 从切面织入点处通过反射机制获取织入点处的方法
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取切入点所在的方法
            Method method = signature.getMethod();
            //获取操作
            OperationLogAnnotation annotation = method.getAnnotation(OperationLogAnnotation.class);
            User user = (User)request.getAttribute("User");
            SysOperLog sysOperLog = new SysOperLog();
            sysOperLog.setUserid(user.getId());
            if(annotation != null)
            {
                sysOperLog.setType(annotation.operType());
                if(annotation.operType().equals("点击") && result.getCode() != 400){
                    Commodity commodity = (Commodity) result.getData();
                    sysOperLog.setCommodityid(commodity.getId());
                }
                else if(annotation.operType().equals("加入购物车") && result.getCode() != 400){
                    ShopCart shopCart = (ShopCart) result.getData();
                    sysOperLog.setCommodityid(shopCart.getCommodityId());
                }
                else sysOperLog.setCommodityid(-1);
            }
            logMapper.insert(sysOperLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
