package com.example.tugas1.controller;

import com.example.tugas1.model.FakultasModel;
import com.example.tugas1.model.MahasiswaModel;
import com.example.tugas1.model.ProgramStudiModel;
import com.example.tugas1.model.UniversitasModel;
import com.example.tugas1.service.FakultasService;
import com.example.tugas1.service.MahasiswaService;
import com.example.tugas1.service.ProdiService;
import com.example.tugas1.service.UniversitasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class MahasiswaController {
    @Autowired
    MahasiswaService mahasiswaDAO;
    @Autowired
    ProdiService prodiDAO;
    @Autowired
    UniversitasService universitasDAO;
    @Autowired
    FakultasService fakultasDAO;

    @RequestMapping("/")
    public String index(Model model){
        model.addAttribute("page","home");
        model.addAttribute("title","SI Kemahasiswaan");
        return "index";
    }

    @RequestMapping("/mahasiswa")
    public String mahasiswa(Model model, @RequestParam(value = "npm", required = false) String npm){
        if(npm.isEmpty() == false){
            if(npm.length() == 12){
                MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
                if (mahasiswa != null){
                    model.addAttribute("page","home");
                    model.addAttribute("mahasiswa", mahasiswa);
                    model.addAttribute("title", "View Mahasiswa");
                    return "view-mahasiswa";
                }else{
                    model.addAttribute("page","home");
                    model.addAttribute("npm", npm);
                    model.addAttribute("kondisi", "Kombinasi NPM salah");
                    model.addAttribute("title", "Mahasiswa Not Found");
                    return "not-found";
                }
            }else{
                model.addAttribute("page","home");
                model.addAttribute("npm", npm);
                model.addAttribute("kondisi", "NPM tidak lengkap");
                model.addAttribute("title", "Mahasiswa Not Found");
                return "not-found";
            }
        }else{
            model.addAttribute("page","home");
            model.addAttribute("npm", npm);
            model.addAttribute("kondisi", "NPM tidak ada");
            model.addAttribute("title", "Mahasiswa Not Found");
            return "not-found";
        }

    }

    @RequestMapping("/mahasiswa/tambah")
    public String mahasiswaTambah(Model model){
        model.addAttribute("page","tambah");
        List<ProgramStudiModel> tempProdi = prodiDAO.selectAllProdi();
        model.addAttribute("prodis", tempProdi);
        model.addAttribute("title","Tambah Mahasiswa");
        return "tambah-mahasiswa";
    }

    private String createNPM(int id_prodi, String jalur_masuk, int tahun_masuk){
        ProgramStudiModel prodi = prodiDAO.selectProdi(id_prodi);
        String kodeJalurMasuk;
        if (jalur_masuk.equals("Undangan Olimpiade")){
            kodeJalurMasuk = "53";
        }else if(jalur_masuk.equals("Undangan Reguler/SNMPTN")){
            kodeJalurMasuk = "54";
        }else if(jalur_masuk.equals("Undangan Paralel/PPKB")){
            kodeJalurMasuk = "55";
        }else if(jalur_masuk.equals("Ujian Tulis Bersama/SBMPTN")){
            kodeJalurMasuk = "57";
        }else{
            kodeJalurMasuk = "62";
        }
        String npm = String.valueOf(tahun_masuk).substring(2)+prodi.getFakultas().getUniv().getKode_univ()+prodi.getKode_prodi()+kodeJalurMasuk;
        String tempNpm = mahasiswaDAO.checkNPM(npm);
        if(tempNpm == null){
            npm = npm + "001";
        }else{
            String tempCount = String.valueOf((Integer.valueOf(tempNpm.substring(9))+1));
            if(tempCount.length() == 1){
                tempCount = "00"+tempCount;
            }else if(tempCount.length() == 2){
                tempCount = "0"+tempCount;
            }
            npm = npm + tempCount;
        }
        return npm;
    }

    @RequestMapping(value = "/mahasiswa/tambah", method = RequestMethod.POST)
    public String mahasiswaTambah(Model model,
                                  @ModelAttribute("nama") String nama,
                                  @ModelAttribute("tempat_lahir") String tempat_lahir,
                                  @ModelAttribute("tanggal_lahir") String tanggal_lahir,
                                  @ModelAttribute("jenis_kelamin") int jenis_kelamin,
                                  @ModelAttribute("agama") String agama,
                                  @ModelAttribute("golongan_darah") String golongan_darah,
                                  @ModelAttribute("tahun_masuk") int tahun_masuk,
                                  @ModelAttribute("jalur_masuk") String jalur_masuk,
                                  @ModelAttribute("id_prodi") int id_prodi){
        ProgramStudiModel prodi = prodiDAO.selectProdi(id_prodi);
        String npm = this.createNPM(id_prodi, jalur_masuk, tahun_masuk);
        MahasiswaModel mahasiswa = new MahasiswaModel(null, npm, nama,tempat_lahir,tanggal_lahir,jenis_kelamin,agama,golongan_darah,"Aktif",tahun_masuk,jalur_masuk,prodi);
        mahasiswaDAO.addMahasiswa(mahasiswa);
        model.addAttribute("page","tambah");
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("title","Berhasil Tambah");
        return "success-tambah";
    }

    @RequestMapping("/mahasiswa/lulus/{status}/{id}")
    public String ubahStatus(Model model, @PathVariable(value = "status") String status, @PathVariable(value = "id") int id){
        mahasiswaDAO.updateStatusMahasiswa(status, id);
        model.addAttribute("title","Berhasil Tambah");
        return "success-update-status";
    }

    @RequestMapping("/mahasiswa/ubah/{npm}")
    public String ubahMahasiswa(Model model, @PathVariable(value = "npm") String npm){
        if(npm.isEmpty() == false){
            if(npm.length() == 12){
                MahasiswaModel mahasiswa = mahasiswaDAO.selectMahasiswa(npm);
                if (mahasiswa != null){
                    List<ProgramStudiModel> tempProdi = prodiDAO.selectAllProdi();
                    model.addAttribute("prodis", tempProdi);
                    model.addAttribute("mahasiswa", mahasiswa);
                    model.addAttribute("idprodi", mahasiswa.getProdi().getId());
                    model.addAttribute("title", "Ubah Mahasiswa");
                    return "ubah-mahasiswa";
                }else{
                    model.addAttribute("npm", npm);
                    model.addAttribute("kondisi", "Kombinasi NPM salah");
                    model.addAttribute("title", "Mahasiswa Not Found");
                    return "not-found";
                }
            }else{
                model.addAttribute("npm", npm);
                model.addAttribute("kondisi", "NPM tidak lengkap");
                model.addAttribute("title", "Mahasiswa Not Found");
                return "not-found";
            }
        }else{
            model.addAttribute("npm", npm);
            model.addAttribute("kondisi", "NPM tidak ada");
            model.addAttribute("title", "Mahasiswa Not Found");
            return "not-found";
        }
    }

    @RequestMapping(value = "/mahasiswa/ubah/submit/{npm}", method = RequestMethod.POST)
    public String ubahMahasiswa(Model model, @PathVariable(value = "npm") String npm, @ModelAttribute MahasiswaModel mahasiswa){
        String npmTemp = this.createNPM(mahasiswa.getProdi().getId(), mahasiswa.getJalur_masuk(), mahasiswa.getTahun_masuk());
        mahasiswa.setNpm(npmTemp);
        mahasiswaDAO.updateMahasiswa(mahasiswa);
        model.addAttribute("mahasiswa", mahasiswa);
        model.addAttribute("title", "Ubah Mahasiswa");
        return "success-ubah";
    }

    @RequestMapping("/kelulusan/")
    public String kelulusan(Model model){
        List<ProgramStudiModel> tempProdi = prodiDAO.selectAllProdi();
        model.addAttribute("page","kelulusan");
        model.addAttribute("prodis", tempProdi);
        model.addAttribute("title", "Kelulusan Mahasiswa");
        return "form-kelulusan";
    }

    @RequestMapping("/kelulusan")
    public String kelulusan(Model model, @RequestParam(value = "tahun", required = true) Integer tahun, @RequestParam(value = "id_prodi", required = true) Integer id_prodi){
        int totalMahasiswaLulus = mahasiswaDAO.getTotalMahasiswaLulus(tahun, id_prodi);
        if(totalMahasiswaLulus != 0){
            int totalMahasiswa = mahasiswaDAO.getTotalMahasiswa(tahun, id_prodi);
            double hasil = (Double.valueOf(totalMahasiswaLulus) / Double.valueOf(totalMahasiswa)) * 100.0;
            String persentase = String.format("%.2f", hasil);
            ProgramStudiModel prodi = prodiDAO.selectProdi(id_prodi);
            model.addAttribute("page","kelulusan");
            model.addAttribute("tahun", tahun);
            model.addAttribute("prodi", prodi);
            model.addAttribute("totalMahasiswaLulus", totalMahasiswaLulus);
            model.addAttribute("persentase", persentase);
            model.addAttribute("totalMahasiswa", totalMahasiswa);
            model.addAttribute("title", "Kelulusan Mahasiswa");
            return "kelulusan";
        }else{
            model.addAttribute("page","kelulusan");
            model.addAttribute("kondisi", "Data tidak ditemukan");
            model.addAttribute("title", "Not Found");
            return "not-found-other";
        }
    }

    @RequestMapping("/usia/")
    public String usia(Model model){
        List<ProgramStudiModel> tempProdi = prodiDAO.selectAllProdi();
        model.addAttribute("page","usia");
        model.addAttribute("prodis", tempProdi);
        model.addAttribute("title", "Usia Mahasiswa");
        return "form-usia";
    }

    @RequestMapping("/usia")
    public String usia(Model model, @RequestParam(value = "tahun", required = true) Integer tahun, @RequestParam(value = "id_prodi", required = true) Integer id_prodi){
        MahasiswaModel mahasiswaMuda = mahasiswaDAO.selectMahasiswaPalingMuda(tahun, id_prodi);
        if(mahasiswaMuda != null){
            MahasiswaModel mahasiswaTua = mahasiswaDAO.selectMahasiswaPalingTua(tahun, id_prodi);
            model.addAttribute("page","usia");
            model.addAttribute("mahasiswaMuda", mahasiswaMuda);
            model.addAttribute("mahasiswaTua", mahasiswaTua);
            model.addAttribute("title", "Usia Mahasiswa");
            return "form-usia-result";
        }else{
            model.addAttribute("page","usia");
            model.addAttribute("kondisi", "Data tidak ditemukan");
            model.addAttribute("title", "Not Found");
            return "not-found-other";
        }
    }

    @RequestMapping("/mahasiswa/cari")
    public String cari(Model model){
        List<ProgramStudiModel> prodis = prodiDAO.selectAllProdi();
        List<UniversitasModel> univs = universitasDAO.selectAllUniv();
        List<FakultasModel> listFakultas = fakultasDAO.selectAllFakultas();
        model.addAttribute("page","cari");
        model.addAttribute("page","cari");
        model.addAttribute("prodis", prodis);
        model.addAttribute("univs", univs);
        model.addAttribute("listFakultas", listFakultas);
        model.addAttribute("title", "Cari Mahasiswa");
        return "cari-mahasiswa";
    }

    @RequestMapping("/mahasiswa/cari/submit")
    public String cariSubmit(Model model, @RequestParam("id_univ") int id_univ, @RequestParam("id_fakultas") int id_fakultas, @RequestParam("id_prodi") int id_prodi ){
        List<MahasiswaModel> listMahasiswa = mahasiswaDAO.selectAllMahasiswaByProdiFakultasUniv(id_prodi, id_fakultas, id_univ);
        model.addAttribute("page","cari");
        model.addAttribute("listMahasiswa", listMahasiswa);
        model.addAttribute("sampleMahasiswa", listMahasiswa.get(0));
        model.addAttribute("title", "Cari Mahasiswa");
        return "cari-mahasiswa-result";
    }
}
