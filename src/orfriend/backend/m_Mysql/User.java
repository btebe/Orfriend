package com.orfriend.orfriend.backend.m_Mysql;

/**
 * Created by basmatebe on 1/2/17.
 */

public class User {

    public String usernameOrEmail,password, email, nationality, status, job, hobby , pic, lname;
    public int id, age;

    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {

        this.lname = lname;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getHobby() {
        return hobby;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getPic() {
        return pic;
    }
}
