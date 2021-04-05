package com.example.banhang.adapter;

import android.content.ContentProvider;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.banhang.R;
import com.example.banhang.model.LoaiSp;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoaiSpAdapter extends BaseAdapter {
    ArrayList<LoaiSp> arraylistloaisp;
    Context context;
    @Override
    public int getCount() {
        return arraylistloaisp.size();
    }
    @Override
    public Object getItem(int position) {
        return arraylistloaisp.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    public class ViewHolder{
        TextView txtTenLoaiSp;
        ImageView imgLoaiSanPham;
    }
    public LoaiSpAdapter(ArrayList<LoaiSp> arraylistloaisp, Context context) {
        this.arraylistloaisp = arraylistloaisp;
        this.context = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder= null ;
        if(view==null){
            viewHolder= new ViewHolder();
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view= inflater.inflate(R.layout.dong_listview_loaisp,null);
            viewHolder.txtTenLoaiSp=(TextView)view.findViewById(R.id.textViewLoaisp);
            viewHolder.imgLoaiSanPham=(ImageView)view.findViewById(R.id.imageView);
            view.setTag(viewHolder);
        }
        else{
            viewHolder=(ViewHolder) view.getTag();
        }
        LoaiSp loaiSp=(LoaiSp) getItem(position);
        viewHolder.txtTenLoaiSp.setText(loaiSp.getTenloaisp());
        Picasso.get().load(loaiSp.getHinhanhloaisp()).into(viewHolder.imgLoaiSanPham);




        return view;
    }
}
