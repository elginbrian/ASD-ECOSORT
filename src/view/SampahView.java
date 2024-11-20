package view;

import model.Sampah;

import java.util.List;

public class SampahView {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String CYAN = "\u001B[36m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";

    public void tampilkanHeader() {
        System.out.println("\n");
        System.out.println(CYAN + "ECO-SORT" + RESET);
        System.out.println(BLUE + "Mengelola Sampah Demi Masa Depan yang Lebih Baik\n" + RESET);
    }

    public void tampilkanMenu() {
        System.out.println(CYAN + "+-------------------------------------+" + RESET);
        System.out.println(CYAN + "|               MENU                  |" + RESET);
        System.out.println(CYAN + "+-------------------------------------+" + RESET);
        System.out.println("1. Tambah Data Sampah");
        System.out.println("2. Lihat Data Sampah");
        System.out.println("3. Sorting Berdasarkan Volume");
        System.out.println("4. Sorting Berdasarkan Kategori");
        System.out.println("5. Statistik Sampah");
        System.out.println("6. Submenu Detail Kategori");
        System.out.println("7. Keluar");
        System.out.println(CYAN + "+-------------------------------------+" + RESET);
        System.out.print("Pilih opsi: ");
    }

    public void tampilkanDataSampah(List<Sampah> sampahList) {
        if (sampahList.isEmpty()) {
            System.out.println(RED + "\nTidak ada data sampah yang tersimpan.\n" + RESET);
        } else {
            System.out.println(YELLOW + "\n======================================" + RESET);
            System.out.println(YELLOW + "            DATA SAMPAH               " + RESET);
            System.out.println(YELLOW + "======================================" + RESET);
            for (Sampah sampah : sampahList) {
                String warnaKategori = getColorForKategori(sampah.getKategori());
                System.out.println(warnaKategori + sampah + RESET);
            }
            System.out.println(YELLOW + "======================================" + RESET);
        }
    }

    public void tampilkanPanduan() {
        System.out.println(GREEN + "\n======================================");
        System.out.println("         Panduan Pengelolaan Sampah");
        System.out.println("======================================" + RESET);
        System.out.println("- " + GREEN + "Organik" + RESET + ": Dapat diolah menjadi kompos (contoh: sisa makanan, daun).");
        System.out.println("- " + RED + "Anorganik" + RESET + ": Tidak mudah terurai, dapat didaur ulang (contoh: plastik, kaca).");
        System.out.println("- " + YELLOW + "Daur Ulang" + RESET + ": Material yang dapat diproses ulang (contoh: botol plastik, kertas).");
    }

    public void tampilkanPesan(String pesan) {
        System.out.println(GREEN + "\n" + pesan + RESET);
    }

    public void tampilkanStatistik(int totalSampah, double totalVolume, int organik, int anorganik, int daurUlang) {
        System.out.println(BLUE + "\n======================================");
        System.out.println("           Statistik Sampah");
        System.out.println("======================================" + RESET);
        System.out.printf("Total Data Sampah  : %d%n", totalSampah);
        System.out.printf("Total Volume       : %.2f kg%n", totalVolume);
        System.out.printf(GREEN + "Organik            : %d%n" + RESET, organik);
        System.out.printf(RED + "Anorganik          : %d%n" + RESET, anorganik);
        System.out.printf(YELLOW + "Daur Ulang         : %d%n" + RESET, daurUlang);
        System.out.println(BLUE + "======================================" + RESET);
    }

    public void tampilkanDetailKategori(String kategori, List<Sampah> sampahList) {
        System.out.println(CYAN + "\n======================================");
        System.out.println("      Detail Kategori: " + kategori);
        System.out.println("======================================" + RESET);
        sampahList.stream()
                .filter(s -> s.getKategori().equalsIgnoreCase(kategori))
                .forEach(s -> System.out.println(getColorForKategori(kategori) + s + RESET));
    }

    private String getColorForKategori(String kategori) {
        kategori = kategori.toLowerCase(); 
        switch (kategori) {
            case "organik":
                return GREEN;
            case "anorganik":
                return RED;
            case "daurulang":
                return YELLOW;
            default:
                return RESET;
        }
    }
    
}
