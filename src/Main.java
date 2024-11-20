import controller.SampahController;
import view.SampahView;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SampahView view = new SampahView();
        SampahController controller = new SampahController(view);
        Scanner scanner = new Scanner(System.in);

        view.tampilkanHeader();

        int pilihan;
        do {
            view.tampilkanMenu();
            pilihan = scanner.nextInt();
            scanner.nextLine(); 

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama sampah: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan kategori (Organik/Anorganik/DaurUlang): ");
                    String kategori = scanner.nextLine();
                    System.out.print("Masukkan volume (kg): ");
                    double volume = scanner.nextDouble();
                    controller.tambahDataSampah(nama, kategori, volume);
                    break;

                case 2:
                    controller.tampilkanDataSampah();
                    break;

                case 3:
                    controller.sortingVolume();
                    controller.tampilkanDataSampah();
                    break;

                case 4:
                    controller.sortingKategori();
                    controller.tampilkanDataSampah();
                    break;

                case 5:
                    controller.tampilkanStatistik();
                    break;

                case 6:
                    System.out.print("Masukkan kategori (Organik/Anorganik/DaurUlang): ");
                    String detailKategori = scanner.nextLine();
                    controller.tampilkanDetailKategori(detailKategori);
                    break;

                case 7:
                    System.out.println(view.GREEN + "Terima kasih telah menggunakan EcoSort CLI. Sampai jumpa!" + view.RESET);
                    break;

                default:
                    System.out.println(view.RED + "Pilihan tidak valid. Silakan coba lagi." + view.RESET);
            }
        } while (pilihan != 7);
    }
}
