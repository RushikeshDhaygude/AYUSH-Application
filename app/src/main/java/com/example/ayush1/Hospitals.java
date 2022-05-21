package com.example.ayush1;

public class Hospitals {

    public String address;
    public String phone_no2;
    public String email;
    public String bed_count;
    public String specialization;
    public String rating;
    public String hospital_name;
    public String latitude;
    public String longitude;

    public Hospitals(){}

    public Hospitals(String hospital_name, String address, String phone2, String email, String bed_count, String specialization, String rating, String latitude, String longitude){
        this.hospital_name = hospital_name;
        this.address = address;
        this.phone_no2 = phone2;
        this.email = email;
        this.bed_count = bed_count;
        this.specialization = specialization;
        this.rating = rating;
        this.latitude = latitude;
        this.longitude = longitude;
    }

}
