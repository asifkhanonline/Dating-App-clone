package com.lco.dating;

public class User {
    public String username;
    public String email;
    public String Password;
//    public String id;
  public String dob;
  public String address;
//    public String postalcode;
    public String city;


    public User(String username,String Email,String Pass,String dob,String address,String city) {
        this.username=username;
        //this.id=id;
        this.Password=Pass;
        this.email=Email;
        this.dob=dob;
        this.city=city;
        this.address=address;
//        this.postalcode=Postalcode;
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }



}