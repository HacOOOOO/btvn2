package com.example.lichamcuoikyjava;

import android.view.View;
import android.widget.TextView;
import com.example.lichamcuoikyjava.licham;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;

public class KhungNhin extends RecyclerView.ViewHolder implements View.OnClickListener{
    public final TextView ngayCuaThang;
    private final ChuyenDoiLich.OnItemListener onItemListener;
    public KhungNhin(@NonNull View itemView, ChuyenDoiLich.OnItemListener onItemListener) {
        super(itemView);

        ngayCuaThang = itemView.findViewById(R.id.oNgay);
        this.onItemListener = onItemListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        onItemListener.onItemClick(getAdapterPosition(), (String) ngayCuaThang.getText());
    }
}
