package model;

import java.sql.Date;

/**
 * Satu baris data pendaftaran hasil JOIN (peserta + kelas + pendaftaran)
 * untuk ditampilkan di JTable. Hanya dibaca, jadi dibuat immutable.
 */
public class PendaftaranInfo {

    private final int idPendaftaran;
    private final String nim;
    private final String namaPeserta;
    private final String namaKelas;
    private final Date tanggalDaftar;
    private final String status;

    public PendaftaranInfo(int idPendaftaran, String nim, String namaPeserta,
            String namaKelas, Date tanggalDaftar, String status) {
        this.idPendaftaran = idPendaftaran;
        this.nim = nim;
        this.namaPeserta = namaPeserta;
        this.namaKelas = namaKelas;
        this.tanggalDaftar = tanggalDaftar;
        this.status = status;
    }

    public int getIdPendaftaran() {
        return idPendaftaran;
    }

    public String getNim() {
        return nim;
    }

    public String getNamaPeserta() {
        return namaPeserta;
    }

    public String getNamaKelas() {
        return namaKelas;
    }

    public Date getTanggalDaftar() {
        return tanggalDaftar;
    }

    public String getStatus() {
        return status;
    }
}
