package model;

public class Peserta extends Orang {
    private String nim;
    private String prodi;
    private String jenisKelamin; // "L" atau "P" (sesuai ENUM di MySQL)
    private String email;

    // Constructor LAMA (Pertemuan 5-6) - dipertahankan agar
    // DemoInheritance, DemoPolymorphism, dan UjiValidasi tetap jalan.
    public Peserta(int id, String nama, String noHp, String nim, String prodi) {
        super(id, nama, noHp);
        setNim(nim);
        setProdi(prodi);
        this.email = "";
    }

    // Constructor BARU (Pertemuan 9) - urutan sesuai tabel `peserta`
    // dan PesertaDAO. id = 0 karena id_peserta dibuat AUTO_INCREMENT oleh MySQL.
    public Peserta(String nim, String nama, String jenisKelamin,
            String prodi, String noHp, String email) {
        super(0, nama, noHp);
        setNim(nim);
        setJenisKelamin(jenisKelamin);
        setProdi(prodi);
        setEmail(email);
    }

    // Nama sesuai panduan; menggunakan id milik Orang.
    public int getIdPeserta() {
        return getId();
    }

    public void setIdPeserta(int idPeserta) {
        setId(idPeserta);
    }

    public String getNim() {
        return nim;
    }

    public final void setNim(String nim) {
        if (nim == null || nim.trim().isEmpty()) {
            throw new IllegalArgumentException("NIM wajib diisi");
        }
        this.nim = nim.trim();
    }

    public String getProdi() {
        return prodi;
    }

    public final void setProdi(String prodi) {
        if (prodi == null || prodi.trim().isEmpty()) {
            throw new IllegalArgumentException("Prodi wajib diisi");
        }
        this.prodi = prodi.trim();
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public final void setJenisKelamin(String jenisKelamin) {
        String nilai = jenisKelamin == null ? "" : jenisKelamin.trim();
        if (!nilai.equals("L") && !nilai.equals("P")) {
            throw new IllegalArgumentException("Jenis kelamin harus L atau P");
        }
        this.jenisKelamin = nilai;
    }

    public String getEmail() {
        return email;
    }

    public final void setEmail(String email) {
        String nilai = email == null ? "" : email.trim();
        if (!nilai.isEmpty() && !nilai.matches("[^@\\s]+@[^@\\s]+\\.[^@\\s]+")) {
            throw new IllegalArgumentException("Format email tidak valid");
        }
        this.email = nilai;
    }

    @Override
    public String getInfo() {
        return "PESERTA | " + super.getInfo()
                + " | NIM: " + getNim()
                + " | Prodi: " + getProdi();
    }
}
