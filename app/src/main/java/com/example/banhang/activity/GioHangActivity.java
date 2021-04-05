package com.example.banhang.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.banhang.R;
import com.example.banhang.adapter.GioHangAdapter;
import com.example.banhang.model.GioHang;

import java.text.DecimalFormat;

public class GioHangActivity extends AppCompatActivity {
    ListView lvgiohang;
    TextView tvthongbao;
    static TextView tvtongtien;
    Button btnthanhtoan, btntieptucmua;
    Toolbar toolbargiohang;
    GioHangAdapter giohangAdapter;
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_giohang);
        Anhxa();
        ActionToolBar();
        CheckData();
        EventUtil();
        CatchOnItemLongClick();
    }

    private void CatchOnItemLongClick() {
        lvgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder= new AlertDialog.Builder(GioHangActivity.this);
                builder.setMessage("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn có muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.mangGioHang.size()<=0){
                            tvthongbao.setVisibility(View.VISIBLE);

                        }
                        else{
                            MainActivity.mangGioHang.remove(which);
                            giohangAdapter.notifyDataSetChanged();
                            EventUtil();
                            if(MainActivity.mangGioHang.size()<=0){
                                tvthongbao.setVisibility(View.VISIBLE);
                            }
                            else{
                                tvthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EventUtil();
                            }
                        }

                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        giohangAdapter.notifyDataSetChanged();
                        EventUtil();
                    }
                });
                builder.show();

                return true;
            }
        });


    }

    public  static   void EventUtil() {
        long tongtien=0;
        for(int i=0;i<MainActivity.mangGioHang.size();i++){
            tongtien +=MainActivity.mangGioHang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat= new DecimalFormat("###,###,###");
        tvtongtien.setText(decimalFormat.format(tongtien)+ " Đ");
    }

    private void CheckData() {
        if(MainActivity.mangGioHang.size()<=0){
            giohangAdapter.notifyDataSetChanged();
            tvthongbao.setVisibility(View.VISIBLE);
            lvgiohang.setVisibility(View.INVISIBLE);
        }
        else{
            giohangAdapter.notifyDataSetChanged();
            tvthongbao.setVisibility(View.INVISIBLE);
            lvgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void Anhxa() {
        lvgiohang= (ListView) findViewById(R.id.listviewgiohang);
        tvthongbao=(TextView) findViewById(R.id.txtthongbao);
        tvtongtien=(TextView) findViewById(R.id.txttongtien);
        btnthanhtoan=(Button)findViewById(R.id.btnthanhtoangiohang);
        btntieptucmua=(Button)findViewById(R.id.btntieptucmuahang);
        toolbargiohang=(Toolbar) findViewById(R.id.toobargiohang);
        giohangAdapter= new GioHangAdapter(GioHangActivity.this,MainActivity.mangGioHang);
        lvgiohang.setAdapter(giohangAdapter);

    }
}
