package com.example.banhang.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Checkable;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ViewFlipper;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhang.adapter.LoaiSpAdapter;
import com.example.banhang.adapter.SanPhamAdapter;
import com.example.banhang.model.GioHang;
import com.example.banhang.model.LoaiSp;
import com.example.banhang.model.SanPham;
import com.example.banhang.ultil.CheckConnection;
import com.example.banhang.ultil.Server;
import com.example.banhang.R;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    androidx.appcompat.widget.Toolbar toolbar;
    ViewFlipper viewFlipper;
    RecyclerView recyclerViewmanhinhchinh;
    NavigationView navigationview;
    ListView listViewmanhinhchinh;
    DrawerLayout drawerLayout;
    ArrayList<LoaiSp> mangloaiSp;
    LoaiSpAdapter loaiSpAdapter;
    int id=0;
    String tenloaisp="";
    String hinhanhloaisp="";
    ArrayList<SanPham> mangSanPham;
    SanPhamAdapter sanPhamAdapter;
    public  static  ArrayList<GioHang> mangGioHang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            ActionBar();
            ActionViewFlipper();
            GetDuLieuLoaiSp();
            GetDuLieuSanPhamMoiNhat();
            CatchOnItemListView();
        }
        else  {
            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối!!");
            finish();
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

    private void CatchOnItemListView() {
        listViewmanhinhchinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                        Intent intent= new Intent(MainActivity.this,MainActivity.class);
                        startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 1:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this,DienThoaiActivity.class);
                            //Gửi id đi
                            intent.putExtra("idloaisanpham", mangloaiSp.get(position).getId());
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 2:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this,LaptopActivity.class);
                            //Gửi id đi
                            intent.putExtra("idloaisanpham", mangloaiSp.get(position).getId());
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 3:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this,LienActivity.class);
                            //Gửi id đi
                            intent.putExtra("idloaisanpham", mangloaiSp.get(position).getId());
                            startActivity(intent);
                        }
                       else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    case 4:
                        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
                            Intent intent= new Intent(MainActivity.this,ThongTinActivity.class);
                            //Gửi id đi
                            intent.putExtra("idloaisanpham", mangloaiSp.get(position).getId());
                            startActivity(intent);
                        }
                        else{
                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
                        }
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                    }
                }
        });
    }

    private void GetDuLieuSanPhamMoiNhat() {
        RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.DuongDanSanPhamMoiNhat, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response!=null){
                    int Id=0;
                    String tenSanPham="";
                    Integer giaSanPham=0;
                    String hinhAnhSanPham="";
                    String moTaSanPham="";
                    int idSanPham=0;
                    for (int i=0;i<response.length();i++){
                        try {
                            JSONObject jsonObject= response.getJSONObject(i);
                            Id=jsonObject.getInt("id");
                            tenSanPham=jsonObject.getString("tenSanPham");
                            giaSanPham=jsonObject.getInt("giaSsanPham");
                            hinhAnhSanPham=jsonObject.getString("hinhAnhSanPham");
                            moTaSanPham=jsonObject.getString("moTaSanPham");
                            idSanPham=jsonObject.getInt("idSanPham");
                            mangSanPham.add(new SanPham(Id,tenSanPham,giaSanPham,hinhAnhSanPham,moTaSanPham,idSanPham));
                            sanPhamAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    private void GetDuLieuLoaiSp() {
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest= new JsonArrayRequest(Server.DuongDanLoaiSp, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if(response !=null){
                    for(int i=0; i<response.length();i++){
                        try {
                            JSONObject jsonObject= response.getJSONObject(i);
                            id= jsonObject.getInt("id");
                            tenloaisp=jsonObject.getString("tenloaisanpham");
                            hinhanhloaisp=jsonObject.getString("hinhanhloaisanpham");
                            mangloaiSp.add(new LoaiSp(id,tenloaisp, hinhanhloaisp));
                            loaiSpAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    mangloaiSp.add(3, new LoaiSp(0,"Liên hệ","https://th.bing.com/th/id/OIF.HMwRewspN1wnTxvgA0M2cg?w=258&h=180&c=7&o=5&dpr=1.25&pid=1.7"));
                    mangloaiSp.add(4, new LoaiSp(0,"Thông tin","https://th.bing.com/th/id/OIP.5_tK1_DXF6qNfjVl2fuHuwHaFN?w=234&h=180&c=7&o=5&dpr=1.25&pid=1.7"));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                CheckConnection.ShowToast_Short(getApplicationContext(),error.toString());
            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //View flipper chạy quảng cáo
    private void ActionViewFlipper() {
        //Tạo mảng để chứa đường dẫn đến quảng cáo
        ArrayList<String> mangquangcao= new ArrayList<>();
        mangquangcao.add("https://th.bing.com/th?id=OIF.qNxa2%2b%2fA1t0Pc3%2fUu7cGjw&w=293&h=180&c=7&o=5&dpr=1.25&pid=1.7");
        mangquangcao.add("https://th.bing.com/th/id/OIF.9OB5oa39Af7eIey0xAcQSg?w=145&h=180&c=7&o=5&dpr=1.25&pid=1.7");
        mangquangcao.add("https://th.bing.com/th/id/OIP.jkZYavlBXfD_T9P1Toq7JwHaE4?w=291&h=190&c=7&o=5&dpr=1.25&pid=1.7");
        mangquangcao.add("https://th.bing.com/th/id/OIP.3-xSRvIB0gKZL5S-eSmKRQHaE8?w=245&h=180&c=7&o=5&dpr=1.25&pid=1.7");
        mangquangcao.add("https://th.bing.com/th/id/OIP.ond7acmnfASzmz9L_4NFUwENEs?w=164&h=181&c=7&o=5&dpr=1.25&pid=1.7");
        //Gán ảnh vào imageview, vì ViewFiller cần tham số là 1 ImageView
        for (int i=0;i<mangquangcao.size();i++){
            ImageView imageView= new ImageView(getApplicationContext());
            Picasso.get().load(mangquangcao.get(i)).into(imageView);
            //set kích cỡ cho imageview
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        //Thời gian chạy 1 quảng cáo
        viewFlipper.setFlipInterval(5000);
        viewFlipper.setAutoStart(true);
        //Tạo Animation
        Animation animation_slide_in_right= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_in_right);
        Animation animation_slide_out_right=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.slide_out_right);
        viewFlipper.setInAnimation(animation_slide_in_right);
        viewFlipper.setOutAnimation(animation_slide_out_right);
    }

    private void ActionBar() {
        //set icon cho toolbar
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(android.R.drawable.ic_menu_sort_by_size);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }
    private  void AnhXa(){
        toolbar=(Toolbar) findViewById(R.id.toolbarmanhinhchinh) ;
        viewFlipper=(ViewFlipper)findViewById(R.id.viewfipper);
        recyclerViewmanhinhchinh=(RecyclerView)findViewById(R.id.recyclerview);
        navigationview=(NavigationView) findViewById(R.id.navigationview);
        listViewmanhinhchinh=(ListView)findViewById(R.id.listviewmanhinhchinh);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mangloaiSp= new ArrayList<>();
        loaiSpAdapter= new LoaiSpAdapter(mangloaiSp,getApplicationContext());
        mangloaiSp.add(0, new LoaiSp(0,"Trang Chính","https://th.bing.com/th/id/OIP.YwWPvpWBht9X_agmDgE8qwHaD6?w=309&h=180&c=7&o=5&dpr=1.25&pid=1.7"));
        listViewmanhinhchinh.setAdapter(loaiSpAdapter);
         mangSanPham= new ArrayList<>();
         sanPhamAdapter= new SanPhamAdapter(getApplicationContext(),mangSanPham);
         recyclerViewmanhinhchinh.setHasFixedSize(true);
         recyclerViewmanhinhchinh.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
         recyclerViewmanhinhchinh.setAdapter(sanPhamAdapter);
         if(mangGioHang!=null){
         }
         else{
             mangGioHang= new ArrayList<>();
         }
    }
}