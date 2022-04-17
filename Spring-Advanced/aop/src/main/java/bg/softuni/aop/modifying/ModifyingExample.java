package bg.softuni.aop.modifying;

import bg.softuni.aop.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@ConditionalOnProperty(name = "examples.modifying.enabled", havingValue = "false")
public class ModifyingExample implements CommandLineRunner {
    private final Student student;

    public ModifyingExample(Student student) {
        this.student = student;
    }

    @Override
    public void run(String... args) throws Exception {
        String firstArg = "Hello, ", secondArg = "John";
        System.out.println("Result before modifying is: " + firstArg + secondArg);
        System.out.println(student.concat("Hello, ", "John"));
    }
}
