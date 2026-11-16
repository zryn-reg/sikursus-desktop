package view;

import dao.UserDAO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import model.User;
import util.UserSession;

/**
 * Form login - Pertemuan 11. Query login ada di UserDAO, bukan di sini.
 */
public class LoginFrame extends JFrame {

    private final Color hijauTua = new Color(0, 77, 64);
    private final Color hijauSedang = new Color(0, 127, 95);
    private final Color hijauMuda = new Color(230, 244, 236);
    private final Color latar = new Color(241, 248, 245);

    private final UserDAO userDAO = new UserDAO();

    private JTextField txtUsername;
    private JPasswordField pwdPassword;
    private JButton btnLogin;
    private JButton btnKeluar;
    private JLabel lblInfo;

    public LoginFrame() {
        setTitle("Login - SIKURSUS Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buatTampilan();
        pack();
        setResizable(false);
        setLocationRelativeTo(null);
        getRootPane().setDefaultButton(btnLogin); // Enter = Login
    }

    private void buatTampilan() {
        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(hijauTua);
        header.setBorder(new EmptyBorder(18, 10, 18, 10));

        JLabel lblTitle = new JLabel("SIKURSUS Desktop", JLabel.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lblTitle.setForeground(Color.WHITE);

        JLabel lblSub = new JLabel(
                "Sistem Informasi Manajemen Kursus & Pelatihan", JLabel.CENTER);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblSub.setForeground(new Color(200, 230, 220));

        header.add(lblTitle, BorderLayout.CENTER);
        header.add(lblSub, BorderLayout.SOUTH);
        utama.add(header, BorderLayout.NORTH);

        // FORM
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(latar);
        form.setBorder(new EmptyBorder(22, 32, 8, 32));

        txtUsername = buatTextField(new JTextField());
        pwdPassword = (JPasswordField) buatTextField(new JPasswordField());

        tambahBaris(form, 0, "Username", txtUsername);
        tambahBaris(form, 1, "Password", pwdPassword);

        lblInfo = new JLabel("Masukkan akun SIKURSUS", JLabel.CENTER);
        lblInfo.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        lblInfo.setForeground(hijauSedang);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 0, 0, 0);
        form.add(lblInfo, gbc);

        utama.add(form, BorderLayout.CENTER);

        // TOMBOL
        btnLogin = buatTombol("Login", hijauSedang);
        btnLogin.addActionListener(this::btnLoginActionPerformed);

        btnKeluar = buatTombol("Keluar", hijauTua);
        btnKeluar.addActionListener(this::btnKeluarActionPerformed);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 8));
        panelTombol.setBackground(latar);
        panelTombol.setBorder(new EmptyBorder(0, 0, 14, 0));
        panelTombol.add(btnLogin);
        panelTombol.add(btnKeluar);
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

    private JTextField buatTextField(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setBackground(hijauMuda);
        field.setForeground(hijauTua);
        field.setPreferredSize(new Dimension(240, 30));
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

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {
        String username = txtUsername.getText().trim();
        char[] password = pwdPassword.getPassword();

        if (username.isEmpty() || password.length == 0) {
            JOptionPane.showMessageDialog(this,
                    "Username dan password wajib diisi.");
            Arrays.fill(password, '\0');
            return;
        }

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        btnLogin.setEnabled(false);

        try {
            User user = userDAO.authenticate(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(this,
                        "Login gagal. Periksa username, password, atau status user.",
                        "Login Gagal",
                        JOptionPane.WARNING_MESSAGE);
                pwdPassword.setText("");
                pwdPassword.requestFocus();
                return;
            }

            UserSession.setCurrentUser(user);
            dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new DashboardFrame().setVisible(true);
                }
            });

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Tidak dapat mengakses database: " + ex.getMessage(),
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE);
        } finally {
            Arrays.fill(password, '\0');
            btnLogin.setEnabled(true);
            setCursor(Cursor.getDefaultCursor());
        }
    }

    private void btnKeluarActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
}
