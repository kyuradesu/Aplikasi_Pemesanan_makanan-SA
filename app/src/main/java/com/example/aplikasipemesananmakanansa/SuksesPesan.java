package com.example.aplikasipemesananmakanansa;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SuksesPesan extends AppCompatActivity {

    private TextView txtNama,txtHarga,txtQuantity,txtTotal;
    private Button btnBackMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sukses_pesan);

        txtNama = findViewById(R.id.txtPesanNama);
        txtHarga = findViewById(R.id.txtPesanHarga);
        txtQuantity = findViewById(R.id.txtquantity);
        txtTotal = findViewById(R.id.txtTotalHarga);
        btnBackMenu = findViewById(R.id.btnKembaliMenu);

        Intent intent = getIntent();
        String namaMenu = intent.getStringExtra("namaMenu");
        double hargaMenu = intent.getDoubleExtra("hargaMenu", 0.0);
        int jumlahPesan = intent.getIntExtra("jumlahPesan", 0);
        double totalHarga = intent.getDoubleExtra("totalHarga", 0.0);

        TextView txtPesanNama = findViewById(R.id.txtPesanNama);
        txtPesanNama.setText(namaMenu);

        TextView txtPesanHarga = findViewById(R.id.txtPesanHarga);
        txtPesanHarga.setText("Rp. " + (int)hargaMenu);

        TextView txtJumlah = findViewById(R.id.txtquantity);
        txtJumlah.setText("" + jumlahPesan);

        TextView txtTotalHarga = findViewById(R.id.txtTotalHarga);
        txtTotalHarga.setText("Rp. " + (int)totalHarga);

        btnBackMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SuksesPesan.this, MainActivity.class);
                startActivity(intent2);
            }
        });
    }
}