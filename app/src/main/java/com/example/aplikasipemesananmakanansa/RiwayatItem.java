package com.example.aplikasipemesananmakanansa;

public class RiwayatItem {
    public void setItemPesanan(String itemPesanan) {
        this.itemPesanan = itemPesanan;
    }

    public void setTotalHarga(String totalHarga) {
        this.totalHarga = totalHarga;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public void setMetodePembayaran(String metodePembayaran) {
        this.metodePembayaran = metodePembayaran;
    }

    private String itemPesanan;
    private String totalHarga;
    private String tanggal;
    private String metodePembayaran;

    public RiwayatItem(String itemPesanan, String totalHarga, String tanggal, String metodePembayaran) {
        this.itemPesanan = itemPesanan;
        this.totalHarga = totalHarga;
        this.tanggal = tanggal;
        this.metodePembayaran = metodePembayaran;
    }

    public String getItemPesanan() {
        return itemPesanan;
    }

    public String getTotalHarga() {
        return totalHarga;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getMetodePembayaran() {
        return metodePembayaran;
    }
}
