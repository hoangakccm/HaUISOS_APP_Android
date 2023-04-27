package com.example.btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_PERMISSION_CODE = 200;
    boolean cameraFlash = false;
    boolean flashon = false;
    ImageButton imgbtn;
    ImageButton btn113,btn114,btn115;
    SensorManager sensorManager;
    Sensor gyroscopes;
    SensorEventListener gyroListen;
    float zValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imgbtn = findViewById(R.id.imageButton2);
        btn113 = findViewById(R.id.btn113);
        btn114 = findViewById(R.id.btn114);
        btn115 = findViewById(R.id.btn115);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        gyroscopes =sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        phanhoi_quyentruycap();
        if(gyroscopes==null){
            Toast.makeText(MainActivity.this,"Khong co cam bien nay",Toast.LENGTH_SHORT).show();
            finish();
        }else
        {
            gyroListen = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent sensorEvent) {
                    zValue = sensorEvent.values[2];
                    if(zValue > 3.0f){
                        nhaycoi();
                    }else if(zValue < -3.0f){
                        try {
                            Nhayden();
                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onAccuracyChanged(Sensor sensor, int i) {

                }
            };
        }


        //nut thoat:
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Khoi tao lai Activity main
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

                // Tao su kien ket thuc app
                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startActivity(startMain);
                finish();
            }
        });
        //nut gọi 113:
        btn113.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = "0342518733";
                Intent intent_call1 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sdt));
                startActivity(intent_call1);
            }
        });
        btn114.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = "114";
                Intent intent_call2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sdt));
                startActivity(intent_call2);
            }
        });
        btn115.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sdt = "115";
                Intent intent_call3 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+sdt));
                startActivity(intent_call3);
            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.btn_home:
                        Toast.makeText(MainActivity.this," Bạn đang ở trang chủ",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.btn_coi:
                        Toast.makeText(MainActivity.this," Bạn đã bật còi báo động ",Toast.LENGTH_SHORT).show();
                        nhaycoi();
                        break;
                    case R.id.btn_den:
                        Toast.makeText(MainActivity.this," Bạn đã bật đèn Flash SOS",Toast.LENGTH_SHORT).show();
                        try {
                            Nhayden();

                        } catch (CameraAccessException e) {
                            e.printStackTrace();
                        }
                        break;
                    case R.id.btn_thongtin:
                        Toast.makeText(MainActivity.this," Bạn đang ở trang thông tin cá nhân",Toast.LENGTH_SHORT).show();
                        thongtin();

                        break;
                }
                return true;
            }
        });

    }

    private void phanhoi_quyentruycap() {
        if(Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            return;
        }
        if(checkSelfPermission(Manifest.permission.CALL_PHONE)==PackageManager.PERMISSION_GRANTED){
            Toast.makeText(this, "da duoc cap quyen", Toast.LENGTH_SHORT).show();
        }
        else {
            String[] permissions ={ Manifest.permission.CALL_PHONE};
            requestPermissions(permissions, REQUEST_PERMISSION_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "DA DUOC CAP QUYEN", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "TU CHOI CAP QUYEN", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void batden() {
        if(cameraFlash){
            if(flashon){
                flashon = false;
                try {
                    FlashlightOff();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
            else {
                flashon = true;
                try {
                    FlashlightOn();
                } catch (CameraAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void FlashlightOn() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraID = cameraManager.getCameraIdList()[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraID,true);
        }
    }

    private void FlashlightOff() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String cameraID = cameraManager.getCameraIdList()[0];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            cameraManager.setTorchMode(cameraID,false);
        }
    }
    private void thongtin(){
        Intent intent_tt = new Intent(MainActivity.this,thongtin.class);
        startActivity(intent_tt);
    }
    private void Nhayden() throws CameraAccessException {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        String myString = "010101011101110111010101";
        long delay = 50;
        for(int i = 0; i<myString.length();i++)
        {
            if(myString.charAt(i)=='0')
            {
                String cameraID = cameraManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(cameraID, true);
                }
            }
            else {
                String cameraID = cameraManager.getCameraIdList()[0];
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    cameraManager.setTorchMode(cameraID, false);
                }
            }try {
            Thread.sleep(delay);
            }catch (InterruptedException e ){
                e.printStackTrace();
            }
        }

    }
    private void nhaycoi(){
        final MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this,R.raw.beep_sos);
        for(int i = 0; i<5;i++){
            mediaPlayer.start();
            if(i==4) {
                break;
            }
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(gyroListen,gyroscopes,SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(gyroListen);
    }
}