/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pembayaran.tampil;


import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.RowFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import pembayaran.koneksi.koneksi;
import net.proteanit.sql.DbUtils;


/**
 *
 * @author bustomiag
 */
public class DataSiswa extends javax.swing.JInternalFrame {

    private Connection conn = new koneksi().connect();
    private DefaultTableModel tabmode;
    //private boolean kondisiCari = false;
    public String tgl1;
    SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");

    protected void kosong() {
        nis.setText("");
        nama.setText("");
        cbkelas.setSelectedItem(0);
        cbkelas.setSelectedIndex(0);
        cbangkatan.setSelectedItem(0);
        cbangkatan.setSelectedIndex(0);
        tempat_lhr.setText("");
        tanggal.setDate(null);
        cbjns_kel.setSelectedItem(0);
        cbjns_kel.setSelectedIndex(0);
        cbagama.setSelectedItem(0);
        cbagama.setSelectedIndex(0);
        txtalamat.setText("");
    }

    protected void datatable() {
        Object[] Baris = {"NIS", "NAMA", "KELAS", "ANGKATAN","TEMPAT LAHIR", "TANGGAL LAHIR", "JENIS KELAMIN", "AGAMA", "ALAMAT"};
        tabmode = new DefaultTableModel(null, Baris);
        tabeldatasiswa.setModel(tabmode);
        /*String kondisi = "";
        if(!dtcari.isEmpty()){
            kondisiCari = true;
            kondisi = " where nama='"+dtcari+"'";
        }
        String sql = "select * from data_siswa" +kondisi;*/
        String sql = "select * from data_siswa";
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

    public DataSiswa() {
        initComponents();
        combobox();
        datatable();
        combobox1();
        //combobox2();
        //AutoCompleteDecorator.decorate(cbsearch);
        
        //filter();
    }
    
   /* private void filter(String query)
    {
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(tabmode);
        tabeldatasiswa.setRowSorter(tr);
        tr.setRowFilter(RowFilter.regexFilter(query));
    }*/
    
private void combobox(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newkkp","root","");
            String sql = "select * from tabeldataangkatan";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("tahun");
                cbangkatan.addItem(a);
            }
        }catch (Exception ex)
        {
        JOptionPane.showMessageDialog(null, ex);    
        }
    }
 private void combobox1(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newkkp","root","");
            String sql = "select * from tabeldatakelas";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("kelas");
                cbkelas.addItem(a);
            }
        }catch (Exception ex)
        {
        JOptionPane.showMessageDialog(null, ex);    
        }
 }
     /* private void combobox2(){
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/newkkp","root","");
            String sql = "select * from data_siswa";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            while (hasil.next())
            {
                String a = hasil.getString("nama");
                cbsearch.addItem(a);
            }
        }catch (Exception ex)
        {
        JOptionPane.showMessageDialog(null, ex);    
        }
 }  */
     /* private void filter(String query)
    {
        TableRowSorter<DefaultTableModel> tr= new TableRowSorter<DefaultTableModel>(tabmode);
        tabeldatasiswa.setRowSorter(tr);
        
        if (query != "None"){
            tr.setRowFilter(RowFilter.regexFilter(query));
        }else {
            tabeldatasiswa.setRowSorter(tr);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        nis = new javax.swing.JTextField();
        nama = new javax.swing.JTextField();
        tempat_lhr = new javax.swing.JTextField();
        tanggal = new com.toedter.calendar.JDateChooser();
        cbjns_kel = new javax.swing.JComboBox<>();
        cbagama = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        cbkelas = new javax.swing.JComboBox<>();
        cbangkatan = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtalamat = new javax.swing.JEditorPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabeldatasiswa = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        bsimpan = new javax.swing.JButton();
        bubah = new javax.swing.JButton();
        bhapus = new javax.swing.JButton();
        bkeluar = new javax.swing.JButton();
        btnrefresh = new javax.swing.JButton();
        txtcari = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();

        jPanel1.setBackground(new java.awt.Color(0, 232, 176));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Data Siswa", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("FreeMono", 1, 18))); // NOI18N

        jPanel2.setBackground(new java.awt.Color(0, 232, 176));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 1, 12), new java.awt.Color(255, 255, 255))); // NOI18N

        jLabel1.setText("NIS");

        jLabel2.setText("NAMA");

        jLabel3.setText("TEMPAT LAHIR");

        jLabel4.setText("TANGGAL LAHIR");

        jLabel5.setText("JENIS KELAMIN");

        jLabel6.setText("AGAMA");

        jLabel7.setText("ALAMAT");

        nis.setOpaque(false);

        nama.setOpaque(false);

        tempat_lhr.setOpaque(false);

        tanggal.setDateFormatString("dd/MM/yyyy");
        tanggal.setOpaque(false);
        tanggal.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                tanggalPropertyChange(evt);
            }
        });

        cbjns_kel.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Jenis Kelamin-", "LAKI-LAKI", "PEREMPUAN" }));
        cbjns_kel.setOpaque(false);

        cbagama.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Agama-", "ISLAM", "KRISTEN", "HINDU", "BUDHA", "KONGHUCHU" }));
        cbagama.setOpaque(false);

        jLabel8.setText("KELAS");

        jLabel10.setText("ANGKATAN");

        cbkelas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Kelas-" }));
        cbkelas.setOpaque(false);

        cbangkatan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-Pilih Angkatan-" }));
        cbangkatan.setOpaque(false);

        jScrollPane2.setViewportView(txtalamat);

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
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbagama, 0, 224, Short.MAX_VALUE)
                            .addComponent(jScrollPane2)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel8)
                            .addComponent(jLabel10))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nama, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cbkelas, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(tempat_lhr, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(tanggal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbjns_kel, 0, 224, Short.MAX_VALUE)
                            .addComponent(nis)
                            .addComponent(cbangkatan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(nis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cbkelas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(cbangkatan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tempat_lhr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(cbjns_kel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbagama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 232, 176));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        tabeldatasiswa.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        tabeldatasiswa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

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
        jScrollPane1.setViewportView(tabeldatasiswa);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 766, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 232, 176));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bsimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Save/Save_16x16.png"))); // NOI18N
        bsimpan.setText("Simpan");
        bsimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bsimpanActionPerformed(evt);
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

        btnrefresh.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pembayaran/gambar/must_have_icon_set/Refresh/Refresh_16x16.png"))); // NOI18N
        btnrefresh.setText("Refresh");
        btnrefresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnrefreshActionPerformed(evt);
            }
        });

        txtcari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtcariKeyReleased(evt);
            }
        });

        jLabel9.setText("*Cari Berdasarkan Nama Siswa");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bsimpan)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bubah)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bhapus)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnrefresh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bkeluar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, 226, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bkeluar)
                    .addComponent(bhapus)
                    .addComponent(bubah)
                    .addComponent(bsimpan)
                    .addComponent(btnrefresh)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtcari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void bkeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bkeluarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bkeluarActionPerformed

    private void bhapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bhapusActionPerformed
        // TODO add your handling code here:
        int ok = JOptionPane.showConfirmDialog(null, "hapus", "Konfirmasi Dialog", JOptionPane.YES_NO_CANCEL_OPTION);
        if (ok == 0) {
            String sql = "delete from data_siswa where nis='" + nis.getText() + "'";
            try {
                PreparedStatement stat = conn.prepareStatement(sql);
                stat.executeUpdate();
                JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
                kosong();
                nis.requestFocus();
                datatable();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Data gagal dihapus");
            }
        }
    }//GEN-LAST:event_bhapusActionPerformed

    private void bubahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bubahActionPerformed
        // TODO add your handling code here:
        String sql = "update data_siswa set nama=?, kelas=?, angkatan=? , tempat_lahir=?, tanggal_lahir=?, jenis_kelamin=?, agama=?, alamat=? where  nis='" + nis.getText() + "'";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);

            stat.setString(1, nama.getText());
           // stat.setString(2, tempat_lhr.getText());
            stat.setString(2, cbkelas.getSelectedItem().toString());
            stat.setString(3, cbangkatan.getSelectedItem().toString());
            stat.setString(4, tempat_lhr.getText());
            stat.setString(5, tgl1);
            stat.setString(6, cbjns_kel.getSelectedItem().toString());
            stat.setString(7, cbagama.getSelectedItem().toString());
            stat.setString(8, txtalamat.getText());
            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Diubah");
            kosong();
            nis.requestFocus();
            datatable();
            } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal diubah" + e);
        }
    }//GEN-LAST:event_bubahActionPerformed

    private void bsimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bsimpanActionPerformed
        // TODO add your handling code here:
        String sql = "insert into data_siswa values (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stat = conn.prepareStatement(sql);
            stat.setInt(1,Integer.parseInt(nis.getText()));
            stat.setString(2, nama.getText());
            stat.setString(3, cbkelas.getSelectedItem().toString());
            stat.setString(4, cbangkatan.getSelectedItem().toString());
            stat.setString(5, tempat_lhr.getText());
            stat.setString(6, tgl1);
            stat.setString(7, cbjns_kel.getSelectedItem().toString());
            stat.setString(8, cbagama.getSelectedItem().toString());
            stat.setString(9, txtalamat.getText());

            stat.executeUpdate();
            JOptionPane.showMessageDialog(null, "Data Berhasil Disimpan");
            kosong();
            nis.requestFocus();
            datatable();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Data Gagal disimpan" + e);
        }

    }//GEN-LAST:event_bsimpanActionPerformed

    private void tabeldatasiswaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tabeldatasiswaKeyReleased
        // TODO add your handling code here:
       
    }//GEN-LAST:event_tabeldatasiswaKeyReleased

    private void tabeldatasiswaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabeldatasiswaMouseClicked
        // TODO add your handling code here:
        try {
            int row = tabeldatasiswa.getSelectedRow();
            String tabel_klik = (tabeldatasiswa.getModel().getValueAt(row, 0).toString());
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet sql = stm.executeQuery("select * from data_siswa where nis='"+tabel_klik+"'");
            if(sql.next()) {
                String a = sql.getString("nis");//1 a
                nis.setText(a);
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
                    date = new SimpleDateFormat("yyyy-MM-dd").parse((String)tabmode.getValueAt(row,5));
                } catch (ParseException ex) {
                    Logger.getLogger(DataSiswa.class.getName()).log(Level.SEVERE, null, ex);
                }
                tanggal.setDate(date);//6
                String g = sql.getString("jenis_kelamin");//7
                cbjns_kel.setSelectedItem(g);
                String h = sql.getString("agama");//8
                cbagama.setSelectedItem(h);
                String i = sql.getString("alamat");//9
                txtalamat.setText(i);
    
                
            }
        }catch (Exception e) {
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

    private void tanggalPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_tanggalPropertyChange
        // TODO add your handling code here:
        if (tanggal.getDate() != null) {
            tgl1 = format1.format(tanggal.getDate());
        }
    }//GEN-LAST:event_tanggalPropertyChange

    private void btnrefreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnrefreshActionPerformed
        // TODO add your handling code here:
        kosong();
        datatable();
//        combobox2();
    }//GEN-LAST:event_btnrefreshActionPerformed

    private void txtcariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtcariKeyReleased
        // TODO add your handling code here:
        String tf = txtcari.getText();
        try{
            String sql = "SELECT * FROM data_siswa WHERE nama LIKE '%"+tf+"%'";
            java.sql.Statement stat = conn.createStatement();
            ResultSet hasil = stat.executeQuery(sql);
            tabeldatasiswa.setModel(DbUtils.resultSetToTableModel(hasil));
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e);
        }/*finally {
            try {

            }
            catch (Exception e){

            }
        }*/
    }//GEN-LAST:event_txtcariKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bhapus;
    private javax.swing.JButton bkeluar;
    private javax.swing.JButton bsimpan;
    private javax.swing.JButton btnrefresh;
    private javax.swing.JButton bubah;
    private javax.swing.JComboBox<String> cbagama;
    private javax.swing.JComboBox<String> cbangkatan;
    private javax.swing.JComboBox<String> cbjns_kel;
    private javax.swing.JComboBox<String> cbkelas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField nama;
    private javax.swing.JTextField nis;
    public javax.swing.JTable tabeldatasiswa;
    private com.toedter.calendar.JDateChooser tanggal;
    private javax.swing.JTextField tempat_lhr;
    private javax.swing.JEditorPane txtalamat;
    private javax.swing.JTextField txtcari;
    // End of variables declaration//GEN-END:variables
}
