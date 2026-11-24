package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import report.ReportService;

/**
 * Input rentang tanggal untuk laporan pembayaran - Pertemuan 13 (tahap 8).
 * Validasi (kosong, format, awal > akhir) dilakukan di sini sebelum
 * parameter dikirim ke ReportService.
 */
public class LaporanPembayaranFrame extends JFrame {

    private final Color hijauTua = new Color(0, 77, 64);
    private final Color hijauSedang = new Color(0, 127, 95);
    private final Color hijauMuda = new Color(230, 244, 236);
    private final Color latar = new Color(241, 248, 245);

    private JTextField txtTanggalAwal;
    private JTextField txtTanggalAkhir;
    private JButton btnCetak;

    public LaporanPembayaranFrame() {
        setTitle("Laporan Pembayaran - SIKURSUS");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        buatTampilan();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnCetak);
    }

    private void buatTampilan() {
        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        JLabel judul = new JLabel("LAPORAN PEMBAYARAN", JLabel.CENTER);
        judul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        judul.setForeground(Color.WHITE);
        judul.setOpaque(true);
        judul.setBackground(hijauTua);
        judul.setBorder(new EmptyBorder(16, 10, 16, 10));
        utama.add(judul, BorderLayout.NORTH);

        // default: dari tanggal 1 bulan ini sampai hari ini
        LocalDate hariIni = LocalDate.now();
        txtTanggalAwal = buatTextField(hariIni.withDayOfMonth(1).toString());
        txtTanggalAkhir = buatTextField(hariIni.toString());

        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(latar);
        form.setBorder(new EmptyBorder(20, 28, 6, 28));
        tambahBaris(form, 0, "Tanggal Awal", txtTanggalAwal);
        tambahBaris(form, 1, "Tanggal Akhir", txtTanggalAkhir);

        JLabel petunjuk = new JLabel("Format tanggal: yyyy-MM-dd, contoh 2026-11-01");
        petunjuk.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        petunjuk.setForeground(hijauSedang);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(8, 0, 0, 0);
        form.add(petunjuk, gbc);
        utama.add(form, BorderLayout.CENTER);

        btnCetak = new JButton("Cetak Laporan");
        btnCetak.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnCetak.setBackground(hijauSedang);
        btnCetak.setForeground(Color.WHITE);
        btnCetak.setOpaque(true);
        btnCetak.setBorderPainted(false);
        btnCetak.setFocusPainted(false);
        btnCetak.setPreferredSize(new Dimension(150, 36));
        btnCetak.addActionListener(this::btnCetakActionPerformed);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        panelTombol.setBackground(latar);
        panelTombol.setBorder(new EmptyBorder(0, 0, 8, 0));
        panelTombol.add(btnCetak);
        utama.add(panelTombol, BorderLayout.SOUTH);

        setContentPane(utama);
    }

    private JTextField buatTextField(String isi) {
        JTextField f = new JTextField(isi);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBackground(hijauMuda);
        f.setForeground(hijauTua);
        f.setPreferredSize(new Dimension(200, 30));
        f.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(hijauSedang),
                new EmptyBorder(2, 8, 2, 8)));
        return f;
    }

    private void tambahBaris(JPanel panel, int baris, String teks,
            java.awt.Component komponen) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = baris;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(6, 0, 6, 12);

        JLabel label = new JLabel(teks);
        label.setFont(new Font("Segoe UI", Font.BOLD, 13));
        label.setForeground(hijauTua);
        gbc.gridx = 0;
        panel.add(label, gbc);

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(6, 0, 6, 0);
        panel.add(komponen, gbc);
    }

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {
        String awalTeks = txtTanggalAwal.getText().trim();
        String akhirTeks = txtTanggalAkhir.getText().trim();

        // 1. tidak boleh kosong
        if (awalTeks.isEmpty() || akhirTeks.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Tanggal awal dan tanggal akhir wajib diisi.",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. format harus yyyy-MM-dd
        java.sql.Date awal;
        java.sql.Date akhir;
        try {
            awal = java.sql.Date.valueOf(awalTeks);
            akhir = java.sql.Date.valueOf(akhirTeks);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    "Format tanggal harus yyyy-MM-dd, contoh 2026-11-01.",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. awal tidak boleh setelah akhir
        if (awal.after(akhir)) {
            JOptionPane.showMessageDialog(this,
                    "Tanggal awal tidak boleh setelah tanggal akhir.",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        ReportService.cetakPembayaran(awal, akhir);
    }
}
