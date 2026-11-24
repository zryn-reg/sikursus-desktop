package view;

import dao.PendaftaranDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.SQLException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.PendaftaranInfo;
import report.ReportService;

/**
 * Daftar pendaftaran - Pertemuan 13.
 * Kolom pertama JTable (indeks 0) = id_pendaftaran, dipakai sebagai
 * parameter report. Form tidak berisi SQL maupun kode Jasper.
 */
public class PendaftaranFrame extends JFrame {

    private final Color hijauTua = new Color(0, 77, 64);
    private final Color hijauSedang = new Color(0, 127, 95);
    private final Color hijauMuda = new Color(230, 244, 236);
    private final Color latar = new Color(241, 248, 245);

    private final PendaftaranDAO dao = new PendaftaranDAO();

    private JTextField txtCari;
    private JButton btnCari;
    private JButton btnReset;
    private JButton btnCetakTerpilih;
    private JTable tblPendaftaran;

    public PendaftaranFrame() {
        setTitle("Data Pendaftaran - SIKURSUS");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        buatTampilan();
        configureTable();
        loadTable();
        pack();
        setLocationRelativeTo(null);
    }

    private void buatTampilan() {
        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        JLabel judul = new JLabel("DATA PENDAFTARAN", JLabel.CENTER);
        judul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        judul.setForeground(Color.WHITE);
        judul.setOpaque(true);
        judul.setBackground(hijauTua);
        judul.setBorder(new EmptyBorder(16, 10, 16, 10));
        utama.add(judul, BorderLayout.NORTH);

        // BARIS PENCARIAN
        JLabel lblCari = new JLabel("Cari NIM / Nama / Kelas");
        lblCari.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCari.setForeground(hijauTua);

        txtCari = new JTextField();
        txtCari.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtCari.setBackground(hijauMuda);
        txtCari.setForeground(hijauTua);
        txtCari.setPreferredSize(new Dimension(240, 30));
        txtCari.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(hijauSedang),
                new EmptyBorder(2, 8, 2, 8)));
        txtCari.addActionListener(this::btnCariActionPerformed);

        btnCari = buatTombol("Cari", hijauSedang);
        btnCari.addActionListener(this::btnCariActionPerformed);

        btnReset = buatTombol("Reset", hijauTua);
        btnReset.addActionListener(this::btnResetActionPerformed);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 8));
        panelCari.setBackground(latar);
        panelCari.setBorder(new EmptyBorder(8, 12, 0, 12));
        panelCari.add(lblCari);
        panelCari.add(txtCari);
        panelCari.add(btnCari);
        panelCari.add(btnReset);

        // TABEL
        tblPendaftaran = new JTable();
        tblPendaftaran.setRowHeight(24);
        tblPendaftaran.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblPendaftaran.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPendaftaran.getTableHeader().setBackground(hijauTua);
        tblPendaftaran.getTableHeader().setForeground(Color.WHITE);
        tblPendaftaran.setSelectionBackground(hijauSedang);
        tblPendaftaran.setSelectionForeground(Color.WHITE);

        JScrollPane scroll = new JScrollPane(tblPendaftaran);
        scroll.setPreferredSize(new Dimension(720, 260));
        scroll.setBorder(BorderFactory.createLineBorder(hijauSedang));

        JPanel tengah = new JPanel(new BorderLayout());
        tengah.setBackground(latar);
        tengah.setBorder(new EmptyBorder(0, 12, 0, 12));
        tengah.add(panelCari, BorderLayout.NORTH);
        tengah.add(scroll, BorderLayout.CENTER);
        utama.add(tengah, BorderLayout.CENTER);

        // TOMBOL BAWAH
        btnCetakTerpilih = buatTombol("Cetak Terpilih", hijauSedang);
        btnCetakTerpilih.setPreferredSize(new Dimension(150, 36));
        btnCetakTerpilih.addActionListener(this::btnCetakTerpilihActionPerformed);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 10));
        panelTombol.setBackground(latar);
        panelTombol.add(btnCetakTerpilih);
        utama.add(panelTombol, BorderLayout.SOUTH);

        setContentPane(utama);
    }

    private JButton buatTombol(String teks, Color warna) {
        JButton b = new JButton(teks);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(warna);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(100, 32));
        return b;
    }

    // ===================== JTABLE =====================

    private void configureTable() {
        // Indeks kolom: 0 ID, 1 NIM, 2 Nama, 3 Kelas, 4 Tanggal, 5 Status
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID Pendaftaran", "NIM", "Nama Peserta",
                    "Kelas", "Tanggal Daftar", "Status"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblPendaftaran.setModel(model);
        tblPendaftaran.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tblPendaftaran.getColumnModel().getColumn(0).setPreferredWidth(100);
    }

    private void fillTable(List<PendaftaranInfo> data) {
        DefaultTableModel model = (DefaultTableModel) tblPendaftaran.getModel();
        model.setRowCount(0);
        for (PendaftaranInfo p : data) {
            model.addRow(new Object[]{
                p.getIdPendaftaran(),
                p.getNim(),
                p.getNamaPeserta(),
                p.getNamaKelas(),
                p.getTanggalDaftar(),
                p.getStatus()
            });
        }
    }

    private void loadTable() {
        try {
            fillTable(dao.findAll());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Gagal memuat data pendaftaran: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===================== EVENT =====================

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        String keyword = txtCari.getText().trim();
        try {
            if (keyword.isEmpty()) {
                loadTable();
            } else {
                fillTable(dao.search(keyword));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Pencarian gagal: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        txtCari.setText("");
        loadTable();
    }

    private void btnCetakTerpilihActionPerformed(java.awt.event.ActionEvent evt) {
        int row = tblPendaftaran.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this,
                    "Pilih satu data pendaftaran terlebih dahulu.");
            return;
        }

        // kolom pertama (indeks 0) = id_pendaftaran
        int idPendaftaran = Integer.parseInt(
                tblPendaftaran.getValueAt(row, 0).toString()
        );

        // uji tahap 6C: ID di Output harus sama dengan baris yang dipilih.
        // Baris ini boleh dihapus setelah bukti screenshot diambil.
        System.out.println("ID terpilih: " + idPendaftaran);

        ReportService.cetakPendaftaran(idPendaftaran);
    }
}
