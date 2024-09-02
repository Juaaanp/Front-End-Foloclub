package frontend.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import frontend.Serialize;

public class Member implements Serializable{
    private int age;
    private String name;
    private String mail;
    private String id;
    
    public Member(int age, String name, String mail, String id) {
        this.age = age;
        this.name = name;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void save(List<Member> members) {
        Serialize.serializeObject(Serialize.daoRoute() + "members.txt", new ArrayList<>(members));
    }

}
