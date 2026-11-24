package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import model.User;
import report.ReportService;
import util.UserSession;
import util.WindowManager;

/**
 * Dashboard utama - Pertemuan 11.
 * Menu: Master, Transaksi, Laporan, Keluar. Hak akses menu mengikuti role user.
 * Form Peserta memakai class FormPeserta milik project ini.
 */
public class DashboardFrame extends JFrame {

    private final Color hijauTua = new Color(0, 77, 64);
    private final Color hijauSedang = new Color(0, 127, 95);
    private final Color latar = new Color(241, 248, 245);

    private JLabel lblUser;
    private JLabel lblRole;

    private JMenu menuMaster;
    private JMenu menuTransaksi;
    private JMenu menuLaporan;
    private JMenu menuKeluar;

    private JMenuItem miPeserta;
    private JMenuItem miInstruktur;
    private JMenuItem miKursus;
    private JMenuItem miKelas;
    private JMenuItem miPendaftaran;
    private JMenuItem miPembayaran;
    private JMenuItem miLaporanPeserta;
    private JMenuItem miLaporanPendaftaran;
    private JMenuItem miLaporanPembayaran;
    private JMenuItem miLogout;
    private JMenuItem miTutup;

    private JPopupMenu popupDashboard;

    public DashboardFrame() {
        setTitle("Dashboard - SIKURSUS Desktop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buatTampilan();
        buatMenu();
        pasangIcon();
        setPreferredSize(new Dimension(820, 520));
        pack();
        setLocationRelativeTo(null);
        applySession();
    }

    // ===================== TAMPILAN =====================

    private void buatTampilan() {
        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        // HEADER
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(hijauTua);
        header.setBorder(new EmptyBorder(14, 20, 14, 20));

        JLabel judul = new JLabel("SIKURSUS Desktop");
        judul.setFont(new Font("Segoe UI", Font.BOLD, 22));
        judul.setForeground(Color.WHITE);

        JPanel panelStatus = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        panelStatus.setOpaque(false);
        lblUser = new JLabel("-");
        lblRole = new JLabel("-");
        for (JLabel l : new JLabel[]{lblUser, lblRole}) {
            l.setFont(new Font("Segoe UI", Font.BOLD, 13));
            l.setForeground(Color.WHITE);
        }
        JLabel t1 = new JLabel("User:");
        JLabel t2 = new JLabel("| Role:");
        for (JLabel l : new JLabel[]{t1, t2}) {
            l.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            l.setForeground(new Color(200, 230, 220));
        }
        panelStatus.add(t1);
        panelStatus.add(lblUser);
        panelStatus.add(t2);
        panelStatus.add(lblRole);

        header.add(judul, BorderLayout.WEST);
        header.add(panelStatus, BorderLayout.EAST);
        utama.add(header, BorderLayout.NORTH);

        // AREA TENGAH
        JPanel tengah = new JPanel(new BorderLayout());
        tengah.setBackground(latar);
        tengah.setBorder(new EmptyBorder(40, 40, 40, 40));

        JLabel sambutan = new JLabel(
                "<html><div style='text-align:center'>"
                + "<span style='font-size:20px;color:#004d40'><b>Selamat datang di SIKURSUS</b></span><br><br>"
                + "<span style='font-size:13px;color:#007f5f'>Sistem Informasi Manajemen Kursus &amp; Pelatihan</span><br><br>"
                + "<span style='font-size:12px;color:#5a7d73'>Pilih menu <b>Master</b>, <b>Transaksi</b>, atau <b>Laporan</b> di bagian atas.<br>"
                + "Klik kanan di area ini untuk menu tambahan.</span>"
                + "</div></html>", JLabel.CENTER);
        tengah.add(sambutan, BorderLayout.CENTER);

        // POPUP MENU (klik kanan)
        popupDashboard = new JPopupMenu();
        JMenuItem miTentang = new JMenuItem("Tentang Aplikasi");
        miTentang.addActionListener(this::miTentangActionPerformed);
        JMenuItem miRefresh = new JMenuItem("Refresh");
        miRefresh.addActionListener(this::miRefreshActionPerformed);
        popupDashboard.add(miTentang);
        popupDashboard.add(miRefresh);
        tengah.setComponentPopupMenu(popupDashboard);
        sambutan.setComponentPopupMenu(popupDashboard);

        utama.add(tengah, BorderLayout.CENTER);
        setContentPane(utama);
    }

    private void buatMenu() {
        JMenuBar bar = new JMenuBar();

        menuMaster = new JMenu("Master");
        miPeserta = new JMenuItem("Peserta");
        miInstruktur = new JMenuItem("Instruktur");
        miKursus = new JMenuItem("Kursus");
        miKelas = new JMenuItem("Kelas");
        menuMaster.add(miPeserta);
        menuMaster.add(miInstruktur);
        menuMaster.add(miKursus);
        menuMaster.add(miKelas);

        menuTransaksi = new JMenu("Transaksi");
        miPendaftaran = new JMenuItem("Pendaftaran");
        miPembayaran = new JMenuItem("Pembayaran");
        menuTransaksi.add(miPendaftaran);
        menuTransaksi.add(miPembayaran);

        menuLaporan = new JMenu("Laporan");
        miLaporanPeserta = new JMenuItem("Laporan Peserta");
        miLaporanPendaftaran = new JMenuItem("Laporan Pendaftaran");
        miLaporanPembayaran = new JMenuItem("Laporan Pembayaran");
        menuLaporan.add(miLaporanPeserta);
        menuLaporan.add(miLaporanPendaftaran);
        menuLaporan.add(miLaporanPembayaran);

        menuKeluar = new JMenu("Keluar");
        miLogout = new JMenuItem("Logout");
        miTutup = new JMenuItem("Tutup Aplikasi");
        menuKeluar.add(miLogout);
        menuKeluar.addSeparator();
        menuKeluar.add(miTutup);

        bar.add(menuMaster);
        bar.add(menuTransaksi);
        bar.add(menuLaporan);
        bar.add(menuKeluar);
        setJMenuBar(bar);

        miPeserta.addActionListener(this::miPesertaActionPerformed);
        miInstruktur.addActionListener(this::miInstrukturActionPerformed);
        miKursus.addActionListener(this::miKursusActionPerformed);
        miKelas.addActionListener(this::miKelasActionPerformed);
        miPendaftaran.addActionListener(this::miPendaftaranActionPerformed);
        miPembayaran.addActionListener(this::miPembayaranActionPerformed);
        miLaporanPeserta.addActionListener(this::miLaporanPesertaActionPerformed);
        miLaporanPendaftaran.addActionListener(this::miPendaftaranActionPerformed);
        miLaporanPembayaran.addActionListener(this::miLaporanPembayaranActionPerformed);
        miLogout.addActionListener(this::miLogoutActionPerformed);
        miTutup.addActionListener(this::miTutupActionPerformed);
    }

    // ===================== ICON (dari resource project) =====================

    private ImageIcon loadIcon(String path) {
        URL url = getClass().getResource(path);
        return url == null ? null : new ImageIcon(url);
    }

    private void pasangIcon() {
        miPeserta.setIcon(loadIcon("/icons/peserta.png"));
        miInstruktur.setIcon(loadIcon("/icons/instruktur.png"));
        miKursus.setIcon(loadIcon("/icons/kursus.png"));
        miKelas.setIcon(loadIcon("/icons/kelas.png"));
    }

    // ===================== SESSION & ROLE =====================

    private void applySession() {
        User user = UserSession.getCurrentUser();

        if (user == null) {
            JOptionPane.showMessageDialog(this,
                    "Session tidak ditemukan. Silakan login ulang.");
            dispose();
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new LoginFrame().setVisible(true);
                }
            });
            return;
        }

        lblUser.setText(user.getUsername());
        lblRole.setText(user.getRole());

        boolean admin = user.isAdmin();
        miInstruktur.setEnabled(admin);
        miKursus.setEnabled(admin);
        miKelas.setEnabled(admin);
        miPeserta.setEnabled(true);
        menuTransaksi.setEnabled(true);
        menuLaporan.setEnabled(true);
    }

    // ===================== EVENT MENU MASTER =====================

    private void miPesertaActionPerformed(java.awt.event.ActionEvent evt) {
        WindowManager.open(FormPeserta.class, FormPeserta::new);
    }

    private void miInstrukturActionPerformed(java.awt.event.ActionEvent evt) {
        WindowManager.open(InstrukturFrame.class, InstrukturFrame::new);
    }

    private void miKursusActionPerformed(java.awt.event.ActionEvent evt) {
        WindowManager.open(KursusFrame.class, KursusFrame::new);
    }

    private void miKelasActionPerformed(java.awt.event.ActionEvent evt) {
        WindowManager.open(KelasFrame.class, KelasFrame::new);
    }

    // ===================== TRANSAKSI & LAPORAN (placeholder) =====================

    // dipakai oleh Transaksi > Pendaftaran dan Laporan > Laporan Pendaftaran
    // (pilih baris di tabel lalu klik Cetak Terpilih)
    private void miPendaftaranActionPerformed(java.awt.event.ActionEvent evt) {
        WindowManager.open(PendaftaranFrame.class, PendaftaranFrame::new);
    }

    private void miPembayaranActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "Modul Pembayaran sedang dikembangkan pada tahap project berikutnya.");
    }

    private void miLaporanPesertaActionPerformed(java.awt.event.ActionEvent evt) {
        ReportService.tampilkanLaporanPeserta();
    }

    private void miLaporanPembayaranActionPerformed(java.awt.event.ActionEvent evt) {
        WindowManager.open(LaporanPembayaranFrame.class, LaporanPembayaranFrame::new);
    }

    // ===================== LOGOUT & TUTUP =====================

    private void miLogoutActionPerformed(java.awt.event.ActionEvent evt) {
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Logout dari aplikasi?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {
            WindowManager.closeAll();   // 1. tutup semua form child
            UserSession.clear();        // 2. hapus session
            dispose();                  // 3. tutup dashboard
            SwingUtilities.invokeLater(new Runnable() { // 4. kembali ke login
                @Override
                public void run() {
                    new LoginFrame().setVisible(true);
                }
            });
        }
    }

    private void miTutupActionPerformed(java.awt.event.ActionEvent evt) {
        int pilihan = JOptionPane.showConfirmDialog(this,
                "Tutup aplikasi SIKURSUS?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION);

        if (pilihan == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // ===================== POPUP MENU =====================

    private void miTentangActionPerformed(java.awt.event.ActionEvent evt) {
        JOptionPane.showMessageDialog(this,
                "SIKURSUS Desktop\nProject PBO - Program Studi Informatika");
    }

    private void miRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        applySession();
        repaint();
    }
}
