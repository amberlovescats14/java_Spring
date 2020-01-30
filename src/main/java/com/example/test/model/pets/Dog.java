package com.example.test.model.pets;

import javax.persistence.*;

@Entity
//@Table(name = "dogs")
public class Dog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "int(11) unsigned not null")
    private long id;

//    @Column(nullable = false)
    @Column(columnDefinition = ("tinyint(3) unsigned"), nullable = false)
    private int age;

    @Column(columnDefinition = ("varchar(200)"), nullable = false)
    private String name;

    @Column(nullable = false, columnDefinition = ("char(2) default 'XX'"))
    private String resideState;

    public Dog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResideState() {
        return resideState;
    }

    public void setResideState(String resideState) {
        this.resideState = resideState;
    }
}
