package dao;

import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.PendaftaranInfo;

public class PendaftaranDAO {

    private static final String SELECT_DASAR
            = "SELECT p.id_pendaftaran, ps.nim, ps.nama AS nama_peserta, "
            + "kls.nama_kelas, p.tanggal_daftar, p.status AS status_pendaftaran "
            + "FROM pendaftaran p "
            + "INNER JOIN peserta ps ON p.id_peserta = ps.id_peserta "
            + "INNER JOIN kelas kls ON p.id_kelas = kls.id_kelas ";

    private PendaftaranInfo mapRow(ResultSet rs) throws SQLException {
        return new PendaftaranInfo(
                rs.getInt("id_pendaftaran"),
                rs.getString("nim"),
                rs.getString("nama_peserta"),
                rs.getString("nama_kelas"),
                rs.getDate("tanggal_daftar"),
                rs.getString("status_pendaftaran")
        );
    }

    public List<PendaftaranInfo> findAll() throws SQLException {
        List<PendaftaranInfo> data = new ArrayList<>();
        String sql = SELECT_DASAR + "ORDER BY p.id_pendaftaran";

        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                data.add(mapRow(rs));
            }
        }
        return data;
    }

    public List<PendaftaranInfo> search(String keyword) throws SQLException {
        List<PendaftaranInfo> data = new ArrayList<>();
        String sql = SELECT_DASAR
                + "WHERE ps.nim LIKE ? OR ps.nama LIKE ? OR kls.nama_kelas LIKE ? "
                + "ORDER BY p.id_pendaftaran";
        String pattern = "%" + keyword.trim() + "%";

        try (Connection conn = Database.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    data.add(mapRow(rs));
                }
            }
        }
        return data;
    }
}
