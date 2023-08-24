package com.example.aplikasipemesananmakanansa;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class RiwayatActivity extends AppCompatActivity {


    private RecyclerView recyclerViewRiwayat;
    private RiwayatAdapter riwayatAdapter;
    private List<RiwayatItem> riwayatItems;
    private SharedPreferences sharedPreferences;
    private Button btnKeMenu;
    private ImageView reset;

    private void reset(){
        SharedPreferences sharedPreferences = getSharedPreferences("riwayat", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
        Toast.makeText(this, "Berhasil di RESET", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);

        ImageView reset = findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                reset();
            }
        });


        btnKeMenu = findViewById(R.id.btnMenu);
        btnKeMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RiwayatActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




        recyclerViewRiwayat = findViewById(R.id.recyclerViewRiwayat);
        riwayatItems = new ArrayList<>();
        sharedPreferences = getSharedPreferences("riwayat", MODE_PRIVATE);

        int riwayatCount = sharedPreferences.getInt("riwayat_count", 0);

        for (int i = 0; i < riwayatCount; i++) {
            String riwayatKey = "riwayat_" + i;
            String pesananJson = sharedPreferences.getString(riwayatKey, "");
            String metodePembayaran = sharedPreferences.getString(riwayatKey + "_metodePembayaran", "");
            String tanggalFormatted = sharedPreferences.getString(riwayatKey + "_tanggal", ""); // Ambil tanggal yang sudah diformat

            Pesanan pesanan = convertJsonToPesanan(pesananJson);

            String itemPesanan = "Item: " + pesanan.getItemName() + " (x" + pesanan.getQuantity() + ")";
            String totalHarga = "Harga: Rp " + (int)pesanan.getTotalPrice();
            String tanggal = "Tanggal: " + tanggalFormatted; // Anda harus menyesuaikan bagian ini
            String metodePembayaranText = "Metode Pembayaran: " + metodePembayaran;

            RiwayatItem riwayatItem = new RiwayatItem(itemPesanan, totalHarga, tanggal, metodePembayaranText);
            riwayatItems.add(riwayatItem);
        }

        riwayatAdapter = new RiwayatAdapter(riwayatItems);
        recyclerViewRiwayat.setAdapter(riwayatAdapter);
        recyclerViewRiwayat.setLayoutManager(new LinearLayoutManager(this));
    }

    private String convertPesananToJson(Pesanan pesanan) {
        Gson gson = new Gson();
        return gson.toJson(pesanan);
    }

    private Pesanan convertJsonToPesanan(String pesananJson) {
        Gson gson = new Gson();
        return gson.fromJson(pesananJson, Pesanan.class);
    }

}
