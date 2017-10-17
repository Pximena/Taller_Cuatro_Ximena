package com.i044114.taller_cuatro_ximena.Models;

/**
 * Created by ACER on 16/10/2017.
 */

public class User {
    private int id;
    private String name;
    private String username;
    private String address;
    private String company;
    private String[] arreglo;

    public String ArregloImages(){
        arreglo= new String[5];
        arreglo[0]= "http://jp.seaicons.com/wp-content/uploads/2015/10/Office-Client-Female-Dark-icon.png";
        arreglo[1]= "http://icons.iconarchive.com/icons/visualpharm/must-have/256/User-icon.png";
        arreglo[2]= "http://icons.iconarchive.com/icons/icons-land/vista-people/256/Office-Client-Male-Light-icon.png";
        arreglo[3]= "http://vitakonsult.lt/wp-content/uploads/2015/10/darbuotoja.png";
        arreglo[4]= "http://www.endeavourhillsphysio.com.au/images/joomlart/GirlPhysio.png";

        int  ran = (int) (Math.random()*5);
        return arreglo[ran];
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
