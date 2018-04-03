package com.example.tugas1.service;

import com.example.tugas1.dao.FakultasMapper;
import com.example.tugas1.model.FakultasModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FakultasServiceDatabase implements FakultasService{
    @Autowired
    private FakultasMapper fakultasMapper;

    @Override
    public List<FakultasModel> selectAllFakultas(){
        log.info("Select all fakultas");
        return fakultasMapper.selectAllFakultas();
    }
}
