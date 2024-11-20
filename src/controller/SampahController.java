package controller;

import model.Sampah;
import view.SampahView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SampahController {
    private final List<Sampah> sampahList = new ArrayList<>();
    private final SampahView view;
    private final String fileName = "data_sampah.txt";

    public SampahController(SampahView view) {
        this.view = view;
        muatDataDariFile();
    }

    public void tambahDataSampah(String nama, String kategori, double volume) {
        if (!isValidKategori(kategori)) {
            view.tampilkanPesan("Kategori tidak valid. Gunakan Organik, Anorganik, atau Daur Ulang.");
            return;
        }
        Sampah sampah = new Sampah(nama, kategori, volume);
        sampahList.add(sampah);
        simpanDataKeFile();
        view.tampilkanPesan("Data sampah berhasil ditambahkan!");
    }

    public void tampilkanDataSampah() {
        view.tampilkanDataSampah(sampahList);
    }

    public void sortingVolume() {
        for (int i = 0; i < sampahList.size(); i++) {
            for (int j = 0; j < sampahList.size() - i - 1; j++) {
                if (sampahList.get(j).getVolume() > sampahList.get(j + 1).getVolume()) {
                    Sampah temp = sampahList.get(j);
                    sampahList.set(j, sampahList.get(j + 1));
                    sampahList.set(j + 1, temp);
                }
            }
        }
        view.tampilkanPesan("Data telah diurutkan berdasarkan volume.");
    }

    public void sortingKategori() {
        sampahList.sort((s1, s2) -> s1.getKategori().compareToIgnoreCase(s2.getKategori()));
        view.tampilkanPesan("Data telah diurutkan berdasarkan kategori.");
    }

    public void tampilkanStatistik() {
        int totalSampah = sampahList.size();
        double totalVolume = sampahList.stream().mapToDouble(Sampah::getVolume).sum();
        int organik = (int) sampahList.stream().filter(s -> s.getKategori().equalsIgnoreCase("Organik")).count();
        int anorganik = (int) sampahList.stream().filter(s -> s.getKategori().equalsIgnoreCase("Anorganik")).count();
        int daurUlang = (int) sampahList.stream().filter(s -> s.getKategori().equalsIgnoreCase("DaurUlang")).count();
        view.tampilkanStatistik(totalSampah, totalVolume, organik, anorganik, daurUlang);
    }

    public void tampilkanDetailKategori(String kategori) {
        if (!isValidKategori(kategori)) {
            view.tampilkanPesan("Kategori tidak valid. Gunakan Organik, Anorganik, atau Daur Ulang.");
            return;
        }
        view.tampilkanDetailKategori(kategori, sampahList);
    }

    private boolean isValidKategori(String kategori) {
        return kategori.equalsIgnoreCase("Organik") ||
                kategori.equalsIgnoreCase("Anorganik") ||
                kategori.equalsIgnoreCase("DaurUlang");
    }

    private void simpanDataKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Sampah sampah : sampahList) {
                writer.write(sampah.getNama() + "," +
                        sampah.getKategori() + "," +
                        sampah.getVolume());
                writer.newLine();
            }
        } catch (IOException e) {
            view.tampilkanPesan("Gagal menyimpan data ke file: " + e.getMessage());
        }
    }

    private void muatDataDariFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    String nama = data[0];
                    String kategori = data[1];
                    double volume = Double.parseDouble(data[2]);
                    sampahList.add(new Sampah(nama, kategori, volume));
                }
            }
        } catch (FileNotFoundException e) {
            view.tampilkanPesan("File data tidak ditemukan. Memulai dengan data kosong.");
        } catch (IOException e) {
            view.tampilkanPesan("Gagal membaca data dari file: " + e.getMessage());
        }
    }
}
