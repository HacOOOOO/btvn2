package com.example.lichamcuoikyjava;

import android.app.Activity;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BoxGhiChu extends Activity {

    private TSXDS tsxds;
    private EditText themGhiChu;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.box_ghi_chu);
        listView = (ListView) findViewById(R.id.danhsach);
        themGhiChu = findViewById(R.id.ghichu);
        tsxds = new TSXDS(this);
        tsxds.open();
        List<DanhSachGC> values = tsxds.danhSach();
        ArrayAdapter<DanhSachGC> adapter = new ArrayAdapter<DanhSachGC>(this, android.R.layout.simple_list_item_1,values);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] ghichu = new String[]{themGhiChu.getText().toString()};
                themGhiChu.setText(ghichu[0]+String.valueOf(position));
            }
        });
    }

    public void themvaodanhsach(View view){
        @SuppressWarnings("unchecked")
        ArrayAdapter<DanhSachGC> adapter = (ArrayAdapter<DanhSachGC>) listView.getAdapter();
        DanhSachGC danhSachGC =new DanhSachGC();
        MainActivity mainActivity = new MainActivity();
        switch (view.getId()){
            case R.id.btn_ghi_chu:
                String[] ghichu = new String[]{themGhiChu.getText().toString()};

                danhSachGC = tsxds.insert(ghichu[0]);

                adapter.add(danhSachGC);
                themGhiChu.setText(null);
                break;

            case R.id.btn_xoa:
                if (listView.getAdapter().getCount() >0){
                    String[] chonSo = new String[]{themGhiChu.getText().toString()};
                    danhSachGC = (DanhSachGC) listView.getAdapter().getItem(Integer.parseInt(chonSo[0]));
                    tsxds.delete(danhSachGC);
                    adapter.remove(danhSachGC);
                    themGhiChu.setText(null);
                }
                break;
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onResume(){
        tsxds.open();
        super.onResume();
    }

    @Override
    public void onPause(){
        tsxds.close();
        super.onPause();
    }
}
