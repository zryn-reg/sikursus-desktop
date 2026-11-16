package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 * Kerangka form untuk modul yang CRUD-nya belum dibuat (Instruktur, Kursus, Kelas).
 * Gunakan pola DAO + JTable dari FormPeserta sebagai template untuk melengkapinya.
 */
public abstract class ModulFrame extends JFrame {

    protected ModulFrame(String judul) {
        Color hijauTua = new Color(0, 77, 64);
        Color latar = new Color(241, 248, 245);

        setTitle(judul + " - SIKURSUS");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        JPanel utama = new JPanel(new BorderLayout());
        utama.setBackground(latar);

        JLabel header = new JLabel(judul.toUpperCase(), JLabel.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setForeground(Color.WHITE);
        header.setOpaque(true);
        header.setBackground(hijauTua);
        header.setBorder(new EmptyBorder(16, 10, 16, 10));
        utama.add(header, BorderLayout.NORTH);

        JLabel isi = new JLabel(
                "Modul ini sedang dikembangkan (CRUD menyusul).", JLabel.CENTER);
        isi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        isi.setForeground(hijauTua);
        utama.add(isi, BorderLayout.CENTER);

        setContentPane(utama);
        setPreferredSize(new Dimension(520, 300));
        pack();
        setLocationRelativeTo(null);
    }
}
