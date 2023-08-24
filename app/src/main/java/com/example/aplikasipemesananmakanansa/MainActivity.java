package com.example.aplikasipemesananmakanansa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MenuAdapter menuAdapter;
    private List<MenuItem> menuItems;
    private Button btnTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTotal = findViewById(R.id.btnTotalBayar);
        recyclerView = findViewById(R.id.rycView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        menuItems = new ArrayList<>();
        int imgRendang = getResources().getIdentifier("rendang", "drawable", getPackageName());
        int imgLimpa = getResources().getIdentifier("limpa", "drawable", getPackageName());
        int imgBabat = getResources().getIdentifier("babat","drawable",getPackageName());
        int imgKikil = getResources().getIdentifier("kikil","drawable",getPackageName());

        menuItems.add(new MenuItem("Nama : Nasi Rendang", "Masakan daging yang berasal dari MinangKabau Sumatra Barat", 26000, imgRendang));
        menuItems.add(new MenuItem("Nama : Nasi Limpa", "Rasanya yang enak dan lezat, gulai hati limpa sangan berguna bagi kesehatan", 17000, imgLimpa));
        menuItems.add(new MenuItem("Nama : Nasi Babat", "Gulai babat menggunakan bahan utama babat sapi yang dimasak dengan rempah-rempah khas", 15000, imgBabat));
        menuItems.add(new MenuItem("Nama : Nasi Kikil", "Kikil salah satu menu yang selalu ada di setiap rumah makan Padang", 19000, imgKikil));

        menuAdapter = new MenuAdapter(menuItems);
        recyclerView.setAdapter(menuAdapter);

        SharedPreferences sharedPreferences = getSharedPreferences("orders", MODE_PRIVATE);

        int orderCount = sharedPreferences.getInt("order_count", 0);

        double totalHargaKeseluruhan = 0;

        for (int i = 0; i < orderCount; i++) {
            String orderKey = "order_" + i;
            String itemName = sharedPreferences.getString(orderKey + "_itemName", "");
            float totalPrice = sharedPreferences.getFloat(orderKey + "_totalPrice", 0);
            totalHargaKeseluruhan += totalPrice;
        }

        if (totalHargaKeseluruhan > 0) {
            btnTotal.setEnabled(true);
            btnTotal.setText("Total Pembayaran: Rp. " + (int)totalHargaKeseluruhan);
        } else {
            btnTotal.setEnabled(false);
            btnTotal.setText("Tidak Ada Pesanan");
        }
    }

    public void toRiwayat(View view) {
        Intent intent = new Intent(this, RiwayatActivity.class);
        startActivity(intent);
    }

    public void toDaftarPesanan(View view) {
        Intent intent = new Intent(this, DaftarPesananActivity.class);
        startActivity(intent);
    }
}