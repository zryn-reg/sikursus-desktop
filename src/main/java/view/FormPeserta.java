package view;

import dao.PesertaDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import model.Peserta;

/**
 * Form Peserta - Pertemuan 10 (CRUD lengkap + JTable + Search).
 * Form hanya mengurus input/validasi/feedback. Akses database ada di PesertaDAO.
 */
public class FormPeserta extends JFrame {

    private final Color hijauTua = new Color(0, 77, 64);
    private final Color hijauSedang = new Color(0, 127, 95);
    private final Color hijauMuda = new Color(230, 244, 236);
    private final Color latar = new Color(241, 248, 245);

    // P10: DAO dan id peserta yang sedang dipilih (-1 = belum ada)
    private final PesertaDAO dao = new PesertaDAO();
    private int selectedId = -1;

    private JTextField txtNim;
    private JTextField txtNama;
    private JRadioButton rbLaki;
    private JRadioButton rbPerempuan;
    private ButtonGroup buttonGroupJenisKelamin;
    private JTextField txtProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;
    private JButton btnSimpan;
    private JButton btnUbah;
    private JButton btnHapus;
    private JButton btnReset;

    // P10: pencarian + tabel
    private JTextField txtCari;
    private JButton btnCari;
    private JTable tblPeserta;

    public FormPeserta() {
        setTitle("Form Peserta - SIKURSUS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buatTampilan();
        configureTable();
        loadTable();
        clearForm();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
    }

    private void buatTampilan() {
        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        // HEADER
        JLabel judul = new JLabel("DATA PESERTA", JLabel.CENTER);
        judul.setFont(new Font("Segoe UI", Font.BOLD, 20));
        judul.setForeground(Color.WHITE);
        judul.setOpaque(true);
        judul.setBackground(hijauTua);
        judul.setBorder(new EmptyBorder(16, 10, 16, 10));
        utama.add(judul, BorderLayout.NORTH);

        // FORM
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(latar);
        form.setBorder(new EmptyBorder(20, 28, 10, 28));

        txtNim = buatTextField();
        txtNama = buatTextField();
        txtProdi = buatTextField();
        txtNoHp = buatTextField();
        txtEmail = buatTextField();

        rbLaki = new JRadioButton("Laki-laki");
        rbPerempuan = new JRadioButton("Perempuan");
        for (JRadioButton rb : new JRadioButton[]{rbLaki, rbPerempuan}) {
            rb.setOpaque(false);
            rb.setFocusPainted(false);
            rb.setForeground(hijauTua);
            rb.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        }
        buttonGroupJenisKelamin = new ButtonGroup();
        buttonGroupJenisKelamin.add(rbLaki);
        buttonGroupJenisKelamin.add(rbPerempuan);

        JPanel panelJk = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        panelJk.setOpaque(false);
        panelJk.add(rbLaki);
        panelJk.add(rbPerempuan);

        tambahBaris(form, 0, "NIM", txtNim);
        tambahBaris(form, 1, "Nama", txtNama);
        tambahBaris(form, 2, "Jenis Kelamin", panelJk);
        tambahBaris(form, 3, "Prodi", txtProdi);
        tambahBaris(form, 4, "No. HP", txtNoHp);
        tambahBaris(form, 5, "Email", txtEmail);

        // TOMBOL AKSI
        btnSimpan = buatTombol("Simpan", hijauSedang);
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnUbah = buatTombol("Ubah", hijauSedang);
        btnUbah.addActionListener(this::btnUbahActionPerformed);

        btnHapus = buatTombol("Hapus", new Color(183, 28, 28));
        btnHapus.addActionListener(this::btnHapusActionPerformed);

        btnReset = buatTombol("Reset", hijauTua);
        btnReset.addActionListener(this::btnResetActionPerformed);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        panelTombol.setBackground(latar);
        panelTombol.add(btnSimpan);
        panelTombol.add(btnUbah);
        panelTombol.add(btnHapus);
        panelTombol.add(btnReset);

        // BAGIAN ATAS = form + tombol
        JPanel atas = new JPanel();
        atas.setLayout(new BoxLayout(atas, BoxLayout.Y_AXIS));
        atas.setBackground(latar);
        atas.add(form);
        atas.add(panelTombol);

        // PENCARIAN
        JLabel lblCari = new JLabel("Cari Nama / NIM");
        lblCari.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblCari.setForeground(hijauTua);

        txtCari = buatTextField();
        txtCari.setPreferredSize(new Dimension(260, 30));
        txtCari.addActionListener(this::btnCariActionPerformed); // tekan Enter = cari

        btnCari = buatTombol("Cari", hijauSedang);
        btnCari.addActionListener(this::btnCariActionPerformed);

        JPanel panelCari = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 6));
        panelCari.setBackground(latar);
        panelCari.setBorder(new EmptyBorder(0, 18, 0, 18));
        panelCari.add(lblCari);
        panelCari.add(txtCari);
        panelCari.add(btnCari);

        // TABEL
        tblPeserta = new JTable();
        tblPeserta.setRowHeight(24);
        tblPeserta.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        tblPeserta.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
        tblPeserta.getTableHeader().setBackground(hijauTua);
        tblPeserta.getTableHeader().setForeground(Color.WHITE);
        tblPeserta.setSelectionBackground(hijauSedang);
        tblPeserta.setSelectionForeground(Color.WHITE);
        // klik baris atau geser pakai panah keyboard -> isi form
        tblPeserta.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                isiFormDariTabel();
            }
        });

        JScrollPane scroll = new JScrollPane(tblPeserta);
        scroll.setPreferredSize(new Dimension(760, 220));
        scroll.setBorder(BorderFactory.createLineBorder(hijauSedang));

        JPanel panelTabel = new JPanel(new BorderLayout());
        panelTabel.setBackground(latar);
        panelTabel.setBorder(new EmptyBorder(0, 18, 18, 18));
        panelTabel.add(panelCari, BorderLayout.NORTH);
        panelTabel.add(scroll, BorderLayout.CENTER);

        utama.add(atas, BorderLayout.CENTER);
        utama.add(panelTabel, BorderLayout.SOUTH);

        setContentPane(utama);
    }

    private void tambahBaris(JPanel panel, int baris, String teksLabel,
            java.awt.Component komponen) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridy = baris;
        gbc.insets = new Insets(6, 0, 6, 12);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel label = new JLabel(teksLabel);
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

    private JTextField buatTextField() {
        JTextField field = new JTextField();
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBackground(hijauMuda);
        field.setForeground(hijauTua);
        field.setPreferredSize(new Dimension(260, 30));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(hijauSedang),
                new EmptyBorder(2, 8, 2, 8)));
        return field;
    }

    private JButton buatTombol(String teks, Color warna) {
        JButton b = new JButton(teks);
        b.setFont(new Font("Segoe UI", Font.BOLD, 13));
        b.setBackground(warna);
        b.setForeground(Color.WHITE);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(110, 36));
        return b;
    }

    // ===================== JTABLE =====================

    private void configureTable() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "NIM", "Nama", "JK", "Prodi", "No. HP", "Email"},
                0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // edit lewat form, bukan lewat sel tabel
            }
        };
        tblPeserta.setModel(model);
        tblPeserta.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    private void fillTable(List<Peserta> data) {
        DefaultTableModel model = (DefaultTableModel) tblPeserta.getModel();
        model.setRowCount(0); // jangan dihapus, biar data tidak dobel
        for (Peserta p : data) {
            model.addRow(new Object[]{
                p.getId(),
                p.getNim(),
                p.getNama(),
                p.getJenisKelamin(),
                p.getProdi(),
                p.getNoHp(),
                p.getEmail()
            });
        }
    }

    private void loadTable() {
        try {
            fillTable(dao.findAll());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal memuat data: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void refreshData() {
        loadTable();
        clearForm();
        txtCari.setText("");
    }

    // anti NullPointerException kalau no_hp / email kosong di database
    private String cell(int row, int col) {
        Object v = tblPeserta.getValueAt(row, col);
        return v == null ? "" : v.toString();
    }

    // dipanggil saat baris tabel dipilih
    private void isiFormDariTabel() {
        int row = tblPeserta.getSelectedRow();
        if (row == -1) {
            return;
        }
        // ambil ID dari kolom ID (kolom 0), BUKAN dari nomor baris
        selectedId = Integer.parseInt(tblPeserta.getValueAt(row, 0).toString());
        txtNim.setText(cell(row, 1));
        txtNama.setText(cell(row, 2));
        String jk = cell(row, 3);
        rbLaki.setSelected("L".equals(jk));
        rbPerempuan.setSelected("P".equals(jk));
        txtProdi.setText(cell(row, 4));
        txtNoHp.setText(cell(row, 5));
        txtEmail.setText(cell(row, 6));

        btnUbah.setEnabled(true);
        btnHapus.setEnabled(true);
    }

    // ===================== FORM =====================

    private void clearForm() {
        selectedId = -1;
        txtNim.setText("");
        txtNama.setText("");
        buttonGroupJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        tblPeserta.clearSelection();
        btnUbah.setEnabled(false);
        btnHapus.setEnabled(false);
        txtNim.requestFocus();
    }

    private String getJenisKelamin() {
        if (rbLaki.isSelected()) {
            return "L";
        }
        if (rbPerempuan.isSelected()) {
            return "P";
        }
        return null;
    }

    private boolean validateForm() {
        if (txtNim.getText().trim().isEmpty()
                || txtNama.getText().trim().isEmpty()
                || txtProdi.getText().trim().isEmpty()
                || getJenisKelamin() == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "NIM, nama, jenis kelamin, dan prodi wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );
            return false;
        }
        return true;
    }

    /**
     * Bentuk object Peserta dari isi form. Model memvalidasi format
     * No. HP & email, jadi bisa melempar IllegalArgumentException.
     */
   private Peserta getFormData() {
    Peserta p = new Peserta(
            txtNim.getText().trim(),
            txtNama.getText().trim(),
            getJenisKelamin(),
            txtProdi.getText().trim(),
            txtNoHp.getText().trim(),
            txtEmail.getText().trim()
    );
    if (selectedId > 0) {   // hanya set id kalau ada baris yang dipilih
        p.setId(selectedId);
    }
    return p;
}

    // ===================== EVENT TOMBOL =====================

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        if (!validateForm()) {
            return;
        }
        Peserta peserta;
        try {
            peserta = getFormData();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (dao.insert(peserta)) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil disimpan.");
                refreshData();
            } else {
                JOptionPane.showMessageDialog(this, "Data tidak tersimpan.",
                        "Informasi", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "NIM " + peserta.getNim() + " sudah terdaftar.\n\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    private void btnUbahActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedId <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data peserta pada tabel terlebih dahulu.");
            return;
        }
        if (!validateForm()) {
            return;
        }
        Peserta peserta;
        try {
            peserta = getFormData();
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(),
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            if (dao.update(peserta)) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil diubah.");
                refreshData();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data tidak ditemukan atau tidak berubah.");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            JOptionPane.showMessageDialog(this,
                    "NIM " + peserta.getNim() + " sudah dipakai peserta lain.\n\n"
                    + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal mengubah data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {
        if (selectedId <= 0) {
            JOptionPane.showMessageDialog(this,
                    "Pilih data peserta yang akan dihapus.");
            return;
        }
        int jawab = JOptionPane.showConfirmDialog(
                this,
                "Yakin menghapus peserta: " + txtNama.getText().trim() + "?",
                "Konfirmasi Hapus",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (jawab != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            if (dao.delete(selectedId)) {
                JOptionPane.showMessageDialog(this, "Data peserta berhasil dihapus.");
                refreshData();
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // peserta sudah dipakai di tabel pendaftaran (foreign key)
            JOptionPane.showMessageDialog(this,
                    "Peserta ini sudah punya data pendaftaran sehingga tidak bisa dihapus.\n\n"
                    + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menghapus data: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {
        String keyword = txtCari.getText().trim();
        try {
            if (keyword.isEmpty()) {
                loadTable();
            } else {
                fillTable(dao.search(keyword));
            }
            clearForm(); // supaya selectedId lama tidak nyangkut
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Pencarian gagal: " + ex.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {
        txtCari.setText("");
        loadTable();
        clearForm();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormPeserta().setVisible(true);
            }
        });
    }
}