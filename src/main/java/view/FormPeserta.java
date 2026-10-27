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
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import model.Peserta;

/**
 * Form Peserta - Pertemuan 9 (JDBC + DAO + PreparedStatement).
 * Form hanya mengurus input/validasi/feedback. Akses database ada di PesertaDAO.
 */
public class FormPeserta extends JFrame {

    private final Color hijauTua = new Color(0, 77, 64);
    private final Color hijauSedang = new Color(0, 127, 95);
    private final Color hijauMuda = new Color(230, 244, 236);
    private final Color latar = new Color(241, 248, 245);

    private JTextField txtNim;
    private JTextField txtNama;
    private JRadioButton rbLaki;
    private JRadioButton rbPerempuan;
    private ButtonGroup buttonGroupJenisKelamin;
    private JTextField txtProdi;
    private JTextField txtNoHp;
    private JTextField txtEmail;
    private JButton btnSimpan;
    private JButton btnReset;

    public FormPeserta() {
        setTitle("Form Peserta - SIKURSUS");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buatTampilan();
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

        utama.add(form, BorderLayout.CENTER);

        // TOMBOL
        btnSimpan = buatTombol("Simpan", hijauSedang);
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);

        btnReset = buatTombol("Reset", hijauTua);
        btnReset.addActionListener(e -> clearForm());

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        panelTombol.setBackground(latar);
        panelTombol.setBorder(new EmptyBorder(0, 0, 14, 0));
        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);
        utama.add(panelTombol, BorderLayout.SOUTH);

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

    private void clearForm() {
        txtNim.setText("");
        txtNama.setText("");
        buttonGroupJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtNim.requestFocus();
    }

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {
        // 1. Ambil input
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();

        String jenisKelamin = "";
        if (rbLaki.isSelected()) {
            jenisKelamin = "L";
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = "P";
        }

        // 2. Validasi wajib isi (sebelum menyentuh database)
        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()
                || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "NIM, nama, jenis kelamin, dan prodi wajib diisi.",
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 3. Bentuk object Peserta (model memvalidasi format No. HP & email)
        Peserta peserta;
        try {
            peserta = new Peserta(nim, nama, jenisKelamin, prodi, noHp, email);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    ex.getMessage(),
                    "Validasi",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        // 4. Panggil DAO
        PesertaDAO dao = new PesertaDAO();
        try {
            boolean berhasil = dao.insert(peserta);

            if (berhasil) {
                JOptionPane.showMessageDialog(
                        this, "Data peserta berhasil disimpan."
                );
                clearForm();
            } else {
                JOptionPane.showMessageDialog(
                        this,
                        "Data tidak tersimpan.",
                        "Informasi",
                        JOptionPane.WARNING_MESSAGE
                );
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // NIM duplikat -> ditolak oleh UNIQUE di MySQL
            JOptionPane.showMessageDialog(
                    this,
                    "NIM " + nim + " sudah terdaftar.\n\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(
                    this,
                    "Gagal menyimpan data:\n" + e.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
            e.printStackTrace();
        }
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
