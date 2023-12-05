package in.dev.gmsk.aop;

import org.springframework.stereotype.Component;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

import java.util.Date;

@Aspect
@Component
public class LoginAspect {

    @Before(value = "execution(* in.dev.gmsk.controller.LoginController.*(..))")
    public void beforeRequestToLogin(JoinPoint joinPoint){
        System.out.println("Requesting to "+ joinPoint.getSignature() +" End time : "+ new Date());
    }
    @After(value = "execution(* in.dev.gmsk.controller.LoginController.*(..))")
    public void afterRequestToLogin(JoinPoint joinPoint){
        System.out.println("Requesting to "+ joinPoint.getSignature() +" End time : "+ new Date());
    }
}
