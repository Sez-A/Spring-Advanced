package bg.softuni.schedules.entity;

import javax.persistence.*;

@Entity

public class Person {
    private Long id;
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public Person setId(Long id) {
        this.id = id;
        return this;
    }

    @Column
    public String getName() {
        return name;
    }

    public Person setName(String name) {
        this.name = name;
        return this;
    }
}
