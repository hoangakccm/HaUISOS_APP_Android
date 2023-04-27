package com.example.btl;

import android.content.Context;

public class DataLocalManager {
    private static final String PREF_FIRST_INTALLED = "PREF_FIRST_INTALLED";
    private static DataLocalManager instance ;
    private static final String HoTen1 = "HoTen1";
     private static final String NhomMau1 = "NhomMau1";
    private static final String Tuoi1 = "Tuoi1";
    private static final String ChieuCao1 = "ChieuCao1";
    private static final String CanNang1 = "CanNang1";
    private static final String Benhtiensu1 = "Benhtiensu1";
    private static final String NguoiThan1 = "NguoiThan1";
    private static final String SDT1 = "SDT1";

    private MySharedPreferences mySharedPreferences;
    public static void init(Context context){
        instance = new DataLocalManager();
        instance.mySharedPreferences= new MySharedPreferences(context);

    }
    public static DataLocalManager getInstance(){
        if(instance ==null){
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setHoten(String ten1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(HoTen1,ten1);
    }
    public static String getHoten(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(HoTen1);
    }
    public static void setNhommau(String mau1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(NhomMau1,mau1);
    }
    public static String getNhommau(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(NhomMau1);
    }
    public static void setGttuoi(String tuoi1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(Tuoi1,tuoi1);
    }
    public static String getGttuoi(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(Tuoi1);
    }
    public static void setChieuCao(String cao1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(ChieuCao1,cao1);
    }
    public static String getChieuCao(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(ChieuCao1);
    }
    public static void setCanNang(String can1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(CanNang1,can1);
    }
    public static String getCanNang(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(CanNang1);
    }
    //
    public static void setBenhtiensu(String benh1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(Benhtiensu1,benh1);
    }
    public static String getBenhtiensu(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(Benhtiensu1);
    }
    //
    public static void setNguoithan(String nt1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(NguoiThan1,nt1);
    }
    public static String getNguoithan(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(NguoiThan1);
    }
    //
    public static void setSDT(String sdtt1){
        DataLocalManager.getInstance().mySharedPreferences.putStringValue(SDT1,sdtt1);
    }
    public static String getSDT(){
        return DataLocalManager.getInstance().mySharedPreferences.getStringValue(SDT1);
    }
}
