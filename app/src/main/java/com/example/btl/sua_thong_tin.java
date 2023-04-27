package com.example.btl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class sua_thong_tin extends AppCompatActivity {
    Button btnxong,btnhuy;
    EditText hoten, gttuoi,edbenh,edmau,edcao,edcan,ednguoithan,edsdt;
    String ten1,tuoi1,benh1,chieucao1,cannang1,nhommau1,nguoithan1,sdt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sua_thong_tin);
        anhxa();
        Intent i3 = getIntent();

        String ten = i3.getStringExtra("hoten");
        String tuoi = i3.getStringExtra("gttuoi");
        String benh = i3.getStringExtra("benh");
        String chieucao = i3.getStringExtra("chieucao");
        String cannang = i3.getStringExtra("cannang");
        String nhommau = i3.getStringExtra("nhommau");
        String nguoithan = i3.getStringExtra("nguoithan");
        String sdt = i3.getStringExtra("sdt");


        DataLocalManager.setHoten(ten);
        DataLocalManager.setGttuoi(tuoi);
        DataLocalManager.setBenhtiensu(benh);
        DataLocalManager.setChieuCao(chieucao);
        DataLocalManager.setCanNang(cannang);
        DataLocalManager.setNhommau(nhommau);
        DataLocalManager.setNguoithan(nguoithan);
        DataLocalManager.setSDT(sdt);

        ten1= DataLocalManager.getHoten();
        tuoi1= DataLocalManager.getGttuoi();
        benh1= DataLocalManager.getBenhtiensu();
        chieucao1= DataLocalManager.getChieuCao();
        cannang1= DataLocalManager.getCanNang();
        nhommau1= DataLocalManager.getNhommau();
        nguoithan1= DataLocalManager.getNguoithan();
        sdt1= DataLocalManager.getSDT();

        hoten.setText(ten1);
        gttuoi.setText(tuoi1);
        edbenh.setText(benh1);
        edcao.setText(chieucao1);
        edmau.setText(nhommau1);
        edcan.setText(cannang1);
        ednguoithan.setText(nguoithan1);
        edsdt.setText(sdt1);

        btnxong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ten = hoten.getText().toString();
                String tuoi = gttuoi.getText().toString();
                String nhommau = edmau.getText().toString();
                String chieucao = edcao.getText().toString();
                String cannang = edcan.getText().toString();
                String benh = edbenh.getText().toString();
                String nguoithan = ednguoithan.getText().toString();
                String sdt = edsdt.getText().toString();

                Intent i1 = new Intent(sua_thong_tin.this, thongtin.class);

                i1.putExtra("hoten",ten);
                i1.putExtra("gttuoi",tuoi);
                i1.putExtra("benh",benh);
                i1.putExtra("chieucao",chieucao);
                i1.putExtra("cannang",cannang);
                i1.putExtra("nhommau",nhommau);
                i1.putExtra("nguoithan",nguoithan);
                i1.putExtra("sdt",sdt);
                startActivity(i1);
                Toast.makeText(sua_thong_tin.this, "Sửa thông tin thành công!", Toast.LENGTH_SHORT).show();

            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void anhxa() {
        btnhuy = findViewById(R.id.btnhuy);
        btnxong = findViewById(R.id.btnluu);
        hoten = findViewById(R.id.hoten);
        gttuoi = findViewById(R.id.gttuoi);
        edbenh = findViewById(R.id.edbenh);
        edmau = findViewById(R.id.edmau);
        edcao = findViewById(R.id.edcao);
        edcan = findViewById(R.id.edcan);
        edsdt = findViewById(R.id.edsdt);
        ednguoithan = findViewById(R.id.ednguoithan);
    }
}