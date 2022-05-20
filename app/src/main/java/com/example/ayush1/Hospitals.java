package com.example.ayush1;

public class Hospitals {
    public String address;
    public String phone_no2;
    public String email;
    public String bedcount;
    public String specialization;
    public String rating;
    public String name;

    public Hospitals(){}

    public Hospitals(String name, String addr, String phone2, String email, String bed, String spec, String rating){
        this.name = name;
        this.address = addr;
        this.phone_no2 = phone2;
        this.email = email;
        this.bedcount = bed;
        this.specialization = spec;
        this.rating = rating;
    }

}
