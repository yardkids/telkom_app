package com.telkom.telkom_app;

public class MyDataModel {

    private String Nama;
    private String Id;
    private String Perusahaan;
    private String Kota;



    public String getNama() {
        return Nama;
    }

    public void setNama(String Nama) {
        this.Nama = Nama;
    }

    public String getPerusahaan(){
        return Perusahaan;
    }

    public void setPerusahaan(String Perusahaan){
        this.Perusahaan = Perusahaan;
    }

    public String getKota() {
        return Kota;
    }

    public void setKota(String Kota){
        this.Kota = Kota;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        this.Id=id;
    }

}