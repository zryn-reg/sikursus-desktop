package report;

import config.Database;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Semua pemanggilan laporan ada di sini, supaya form hanya perlu
 * memanggil satu method. Form tidak tahu apa-apa soal JasperReports.
 *
 * Alur: ambil Connection -> muat report -> isi report (parameter + koneksi)
 * -> tampilkan JasperViewer.
 */
public final class ReportService {

    private static final String FOLDER = "/report/";

    private ReportService() {
    }

    // ===================== PERTEMUAN 12 =====================

    /** Laporan seluruh peserta (tanpa parameter). */
    public static void tampilkanLaporanPeserta() {
        tampilkan("laporan_peserta",
                new HashMap<String, Object>(),
                "Laporan Peserta",
                "Belum ada data peserta untuk dicetak.");
    }

    // ===================== PERTEMUAN 13 =====================

    /**
     * Laporan satu pendaftaran. ID berasal dari baris JTable yang dipilih,
     * dikirim ke report lewat Map dengan key P_ID_PENDAFTARAN.
     */
    public static void cetakPendaftaran(int idPendaftaran) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PENDAFTARAN", idPendaftaran); // otomatis jadi Integer

        tampilkan("laporan_pendaftaran",
                params,
                "Laporan Pendaftaran",
                "Data pendaftaran dengan ID " + idPendaftaran
                + " tidak ditemukan.");
    }

    /**
     * Laporan pembayaran pada rentang tanggal.
     * Tipe parameter harus java.sql.Date, sama dengan deklarasi di report.
     */
    public static void cetakPembayaran(java.sql.Date tanggalAwal,
            java.sql.Date tanggalAkhir) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_TANGGAL_AWAL", tanggalAwal);
        params.put("P_TANGGAL_AKHIR", tanggalAkhir);

        tampilkan("laporan_pembayaran",
                params,
                "Laporan Pembayaran",
                "Tidak ada transaksi pembayaran pada rentang tanggal tersebut.");
    }

    // ===================== INTI =====================

    private static void tampilkan(String namaReport,
            Map<String, Object> params,
            String judulDialog,
            String pesanKosong) {

        try (Connection conn = Database.getConnection()) {

            JasperReport report = muatReport(namaReport);
            JasperPrint print = JasperFillManager.fillReport(report, params, conn);

            if (print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, pesanKosong,
                        judulDialog, JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            // false = menutup viewer TIDAK mematikan aplikasi utama
            JasperViewer.viewReport(print, false);

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Gagal membuka laporan: " + e.getMessage(),
                    judulDialog, JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Prioritas 1: file .jasper (hasil Compile Report di Jaspersoft Studio).
     * Prioritas 2: kalau .jasper tidak ada atau versinya tidak cocok,
     * compile .jrxml langsung saat aplikasi berjalan.
     */
    private static JasperReport muatReport(String nama)
            throws JRException, IOException {

        try (InputStream compiled = ReportService.class
                .getResourceAsStream(FOLDER + nama + ".jasper")) {
            if (compiled != null) {
                try {
                    return (JasperReport) JRLoader.loadObject(compiled);
                } catch (JRException ex) {
                    System.err.println("File " + nama + ".jasper tidak bisa dibaca ("
                            + ex.getMessage() + "), mencoba compile dari .jrxml");
                }
            }
        }

        try (InputStream source = ReportService.class
                .getResourceAsStream(FOLDER + nama + ".jrxml")) {
            if (source == null) {
                throw new IllegalStateException("File " + nama
                        + ".jasper / .jrxml tidak ditemukan di src/main/resources"
                        + FOLDER);
            }
            return JasperCompileManager.compileReport(source);
        }
    }
}
