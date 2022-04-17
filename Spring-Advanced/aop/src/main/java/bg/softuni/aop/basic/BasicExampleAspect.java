package bg.softuni.aop.basic;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "true")
public class BasicExampleAspect {
    private final Logger LOGGER = LoggerFactory.getLogger(BasicExampleAspect.class);

    @Pointcut("execution(* bg.softuni.aop.Student.*(..))")
    public void track() {
    }

    @Pointcut("execution(* bg.softuni.aop.Student.echo(..))")
    public void trackEcho() {
    }

    @Pointcut("execution(* bg.softuni.aop.Student.concat(..))")
    public void trackConcat() {
    }

    @Before("track()")
    public void beforeAnyMethod(JoinPoint joinPoint) {
        LOGGER.info("Before calling: {}", joinPoint.getSignature());
    }

    @Before("trackEcho()")
    public void beforeEchoMethod() {
        LOGGER.info("Advice execution before calling echo.");
    }

    @AfterThrowing(pointcut = "track()", throwing = "error")
    public void trackExceptions(Throwable error) {
        LOGGER.info("I have detected exception ", error);
    }

    @AfterReturning(value = "trackConcat()", returning = "result")
    public void afterConcatMethod(Object result) {
        LOGGER.info("After concat method result is: {}", result);
    }
}
