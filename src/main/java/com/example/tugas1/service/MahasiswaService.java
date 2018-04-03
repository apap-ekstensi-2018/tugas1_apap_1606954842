package com.example.tugas1.service;

import com.example.tugas1.model.MahasiswaModel;

import java.util.List;

public interface MahasiswaService {
    List<MahasiswaModel> selectAllMahasiswa();

    List<MahasiswaModel> selectAllMahasiswaByProdiFakultasUniv(int id_pordi, int id_fakultas, int id_univ);

    MahasiswaModel selectMahasiswa(String npm);

    MahasiswaModel selectMahasiswaPalingMuda(int tahun, int id_prodi);

    MahasiswaModel selectMahasiswaPalingTua(int tahun, int id_prodi);

    String checkNPM(String npm);

    void updateStatusMahasiswa(String status, int id);

    void addMahasiswa(MahasiswaModel mahasiswa);

    void updateMahasiswa(MahasiswaModel mahasiswa);

    int getTotalMahasiswaLulus(int tahun, int id_prodi);

    int getTotalMahasiswa(int tahun, int id_prodi);
}
