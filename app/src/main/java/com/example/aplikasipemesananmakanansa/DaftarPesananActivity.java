package com.example.aplikasipemesananmakanansa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DaftarPesananActivity extends AppCompatActivity {

    private ListView listViewPesanan;
    private List<String> pesananList;
    private TextView txtTotBayar;
    private Button btnByrSekarang;
    private SharedPreferences sharedPreferences;
    private RadioGroup radioGroup;
    private RadioButton radioOVO, radioGOPAY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pesanan);

        radioGroup = findViewById(R.id.radioGroup);
        radioOVO = findViewById(R.id.radioGopay);
        radioGOPAY = findViewById(R.id.radioShopee);
        txtTotBayar = findViewById(R.id.txtTotBayar);
        btnByrSekarang = findViewById(R.id.btnBayarSekarang);
        sharedPreferences = getSharedPreferences("orders", MODE_PRIVATE);

        btnByrSekarang.setOnClickListener(view -> {
            // Panggil metode untuk menyimpan data ke SharedPreferences
            simpanPesananKeSharedPreferences();
        });

        listViewPesanan = findViewById(R.id.listViewPesanan);
        pesananList = new ArrayList<>();


        int orderCount = sharedPreferences.getInt("order_count", 0);
        float totalHargaKeseluruhan = 0;

        for (int i = 0; i < orderCount; i++) {
            String orderKey = "order_" + i;
            String itemName = sharedPreferences.getString(orderKey + "_itemName", "");
            int quantity = sharedPreferences.getInt(orderKey + "_quantity", 0);
            float totalPrice = sharedPreferences.getFloat(orderKey + "_totalPrice", 0);

            String pesananText = itemName + " (x" + quantity + ") Harga: Rp. " + (int)totalPrice;
            pesananList.add(pesananText);

            totalHargaKeseluruhan += totalPrice;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pesananList);
        listViewPesanan.setAdapter(adapter);

        // Set total harga pada txtTotBayar
        txtTotBayar.setText("Rp. " + (int)totalHargaKeseluruhan);
    }

    private void simpanPesananKeSharedPreferences() {
        List<MenuItem> items = new ArrayList<>();
        // Mendapatkan order count dari SharedPreferences
        int orderCount = sharedPreferences.getInt("order_count", 0);

        // Mendapatkan riwayat count dari SharedPreferences riwayat
        SharedPreferences riwayatSharedPreferences = getSharedPreferences("riwayat", MODE_PRIVATE);
        SharedPreferences.Editor riwayatEditor = riwayatSharedPreferences.edit();
        int riwayatCount = riwayatSharedPreferences.getInt("riwayat_count", 0);

        int selectedId = radioGroup.getCheckedRadioButtonId();

        String metodePembayaran = "";
        if (selectedId == R.id.radioGopay) {
            metodePembayaran = "Gopay";
        } else if (selectedId == R.id.radioShopee) {
            metodePembayaran = "ShopeePay";
        }

        // Loop melalui setiap pesanan yang ada
        for (int i = 0; i < orderCount; i++) {
            String orderKey = "order_" + i;
            String itemName = sharedPreferences.getString(orderKey + "_itemName", "");
            int quantity = sharedPreferences.getInt(orderKey + "_quantity", 0);
            float totalPrice = sharedPreferences.getFloat(orderKey + "_totalPrice", 0);
            MenuItem item = new MenuItem(itemName, "", totalPrice, 0);
            items.add(item);

            // Buat instance dari Pesanan
            Date tanggalPesanan = new Date();
            Pesanan pesanan = new Pesanan(itemName, totalPrice, quantity, totalPrice, tanggalPesanan);

            SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
            String tanggalFormatted = dateFormat.format(tanggalPesanan);

            // Konversi instance Pesanan menjadi format JSON
            String pesananJson = convertPesananToJson(pesanan);

            // Simpan pesananJson dalam SharedPreferences riwayat
            String riwayatKey = "riwayat_" + riwayatCount;
            riwayatEditor.putString(riwayatKey, pesananJson);
            riwayatEditor.putString(riwayatKey + "_metodePembayaran", metodePembayaran);
            riwayatEditor.putString(riwayatKey + "_tanggal", tanggalFormatted); // Simpan tanggal yang sudah diformat


            // Increment riwayat count
            riwayatCount++;
        }

        // Simpan riwayat count kembali ke SharedPreferences riwayat
        riwayatEditor.putInt("riwayat_count", riwayatCount);
        riwayatEditor.apply();

        // Hapus semua data pesanan dari SharedPreferences orders
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // Kembali ke halaman sebelumnya atau lakukan operasi lain yang diperlukan
        Intent intent = new Intent(this, RiwayatActivity.class);
        startActivity(intent);
    }

    // Metode untuk mengkonversi Pesanan menjadi format JSON
    private String convertPesananToJson(Pesanan pesanan) {
        Gson gson = new Gson();
        return gson.toJson(pesanan);
    }

}
