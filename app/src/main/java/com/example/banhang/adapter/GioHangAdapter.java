package com.example.banhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banhang.R;
import com.example.banhang.activity.GioHangActivity;
import com.example.banhang.activity.MainActivity;
import com.example.banhang.model.GioHang;
import com.example.banhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GioHangAdapter extends BaseAdapter {
    Context context;
    ArrayList<GioHang> manggiohang;

    public GioHangAdapter(Context context, ArrayList<GioHang> manggiohang) {
        this.context = context;
        this.manggiohang = manggiohang;
    }

    @Override
    public int getCount() {
        return manggiohang.size();
    }

    @Override
    public Object getItem(int position) {
        return manggiohang.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class ViewHolder{
        public TextView txttengiohang, txtgiagiohang;
        public ImageView imggiohang;
        public Button btnminus,btnplus,btnvalue;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.dong_gio_hang,null);
            viewHolder.txttengiohang=(TextView) convertView.findViewById(R.id.textviewtengiohang);
            viewHolder.txtgiagiohang=(TextView)convertView.findViewById(R.id.textviewgiamonhang);
            viewHolder.imggiohang=(ImageView)convertView.findViewById(R.id.imggiohang);
            viewHolder.btnminus=(Button)convertView.findViewById(R.id.buttonminus);
            viewHolder.btnplus=(Button)convertView.findViewById(R.id.buttonplus);
            viewHolder.btnvalue=(Button)convertView.findViewById(R.id.buttonvalue);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        GioHang gioHang= (GioHang) getItem(position);
        viewHolder.txttengiohang.setText(gioHang.getTensp());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText("Giá: "+decimalFormat.format(gioHang.getGiasp())+" Đ");
        Picasso.get().load(gioHang.getHinhanhsp()).into(viewHolder.imggiohang);
        viewHolder.btnvalue.setText(String.valueOf(gioHang.getSoluong()));

        int sl= Integer.valueOf(viewHolder.btnvalue.getText().toString());
        if(sl>=10){
            viewHolder.btnplus.setVisibility(View.INVISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);

        }
        else if(sl<=1){
            viewHolder.btnminus.setVisibility(View.INVISIBLE);
        }
        else {
            viewHolder.btnplus.setVisibility(View.VISIBLE);
            viewHolder.btnminus.setVisibility(View.VISIBLE);
        }
        ViewHolder finalViewHolder = viewHolder;
        viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat= Integer.parseInt(finalViewHolder.btnvalue.getText().toString())+1;
                int slhientai= MainActivity.mangGioHang.get(position).getSoluong();
                long giahientai= MainActivity.mangGioHang.get(position).getGiasp();
                MainActivity.mangGioHang.get(position).setSoluong(slmoinhat);
                long giamoinhat= (giahientai*slmoinhat)/slhientai;
                MainActivity.mangGioHang.get(position).getGiasp();
                DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText("Giá: "+decimalFormat.format(giamoinhat)+" Đ");
                GioHangActivity.EventUtil();

                if(slmoinhat>9){
                    finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int slmoinhat= Integer.parseInt(finalViewHolder.btnvalue.getText().toString())-1;
                int slhientai= MainActivity.mangGioHang.get(position).getSoluong();
                long giahientai= MainActivity.mangGioHang.get(position).getGiasp();
                MainActivity.mangGioHang.get(position).setSoluong(slmoinhat);
                long giamoinhat= (giahientai*slmoinhat)/slhientai;
                MainActivity.mangGioHang.get(position).getGiasp();
                DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText("Giá: "+decimalFormat.format(giamoinhat)+" Đ");
                GioHangActivity.EventUtil();

                if(slmoinhat<2){
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                    finalViewHolder.btnvalue.setText(String.valueOf(slmoinhat));
                }
            }
        });

        return convertView;
    }
}
