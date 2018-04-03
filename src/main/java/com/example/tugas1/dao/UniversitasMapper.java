package com.example.tugas1.dao;

import com.example.tugas1.model.UniversitasModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UniversitasMapper {
    @Select("select u.* from universitas u")
    List<UniversitasModel> selectAllUniv();
}
