package com.mascitra.digphotoworks.product;

/**
 * Created by SONY on 09/10/2017.
 */

public class Promo {

    private int gambarID;
    private String paket;
    private String harga;

    public Promo(int gambarID, String paket, String harga) {
        this.gambarID = gambarID;
        this.paket = paket;
        this.harga = harga;
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

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
    }
}
