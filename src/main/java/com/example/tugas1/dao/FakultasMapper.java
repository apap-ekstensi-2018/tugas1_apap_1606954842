package com.example.tugas1.dao;

import com.example.tugas1.model.FakultasModel;
import com.example.tugas1.model.UniversitasModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FakultasMapper {
    @Select("select f.* from fakultas f")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_fakultas", column = "kode_fakultas"),
            @Result(property = "nama_fakultas", column = "nama_fakultas"),
            @Result(property = "univ", column = "id_univ", one = @One(select = "selectUniv"))
    })
    List<FakultasModel> selectAllFakultas();

    @Select("select u.* from universitas u where u.id = #{id_univ}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "kode_univ", column = "kode_univ"),
            @Result(property = "nama_univ", column = "nama_univ")
    })
    UniversitasModel selectUniv(@Param("id_univ") int id_univ);
}
