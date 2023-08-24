package com.example.aplikasipemesananmakanansa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class PesanActivity extends AppCompatActivity {

    private int quantity = 1;
    private double itemPrice;
    private Button xBtnMinus,xBtnPlus,xBtnPesan;
    private TextView txtQuantity,txtTotalHarga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesan);

        xBtnMinus = findViewById(R.id.btnMinus);
        xBtnPlus = findViewById(R.id.btnPlus);
        txtQuantity = findViewById(R.id.txtQuantity);
        txtTotalHarga = findViewById(R.id.txtTotalHarga);

        Intent intent = getIntent();
        String itemName = intent.getStringExtra("itemName");
        String itemDescription = intent.getStringExtra("itemDescription");
        itemPrice = intent.getDoubleExtra("itemPrice", 0.0);
        int itemImage = intent.getIntExtra("itemImage", R.drawable.bgpesan);

        ImageView menuItemImage = findViewById(R.id.imgPesanGambar);
        TextView menuItemName = findViewById(R.id.txtnama);
        TextView menuItemDescription = findViewById(R.id.txtPesanNama);
        TextView menuItemPrice = findViewById(R.id.txtPesanHarga);

        menuItemImage.setImageResource(itemImage);
        menuItemName.setText(itemName);
        menuItemDescription.setText(itemDescription);
        menuItemPrice.setText("Harga: Rp. " + (int)itemPrice);


        xBtnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quantity++;
                updateQuantityAndTotal();
            }
        });

        xBtnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (quantity > 1) {
                    quantity--;
                    updateQuantityAndTotal();
                }
            }
        });

        updateQuantityAndTotal();

        xBtnPesan = findViewById(R.id.btnPesanMenu);
        xBtnPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Calculate total bayar based on quantity
                double totalBayar = quantity * itemPrice;

                // Save order details to SharedPreferences
                SharedPreferences sharedPreferences = getSharedPreferences("orders", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                // Get the current order count
                int orderCount = sharedPreferences.getInt("order_count", 0);

                // Create unique key for the order using the order count
                String orderKey = "order_" + orderCount;

                // Save order details using the unique order key
                editor.putString(orderKey + "_itemName", itemName); // Save the item name
                editor.putInt(orderKey + "_quantity", quantity);
                editor.putFloat(orderKey + "_totalPrice", (float) totalBayar);

                // Update the order count
                editor.putInt("order_count", orderCount + 1);
                editor.apply();

                // Create intent to go back to MenuActivity
                Intent intent = new Intent(PesanActivity.this, SuksesPesan.class);
                intent.putExtra("namaMenu", itemName);
                intent.putExtra("hargaMenu", itemPrice);
                intent.putExtra("jumlahPesan", quantity);
                intent.putExtra("totalHarga", totalBayar);
                startActivity(intent);
                finish();
            }
        });







    }

    private void updateQuantityAndTotal() {
        txtQuantity.setText(String.valueOf(quantity));
        double totalHarga = quantity * itemPrice;

        DecimalFormat decimalFormat = new DecimalFormat("0"); // Format tanpa desimal
        String totalHargaFormatted = "Total Harga: Rp " + decimalFormat.format(totalHarga);

        txtTotalHarga.setText(totalHargaFormatted);
    }
}
