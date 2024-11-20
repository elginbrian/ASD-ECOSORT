package model;

public class Sampah {
    private String nama;
    private String kategori;
    private double volume;

    public Sampah(String nama, String kategori, double volume) {
        this.nama = nama;
        this.kategori = kategori;
        this.volume = volume;
    }

    public String getNama() {
        return nama;
    }

    public String getKategori() {
        return kategori;
    }

    public double getVolume() {
        return volume;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Kategori: " + kategori + ", Volume: " + volume + " kg";
    }
}
