package bg.softuni.aop.modifying;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "false")
public class ModifyingAspect {

    @Pointcut("execution(* bg.softuni.aop.Student.concat(..))")
    public void concatPointcut() {
    }

    @Around("concatPointcut() && args(a,b)")
    public Object modifyArguments(ProceedingJoinPoint pjp, String a, String b) throws Throwable {
        String firstModifiedArgument = "Bye bye, ";
        String secondModifiedArgument = "John!";

        Object proceed = pjp.proceed(new Object[]{
                firstModifiedArgument,
                secondModifiedArgument
        });

        return "Result after modifying in ModifyingAspect.class is: " + proceed;
    }
}
