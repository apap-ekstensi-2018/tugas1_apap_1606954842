package com.example.tugas1.service;

import com.example.tugas1.model.ProgramStudiModel;

import java.util.List;

public interface ProdiService {
    List<ProgramStudiModel> selectAllProdi();

    ProgramStudiModel selectProdi(int id);
}
