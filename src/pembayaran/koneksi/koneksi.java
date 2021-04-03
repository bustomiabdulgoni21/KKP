package pembayaran.koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private Connection koneksi;
    public Connection connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Berhasil Koneksi");
          } catch (ClassNotFoundException ex) {
              System.out.println("Gagal Koneksi"+ex);
          }
        String url = "jdbc:mysql://localhost:3306/newkkp";
        try {
            koneksi = DriverManager.getConnection(url, "root","");
            System.out.println("Berhasil Koneksi Database");
        } catch(SQLException ex) {
            System.out.println("Gagal Koneksi Database"+ex);
        }return koneksi;
}
}
