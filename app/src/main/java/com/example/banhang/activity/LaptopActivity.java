//package com.example.banhang.activity;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.appcompat.widget.Toolbar;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.widget.ListView;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//import com.example.banhang.R;
//import com.example.banhang.adapter.DienThoaiAdapter;
//import com.example.banhang.model.SanPham;
//import com.example.banhang.ultil.CheckConnection;
//import com.example.banhang.ultil.Server;
//
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Map;
//
//public class DienThoaiActivity extends AppCompatActivity {
//    androidx.appcompat.widget.Toolbar toolBardt;
//    ListView listviewDienThoai;
//    DienThoaiAdapter adapter;
//    ArrayList<SanPham> arrayDienThoai;
//    int idDienThoai=0;
//    int page=1;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_dien_thoai);
//        AnhXa();
//        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
//            GetIdLoaiSanPham();
//            AcionToolBar();
//            GetDaTa(page);
//        }
//        else{
//            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại Intenet");
//        }
//
//    }
//
////    private void GetDaTa(int page) {
////        //Dùng Rq để đưa reques lên server
////        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
////        String duongdan= Server.DuongDanDienThoai+ String.valueOf(page);
////        //Đọc về tất cả dữ liệu trước
////        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
////            @Override
////            public void onResponse(String response) {
////                int Id = 0;
////                String tenDienthoai="";
////                int gia=0;
////                String hinhAnh="";
////                String mota= "";
////                int idSpdt=0;
////                if(response==null){
////                    try {
////                        JSONArray jsonArray= new JSONArray(response);
////                        for (int i=0;i<jsonArray.length();i++){
////                            JSONObject jsonObject= jsonArray.getJSONObject(i);
////                            Id=jsonObject.getInt("id");
////                            tenDienthoai=jsonObject.getString("tenSanPham");
////                            gia=jsonObject.getInt("giaSsanPham");
////                            hinhAnh=jsonObject.getString("hinhAnhSanPham");
////                            mota=jsonObject.getString("moTaSanPham");
////                            idSpdt=jsonObject.getInt("idSanPham");
////                            arrayDienThoai.add(new SanPham(Id,tenDienthoai,gia,hinhAnh,mota,idSpdt));
////                            adapter.notifyDataSetChanged();
////                        }
////
//////                        Id=1;
//////                        tenDienthoai="IPhone";
//////                        gia=1700000;
//////                        hinhAnh="";
//////                        mota="Day la iphone";
//////                        idSpdt=1;
//////                        arrayDienThoai.add(new SanPham(Id,tenDienthoai,gia,hinhAnh,mota,idSpdt));
//////                        adapter.notifyDataSetChanged();
////
////                    } catch (JSONException e) {
////                        e.printStackTrace();
////                    }
////                }
////            }
////        }, new Response.ErrorListener() {
////            @Override
////            public void onErrorResponse(VolleyError error) {
////
////            }
////        }){
////            @Override
////            protected Map<String, String> getParams() throws AuthFailureError {
////                HashMap<String,String> params= new HashMap<String,String>();
////                params.put("idsanpham",String.valueOf(idDienThoai));
////                return params;
////            }
////        };
////        requestQueue.add(stringRequest);
////    }
//
//
//    private void GetDaTa(int page) {
//        //Dùng Rq để đưa reques lên server
//        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//        String duongdan= Server.DuongDanDienThoai+ String.valueOf(page);
//        //Đọc về tất cả dữ liệu trước
//        StringRequest stringRequest= new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                int Id = 0;
//                String tenDienthoai="";
//                int gia=0;
//                String hinhAnh="";
//                String mota= "";
//                int idSpdt=0;
//                if(response==null){
//                    try {
//                        JSONArray jsonArray= new JSONArray(response);
//                        for (int i=0;i<jsonArray.length();i++){
//                            JSONObject jsonObject= jsonArray.getJSONObject(i);
//                            Id=jsonObject.getInt("id");
//                            tenDienthoai=jsonObject.getString("tenSanPham");
//                            gia=jsonObject.getInt("giaSsanPham");
//                            hinhAnh=jsonObject.getString("hinhAnhSanPham");
//                            mota=jsonObject.getString("moTaSanPham");
//                            idSpdt=jsonObject.getInt("idSanPham");
//                            arrayDienThoai.add(new SanPham(Id,tenDienthoai,gia,hinhAnh,mota,idSpdt));
//                            adapter.notifyDataSetChanged();
//                        }
//
////                        Id=1;
////                        tenDienthoai="IPhone";
////                        gia=1700000;
////                        hinhAnh="";
////                        mota="Day la iphone";
////                        idSpdt=1;
////                        arrayDienThoai.add(new SanPham(Id,tenDienthoai,gia,hinhAnh,mota,idSpdt));
////                        adapter.notifyDataSetChanged();
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        }){
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                HashMap<String,String> params= new HashMap<String,String>();
//                params.put("idsanpham",String.valueOf(idDienThoai));
//                return params;
//            }
//        };
//        requestQueue.add(stringRequest);
//    }
//
//
//
//    private void AcionToolBar() {
//        setSupportActionBar(toolBardt);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        toolBardt.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
//    }
//
//    private void GetIdLoaiSanPham() {
//        idDienThoai= getIntent().getIntExtra("idloaisanpham",-1);
//        Log.d("giatriloaisanpham",  idDienThoai+"");
//    }
//
//    private void AnhXa() {
//        toolBardt= (Toolbar)findViewById(R.id.toolbarDienThoai);
//        listviewDienThoai=(ListView)findViewById(R.id.listviewdienthoai);
//        arrayDienThoai= new ArrayList<>();
//        adapter= new DienThoaiAdapter(getApplicationContext(),arrayDienThoai);
//        listviewDienThoai.setAdapter(adapter);
//    }
//}

package com.example.banhang.activity;


import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;
import androidx.appcompat.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhang.R;
import com.example.banhang.adapter.DienThoaiAdapter;
import com.example.banhang.adapter.LaptopAdapter;
import com.example.banhang.model.SanPham;
import com.example.banhang.ultil.CheckConnection;
import com.example.banhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LaptopActivity extends AppCompatActivity {
    private Toolbar toolbarlt;
    private ListView listView;
    private LaptopAdapter laptopAdapter;
    private ArrayList<SanPham> manglaptop;
    private int idlt=1;
    private int page=1;
    private boolean isLoading=false;
    private boolean limitdata=false;
    private View footerview;
    private mHandler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop);
        anhxa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())) {
            GetIdloaisp();
            Actiontoolbar();
            GetData(page);
            LoadmoreData();
        }else {
            CheckConnection.ShowToast_Short(getApplicationContext(),"lỗi");
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menugiohang:
                Intent intent= new Intent(getApplicationContext(),GioHangActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    private void LoadmoreData() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(getApplicationContext(),ChiTietSanPhamActivity.class);
                intent.putExtra("thongtinsanpham",manglaptop.get(position));
                startActivity(intent);
            }
        });
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if(firstVisibleItem +visibleItemCount == totalItemCount && totalItemCount != 0 && isLoading==false && limitdata ==false){
                    isLoading=true;
                    ThreadData threadData=new ThreadData();
                    threadData.start();
                }
            }
        });
    }
    private void GetData(int page) {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        String duongdan=Server.DuongDanDienThoai+String.valueOf(page);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, duongdan, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null && response.length() != 2){
                    listView.removeFooterView(footerview); //có dữ liệu thì sẽ mất biểu tưởng load
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for(int i=0;i<jsonArray.length();i++){
                            JSONObject jsonObject=jsonArray.getJSONObject(i);
                            manglaptop.add(new SanPham(jsonObject.getInt("id")
                                    ,jsonObject.getString("tenSanPham")
                                    ,jsonObject.getInt("giaSsanPham")
                                    ,jsonObject.getString("hinhAnhSanPham")
                                    ,jsonObject.getString("moTaSanPham")
                                    ,jsonObject.getInt("idSanPham")));
                            laptopAdapter.notifyDataSetChanged();
                        }
                        laptopAdapter=new LaptopAdapter(getApplicationContext(),manglaptop);
                        listView.setAdapter(laptopAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    limitdata=true;
                    listView.removeFooterView(footerview);
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Đã hết dữ liệu");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> param=new HashMap<String, String>();
                param.put("idsanpham",String.valueOf(idlt));
                return param;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void Actiontoolbar() {
        setSupportActionBar(toolbarlt);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarlt.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIdloaisp() {
        idlt=getIntent().getIntExtra("idloaisanpham",-1);
        Log.d("giatriloaisanpham",idlt+"");
    }

    public void anhxa(){
        toolbarlt=(Toolbar)findViewById(R.id.toolbarlaptop);
        listView=(ListView)findViewById(R.id.listviewlaptop);
        manglaptop=new ArrayList<>();
        laptopAdapter=new LaptopAdapter(getApplicationContext(),manglaptop);
        listView.setAdapter(laptopAdapter);
        LayoutInflater inflater= (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        footerview =inflater.inflate(R.layout.progressbar,null);
        mHandler=new mHandler();
    }
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu,menu);
//        SearchView searchView= (SearchView) menu.findItem(R.id.menusearch).getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                dienThoaiAdapter.filter(s.trim());
//                return false;
//            }
//        });
//        return true;
//    }

    //    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()){
//            case R.id.menugiohang :
//                Intent intent=new Intent(getApplicationContext(),GioHang.class);
//                startActivity(intent);
//                break;
//            case R.id.menusearchdollar :
//                DialogTheoGia();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
    public class mHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0:
                    listView.addFooterView(footerview);
                    break;
                case 1:
                    GetData(++page);
                    isLoading=false;
                    break;
            }
            super.handleMessage(msg);
        }
    }
    public class ThreadData extends Thread{
        public void run(){
            mHandler.sendEmptyMessage(0);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message message=mHandler.obtainMessage(1);
            mHandler.sendMessage(message);
            super.run();
        }
    }
//    private void DialogTheoGia(){
//        final Dialog dialog=new Dialog(this);
//        dialog.setContentView(R.layout.tim_kiem_theo_gia);
//        RadioGroup group=(RadioGroup)dialog.findViewById(R.id.radiogroud);
//        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId){
//                    case R.id.radiobuttonduoi2tr:
//                        dienThoaiAdapter.theogia(1);
//                        dialog.dismiss();
//                        break;
//                    case R.id.radiobuttonduoi2tr5tr:
//                        dienThoaiAdapter.theogia(2);
//                        dialog.dismiss();
//                        break;
//                    case R.id.radiobuttonduoi5tr10tr:
//                        dienThoaiAdapter.theogia(3);
//                        dialog.dismiss();
//                        break;
//                    case R.id.radiobuttontren10tr:
//                        dienThoaiAdapter.theogia(4);
//                        dialog.dismiss();
//                        break;
//                    case R.id.radiobuttontatca:
//                        dienThoaiAdapter.theogia(5);
//                        dialog.dismiss();
//                        break;
//                }
//            }
//        });
//        dialog.show();
//
//    }

}
