/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaran.tampil;

import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import pembayaran.koneksi.koneksi;
import pembayaran.tampil.DataSiswa;
import pembayaran.tampil.DataSiswa;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author bustomiag
 */
public class ProsesPembayaranSPP extends javax.swing.JInternalFrame {

    //ArrayList name = new ArrayList();
    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    private boolean kondisiCari = false;
    public String tgl1;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    protected void kosong() {
       // cbcari.setSelectedItem(0);
        no_pembayaran.setText("");//1
        tanggal_bayar.setDate(null);//2
        txtnis.setText("");//3
        nama.setText("");//4
        kelas.setText("");//5
//        txtangkatan.setText("");
        //cbjenis_pem.setSelectedItem(0);//6
        cbjenis_pem.setSelectedIndex(0);
        cbbulan.setSelectedIndex(0);
        txtbiaya.setText("");//7
        txtbayar.setText("");
        txtkurang.setText("");//8
        ket.setText("");//9
//        cbcetak.setSelectedIndex(0);
        tabelpembayaran.tableChanged(null);
    }

    protected void datatable(String dtcari) {
        Object[] Baris = {"No Pembayaran", "Tanggal Pembayaran", "NIS", "Nama", "Kelas", "Jenis Pembayaran", "Bulan", "Biaya", "Bayar", "Kurang", "Keterangan"};
        tabmode = new DefaultTableModel(null, Baris);
        tabelpembayaran.setModel(tabmode);
        String kondisi = "";
        if(!dtcari.isEmpty()){
            kondisiCari = true;
            kondisi = " where nama='"+dtcari+"'";
        }
        String sql = "select * from pembayaranspp" +kondisi;
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String no = hasil.getString("no_pemb");
                String tgl = hasil.getString("tgl_pem");
                String Nis = hasil.getString("nis");
                String Nii = hasil.getString("nama");
                String kel = hasil.getString("kelas");
              //  String a = hasil.getString("angkatan");
                String Niss = hasil.getString("jns_pem");
                String bln = hasil.getString("bulan");
                String Nisss = hasil.getString("biaya");
                String z = hasil.getString("bayar");
                String Nissss = hasil.getString("kurang");
                String Niis = hasil.getString("ket");
                String[] data = {no, tgl, Nis, Nii, kel,  Niss, bln, Nisss,z, Nissss, Niis};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }
    protected void carinama1(String dtcari) {
     //protected void carinama() {
        Object[] Baris = {"NIS", "NAMA", "KELAS", "ANGKATAN","TEMPAT LAHIR", "TANGGAL LAHIR", "JENIS KELAMIN", "AGAMA", "ALAMAT"};
        tabmode = new DefaultTableModel(null, Baris);
        datasiswa.setModel(tabmode); 
        String kondisi = "";
        if(!dtcari.isEmpty()){
            kondisiCari = true;
            kondisi = " where nama='"+dtcari+"'";
        }
        String sql = "select * from data_siswa" +kondisi;
        //String sql = "select * from data_siswa";
        try {
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String no = hasil.getString("nis");
                String tgl = hasil.getString("nama");
                String c = hasil.getString("kelas");
                String angkatan = hasil.getString("angkatan");
                String Nis = hasil.getString("tempat_lahir");
                String Nii = hasil.getString("tanggal_lahir");
                String Niss = hasil.getString("jenis_kelamin");
                String Nisss = hasil.getString("agama");
                String Nissss = hasil.getString("alamat");
                String[] data = {no, tgl, c, angkatan, Nis, Nii, Niss, Nisss, Nissss};
                tabmode.addRow(data);
            }
        } catch (Exception e) {
        }
    }

    private void comboboxjenis() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newkkp", "root", "");
            String sql = "select * from jeniss";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String a = hasil.getString("jns_pem");
                cbjenis_pem.addItem(a);
                //String b = hasil.getString("harga");
                //biaya.setText(b);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
/*private void tampilnama() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newkkp", "root", "");
            String sql = "select * from data_siswa";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                String a = hasil.getString("nama");
                cbnama.addItem(a);
                //String b = hasil.getString("harga");
                //biaya.setText(b);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }*/
    public void auto() {
        try {
            String sql = "select max(right(no_pemb,3)) from pembayaranspp";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next()) {
                if (hasil.first() == false) {
                    no_pembayaran.setText("001");
                } else {
                    hasil.last();
                    int code = hasil.getInt(1) + 1;
                    String Nomor = String.valueOf(code);
                    int noLong = Nomor.length();

                    for (int a = 0; a < 3 - noLong; a++) {
                        Nomor = "0" + Nomor;
                    }
                    no_pembayaran.setText("P-" + Nomor);

                }
            }
        } catch (Exception e) {

        }
    }

    
     private void cetakspp(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newkkp","root","");
            String sql = "select * from pembayaranspp";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("nama");
                cbcetak.addItem(a);
            }
        }catch (Exception ex)
        {
        JOptionPane.showMessageDialog(null, ex);    
        }
 }  

    public ProsesPembayaranSPP() {
        initComponents();
        carinama1("");
        d_pembayaran.setLocationRelativeTo(this);
       AutoCompleteDecorator.decorate(cbcetak);
       AutoCompleteDecorator.decorate(cbbulan);
       AutoCompleteDecorator.decorate(cbjenis_pem);
       /*AutoCompleteDecorator.decorate(cbnama);
       tampilnama();*/
        datatable("");
        auto();
        comboboxjenis();
        cetakspp();
        //    comboboxcarinis();
        no_pembayaran.setEnabled(false);
        //databasenama();
    }

 /*   private void filter(String query) {
        TableRowSorter<DefaultTableModel> tr = new TableRowSorter<DefaultTableModel>(tabmode);
        tabelpembayaran.setRowSorter(tr);
        
        if (query != "None"){
            tr.setRowFilter(RowFilter.regexFilter(query));
        }else {
            tabelpembayaran.setRowSorter(tr);
        }
    }*/

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        d_pembayaran = new javax.swing.JDialog();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        txtcari1 = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        datasiswa = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        no_pembayaran = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        txtbiaya = new javax.swing.JTextField();
        txtkurang = new javax.swing.JTextField();
        ket = new javax.swing.JTextField();
        cbjenis_pem = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        kelas = new javax.swing.JTextField();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        nis1 = new javax.swing.JTextField();
        nama1 = new javax.swing.JTextField();
        tempat_lhr = new javax.swing.JTextField();
        cbjns_kel = new javax.swing.JComboBox<>();
        cbagama = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtalamat = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        cbkelas = new javax.swing.JComboBox<>();
        cbangkatan = new javax.swing.JComboBox<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabeldatasiswa = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        bsimpan1 = new javax.swing.JButton();
        bubah1 = new javax.swing.JButton();
        bhapus1 = new javax.swing.JButton();
        bkeluar1 = new javax.swing.JButton();
        cbsearch = new javax.swing.JComboBox<>();
        btnrefresh = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        cbbulan = new javax.swing.JComboBox<>();
        txtnis = new javax.swing.JTextField();
        btncarii = new javax.swing.JButton();
        jLabel19 = new javax.swing.JLabel();
        txtbayar = new javax.swing.JTextField();
        tanggal_bayar = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        brefresh = new javax.swing.JButton();
        btncetak = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        cbcetak = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelpembayaran = new javax.swing.JTable();

        d_pembayaran.setMinimumSize(new java.awt.Dimension(901, 546));

        jPanel9.setBackground(new java.awt.Color(0, 232, 176));

        jPanel10.setBackground(new java.awt.Color(0, 232, 176));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel20.setText("Cari Berdasarkan Nama :");

        txtcari1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcari1KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(txtcari1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel11.setBackground(new java.awt.Color(0, 232, 176));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        datasiswa.setModel(new javax.swing.table.DefaultTableModel(
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
        datasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datasiswaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(datasiswa);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 851, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 445, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout d_pembayaranLayout = new javax.swing.GroupLayout(d_pembayaran.getContentPane());
        d_pembayaran.getContentPane().setLayout(d_pembayaranLayout);
        d_pembayaranLayout.setHorizontalGroup(
            d_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        d_pembayaranLayout.setVerticalGroup(
            d_pembayaranLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(0, 232, 176));

        jPanel1.setBackground(new java.awt.Color(0, 232, 176));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Proses Pembayaran SPP"));

        jPanel2.setBackground(new java.awt.Color(0, 232, 176));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel1.setText("No. Pembayaran");

        jLabel2.setText("Tanggal Pembayaran");

        jLabel3.setText("NIS");

        jLabel4.setText("NAMA");

        jLabel5.setText("JENIS PEMBAYARAN");

        jLabel6.setText("BIAYA");

        jLabel7.setText("KURANG");

        jLabel8.setText("KETERANGAN");

        no_pembayaran.setMaximumSize(new java.awt.Dimension(4, 19));

        nama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaActionPerformed(evt);
            }
        });

        txtbiaya.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtbiayaActionPerformed(evt);
            }
        });
        txtbiaya.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbiayaKeyReleased(evt);
            }
        });

        txtkurang.setMaximumSize(new java.awt.Dimension(4, 19));

        ket.setMaximumSize(new java.awt.Dimension(4, 19));

        cbjenis_pem.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Silahkan Pilih-" }));
        cbjenis_pem.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbjenis_pemItemStateChanged(evt);
            }
        });
        cbjenis_pem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbjenis_pemMouseClicked(evt);
            }
        });
        cbjenis_pem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbjenis_pemActionPerformed(evt);
            }
        });

        jLabel9.setText("KELAS");

        kelas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kelasActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 232, 176));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FreeMono", 1, 18))); // NOI18N

        jPanel6.setBackground(new java.awt.Color(0, 232, 176));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel10.setText("NIS");

        jLabel11.setText("NAMA");

        jLabel12.setText("TEMPAT LAHIR");

        jLabel13.setText("TANGGAL LAHIR");

        jLabel14.setText("JENIS KELAMIN");

        jLabel15.setText("AGAMA");

        jLabel16.setText("ALAMAT");

        nis1.setOpaque(false);

        nama1.setOpaque(false);

        tempat_lhr.setOpaque(false);

        cbjns_kel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Jenis Kelamin-", "Laki-laki", "Perempuan" }));
        cbjns_kel.setOpaque(false);

        cbagama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Agama-", "Islam", "Kristen", "Hindu", "Budha", "Konghucu" }));
        cbagama.setOpaque(false);

        jLabel17.setText("KELAS");

        txtalamat.setOpaque(false);

        jLabel18.setText("ANGKATAN");

        cbkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Kelas-" }));
        cbkelas.setOpaque(false);

        cbangkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Angkatan-" }));
        cbangkatan.setOpaque(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addComponent(jLabel15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbagama, 0, 224, Short.MAX_VALUE)
                            .addComponent(txtalamat)))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11)
                            .addComponent(jLabel13)
                            .addComponent(jLabel14)
                            .addComponent(jLabel12)
                            .addComponent(jLabel17)
                            .addComponent(jLabel18))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama1, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbkelas, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tempat_lhr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbjns_kel, 0, 224, Short.MAX_VALUE)
                            .addComponent(nis1)
                            .addComponent(cbangkatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(nis1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(nama1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(cbkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(cbangkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempat_lhr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addComponent(jLabel13)
                .addGap(27, 27, 27)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(cbjns_kel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbagama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(txtalamat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBackground(new java.awt.Color(0, 232, 176));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tabeldatasiswa.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tabeldatasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "NIS", "NAMA", "KELAS", "TANGGAL LAHIR", "TEMPAT LAHIR", "JENIS KELAMIN", "AGAMA", "ALAMAT"
            }
        ));
        tabeldatasiswa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabeldatasiswaMouseClicked(evt);
            }
        });
        tabeldatasiswa.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tabeldatasiswaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tabeldatasiswa);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel8.setBackground(new java.awt.Color(0, 232, 176));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bsimpan1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Save/Save_16x16.png"))); // NOI18N
        bsimpan1.setText("Simpan");
        bsimpan1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpan1ActionPerformed(evt);
            }
        });

        bubah1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Edit/Edit_16x16.png"))); // NOI18N
        bubah1.setText("Ubah");
        bubah1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubah1ActionPerformed(evt);
            }
        });

        bhapus1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Delete/Delete_16x16.png"))); // NOI18N
        bhapus1.setText("Hapus");
        bhapus1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapus1ActionPerformed(evt);
            }
        });

        bkeluar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Log Out/Log Out_16x16.png"))); // NOI18N
        bkeluar1.setText("Keluar");
        bkeluar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluar1ActionPerformed(evt);
            }
        });

        cbsearch.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cari Berdasarkan Nama" }));
        cbsearch.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbsearchItemStateChanged(evt);
            }
        });
        cbsearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                cbsearchKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbsearchKeyReleased(evt);
            }
        });

        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Refresh/Refresh_16x16.png"))); // NOI18N
        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bsimpan1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bubah1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bhapus1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnrefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bkeluar1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbsearch, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bkeluar1)
                    .addComponent(bhapus1)
                    .addComponent(bubah1)
                    .addComponent(bsimpan1)
                    .addComponent(cbsearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnrefresh))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jLabel21.setText("BULAN");

        cbbulan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Silahkan Pilih Bulan-" }));
        cbbulan.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbbulanItemStateChanged(evt);
            }
        });
        cbbulan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbbulanMouseClicked(evt);
            }
        });
        cbbulan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbulanActionPerformed(evt);
            }
        });

        btncarii.setText("Pilih");
        btncarii.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncariiActionPerformed(evt);
            }
        });

        jLabel19.setText("BAYAR");

        txtbayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtbayarKeyReleased(evt);
            }
        });

        tanggal_bayar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggal_bayarPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(83, 83, 83)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtkurang, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ket, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel21)
                            .addComponent(jLabel9)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel19))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(tanggal_bayar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE)
                                .addComponent(no_pembayaran, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(nama, javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addComponent(txtnis, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btncarii, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbjenis_pem, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbbulan, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbiaya, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 232, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 230, Short.MAX_VALUE)))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(no_pembayaran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel2)
                    .addComponent(tanggal_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtnis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncarii))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(kelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbjenis_pem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbbulan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtbiaya, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtbayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtkurang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ket, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 239, Short.MAX_VALUE)
                    .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 240, Short.MAX_VALUE)))
        );

        jPanel3.setBackground(new java.awt.Color(0, 232, 176));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Save/Save_16x16.png"))); // NOI18N
        btnsimpan.setText("Simpan");
        btnsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnsimpanActionPerformed(evt);
            }
        });

        bubah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Edit/Edit_16x16.png"))); // NOI18N
        bubah.setText("Ubah");
        bubah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bubahActionPerformed(evt);
            }
        });

        bhapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Delete/Delete_16x16.png"))); // NOI18N
        bhapus.setText("Hapus");
        bhapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bhapusActionPerformed(evt);
            }
        });

        bkeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Log Out/Log Out_16x16.png"))); // NOI18N
        bkeluar.setText("Keluar");
        bkeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bkeluarActionPerformed(evt);
            }
        });

        brefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Refresh/Refresh_16x16.png"))); // NOI18N
        brefresh.setText("Refresh");
        brefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brefreshActionPerformed(evt);
            }
        });

        btncetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Print/Print_16x16.png"))); // NOI18N
        btncetak.setText("Cetak");
        btncetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncetakActionPerformed(evt);
            }
        });

        jButton1.setText("Cari");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        cbcetak.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cari Berdasarkan Nama" }));
        cbcetak.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cbcetakKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bubah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bhapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbcetak, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btncetak)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bkeluar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btncetak)
                        .addComponent(bkeluar)
                        .addComponent(jButton1)
                        .addComponent(cbcetak, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnsimpan)
                        .addComponent(bubah)
                        .addComponent(bhapus)
                        .addComponent(brefresh)))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 232, 176));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tabelpembayaran.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelpembayaran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelpembayaranMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelpembayaran);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 826, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnsimpanActionPerformed
        // TODO add your handling code here:
        String sql = "insert into pembayaranspp values (?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, no_pembayaran.getText());
            stat.setString(2, tgl1);
            stat.setString(3, txtnis.getText());
            stat.setString(4, nama.getText());
            stat.setString(5, kelas.getText());
          //  stat.setString(6, txtangkatan.getText());
            stat.setString(6, cbjenis_pem.getSelectedItem().toString());
            stat.setString(7, cbbulan.getSelectedItem().toString());
            stat.setString(8, txtbiaya.getText());
            stat.setString(9, txtbayar.getText());
            stat.setString(10, txtkurang.getText());
            stat.setString(11, ket.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            no_pembayaran.requestFocus();
            datatable("");
            auto();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan" + e);
        }
    }//GEN-LAST:event_btnsimpanActionPerformed

    private void tabelpembayaranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelpembayaranMouseClicked
        // TODO add your handling code here:
        try {
            int row = tabelpembayaran.getSelectedRow();
            String tabel_klik = (tabelpembayaran.getModel().getValueAt(row, 0).toString());
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from pembayaranspp where no_pemb='" + tabel_klik + "'");
            if (sql.next()) {
                String no = sql.getString("no_pemb");
                no_pembayaran.setText(no);
                String b = sql.getString("tgl_pem");
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String) tabmode.getValueAt(row, 1));
                } catch (ParseException ex) {
                    Logger.getLogger(ProsesPembayaranSPP.class.getName()).log(Level.SEVERE, null, ex);
                }
                tanggal_bayar.setDate(date);

                String c = sql.getString("nis");
                txtnis.setText(c);
                String d = sql.getString("nama");
                nama.setText(d);
                String e = sql.getString("kelas");
                kelas.setText(e);
              /*  String f = sql.getString("angkatan");
                txtangkatan.setText(f);*/
                String f = sql.getString("jns_pem");
                cbjenis_pem.setSelectedItem(f);
                String g = sql.getString("bulan");
                cbbulan.setSelectedItem(g);
                String h = sql.getString("biaya");
                txtbiaya.setText(h);
                String i = sql.getString("bayar");
                txtbayar.setText(i);
                String j = sql.getString("kurang");
                txtkurang.setText(j);
                String k = sql.getString("ket");
                ket.setText(k);
            }
        } catch (Exception e) {

        }

        /*  int bar = tabelpembayaran.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();//1
        String b = tabmode.getValueAt(bar, 1).toString();//2
        String c = tabmode.getValueAt(bar, 2).toString();//3
        String d = tabmode.getValueAt(bar, 3).toString();//4
        String e = tabmode.getValueAt(bar, 4).toString();//5       
        String f = tabmode.getValueAt(bar, 5).toString();//6
        String g = tabmode.getValueAt(bar, 6).toString();//7
        String h = tabmode.getValueAt(bar, 7).toString();//8
        String i = tabmode.getValueAt(bar, 8).toString();//9

        no_pembayaran.setText(a);//1
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabmode.getValueAt(bar,1));
        } catch (ParseException ex) {
            Logger.getLogger(ProsesPembayaran.class.getName()).log(Level.SEVERE, null, ex);
        }
        tanggal_bayar.setDate(date);//2
        nis.setText(c);//3
        nama.setText(d);//4
        kelas.setText(e);//5
        cbjenis_pem.setSelectedItem(f);//6
        biaya.setText(g);//7
        total.setText(h);//8
        ket.setText(i);//9*/

    }//GEN-LAST:event_tabelpembayaranMouseClicked

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        // TODO add your handling code here:
        String sql = "update pembayaranspp set tgl_pem=?, nis=?, nama=?, kelas=? , jns_pem=?, bulan=?, biaya=?, bayar=?, kurang=?, ket=? where  no_pemb='" + no_pembayaran.getText() + "'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, tgl1);
            stat.setString(2, txtnis.getText());
            stat.setString(3, nama.getText());
            stat.setString(4, kelas.getText());
           // stat.setString(5, txtangkatan.getText());
            stat.setString(5, cbjenis_pem.getSelectedItem().toString());
            stat.setString(6, cbbulan.getSelectedItem().toString());
            stat.setString(7, txtbiaya.getText());
            stat.setString(8, txtbayar.getText());
            stat.setString(9, txtkurang.getText());
            stat.setString(10, ket.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            no_pembayaran.requestFocus();
            auto();
            datatable("");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah" + e);
        }
    }//GEN-LAST:event_bubahActionPerformed

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
        
    }//GEN-LAST:event_bkeluarActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from pembayaranspp where no_pemb='" + no_pembayaran.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                no_pembayaran.requestFocus();
                datatable("");
                auto();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus");
            }
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void tanggal_bayarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggal_bayarPropertyChange
        // TODO add your handling code here:
        if (tanggal_bayar.getDate() != null) {
            tgl1 = format1.format(tanggal_bayar.getDate());
        }
    }//GEN-LAST:event_tanggal_bayarPropertyChange

    private void brefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brefreshActionPerformed
        // TODO add your handling code here:
        kosong();
        datatable("");
        auto();
       // cetakspp();
       // datatable();
    }//GEN-LAST:event_brefreshActionPerformed

    private void cbjenis_pemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbjenis_pemActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "SELECT * FROM jeniss where jns_pem=? ";
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setString(1, (String) cbjenis_pem.getSelectedItem());
            ResultSet hasil = stat.executeQuery();
            while (hasil.next()) {
                txtbiaya.setText(hasil.getString("harga"));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

    }//GEN-LAST:event_cbjenis_pemActionPerformed

    private void txtbiayaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtbiayaActionPerformed
        // TODO add your handling code here:
        int bayar = Integer.parseInt(txtbiaya.getText());
        if (bayar == 0) {
            ket.setText("Lunas");
        } else {
            ket.setText("Belum Lunas");
        }
    }//GEN-LAST:event_txtbiayaActionPerformed

    private void txtbiayaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbiayaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_txtbiayaKeyReleased

    private void cbjenis_pemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbjenis_pemMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_cbjenis_pemMouseClicked

    private void namaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
    }//GEN-LAST:event_namaActionPerformed

    private void kelasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kelasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kelasActionPerformed

    private void cbjenis_pemItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbjenis_pemItemStateChanged
        // TODO add your handling code here:

        ArrayList<String> array = new ArrayList<>();
        Iterator<String> iter;
        if (cbjenis_pem.getSelectedItem().equals("SPP")) {
            cbbulan.removeAllItems();
            array.add("JANUARI");
            array.add("FEBRUARI");
            array.add("MARET");
            array.add("APRIL");
            array.add("MEI");
            array.add("JUNI");
            array.add("JULI");
            array.add("AGUSTUS");
            array.add("SEPTEMBER");
            array.add("OKTOBER");
            array.add("NOVEMBER");
            array.add("DESEMBER");
            iter = array.iterator();
            while (iter.hasNext()) {
                cbbulan.addItem(iter.next());
            }
        }
    }//GEN-LAST:event_cbjenis_pemItemStateChanged

    private void tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalPropertyChange
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tanggalPropertyChange

    private void tabeldatasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldatasiswaMouseClicked
        // TODO add your handling code here:
        try {
            int row = tabeldatasiswa.getSelectedRow();
            String tabel_klik = (tabeldatasiswa.getModel().getValueAt(row, 0).toString());
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from data_siswa where nis='" + tabel_klik + "'");
            if (sql.next()) {
                String a = sql.getString("nis");//1 a
                txtnis.setText(a);
                String b = sql.getString("nama");//2 b
                nama.setText(b);
                String c = sql.getString("kelas");//3
                cbkelas.setSelectedItem(c);
                String d = sql.getString("angkatan");//4
                cbangkatan.setSelectedItem(d);
                String e = sql.getString("tempat_lahir");//5
                tempat_lhr.setText(e);
                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String) tabmode.getValueAt(row, 5));
                } catch (ParseException ex) {
                    Logger.getLogger(DataSiswa.class.getName()).log(Level.SEVERE, null, ex);
                }
                tanggal_bayar.setDate(date);//6
                String g = sql.getString("jenis_kelamin");//7
                cbjns_kel.setSelectedItem(g);
                String h = sql.getString("agama");//8
                cbagama.setSelectedItem(h);
                String i = sql.getString("alamat");//9
                txtalamat.setText(i);

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Tidak ada data yang dipilih");
        }
        /*

        int bar = tabeldatasiswa.getSelectedRow();
        String a = tabmode.getValueAt(bar, 0).toString();//1 nis
        String b = tabmode.getValueAt(bar, 1).toString();//2 nama
        String c = tabmode.getValueAt(bar, 2).toString();//3 kelas
        String d = tabmode.getValueAt(bar, 3).toString();//4 angkatan
        String e = tabmode.getValueAt(bar, 4).toString();//5 tempat lahir
        String f = tabmode.getValueAt(bar, 5).toString();//6 tanggal lahir
        String g = tabmode.getValueAt(bar, 6).toString();//7 jenis kelamin
        String h = tabmode.getValueAt(bar, 7).toString();//8 agama
        String i = tabmode.getValueAt(bar, 8).toString();//9 alamat

        nis.setText(a);//1
        nama.setText(b);//2
        cbkelas.setSelectedItem(c);//3
        cbangkatan.setSelectedItem(d);
        tempat_lhr.setText(e);//5
        //6
        cbjns_kel.setSelectedItem(g);//7
        cbagama.setSelectedItem(h);//8
        txtalamat.setText(i);//9

         */
    }//GEN-LAST:event_tabeldatasiswaMouseClicked

    private void tabeldatasiswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabeldatasiswaKeyReleased
        // TODO add your handling code here:

    }//GEN-LAST:event_tabeldatasiswaKeyReleased

    private void bsimpan1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpan1ActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_bsimpan1ActionPerformed

    private void bubah1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubah1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bubah1ActionPerformed

    private void bhapus1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapus1ActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from data_siswa where no_pemb='" + no_pembayaran.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                no_pembayaran.requestFocus();
                datatable("");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus");
            }
        }
    }//GEN-LAST:event_bhapus1ActionPerformed

    private void bkeluar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluar1ActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bkeluar1ActionPerformed

    private void cbsearchItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbsearchItemStateChanged
        // TODO add your handling code here:
        // String query = cbsearch.getSelectedItem().toString();
        // filter(query);
    }//GEN-LAST:event_cbsearchItemStateChanged

    private void cbsearchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbsearchKeyTyped
        // TODO add your handling code here:

    }//GEN-LAST:event_cbsearchKeyTyped

    private void cbsearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbsearchKeyReleased

    }//GEN-LAST:event_cbsearchKeyReleased

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        //cbsearch.setSelectedIndex(0);
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void cbbulanItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbbulanItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbulanItemStateChanged

    private void cbbulanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbbulanMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbulanMouseClicked

    private void cbbulanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbbulanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbbulanActionPerformed

    private void btncariiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncariiActionPerformed
        // TODO add your handling code here:
        d_pembayaran.setVisible(true);
    }//GEN-LAST:event_btncariiActionPerformed

    private void datasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datasiswaMouseClicked
        // TODO add your handling code here:
        try {
            int row = datasiswa.getSelectedRow();
            String tabel_klik = (datasiswa.getModel().getValueAt(row, 0).toString());
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from data_siswa where nis='"+tabel_klik+"'");
            if(sql.next()) {
                String a = sql.getString("nis");//1 a
                txtnis.setText(a);
                String b = sql.getString("nama");//2 b
                nama.setText(b);
                String c = sql.getString("kelas");//3
                kelas.setText(c);
                String d = sql.getString("angkatan");//4
                
                String e = sql.getString("tempat_lahir");//5
                
               /* Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabmode.getValueAt(row,5));
                } catch (ParseException ex) {
                    Logger.getLogger(DataSiswa.class.getName()).log(Level.SEVERE, null, ex);
                }*/
                
                
                String g = sql.getString("jenis_kelamin");//7
                
                String h = sql.getString("agama");//8
                
                String i = sql.getString("alamat");//9
            }
        }catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Tidak ada data yang dipilih");
        }
        d_pembayaran.setVisible(false);
    }//GEN-LAST:event_datasiswaMouseClicked

    private void txtbayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtbayarKeyReleased
        // TODO add your handling code here:
        int biaya = Integer.parseInt(txtbiaya.getText());
         int bayar = Integer.parseInt(txtbayar.getText());
         int kurang = biaya-bayar;
         txtkurang.setText(""+kurang);
        if (kurang == 0){
            ket.setText("Lunas");
        } else {
            ket.setText("Belum Lunas");
        }
    }//GEN-LAST:event_txtbayarKeyReleased

    private void btncetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncetakActionPerformed
        // TODO add your handling code here:
        try {
            HashMap data = new HashMap();
            String logo = ("lib/");
            data.put("logo", logo);
            data.put("nama",cbcetak.getSelectedItem().toString());
            String buatLaporan=("./src/pembayaran/laporan/BuktiPembayaranSPP.jasper");
            JasperPrint cetak_laporan = JasperFillManager.fillReport(buatLaporan, data, conn);
            JasperViewer LaporanData = new JasperViewer(cetak_laporan, false);
            LaporanData.setVisible(true);
        }catch (Exception e){
            javax.swing.JOptionPane.showMessageDialog(rootPane, "Gagal Menampilkan Bukti Pembayaran");
        }
    }//GEN-LAST:event_btncetakActionPerformed

    private void txtcari1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcari1KeyReleased
        // TODO add your handling code here:
        String tf = txtcari1.getText();
        try{
            String sql = "SELECT * FROM data_siswa WHERE nama LIKE '%"+tf+"%'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            datasiswa.setModel(DbUtils.resultSetToTableModel(hasil));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }/*finally {
            try {

            }
            catch (Exception e){

            }
        }*/
    }//GEN-LAST:event_txtcari1KeyReleased

    private void cbcetakKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cbcetakKeyReleased
        // TODO add your handling code here:
        String tf = (String) cbcetak.getSelectedItem();
        try{
            String sql = "SELECT * FROM data_siswa WHERE nama LIKE '%"+tf+"%'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            datasiswa.setModel(DbUtils.resultSetToTableModel(hasil));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_cbcetakKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        datatable(cbcetak.getSelectedItem().toString());
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bhapus1;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bkeluar1;
    private javax.swing.JButton brefresh;
    private javax.swing.JButton bsimpan1;
    private javax.swing.JButton btncarii;
    private javax.swing.JButton btncetak;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton btnsimpan;
    private javax.swing.JButton bubah;
    private javax.swing.JButton bubah1;
    private javax.swing.JComboBox<String> cbagama;
    private javax.swing.JComboBox<String> cbangkatan;
    public javax.swing.JComboBox<String> cbbulan;
    private javax.swing.JComboBox<String> cbcetak;
    public javax.swing.JComboBox<String> cbjenis_pem;
    private javax.swing.JComboBox<String> cbjns_kel;
    private javax.swing.JComboBox<String> cbkelas;
    private javax.swing.JComboBox<String> cbsearch;
    private javax.swing.JDialog d_pembayaran;
    private javax.swing.JTable datasiswa;
    private javax.swing.JButton jButton1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextField kelas;
    private javax.swing.JTextField ket;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nama1;
    private javax.swing.JTextField nis1;
    private javax.swing.JTextField no_pembayaran;
    public javax.swing.JTable tabeldatasiswa;
    public javax.swing.JTable tabelpembayaran;
    private com.toedter.calendar.JDateChooser tanggal_bayar;
    private javax.swing.JTextField tempat_lhr;
    private javax.swing.JTextField txtalamat;
    private javax.swing.JTextField txtbayar;
    private javax.swing.JTextField txtbiaya;
    private javax.swing.JTextField txtcari1;
    private javax.swing.JTextField txtkurang;
    private javax.swing.JTextField txtnis;
    // End of variables declaration//GEN-END:variables

    /* private void databasenama(){
     try {
          java.sql.Connection conn = new koneksi().connect();
          java.sql.Statement stat = conn.createStatement();
          String sql = " select * from data_siswa";
           ResultSet hasil = stat.executeQuery(sql);
            while(hasil.next()){
                String a = hasil.getString("nama");
                name.add(a);
            }
            hasil.close();
            stat.close();
            conn.close();
     }   catch (Exception e){
         
     }
    }
    
    public void autocompalet(String txt) {
        String complate = "";
        int start = txt.length();
        int last = txt.length();
        int b;
        for (b=0; b<name.size(); b++){
            if (name.get(b).toString().startsWith(txt)){
                complate = name.get(b).toString();
                last = complate.length();
                break;
            }
            if(last>start){
                txtnis.setText(complate);
                txtnis.setCaretPosition(last);
                txtnis.moveCaretPosition(start);
            }
        }
    }*/
}
