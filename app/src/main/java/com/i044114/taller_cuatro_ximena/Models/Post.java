package com.i044114.taller_cuatro_ximena.Models;

/**
 * Created by ACER on 16/10/2017.
 */

public class Post {

    private int userid;
    private int id;
    private String title;
    private String body;

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
