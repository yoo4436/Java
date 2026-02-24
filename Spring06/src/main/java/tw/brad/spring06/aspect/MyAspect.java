package tw.brad.spring06.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import tw.brad.spring06.dto.Register;

@Aspect
@Component
public class MyAspect {

    @Pointcut("execution(* tw.brad.spring06.controller..*(..))")
    public void doAllController() {}

    @Pointcut("execution(* tw.brad.spring06.controller.TestController.*(..))")
    public void doTestController() {}

    @Pointcut("execution(* tw.brad.spring06.controller..*(..))")
    public void doControllerMethod() {

    }

    @Before("doBradController()")
    public void doBradBefore(JoinPoint joinPoint) {
        String name = joinPoint.getSignature().getName();
        String type = joinPoint.getSignature().getDeclaringTypeName();
        
        Object[] args = joinPoint.getArgs();

        System.out.printf("%s:%s\n", name, type);
        if (args != null) {
            if (args[0] instanceof Register) {
                Register reg = (Register)args[0];
                System.out.println(reg.getAccount());
                System.out.println(reg.getPw());
                System.out.println(reg.getName());
                String account = reg.getAccount().toUpperCase();
                reg.setAccount(account);
            }
        }
        
    }
}
