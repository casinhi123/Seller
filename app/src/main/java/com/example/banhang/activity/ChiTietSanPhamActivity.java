package com.example.banhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.banhang.R;
import com.example.banhang.model.GioHang;
import com.example.banhang.model.SanPham;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class ChiTietSanPhamActivity extends AppCompatActivity {

    Toolbar toolbarchitietsanpham;
    ImageView imgchitiet;
    TextView txtTen,txtGia, txtMota;
    Spinner spinner;
    Button btndatmua;
    int id=0;
    String TenChiTiet="";
    int GiaChiTiet=0;
    String HinhAnh="";
    String Mota="";
    int Idsanpham=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitietsanpham);
        Anhxa();
        ActionToolbar();
        GetInfomation();
        CatchEventSpinner();
        EventButton();
    }

    private void EventButton() {
        btndatmua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(MainActivity.mangGioHang.size()>0){
//                    int sl= Integer.parseInt(spinner.getSelectedItem().toString());
//                    boolean exists=false;
//                    for(int i=0;i<MainActivity.mangGioHang.size();i++){
//                        if(MainActivity.mangGioHang.get(i).getIdsp()==id){
//                            MainActivity.mangGioHang.get(i).setSoluong(MainActivity.mangGioHang.get(i).getSoluong()+sl);
//                            if(MainActivity.mangGioHang.get(i).getSoluong()>10){
//                                MainActivity.mangGioHang.get(i).setSoluong(10);
//                            }
//                            MainActivity.mangGioHang.get(i).setGiasp(GiaChiTiet*MainActivity.mangGioHang.get(i).getSoluong());
//                            exists=true;
//                        }
//                    }
//                    if(exists=false){
//                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
//                        long giamoi= soluong*GiaChiTiet;
//                        MainActivity.mangGioHang.add(new GioHang(id,TenChiTiet,giamoi,HinhAnh,soluong));
//                    }
//                }else{
//                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
//                    long giamoi= soluong*GiaChiTiet;
//                    MainActivity.mangGioHang.add(new GioHang(id,TenChiTiet,giamoi,HinhAnh,soluong));
//                }
//                Intent intent = new Intent(getApplicationContext(), GioHangActivity.class);
//                startActivity(intent);
                if(MainActivity.mangGioHang.size()>0){
                    int sl=Integer.parseInt(spinner.getSelectedItem().toString());
                    boolean exits=false;
                    for(int i=0;i<MainActivity.mangGioHang.size();i++){
                        if(MainActivity.mangGioHang.get(i).getIdsp() == id){
                            MainActivity.mangGioHang.get(i).setSoluong(MainActivity.mangGioHang.get(i).getSoluong()+sl);
                            if(MainActivity.mangGioHang.get(i).getSoluong()>10){
                                MainActivity.mangGioHang.get(i).setSoluong(10);
                            }
                            MainActivity.mangGioHang.get(i).setGiasp(GiaChiTiet*MainActivity.mangGioHang.get(i).getSoluong());
                            exits=true;
                        }
                    }
                    if(exits==false){
                        int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                        long giamoi1=soluong*GiaChiTiet;
                        MainActivity.mangGioHang.add(new GioHang(id,TenChiTiet,giamoi1,HinhAnh,soluong));
                    }
                }else{
                    int soluong=Integer.parseInt(spinner.getSelectedItem().toString());
                    long giamoi=soluong*GiaChiTiet;
                    MainActivity.mangGioHang.add(new GioHang(id,TenChiTiet,giamoi,HinhAnh,soluong));
                }
                Intent intent=new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
            }
        });
    }

    private void CatchEventSpinner() {
        Integer[] soluong= new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> arrayAdapter= new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,soluong);
        spinner.setAdapter(arrayAdapter);
    }

    private void GetInfomation() {

        SanPham sanPham= (SanPham) getIntent().getSerializableExtra("thongtinsanpham");
        id=sanPham.getId();
        TenChiTiet=sanPham.getTenCuaSanPham();
        HinhAnh=sanPham.getHinhAnhSanPham();
        GiaChiTiet=sanPham.getGiaSanPham();
        Mota=sanPham.getMoTaSanPham();
        Idsanpham=sanPham.getIdSanPham();
        txtTen.setText(TenChiTiet);
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        txtGia.setText("Giá: "+decimalFormat.format(+GiaChiTiet)+" Đ");
        txtMota.setText(Mota);
        Picasso.get().load(HinhAnh).into(imgchitiet);


    }

    private void ActionToolbar() {
        setSupportActionBar(toolbarchitietsanpham);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarchitietsanpham.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        toolbarchitietsanpham=(Toolbar) findViewById(R.id.toolbarchitietsanpham);
        imgchitiet=(ImageView) findViewById(R.id.imgchitietsanpham);
        txtTen=(TextView) findViewById(R.id.txttenchitietsanpham);
        txtGia=(TextView) findViewById(R.id.txtgiasanham);
        txtMota=(TextView) findViewById(R.id.textviewmotachitietsanpham);
        spinner=(Spinner) findViewById(R.id.spiner);
        btndatmua=(Button) findViewById(R.id.btndathang);
    }
}
