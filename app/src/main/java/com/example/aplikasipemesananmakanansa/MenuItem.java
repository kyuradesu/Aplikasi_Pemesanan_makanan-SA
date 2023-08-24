package com.example.aplikasipemesananmakanansa;

public class MenuItem {
    private String namaMenu;
    private String deskripsiMenu;
    private double hargaMenu;
    private int imageResId; // atribut untuk ID sumber gambar

    public MenuItem(String name, String description, double price, int imageResId) {
        this.namaMenu = name;
        this.deskripsiMenu = description;
        this.hargaMenu = price;
        this.imageResId = imageResId;
    }

    public String getNamaMenu() {
        return namaMenu;
    }

    public void setNamaMenu(String namaMenu) {
        this.namaMenu = namaMenu;
    }

    public String getDeskripsiMenu() {
        return deskripsiMenu;
    }

    public void setDeskripsiMenu(String deskripsiMenu) {
        this.deskripsiMenu = deskripsiMenu;
    }

    public double getHargaMenu() {
        return hargaMenu;
    }

    public void setHargaMenu(double hargaMenu) {
        this.hargaMenu = hargaMenu;
    }

    public int getImageResId() {
        return imageResId;
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public double getPrice() {
        return hargaMenu;
    }
}
