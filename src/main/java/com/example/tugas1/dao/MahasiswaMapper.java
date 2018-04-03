package com.example.tugas1.dao;

import com.example.tugas1.model.FakultasModel;
import com.example.tugas1.model.MahasiswaModel;
import com.example.tugas1.model.ProgramStudiModel;
import com.example.tugas1.model.UniversitasModel;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MahasiswaMapper {
    @Select("select m.* from mahasiswa m")
    List<MahasiswaModel> selectAllMahasiswa();

    @Select("select m.* from mahasiswa m, program_studi p, fakultas f, universitas u WHERE m.id_prodi=p.id and p.id_fakultas=f.id and f.id_univ=u.id and p.id = #{id_prodi} and f.id = #{id_fakultas} and u.id = #{id_univ}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk"),
            @Result(property = "prodi", column = "id_prodi", one = @One(select = "selectProdi"))
    })
    List<MahasiswaModel> selectAllMahasiswaByProdiFakultasUniv(@Param("id_prodi") int id_prodi, @Param("id_fakultas") int id_fakultas, @Param("id_univ") int id_univ);

    @Update("update mahasiswa set status = #{status} where id = #{id}")
    void updateStatusMahasiswa(@Param("status") String status, @Param("id") int id);

    @Select("select m.* from mahasiswa m where m.tahun_masuk = #{tahun} and m.id_prodi = #{id_prodi} order by tanggal_lahir asc limit 1")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk"),
            @Result(property = "prodi", column = "id_prodi", one = @One(select = "selectProdi"))
    })
    MahasiswaModel selectMahasiswaPalingMuda(@Param("tahun") int tahun, @Param("id_prodi") int id_prodi);

    @Select("select m.* from mahasiswa m where m.tahun_masuk = #{tahun} and m.id_prodi = #{id_prodi} order by tanggal_lahir desc limit 1")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk"),
            @Result(property = "prodi", column = "id_prodi", one = @One(select = "selectProdi"))
    })
    MahasiswaModel selectMahasiswaPalingTua(@Param("tahun") int tahun, @Param("id_prodi") int id_prodi);

    @Select("select m.npm from mahasiswa m where npm like CONCAT(#{npm},'%') order by npm desc limit 1")
    String checkNPM(@Param("npm") String npm);

    @Select("select count(*) as total from mahasiswa m where m.tahun_masuk = #{tahun} and m.id_prodi = #{id_prodi}")
    int getTotalMahasiswa(@Param("tahun") int tahun, @Param("id_prodi") int id_prodi);

    @Select("select count(*) as total from mahasiswa m where m.tahun_masuk = #{tahun} and m.id_prodi = #{id_prodi} and m.status = 'Lulus'")
    int getTotalMahasiswaLulus(@Param("tahun") int tahun, @Param("id_prodi") int id_prodi);

    @Select("select m.* from mahasiswa m where m.npm = #{npm}")
    @Results( value = {
            @Result(property = "id", column = "id"),
            @Result(property = "npm", column = "npm"),
            @Result(property = "nama", column = "nama"),
            @Result(property = "tempat_lahir", column = "tempat_lahir"),
            @Result(property = "tanggal_lahir", column = "tanggal_lahir"),
            @Result(property = "jenis_kelamin", column = "jenis_kelamin"),
            @Result(property = "agama", column = "agama"),
            @Result(property = "golongan_darah", column = "golongan_darah"),
            @Result(property = "status", column = "status"),
            @Result(property = "tahun_masuk", column = "tahun_masuk"),
            @Result(property = "jalur_masuk", column = "jalur_masuk"),
            @Result(property = "prodi", column = "id_prodi", one = @One(select = "selectProdi"))
    })
    MahasiswaModel selectMahasiswa(@Param("npm") String npm);

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

    @Insert("INSERT INTO mahasiswa VALUES (#{id}, #{npm}, #{nama}, #{tempat_lahir}, #{tanggal_lahir}, #{jenis_kelamin}, #{agama}, #{golongan_darah}, #{status}, #{tahun_masuk}, #{jalur_masuk}, #{prodi.id})")
    void addMahasiswa (MahasiswaModel mahasiswa);

    @Update("UPDATE mahasiswa set npm = #{npm}, nama = #{nama}, tempat_lahir = #{tempat_lahir}, tanggal_lahir = #{tanggal_lahir}, jenis_kelamin = #{jenis_kelamin}, agama = #{agama}, golongan_darah = #{golongan_darah}, status = #{status}, tahun_masuk = #{tahun_masuk}, jalur_masuk = #{jalur_masuk}, id_prodi = #{prodi.id} where id = #{id}")
    void updateMahasiswa(MahasiswaModel mahasiswa);
}
