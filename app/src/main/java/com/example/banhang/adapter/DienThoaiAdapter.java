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

public class DienThoaiAdapter extends BaseAdapter {
    Context context;
    ArrayList<SanPham> arrayDienThoai;
    @Override
    public int getCount() {
        return arrayDienThoai.size();
    }

    public DienThoaiAdapter(Context context, ArrayList<SanPham> arrayDienThoai) {
        this.context = context;
        this.arrayDienThoai = arrayDienThoai;
    }

    @Override
    public Object getItem(int position) {
        return arrayDienThoai.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    public  class ViewHolder{
        public TextView txtTenDienThoai,txtGiaDienThoai,txtMotadienthoai;
        public ImageView imgDienThoai;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder= null;
        if(convertView==null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView= inflater.inflate(R.layout.dong_dien_thoai,null);
            viewHolder.txtTenDienThoai=(TextView) convertView.findViewById(R.id.textviewDienThoai);
            viewHolder.txtGiaDienThoai=(TextView) convertView.findViewById(R.id.textviewGia);
            viewHolder.txtMotadienthoai=(TextView) convertView.findViewById(R.id.textviewMoTaDienThoai);
            viewHolder.imgDienThoai=(ImageView)convertView.findViewById(R.id.imageViewDienThoai);
            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (ViewHolder)convertView.getTag();
        }
        SanPham  sanpham=(SanPham) getItem(position);
        viewHolder.txtTenDienThoai.setText(sanpham.getTenCuaSanPham());
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        viewHolder.txtGiaDienThoai.setText("Giá: "+decimalFormat.format(sanpham.getGiaSanPham())+ " Đ");
        viewHolder.txtMotadienthoai.setMaxLines(2);
        viewHolder.txtMotadienthoai.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtMotadienthoai.setText(sanpham.getMoTaSanPham());
        Picasso.get().load(sanpham.getHinhAnhSanPham()).placeholder(R.drawable.iconnew).error(R.drawable.iconnew)
                .into(viewHolder.imgDienThoai);
        return convertView;
    }
}
