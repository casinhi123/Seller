package com.example.banhang.model;

import java.io.Serializable;
import java.io.SerializablePermission;

public class SanPham implements Serializable {
    private  int id;

    public SanPham(int id, String tenCuaSanPham, int giaSanPham, String hinhAnhSanPham, String moTaSanPham, int idSanPham) {
        this.id = id;
        this.tenCuaSanPham = tenCuaSanPham;
        this.giaSanPham = giaSanPham;
        this.hinhAnhSanPham = hinhAnhSanPham;
        this.moTaSanPham = moTaSanPham;
        this.idSanPham = idSanPham;
    }

    private  String tenCuaSanPham;
    private  int giaSanPham;
    private  String hinhAnhSanPham;
    private  String moTaSanPham;
    private  int idSanPham;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenCuaSanPham() {
        return tenCuaSanPham;
    }

    public void setTenCuaSanPham(String tenCuaSanPham) {
        this.tenCuaSanPham = tenCuaSanPham;
    }

    public int getGiaSanPham() {
        return giaSanPham;
    }

    public void setGiaSanPham(int giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getHinhAnhSanPham() {
        return hinhAnhSanPham;
    }

    public void setHinhAnhSanPham(String hinhAnhSanPham) {
        this.hinhAnhSanPham = hinhAnhSanPham;
    }

    public String getMoTaSanPham() {
        return moTaSanPham;
    }

    public void setMoTaSanPham(String moTaSanPham) {
        this.moTaSanPham = moTaSanPham;
    }

    public int getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(int idSanPham) {
        this.idSanPham = idSanPham;
    }


}
