package com.example.tugas1.service;

import com.example.tugas1.dao.UniversitasMapper;
import com.example.tugas1.model.UniversitasModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UniversitasServiceDatabase implements UniversitasService{
    @Autowired
    private UniversitasMapper universitasMapper;

    @Override
    public List<UniversitasModel> selectAllUniv(){
        log.info("Select all universitas");
        return universitasMapper.selectAllUniv();
    }
}
