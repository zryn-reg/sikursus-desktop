<<<<<<< HEAD
package view;
import model.Kursus;
import javax.swing.JOptionPane;


public class FormPendaftaranAwal extends javax.swing.JFrame {
    public FormPendaftaranAwal() {
    initComponents();
    aturDesain();
}
    public static void main(String args[]) {
    java.awt.EventQueue.invokeLater(new Runnable() {
        public void run() {
            new FormPendaftaranAwal().setVisible(true);
        }
    });
}

private void aturDesain() {
    java.awt.Color hijauTua = new java.awt.Color(0, 77, 64);
    java.awt.Color hijauMuda = new java.awt.Color(230, 244, 236);
    java.awt.Color putih = java.awt.Color.WHITE;
    java.awt.Color borderHijau = new java.awt.Color(0, 127, 95);

    // Judul agar tidak terpotong
    jLabel1.setText("FORM PENDAFTARAN");
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 17));
    jLabel1.setForeground(hijauTua);
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setPreferredSize(new java.awt.Dimension(500, 50));
    jLabel1.setMinimumSize(new java.awt.Dimension(500, 50));
    jLabel1.setMaximumSize(new java.awt.Dimension(500, 50));

    // Kotak input
    txtNama.setBackground(hijauMuda);
    txtBiaya.setBackground(hijauMuda);
    txtJumlah.setBackground(hijauMuda);
    txtDiskon.setBackground(hijauMuda);
    cmbKursus.setBackground(hijauMuda);

    // Garis tepi hijau
    txtNama.setBorder(javax.swing.BorderFactory.createLineBorder(borderHijau));
    txtBiaya.setBorder(javax.swing.BorderFactory.createLineBorder(borderHijau));
    txtJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(borderHijau));
    txtDiskon.setBorder(javax.swing.BorderFactory.createLineBorder(borderHijau));

    // Tombol Proses
    btnProses.setBackground(borderHijau);
    btnProses.setForeground(putih);
    btnProses.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
    btnProses.setFocusPainted(false);

    // Tombol Reset
    btnReset.setBackground(hijauTua);
    btnReset.setForeground(putih);
    btnReset.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12));
    btnReset.setFocusPainted(false);

    // Kotak hasil
    txtHasil.setBackground(putih);
    txtHasil.setForeground(hijauTua);
    txtHasil.setBorder(javax.swing.BorderFactory.createLineBorder(borderHijau));

    // Latar belakang form
    getContentPane().setBackground(new java.awt.Color(241, 248, 245));

    // Posisi form di tengah layar
    setLocationRelativeTo(null);
}



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtJumlah = new javax.swing.JTextField();
        txtBiaya = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cmbKursus = new javax.swing.JComboBox<>();
        btnProses = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHasil = new javax.swing.JTextArea();
        txtNama = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDiskon = new javax.swing.JTextField();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(241, 248, 245));

        jLabel1.setFont(new java.awt.Font("Tw Cen MT", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(37, 52, 63));
        jLabel1.setText("Form Pendaftaran");
        jLabel1.setMaximumSize(new java.awt.Dimension(100, 34));
        jLabel1.setPreferredSize(new java.awt.Dimension(260, 54));

        jLabel2.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(37, 52, 63));
        jLabel2.setText("Nama");

        jLabel3.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(37, 52, 63));
        jLabel3.setText("Biaya");

        jLabel4.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(37, 52, 63));
        jLabel4.setText("Jumlah");

        txtJumlah.setBackground(new java.awt.Color(191, 201, 209));
        txtJumlah.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtJumlah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtJumlahActionPerformed(evt);
            }
        });

        txtBiaya.setBackground(new java.awt.Color(191, 201, 209));
        txtBiaya.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtBiaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBiayaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(37, 52, 63));
        jLabel5.setText("Kursus");

        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Data Science", "UI/UX" }));
        cmbKursus.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnProses.setBackground(new java.awt.Color(255, 155, 81));
        btnProses.setForeground(new java.awt.Color(37, 52, 63));
        btnProses.setText("Proses");
        btnProses.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnProses.setBorderPainted(false);
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnReset.setBorderPainted(false);
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtHasil.setColumns(20);
        txtHasil.setRows(5);
        txtHasil.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jScrollPane1.setViewportView(txtHasil);

        txtNama.setBackground(new java.awt.Color(191, 201, 209));
        txtNama.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tw Cen MT", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(37, 52, 63));
        jLabel6.setText("DIskon");

        txtDiskon.setBackground(new java.awt.Color(191, 201, 209));
        txtDiskon.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtDiskon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiskonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(88, 88, 88)
                        .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(90, 90, 90)
                        .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(80, 80, 80)
                        .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(82, 82, 82)
                        .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(188, 188, 188)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(93, 93, 93))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtDiskon, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(141, 141, 141))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtJumlahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtJumlahActionPerformed

    }//GEN-LAST:event_txtJumlahActionPerformed

    private void txtBiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBiayaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBiayaActionPerformed

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code he
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
 
     String nama = txtNama.getText().trim();

    // Validasi nama
    if (nama.isEmpty() || nama.equalsIgnoreCase("Nama")) {
        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Nama peserta harus diisi!",
                "Peringatan",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtNama.requestFocus();
        return;
    }

    int biaya;
    int jumlah;
    double diskon;

    // =========================
    // VALIDASI BIAYA
    // =========================
    try {
        biaya = Integer.parseInt(txtBiaya.getText().trim());

    } catch (NumberFormatException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Biaya Kursus harus berupa angka!\n"
                + "Contoh: 150000",
                "Biaya Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtBiaya.requestFocus();
        return;
    }

    // Biaya tidak boleh negatif
    if (biaya < 0) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Biaya Kursus tidak boleh kurang dari 0!",
                "Biaya Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtBiaya.requestFocus();
        return;
    }

    // =========================
    // VALIDASI JUMLAH
    // =========================
    try {
        jumlah = Integer.parseInt(txtJumlah.getText().trim());

    } catch (NumberFormatException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Jumlah harus berupa angka!\n"
                + "Contoh: 2",
                "Jumlah Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtJumlah.requestFocus();
        return;
    }

    // Jumlah harus lebih dari 0
    if (jumlah <= 0) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Jumlah harus lebih dari 0!",
                "Jumlah Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtJumlah.requestFocus();
        return;
    }

    // =========================
    // VALIDASI DISKON
    // =========================
    try {
        diskon = Double.parseDouble(txtDiskon.getText().trim());

    } catch (NumberFormatException e) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Diskon harus berupa angka!\n"
                + "Contoh: 10",
                "Diskon Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtDiskon.requestFocus();
        return;
    }

    // Diskon harus 0 sampai 100
    if (diskon < 0 || diskon > 100) {

        javax.swing.JOptionPane.showMessageDialog(
                this,
                "Diskon harus antara 0 sampai 100%!",
                "Diskon Tidak Valid",
                javax.swing.JOptionPane.WARNING_MESSAGE
        );

        txtDiskon.requestFocus();
        return;
    }

    // =========================
    // MENGAMBIL KURSUS
    // =========================
    String kursus = cmbKursus.getSelectedItem().toString();

    // =========================
    // PERHITUNGAN
    // =========================

    // Total harga sebelum diskon
    double totalSebelumDiskon = biaya * jumlah;

    // Menghitung nilai diskon
    double nilaiDiskon = totalSebelumDiskon * diskon / 100;

    // Total setelah diskon
    double totalHarga = totalSebelumDiskon - nilaiDiskon;

    // =========================
    // MENAMPILKAN HASIL
    // =========================
    txtHasil.setText(
            "INFORMASI PENDAFTARAN\n"
            + "==============================\n"
            + "Nama Peserta : " + nama + "\n"
            + "Biaya Kursus : Rp "
            + String.format("%,.0f", (double) biaya) + "\n"
            + "Jumlah       : " + jumlah + "\n"
            + "Pilih Kursus : " + kursus + "\n"
            + "Diskon       : "
            + String.format("%.0f", diskon) + "%\n"
            + "Nilai Diskon : Rp "
            + String.format("%,.0f", nilaiDiskon) + "\n"
            + "Total Harga  : Rp "
            + String.format("%,.0f", totalHarga)
    );
    }//GEN-LAST:event_btnProsesActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
   txtNama.setText("");
        txtBiaya.setText("");
        txtJumlah.setText("");

        // Mengembalikan pilihan kursus ke pilihan pertama
        cmbKursus.setSelectedIndex(0);

        // Menghapus hasil
        txtHasil.setText("");

        // Cursor kembali ke Nama
        txtNama.requestFocus();     // TODO add your handling code here:
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtDiskonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiskonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiskonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextField txtDiskon;
    private javax.swing.JTextArea txtHasil;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
=======
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.swing.JOptionPane;

/**
 *
 * @author USER
 */
public class FormPendaftaranAwal extends javax.swing.JFrame {

    /**
     * Creates new form FormPendaftaranAwal
     */
    public FormPendaftaranAwal() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtBiaya = new javax.swing.JTextField();
        txtJumlah = new javax.swing.JTextField();
        cmbKursus = new javax.swing.JComboBox<>();
        btnProses = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtHasil = new javax.swing.JTextArea();
        txtNama = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(109, 8, 8));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel1.setFont(new java.awt.Font("Consolas", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(48, 56, 65));
        jLabel1.setText("Form Pendaftaran");

        txtBiaya.setBackground(new java.awt.Color(234, 228, 213));
        txtBiaya.setText("BIAYA");

        txtJumlah.setBackground(new java.awt.Color(234, 228, 213));
        txtJumlah.setText("Jumlah");

        cmbKursus.setBackground(new java.awt.Color(234, 228, 213));
        cmbKursus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Java", "Data Science", "UI/UX" }));
        cmbKursus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbKursusActionPerformed(evt);
            }
        });

        btnProses.setBackground(new java.awt.Color(182, 176, 159));
        btnProses.setText("Proses");
        btnProses.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        btnProses.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnProses.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        btnProses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProsesActionPerformed(evt);
            }
        });

        btnReset.setBackground(new java.awt.Color(182, 176, 159));
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        txtHasil.setBackground(new java.awt.Color(234, 228, 213));
        txtHasil.setColumns(20);
        txtHasil.setRows(5);
        jScrollPane1.setViewportView(txtHasil);

        txtNama.setBackground(new java.awt.Color(234, 228, 213));
        txtNama.setText("NAMA");
        txtNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER, false)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(txtBiaya, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(82, 82, 82)
                                .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(txtJumlah, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(91, 91, 91)
                        .addComponent(btnReset))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 80, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(5, 5, 5)
                .addComponent(txtNama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtBiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtJumlah, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cmbKursus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 166, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnProses, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnReset))
                        .addGap(74, 74, 74))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamaActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        String nama = txtNama.getText().trim();
    String kursus = (String) cmbKursus.getSelectedItem();

    if (nama.isEmpty()) {
        JOptionPane.showMessageDialog(this,
                "Nama tidak boleh kosong!",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE);
        return;
    }

    try {
        double biaya = Double.parseDouble(txtBiaya.getText().trim());
        int jumlah = Integer.parseInt(txtJumlah.getText().trim());

        double total = hitungTotal(biaya, jumlah);

        String hasil = "Nama       : " + nama + "\n"
                     + "Kursus     : " + kursus + "\n"
                     + "Biaya      : " + biaya + "\n"
                     + "Jumlah     : " + jumlah + "\n"
                     + "Total Bayar: " + total;

        txtHasil.setText(hasil);

        JOptionPane.showMessageDialog(this,
                "Pendaftaran berhasil diproses!",
                "Sukses",
                JOptionPane.INFORMATION_MESSAGE);

    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this,
                "Biaya dan Jumlah harus berupa angka!",
                "Error Input",
                JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_btnProsesActionPerformed

    private void cmbKursusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbKursusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbKursusActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
         txtNama.setText("");
    txtBiaya.setText("");
    txtJumlah.setText("");
    txtHasil.setText("");
    cmbKursus.setSelectedIndex(0);
    txtNama.requestFocus();
    }//GEN-LAST:event_btnResetActionPerformed
    private double hitungTotal(double biaya, int jumlah) {
    return biaya * jumlah;
}
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPendaftaranAwal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FormPendaftaranAwal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnProses;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cmbKursus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtBiaya;
    private javax.swing.JTextArea txtHasil;
    private javax.swing.JTextField txtJumlah;
    private javax.swing.JTextField txtNama;
    // End of variables declaration//GEN-END:variables
}
>>>>>>> origin/master
