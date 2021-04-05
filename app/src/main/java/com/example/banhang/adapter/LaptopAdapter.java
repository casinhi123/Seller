package com.example.banhang.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banhang.R;
import com.example.banhang.model.SanPham;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class LaptopAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayLaptop;
    @Override
    public int getCount() {
        return arrayLaptop.size();
    }

    public LaptopAdapter(Context context, ArrayList<SanPham> arrayLaptop) {
        this.context = context;
        this.arrayLaptop = arrayLaptop;
    }

    @Override
    public Object getItem(int position) {
        return arrayLaptop.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class ViewHolder{
        public TextView txtTenLaptop,txtGiaLaptop,txtMotaLaptop;
        public ImageView imgDienThoai;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LaptopAdapter.ViewHolder viewHolder= null;
        if(convertView==null){
            viewHolder = new LaptopAdapter.ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_laptop,null);
            viewHolder.txtTenLaptop=(TextView) convertView.findViewById(R.id.textviewTenLaptop);
            viewHolder.txtGiaLaptop=(TextView) convertView.findViewById(R.id.textviewGia);
            viewHolder.txtMotaLaptop=(TextView) convertView.findViewById(R.id.textviewMoTaLaptop);
            viewHolder.imgDienThoai=(ImageView)convertView.findViewById(R.id.imageViewLaptop);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (LaptopAdapter.ViewHolder)convertView.getTag();
        }
        SanPham  sanpham=(SanPham) getItem(position);
        viewHolder.txtTenLaptop.setText(sanpham.getTenCuaSanPham());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtGiaLaptop.setText("Giá: "+decimalFormat.format(sanpham.getGiaSanPham())+ " Đ");
        viewHolder.txtMotaLaptop.setMaxLines(2);
        viewHolder.txtMotaLaptop.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotaLaptop.setText(sanpham.getMoTaSanPham());
        Picasso.get().load(sanpham.getHinhAnhSanPham()).placeholder(R.drawable.iconnew).error(R.drawable.iconnew)
                .into(viewHolder.imgDienThoai);
        return convertView;
    }
}
