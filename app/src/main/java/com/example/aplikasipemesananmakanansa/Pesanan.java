package com.example.aplikasipemesananmakanansa;

import java.util.Date;
import java.util.List;

public class Pesanan {
    private List<MenuItem> items;
    private String menu;
    private float hargaMenu;
    private int quantity;
    private float totalHarga;
    private Date tanggal;
    private String metodePembayaran;

    public Pesanan(String itemName, float harga, int quantity, float totalHarga, Date tanggal) {
        this.menu = itemName;
        this.hargaMenu = harga;
        this.quantity = quantity;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
    }

    public Pesanan(List<MenuItem> items) {
        this.items = items;
        calculateTotalHarga();
    }
    private void calculateTotalHarga() {
        totalHarga = 0;
        for (MenuItem item : items) {
            totalHarga += item.getPrice();
        }
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public float getTotalHarga() {
        return totalHarga;
    }

    public String getItemName() {
        return menu;
    }

    public float getTotalPrice() {
        return totalHarga;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }

}

