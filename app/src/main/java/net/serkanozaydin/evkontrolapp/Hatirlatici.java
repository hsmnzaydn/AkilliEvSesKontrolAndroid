package net.serkanozaydin.evkontrolapp;

/**
 * Created by hsmnzaydn on 4/16/17.
 */

public class Hatirlatici {
    private String Ay;
    private String Gun;
    private String Yil;
    private String Saat;
    private String Dakika;
    private String Gorev;


    public Hatirlatici(String Ay,String Gun,String Yil,String Saat,String Dakika,String Gorev){
        this.setAy(Ay);
        this.setGun(Gun);
        this.setDakika(Dakika);
        this.setSaat(Saat);
        this.setYil(Yil);
        this.setGorev(Gorev);
    }


    public String getAy() {
        return Ay;
    }

    public void setAy(String ay) {
        Ay = ay;
    }

    public String getGun() {
        return Gun;
    }

    public void setGun(String gun) {
        Gun = gun;
    }

    public String getYil() {
        return Yil;
    }

    public void setYil(String yil) {
        Yil = yil;
    }

    public String getSaat() {
        return Saat;
    }

    public void setSaat(String saat) {
        Saat = saat;
    }

    public String getDakika() {
        return Dakika;
    }

    public void setDakika(String dakika) {
        Dakika = dakika;
    }

    public String getGorev() {
        return Gorev;
    }

    public void setGorev(String gorev) {
        Gorev = gorev;
    }
}

