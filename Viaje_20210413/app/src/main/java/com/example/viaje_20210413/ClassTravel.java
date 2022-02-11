package com.example.viaje_20210413;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ClassTravel implements Serializable {

    private String code, destiny, passengers, price;

    public ClassTravel(String code, String destiny, String passengers, String price){
        this.code = code;
        this.destiny = destiny;
        this.passengers = passengers;
        this.price = price;
    }

    public  String getCode(){return  code;}
    public  String getDestiny(){return  destiny;}
    public  String getPassengers(){return  passengers;}
    public  String getPrice(){return  price;}


    public  void setCode(String code){this.code = code;}
    public  void setDestiny(String destiny){this.destiny = destiny;}
    public  void setPassengers(String passengers){this.passengers = passengers;}
    public  void setPrice(String ppp){this.price = ppp;}

    @NonNull
    @Override
    public String toString() {
        return "ClassTravel{" +
                "code ='" + code + '\'' +
                ", destiny ='" + destiny + '\'' +
                ", passengers ='" + passengers + '\'' +
                ", price per passenger ='" + price + '\'' +
                '}';
    }
}
