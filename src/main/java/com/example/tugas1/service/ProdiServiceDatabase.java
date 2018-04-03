package com.example.tugas1.service;

import com.example.tugas1.dao.ProdiMapper;
import com.example.tugas1.model.ProgramStudiModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProdiServiceDatabase implements ProdiService{
    @Autowired
    private ProdiMapper prodiMapper;

    @Override
    public List<ProgramStudiModel> selectAllProdi(){
        log.info("Select all prodi");
        return prodiMapper.selectAllProdi();
    }

    @Override
    public ProgramStudiModel selectProdi(int id){
        log.info("Select prodi with id "+id);
        return prodiMapper.selectProdi(id);
    }
}
