package com.example.banhang.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.banhang.R;
import com.example.banhang.ultil.CheckConnection;
import com.example.banhang.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ThongTinKhachHang  extends AppCompatActivity {
    EditText edtTenKhachHang,edtSDT,edtEmail;
    Button btnXacNhan, btnTroVe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_khach_hang);
        AnhXa();
    }

    private void AnhXa() {
        edtTenKhachHang=(EditText)findViewById(R.id.edittexttenkhachhang);
        edtSDT=(EditText)findViewById(R.id.editextsodienthoaikhachhang);
        edtEmail=(EditText)findViewById(R.id.edittextemail);
        btnXacNhan=(Button)findViewById(R.id.btnxacnhan);
        btnTroVe=(Button)findViewById(R.id.btntrove);

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if(CheckConnection.haveNetworkConnection(getApplicationContext())){
            EventButton();
        }else{
                CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn hãy kiểm tra lại kết nối");
        }
    }

    private void EventButton() {
//        btnXacNhan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                final String ten= edtTenKhachHang.getText().toString().trim();
//                final String sdt= edtSDT.getText().toString().trim();
//                final String email= edtEmail.getText().toString().trim();
//                if(ten.length()>0&& sdt.length()>0&& email.length()>0){
//                  final  RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
//                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.DuongDanDonHang, new Response.Listener<String>() {
//                        @Override
//                        public void onResponse(final String madonhang) {
//                            Log.d("madonhang",madonhang);
//                            if(Integer.parseInt(madonhang)>0){
//                                RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
//                                StringRequest request= new StringRequest(Request.Method.POST, Server.DuongDanChiTietDonHang, new Response.Listener<String>() {
//                                    @Override
//                                    public void onResponse(String response) {
//                                        if(response.equals("1")){
//                                            MainActivity.mangGioHang.clear();
//                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm dữ liệu giỏ hàng thành công");
//                                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
//                                            startActivity(intent);
//                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua hàng");
//                                        }
//                                        else{
//                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Dữ liệu giỏ hàng của bạn đã bị lỗi");
//                                        }
//                                    }
//                                }, new Response.ErrorListener() {
//                                    @Override
//                                    public void onErrorResponse(VolleyError error) {
//
//                                    }
//                                }){
//                                    @Override
//                                    protected Map<String, String> getParams() throws AuthFailureError {
//                                        JSONArray jsonArray= new JSONArray();
//                                        for(int i=0; i<MainActivity.mangGioHang.size();i++){
//                                            JSONObject jsonObject= new JSONObject();
//                                            try {
//                                                jsonObject.put("MaDonHang",madonhang);
//                                                jsonObject.put("MaSanPham",MainActivity.mangGioHang.get(i).idsp);
//                                                jsonObject.put("TenSanPham",MainActivity.mangGioHang.get(i).tensp);
//                                                jsonObject.put("GiaSanPham",MainActivity.mangGioHang.get(i).giasp);
//                                                jsonObject.put("SoLuongSanPham",MainActivity.mangGioHang.get(i).soluong);
//                                            } catch (JSONException e) {
//
//                                                e.printStackTrace();
//                                            }
//                                            jsonArray.put(jsonObject);
//                                        }
//                                        HashMap<String,String> hashMap= new HashMap<String,String>();
//                                        hashMap.put("json",jsonArray.toString());
//                                        return hashMap;
//                                    }
//                                };
//                                queue.add(request);
//                            }
//                        }
//                    }, new Response.ErrorListener() {
//                        @Override
//                        public void onErrorResponse(VolleyError error) {
//                        }
//                    })
//                    {
//                        @Override
//                        protected Map<String, String> getParams() throws AuthFailureError {
//                            HashMap<String,String> hashMap= new HashMap<String,String>();
//                            hashMap.put("tenKhachHang",ten);
//                            hashMap.put("SDT",sdt);
//                            hashMap.put("Email",email);
//                            return hashMap;
//                        }
//                    };
//                    requestQueue.add(stringRequest);
//                }else{
//                    CheckConnection.ShowToast_Short(getApplicationContext(),"Kiểm tra lại dữ liệu");
//                }
//
//            }
//        });

        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String tenkh=edtTenKhachHang.getText().toString();
                final String email=edtEmail.getText().toString();
                final String sdt=edtSDT.getText().toString();
                if(email.length()>0 && sdt.length()>0 && tenkh.length()>0){
                    final RequestQueue requestQueue=Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest= new StringRequest(Request.Method.POST, Server.DuongDanDonHang, new Response.Listener<String>() {
                        @Override
                        public void onResponse(final String madonhang) {
                            Log.d("mahoadon",madonhang);
                            if(Integer.parseInt(madonhang)>0){
                                RequestQueue queue=Volley.newRequestQueue(getApplicationContext());
                                StringRequest  stringRequest1=new StringRequest(Request.Method.POST, Server.DuongDanChiTietDonHang, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        Log.d("machitiec",response);
                                        if(response.equals("1")){
                                            MainActivity.mangGioHang.clear();
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn đã thêm giỏ hàng thành công");
                                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"Mời bạn tiếp tục mua sản phẩm");
                                        }else{
                                            CheckConnection.ShowToast_Short(getApplicationContext(),"dữ liệu của bạn đã bị lỗi");
                                        }
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {

                                    }
                                }){
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {
                                        JSONArray jsonArray=new JSONArray();
                                        for(int i=0;i<MainActivity.mangGioHang.size();i++){
                                            JSONObject object=new JSONObject();
                                            try {
                                                object.put("MaDonHang",madonhang);
                                                object.put("MaSanPham",MainActivity.mangGioHang.get(i).getIdsp());
                                                object.put("TenSanPham",MainActivity.mangGioHang.get(i).getTensp());
                                                object.put("GiaSanPham",MainActivity.mangGioHang.get(i).getGiasp());
                                                object.put("SoLuongSanPham",MainActivity.mangGioHang.get(i).getSoluong());
                                            } catch (JSONException e) {
                                                e.printStackTrace();
                                            }
                                            jsonArray.put(object);

                                        }
                                        HashMap<String,String> hashMap=new HashMap<String, String>();
                                        hashMap.put("json",jsonArray.toString());
                                        return hashMap;
                                    }
                                };
                                queue.add(stringRequest1);
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    }){
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            HashMap<String,String> hashMap=new HashMap<String,String>();
                            hashMap.put("tenKhachHang",tenkh);
                            hashMap.put("SDT",sdt);
                            hashMap.put("Email",email);
                            return hashMap;
                        }
                    };
                    requestQueue.add(stringRequest);
                }else{
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Bạn vui lòng điền đầy đủ thông tin");
                }
            }
        });
    }
}
