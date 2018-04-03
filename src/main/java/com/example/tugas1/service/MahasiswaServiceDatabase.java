package com.example.tugas1.service;

import com.example.tugas1.dao.MahasiswaMapper;
import com.example.tugas1.model.MahasiswaModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MahasiswaServiceDatabase implements MahasiswaService{
    @Autowired
    private MahasiswaMapper mahasiswaMapper;

    @Override
    public List<MahasiswaModel> selectAllMahasiswa(){
        log.info("Select all mahasiswa");
        return mahasiswaMapper.selectAllMahasiswa();
    }

    @Override
    public List<MahasiswaModel> selectAllMahasiswaByProdiFakultasUniv(int id_pordi, int id_fakultas, int id_univ){
        log.info("Select all mahasiswa by prodi, fakultas, univ");
        return mahasiswaMapper.selectAllMahasiswaByProdiFakultasUniv(id_pordi, id_fakultas, id_univ);
    }

    @Override
    public MahasiswaModel selectMahasiswa(String npm){
        log.info("Select mahasiswa with npm {}", npm);
        return mahasiswaMapper.selectMahasiswa(npm);
    }

    @Override
    public void updateStatusMahasiswa(String status, int id){
        log.info("Update status mahasiswa");
        mahasiswaMapper.updateStatusMahasiswa(status, id);
    }

    @Override
    public MahasiswaModel selectMahasiswaPalingMuda(int tahun, int id_prodi){
        log.info("Select mahasiswa paling muda");
        return  mahasiswaMapper.selectMahasiswaPalingMuda(tahun, id_prodi);
    }

    @Override
    public MahasiswaModel selectMahasiswaPalingTua(int tahun, int id_prodi){
        log.info("Select mahasiswa paling tua");
        return  mahasiswaMapper.selectMahasiswaPalingTua(tahun, id_prodi);
    }

    @Override
    public String checkNPM(String npm){
        log.info("Check NPM with npm {}", npm);
        return mahasiswaMapper.checkNPM(npm);
    }

    @Override
    public void addMahasiswa(MahasiswaModel mahasiswa){
        log.info("Add mahasiswa "+mahasiswa.getNama());
        mahasiswaMapper.addMahasiswa(mahasiswa);
    }

    @Override
    public void updateMahasiswa(MahasiswaModel mahasiswa){
        log.info("Update mahasiswa with npm "+mahasiswa.getNpm());
        mahasiswaMapper.updateMahasiswa(mahasiswa);
    }

    @Override
    public int getTotalMahasiswaLulus(int tahun, int id_prodi){
        log.info("Get total mahasiswa lulus by tahun "+tahun+" id prodi "+id_prodi);
        return mahasiswaMapper.getTotalMahasiswaLulus(tahun, id_prodi);
    }

    @Override
    public int getTotalMahasiswa(int tahun, int id_prodi){
        log.info("Get total mahasiswa by tahun "+tahun+" id prodi "+id_prodi);
        return mahasiswaMapper.getTotalMahasiswa(tahun, id_prodi);
    }
}
