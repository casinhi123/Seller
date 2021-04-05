package com.example.banhang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.banhang.R;
import com.example.banhang.activity.ChiTietSanPhamActivity;
import com.example.banhang.model.SanPham;
import com.example.banhang.ultil.CheckConnection;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ItemHolder> {
    Context context;
    ArrayList<SanPham> arraySanPham;
    public SanPhamAdapter(Context context, ArrayList<SanPham> arraySanPham) {
        this.context = context;
        this.arraySanPham = arraySanPham;
    }

    @Override
    public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.dong_sanphammoinhat,null);
        ItemHolder itemHolder= new ItemHolder(v);

        return itemHolder;
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        SanPham sanpham= arraySanPham.get(position);
        holder.txtTenSanPham.setText(sanpham.getTenCuaSanPham());
        String pattern="###,###,###";
        DecimalFormat decimalFormat= new DecimalFormat(pattern);
        holder.txtGiaSanPham.setText("Giá: "+decimalFormat.format(sanpham.getGiaSanPham())+" Đ");
        //holder.txtGiaSanPham.setText("Giá: "+sanpham.getGiaSanPham()+" Đ");
        Picasso.get().load(sanpham.getHinhAnhSanPham()).into(holder.imgHinhSanPham);
    }

    @Override
    public int getItemCount() {
        return arraySanPham.size();
    }

    public  class ItemHolder extends RecyclerView.ViewHolder{
        public ImageView imgHinhSanPham;
        public TextView txtTenSanPham,txtGiaSanPham;
        public ItemHolder(View itemView) {
            super(itemView);
            imgHinhSanPham=(ImageView) itemView.findViewById(R.id.imageviewsanpham);
            txtGiaSanPham=(TextView)itemView.findViewById(R.id.textviewgiasanpham);
            txtTenSanPham=(TextView)itemView.findViewById(R.id.textviewtensanpham);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, ChiTietSanPhamActivity.class);
                    intent.putExtra("thongtinsanpham",arraySanPham.get(getPosition()));
                    intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
                    CheckConnection.ShowToast_Short(context,arraySanPham.get(getPosition()).getTenCuaSanPham());
                    context.startActivity(intent);
                }
            });
        }
    }
}
