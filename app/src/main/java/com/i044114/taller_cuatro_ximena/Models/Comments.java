package com.i044114.taller_cuatro_ximena.Models;

/**
 * Created by ACER on 16/10/2017.
 */

public class Comments {

    private int postid;
    private int id;
    private String email;
    private String body;

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
