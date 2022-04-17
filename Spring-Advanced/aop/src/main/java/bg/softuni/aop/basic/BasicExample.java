package bg.softuni.aop.basic;

import bg.softuni.aop.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "true")
public class BasicExample implements CommandLineRunner {
    private final Student student;

    public BasicExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {
        student.sayHello();
        student.echo("Calling from BasicExample.class");
        student.concat("Hello, ", "John");

        try {
            student.boom();
        } catch (IllegalStateException e) {
            System.out.println("Exception message: " + e.getMessage());
        }
    }
}
