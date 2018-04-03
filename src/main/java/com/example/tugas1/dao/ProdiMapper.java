package com.example.tugas1.dao;

import com.example.tugas1.model.FakultasModel;
import com.example.tugas1.model.ProgramStudiModel;
import com.example.tugas1.model.UniversitasModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ProdiMapper {
    @Select("select p.* from program_studi p")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_prodi", column = "kode_prodi"),
            @Result(property = "nama_prodi", column = "nama_prodi"),
            @Result(property = "fakultas", column = "id_fakultas", one = @One(select = "selectFakultas"))
    })
    List<ProgramStudiModel> selectAllProdi();

    @Select("select p.* from program_studi p where p.id = #{id_prodi}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_prodi", column = "kode_prodi"),
            @Result(property = "nama_prodi", column = "nama_prodi"),
            @Result(property = "fakultas", column = "id_fakultas", one = @One(select = "selectFakultas"))
    })
    ProgramStudiModel selectProdi(@Param("id_prodi") int id_prodi);

    @Select("select f.* from fakultas f where f.id = #{id_fakultas}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_fakultas", column = "kode_fakultas"),
            @Result(property = "nama_fakultas", column = "nama_fakultas"),
            @Result(property = "univ", column = "id_univ", one = @One(select = "selectUniv"))
    })
    FakultasModel selectFakultas(@Param("id_fakultas") int id_fakultas);

    @Select("select u.* from universitas u where u.id = #{id_univ}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_univ", column = "kode_univ"),
            @Result(property = "nama_univ", column = "nama_univ")
    })
    UniversitasModel selectUniv(@Param("id_univ") int id_univ);
}
