package com.example.tugas1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FakultasModel {
    private int id;
    private String kode_fakultas;
    private String nama_fakultas;
    private UniversitasModel univ;
}
