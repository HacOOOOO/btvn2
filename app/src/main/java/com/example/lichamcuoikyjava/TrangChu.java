package com.example.lichamcuoikyjava;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class TrangChu extends Activity {

    private LocalDate getChonNgay = LocalDate.now();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trang_chu);

        TextView thangAmTC = findViewById(R.id.thangTrangChu);
        TextView ngayAmTC = findViewById(R.id.ngayAmTrangChu);
        TextView ngayCC = findViewById(R.id.ngayCanChi);
        TextView thangCC = findViewById(R.id.thangCanChi);
        TextView namCC = findViewById(R.id.namCanChi);
        TextView thu = findViewById(R.id.thuTrangChu);

        thangAmTC.setText("Tháng "+thangAm()+" Năm "+namAm());
        ngayAmTC.setText(dateAm());
        ngayCC.setText("Ngày "+canChiNgay());
        thangCC.setText("Tháng "+canChiThang());
        namCC.setText("Năm "+canChiNam());
        thu.setText(thuTrangChu());
    }

    public String thuTrangChu(){
        String thuTrangChu;
        java.util.Date date=new java.util.Date();
        thuTrangChu=String.valueOf(date);
        String[] str = thuTrangChu.split(" ");
        return chuyenDoiThu(str[0]);

    }

    public String chuyenDoiThu(String str){
        String thuChuan = null;
        switch(str){
            case "Mon":
                thuChuan="Thứ Hai";
                break;
            case "Tue":
                thuChuan="Thứ Ba";
                break;
            case "Wed":
                thuChuan="Thứ Tư";
                break;
            case "Thu":
                thuChuan="Thứ Năm";
                break;
            case "Fri":
                thuChuan="Thứ Sáu";
                break;
            case "Sat":
                thuChuan="Thứ Bảy";
                break;
            case "Sun":
                thuChuan="Chủ Nhật";
                break;
        }
        return thuChuan;
    }

    public String canChiNam(){
        String canChiNam ;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);
        canChiNam = am.getLunarYear();

        String str = String.valueOf(canChiNam);

        return str;
    }

    public String canChiThang(){
        String canChiThang ;
        int thangam;
        int namam;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);
        thangam = am.ConVertToLunarMonth();
        namam= am.ConVertToLunarYear();
        canChiThang = am.getLunarMonth();

        String str = String.valueOf(canChiThang);

        return str;
    }

    public String canChiNgay(){
        String canChiNgay ;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);
        canChiNgay = am.getLunarDate();

        String str = String.valueOf(canChiNgay);

        return str;
    }
    String ngay;
    public String dateAm(){
        int dateAm = 0;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);

        dateAm = am.ConVertToLunarDay();

        if(dateAm<10){
            ngay = "0"+ String.valueOf(dateAm);
        }
        String str = ngay;
        return str;
    }

    public String thangAm(){
        int thangAm = 0;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);

        thangAm = am.ConVertToLunarMonth();

        String str = String.valueOf(thangAm);

        return str;
    }

    public String namAm(){
        int namAm = 0;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);

        namAm = am.ConVertToLunarYear();

        String str = String.valueOf(namAm);

        return str;
    }

    public String namTuNgay(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String thangNamChuoi = date.format(formatter);
        String[] catChuoiThangNam = thangNamChuoi.split(" ");
        String namDangChu = String.valueOf(catChuoiThangNam[1]);
        return namDangChu;
    }

    public String thangNamTuNgay(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM yyyy");
        String thangNamChuoi = date.format(formatter);
        String[] catChuoiThangNam = thangNamChuoi.split(" ");
        String thangDangChu = String.valueOf(chuyenDoiThangSangInt(catChuoiThangNam[0]));
        return thangDangChu;
    }

    public String ngayTuNgay(LocalDate date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd yyyy");
        String thangNamChuoi = date.format(formatter);
        String[] catChuoiThangNam = thangNamChuoi.split(" ");
        String thangDangChu = String.valueOf(catChuoiThangNam[1]);
        Log.e("tttttt",""+thangDangChu);
        return thangDangChu;
    }

    private int chuyenDoiThangSangInt(String str){
        int thangKieuInt = 0;
        switch (str){
            case "January":
                thangKieuInt = 1;
                break;
            case "February":
                thangKieuInt = 2;
                break;
            case "March":
                thangKieuInt = 3;
                break;
            case "April":
                thangKieuInt = 4;
                break;
            case "May":
                thangKieuInt = 5;
                break;
            case "June":
                thangKieuInt = 6;
                break;
            case "July":
                thangKieuInt = 7;
                break;
            case "August":
                thangKieuInt = 8;
                break;
            case "September":
                thangKieuInt = 9;
                break;
            case "October":
                thangKieuInt = 10;
                break;
            case "November":
                thangKieuInt = 11;
                break;
            case "December":
                thangKieuInt = 12;
                break;
        }
        return thangKieuInt;
    }

    public void xemLich(View view){
        Intent intent =new Intent(TrangChu.this,MainActivity.class);
        startActivity(intent);

    }
}
