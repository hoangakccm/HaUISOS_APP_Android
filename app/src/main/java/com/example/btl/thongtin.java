package com.example.btl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class thongtin extends AppCompatActivity {
    Toolbar toolbar;
    TextView hoten, gttuoi,tvsua,tvmau,tvcao,tvcan,tvbenh, tvnguoithan,tvsdt;
    String ten1,tuoi1,benh1,chieucao1,cannang1,nhommau1,nguoithan1,sdt1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thongtin);
        anhxa();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //lay du lieu tu du lieu da lưu trong app
        ten1= DataLocalManager.getHoten();
        tuoi1= DataLocalManager.getGttuoi();
        benh1= DataLocalManager.getBenhtiensu();
        chieucao1= DataLocalManager.getChieuCao();
        cannang1= DataLocalManager.getCanNang();
        nhommau1= DataLocalManager.getNhommau();
        nguoithan1= DataLocalManager.getNguoithan();
        sdt1= DataLocalManager.getSDT();


        //lay du lieu bang intent
        Intent i3 = getIntent();

        String ten = i3.getStringExtra("hoten");
        String tuoi = i3.getStringExtra("gttuoi");
        String benh = i3.getStringExtra("benh");
        String chieucao = i3.getStringExtra("chieucao");
        String cannang = i3.getStringExtra("cannang");
        String nhommau = i3.getStringExtra("nhommau");
        String nguoithan = i3.getStringExtra("nguoithan");
        String sdt = i3.getStringExtra("sdt");


        hoten.setText(ten1);
        gttuoi.setText(tuoi1);
        tvbenh.setText(benh1);
        tvmau.setText(nhommau1);
        tvcao.setText(chieucao1);
        tvcan.setText(cannang1);
        tvnguoithan.setText(nguoithan1);
        tvsdt.setText(sdt1);

        tvsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(thongtin.this, sua_thong_tin.class);
                i2.putExtra("hoten",ten);
                i2.putExtra("gttuoi",tuoi);
                i2.putExtra("benh",benh);
                i2.putExtra("nhommau",nhommau);
                i2.putExtra("chieucao",chieucao);
                i2.putExtra("cannang",cannang);
                i2.putExtra("nguoithan",nguoithan);
                i2.putExtra("sdt",sdt);
                startActivity(i2);

            }
        });


    }

    private void anhxa() {
        toolbar = findViewById(R.id.toolbarMonhoc);
        hoten = findViewById(R.id.hoten);
        gttuoi = findViewById(R.id.gttuoi);
        tvcan = findViewById(R.id.tvcan);
        tvcao = findViewById(R.id.tvcao);
        tvmau = findViewById(R.id.tvmau);
        tvbenh = findViewById(R.id.textView4);
        tvsua = findViewById(R.id.textView3);
        tvnguoithan = findViewById(R.id.tvnguoithan);
        tvsdt = findViewById(R.id.tvsdt);

    }
    //thêm 1 menu là add vào toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd,menu);

        return true;
    }
//
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {

            //nếu click vào sua
            case R.id.menuadd:
                Intent i3 = getIntent();

                String ten = i3.getStringExtra("hoten");
                String tuoi = i3.getStringExtra("gttuoi");
                String benh = i3.getStringExtra("benh");
                String chieucao = i3.getStringExtra("chieucao");
                String cannang = i3.getStringExtra("cannang");
                String nhommau = i3.getStringExtra("nhommau");
                String nguoithan = i3.getStringExtra("nguoithan");
                String sdt = i3.getStringExtra("sdt");

                gttuoi.setText(tuoi);
                hoten.setText(ten);
                tvcan.setText(cannang);
                tvcao.setText(chieucao);
                tvmau.setText(nhommau);
                tvbenh.setText(benh);
                tvnguoithan.setText(nguoithan);
                tvsdt.setText(sdt);

                DataLocalManager.setHoten(ten);
                DataLocalManager.setGttuoi(tuoi);
                DataLocalManager.setBenhtiensu(benh);
                DataLocalManager.setChieuCao(chieucao);
                DataLocalManager.setCanNang(cannang);
                DataLocalManager.setNhommau(nhommau);
                DataLocalManager.setNguoithan(nguoithan);
                DataLocalManager.setSDT(sdt);


                break;
            //    Nếu click Back ở điẻn thoại sẽ trở về main ban đầu
            default:
                Intent intent1 = new Intent(thongtin.this, MainActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


}