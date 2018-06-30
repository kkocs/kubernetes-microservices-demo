package com.example.springjpademo;

import javax.persistence.*;

@Entity
@Table(name = "resource_table")
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String content;

    public Resource() {
        super();
    }

    public Resource(int id, String name, String token, String content) {
        this.id = id;
        this.name = name;
        this.content = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return content;
    }

    public void setToken(String token) {
        this.content = token;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", token='" + content + '\'' +
                '}';
    }
}