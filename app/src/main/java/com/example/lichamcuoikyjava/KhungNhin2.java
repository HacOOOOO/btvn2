package com.example.lichamcuoikyjava;

import android.view.View;
import android.widget.TextView;
import com.example.lichamcuoikyjava.licham;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;

public class KhungNhin2 extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView ngayCuaThang;
    private final ChuyenDoiLich2.OnItemListener onItemListener;
    public KhungNhin2(@NonNull View itemView, ChuyenDoiLich2.OnItemListener onItemListener) {
        super(itemView);

        ngayCuaThang = itemView.findViewById(R.id.oNgay2);
        this.onItemListener = (ChuyenDoiLich2.OnItemListener) onItemListener;
        itemView.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) ngayCuaThang.getText());
    }
}
