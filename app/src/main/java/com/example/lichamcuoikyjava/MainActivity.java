package com.example.lichamcuoikyjava;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.app.NotificationManagerCompat;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;




public class MainActivity extends AppCompatActivity implements ChuyenDoiLich.OnItemListener {

    private TextView thangCuaNam;
    private RecyclerView lich;
    private LocalDate chonNgay;
    private LocalDate getChonNgay = LocalDate.now();
    public String ntn;
    public static String chonSelect;
    public static  String Date_Choose;
    public String CHANNEL_ID="channel";
    private NotificationManager notificationManagerCompat;

    //       ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        chonNgay = LocalDate.now();
        chonSelect = dateAm()+thangAm()+namAm();
        setThang();

    }

    private void initWidgets() {
        lich = findViewById(R.id.lich);
        thangCuaNam = findViewById(R.id.thangNam);
    }

    private void setThang() {
        thangCuaNam.setText("Tháng "+thangAmLich(chonNgay) + " Năm "+namAmLich(chonNgay));
        ArrayList<String> ngayTrongThang = ngayTrongThangArray(chonNgay);
        ChuyenDoiLich chuyenDoiLich = new ChuyenDoiLich(ngayTrongThang, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),7);
        lich.setLayoutManager(layoutManager);
        lich.setAdapter(chuyenDoiLich);
    }


    private ArrayList<String> ngayTrongThangArray(LocalDate date) {
        ArrayList<String> ngayTrongThangArray = new ArrayList<>();
        YearMonth yearMonth = YearMonth.from(date);
        int ngayTrongThang = yearMonth.lengthOfMonth();
        LocalDate ngayDauThang = chonNgay.withDayOfMonth(ngayDuongCoNgayAm1(chonNgay));
        int ngayCuaTuan = ngayDauThang.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++){
            if(i <= ngayCuaTuan || i > ngayTrongThang+ngayCuaTuan){
                ngayTrongThangArray.add("");
            }else {
                if(i-ngayCuaTuan==ngayAm(chonNgay) && Integer.parseInt(thangAm())==(Integer.parseInt(thangNamTuNgay(date))-1)){
                    Log.e("aaa",""+Integer.parseInt(thangAm()));
                    ngayTrongThangArray.add("["+String.valueOf(i- ngayCuaTuan)+"]");
                }else {
                    ngayTrongThangArray.add(String.valueOf(i- ngayCuaTuan));
                }




            }
        }


        return ngayTrongThangArray;
    }

    private int ngayAm(LocalDate date) {
        int ngayAm_ = 0;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(date));
        int namTamThoi = Integer.parseInt(namTuNgay(date));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(date));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);
        ngayAm_ = am.ConVertToLunarDay();
        Log.e("ngya",""+ngayAm_);
        return ngayAm_;
    }

    private int ngayDuongCoNgayAm1(LocalDate date) {
        int ngayDuongCoNgayAm1=0;
        YearMonth yearMonth = YearMonth.from(date);
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(chonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(chonNgay));
        int ngayTrongThang = yearMonth.lengthOfMonth();
        LocalDate ngayDauThang = chonNgay.withDayOfMonth(1);
        int ngayCuaTuan = ngayDauThang.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++){
            if(i <= ngayCuaTuan || i > ngayTrongThang+ngayCuaTuan){
            }else {
                int ngay = i - ngayCuaTuan;
                licham am = new licham(ngay,thangTamThoi,namTamThoi,7);
                int ngayAm = am.ConVertToLunarDay();
                if (ngayAm ==1){
                    ngayDuongCoNgayAm1 = ngay;
                }
            }
        }


        return ngayDuongCoNgayAm1;
    }

    public String dateAm(){
        int dateAm = 0;
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(getChonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(getChonNgay));
        int ngayTamThoi = Integer.parseInt(ngayTuNgay(getChonNgay));
        licham am = new licham(ngayTamThoi, thangTamThoi, namTamThoi, 7);

        dateAm = am.ConVertToLunarDay();

        String str = String.valueOf(dateAm);

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

    public String thangAmLich(LocalDate date){
        int thangAm=0;
        YearMonth yearMonth = YearMonth.from(date);
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(chonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(chonNgay));
        int ngayTrongThang = yearMonth.lengthOfMonth();
        LocalDate ngayDauThang = chonNgay.withDayOfMonth(1);
        int ngayCuaTuan = ngayDauThang.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++){
            if(i <= ngayCuaTuan || i > ngayTrongThang+ngayCuaTuan){
            }else {
                int ngay = i - ngayCuaTuan;
                licham am = new licham(ngay,thangTamThoi,namTamThoi,7);
                int ngayAm = am.ConVertToLunarDay();
                if (ngayAm ==1){
                    thangAm = am.ConVertToLunarMonth();
                }
            }
        }
        String str = String.valueOf(thangAm);

        return str;
    }

    public String namAmLich(LocalDate date){
        int namAm=0;
        YearMonth yearMonth = YearMonth.from(date);
        int thangTamThoi = Integer.parseInt(thangNamTuNgay(chonNgay));
        int namTamThoi = Integer.parseInt(namTuNgay(chonNgay));
        int ngayTrongThang = yearMonth.lengthOfMonth();
        LocalDate ngayDauThang = chonNgay.withDayOfMonth(1);
        int ngayCuaTuan = ngayDauThang.getDayOfWeek().getValue();
        for(int i = 1; i <= 42; i++){
            if(i <= ngayCuaTuan || i > ngayTrongThang+ngayCuaTuan){
            }else {
                int ngay = i - ngayCuaTuan;
                licham am = new licham(ngay,thangTamThoi,namTamThoi,7);
                int ngayAm = am.ConVertToLunarDay();
                if (ngayAm ==1){
                    namAm = am.ConVertToLunarYear();
                }
            }
        }
        String str = String.valueOf(namAm);

        return str;
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

    public void thongBao(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,"aaa",NotificationManager.IMPORTANCE_HIGH);
            NotificationManager manager = this.getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Thông báo")
                .setContentText("Có một công việc")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_PROMO)
                .build();
        int notificationId = 1;
        this.notificationManagerCompat.notify(notificationId,notification);
    }

    public void thangSau(View view) {
        chonNgay = chonNgay.plusMonths(1);
        setThang();
    }

    public void thangTruoc(View view) {
        chonNgay = chonNgay.minusMonths(1);
        setThang();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(!dayText.equals("")){
            String message = "Đã chọn ngày "+dayText+" "+thangAmLich(chonNgay);
            Date_Choose=dayText+thangAmLich(chonNgay)+namAmLich(chonNgay);
            Log.e("datechose",Date_Choose);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }


        Intent intent =new Intent(MainActivity.this,BoxGhiChu.class);
        startActivity(intent);
    }

}