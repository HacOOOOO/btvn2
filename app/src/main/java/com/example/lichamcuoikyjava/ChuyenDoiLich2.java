package com.example.lichamcuoikyjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;

public class ChuyenDoiLich2 extends RecyclerView.Adapter<KhungNhin2> {
    private final ArrayList<String> ngayCuaThang;
    private final OnItemListener onItemListener;

    public ChuyenDoiLich2(ArrayList<String> ngayCuaThang, OnItemListener onItemListener) {
        this.ngayCuaThang = ngayCuaThang;
        this.onItemListener = onItemListener;
    }

    @NonNull
    @Override
    public KhungNhin2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.lich_o_2, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight()*0.166666666);
        return new KhungNhin2(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull KhungNhin2 holder, int position) {
        holder.ngayCuaThang.setText(ngayCuaThang.get(position));
    }

    @Override
    public int getItemCount() {
        return ngayCuaThang.size();
    }

    public interface OnItemListener{
        void onItemClick(int position, String dayText);
    }
}
