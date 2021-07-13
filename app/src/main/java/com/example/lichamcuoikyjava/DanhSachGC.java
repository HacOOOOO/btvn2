package com.example.lichamcuoikyjava;

public class DanhSachGC {
    private long id;
    private String noidung;
    private String daykey;
    private String daytime;


    public DanhSachGC() {
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoidung() {
        return noidung;
    }

    public void setNoidung(String noidung) {
        this.noidung = noidung;
    }

    public String getDaykey() {
        return daykey;
    }

    public void setDaykey(String daykey) {
        this.daykey = daykey;
    }

    public String getDaytime() {
        return daytime;
    }

    public void setDaytime(String daytime) {
        this.daytime = daytime;
    }

    @Override
    public String toString(){

        return noidung+ " ("+daytime+")";
    }
}
