package com.mascitra.digphotoworks.product;

/**
 * Created by SONY on 25/09/2017.
 */

public class Product {

    private int gambarID;
    private String paket;
    private String harga;
    private String deskripsi;


    public Product() {

    }

    public Product(int gambarID, String paket,String harga, String deskripsi) {
        this.gambarID = gambarID;
        this.paket = paket;
        this.harga = harga;
        this.deskripsi = deskripsi;

    }

    public int getGambarID() {
        return gambarID;
    }

    public void setGambarID(int gambarID) {
        this.gambarID = gambarID;
    }

    public String getPaket() {
        return paket;
    }

    public void setPaket(String paket) {
        this.paket = paket;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
