package com.example.test.sql.Entities;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String tel;
    private int age;

    public User(
            String firstName,
            String lastName,
            String tel,
            int age
    ) {
        this.firstName = firstName;
        this.lastName  = lastName;
        this.tel = tel;
        this.age = age;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "tel")
    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Column(name = "age")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
