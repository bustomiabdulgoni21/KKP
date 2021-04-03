-- phpMyAdmin SQL Dump
-- version 4.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 23, 2020 at 01:23 PM
-- Server version: 10.1.13-MariaDB
-- PHP Version: 5.6.20

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `newkkp`
--

-- --------------------------------------------------------

--
-- Table structure for table `daftar`
--

CREATE TABLE `daftar` (
  `user` varchar(100) NOT NULL,
  `pass` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `daftar`
--

INSERT INTO `daftar` (`user`, `pass`) VALUES
('admin', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `data_siswa`
--

CREATE TABLE `data_siswa` (
  `nis` int(20) NOT NULL,
  `nama` varchar(200) NOT NULL,
  `kelas` varchar(20) DEFAULT NULL,
  `angkatan` varchar(200) NOT NULL,
  `tempat_lahir` varchar(200) DEFAULT NULL,
  `tanggal_lahir` varchar(14) DEFAULT NULL,
  `jenis_kelamin` varchar(9) DEFAULT NULL,
  `agama` varchar(15) DEFAULT NULL,
  `alamat` varchar(200) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `data_siswa`
--

INSERT INTO `data_siswa` (`nis`, `nama`, `kelas`, `angkatan`, `tempat_lahir`, `tanggal_lahir`, `jenis_kelamin`, `agama`, `alamat`) VALUES
(20101001, 'Abdul', '10-TKJ', '2013/2014', 'Bogor', '2020-02-29', 'Laki-laki', 'Islam', 'Bojong'),
(20101002, 'Barnes', '10-TSM', '2013/2014', 'Depok', '2020-02-21', 'Laki-laki', 'Hindu', 'Parung'),
(20101003, 'Sule', '10-MM', '2013/2014', 'Bekasi', '2020-02-01', 'Laki-laki', 'Islam', 'Kemang');

-- --------------------------------------------------------

--
-- Table structure for table `jeniss`
--

CREATE TABLE `jeniss` (
  `id_jns` varchar(15) NOT NULL,
  `jns_pem` varchar(20) NOT NULL,
  `harga` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jeniss`
--

INSERT INTO `jeniss` (`id_jns`, `jns_pem`, `harga`) VALUES
('Id-002', 'SPP', '200000'),
('Id-003', 'ALMAMATER', '150000'),
('Id-006', 'KEBERSIHAN 1 TAHUN', '100000'),
('Id-021', 'PENSI', '150000'),
('Id-026', 'DSP', '1000000'),
('Id-029', 'Kuis', '120000');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `no_pemb` varchar(200) NOT NULL,
  `tgl_pem` varchar(50) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kelas` varchar(18) NOT NULL,
  `jns_pem` varchar(20) NOT NULL,
  `biaya` varchar(100) NOT NULL,
  `total` varchar(100) NOT NULL,
  `kurang` varchar(50) NOT NULL,
  `ket` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`no_pemb`, `tgl_pem`, `nis`, `nama`, `kelas`, `jns_pem`, `biaya`, `total`, `kurang`, `ket`) VALUES
('P-001', '2020-02-14', '20101002', 'Barnes', '10-TSM', 'ALMAMATER', '150000', '150000', '0', 'Lunas'),
('P-003', '2020-02-23', '20101001', 'Abdul', '10-TKJ', 'DSP', '1000000', '1000000', '0', 'Lunas'),
('P-004', '2020-07-29', '20101001', 'Abdul', '10-TKJ', 'DSP', '1000000', '1000000', '0', 'Lunas'),
('P-005', '2020-02-24', '20101003', 'Sule', '10-MM', 'DSP', '1000000', '1000000', '0', 'Lunas');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaranspp`
--

CREATE TABLE `pembayaranspp` (
  `no_pemb` varchar(200) NOT NULL,
  `tgl_pem` varchar(50) NOT NULL,
  `nis` varchar(15) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kelas` varchar(18) NOT NULL,
  `jns_pem` varchar(20) NOT NULL,
  `bulan` varchar(20) NOT NULL,
  `biaya` varchar(100) NOT NULL,
  `bayar` varchar(200) NOT NULL,
  `kurang` varchar(100) NOT NULL,
  `ket` varchar(200) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaranspp`
--

INSERT INTO `pembayaranspp` (`no_pemb`, `tgl_pem`, `nis`, `nama`, `kelas`, `jns_pem`, `bulan`, `biaya`, `bayar`, `kurang`, `ket`) VALUES
('P-001', '2020-02-21', '20101001', 'Abdul', '10-TKJ', 'SPP', 'JANUARI', '200000', '100000', '100000', 'Belum Lunas'),
('P-002', '2020-02-13', '20101002', 'Barnes', '10-TSM', 'SPP', 'JANUARI', '200000', '50000', '150000', 'Belum Lunas'),
('P-003', '2020-02-14', '20101003', 'Sule', '10-MM', 'SPP', 'JANUARI', '200000', '90000', '110000', 'Belum Lunas');

-- --------------------------------------------------------

--
-- Table structure for table `tabeladdjenis`
--

CREATE TABLE `tabeladdjenis` (
  `id` varchar(50) NOT NULL,
  `jenis` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tabeladdjenis`
--

INSERT INTO `tabeladdjenis` (`id`, `jenis`) VALUES
('Id-002', 'SPP'),
('Id-003', 'ALMAMATER'),
('Id-004', 'BAJU WEARPACK'),
('Id-005', 'DASI,BET+LOKASI'),
('Id-006', 'KEBERSIHAN 1 TAHUN'),
('Id-007', 'ESKUL 1 TAHUN'),
('Id-008', 'PRAKTIKUM'),
('Id-009', 'OSIS 1 TAHUN'),
('Id-010', 'RAPORT/MAP'),
('Id-011', 'BANTUAN KOMPUTER'),
('Id-012', 'LDKS'),
('Id-013', 'LKS'),
('Id-014', 'SEMESTER'),
('Id-015', 'MID'),
('Id-016', 'DAFTAR ULANG'),
('Id-017', 'PERPISAHAN'),
('Id-018', 'KENAIKAN'),
('Id-019', 'KUNJUNGAN INDUSTRI'),
('Id-020', 'UJIAN NASIONAL'),
('Id-021', 'PENSI'),
('Id-022', 'MAULID'),
('Id-023', 'AGUSTUSAN'),
('Id-024', 'UTS'),
('Id-025', 'UAS'),
('Id-026', 'DSP'),
('Id-027', 'Mapala');

-- --------------------------------------------------------

--
-- Table structure for table `tabeldataangkatan`
--

CREATE TABLE `tabeldataangkatan` (
  `idtahun` varchar(10) NOT NULL,
  `tahun` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tabeldataangkatan`
--

INSERT INTO `tabeldataangkatan` (`idtahun`, `tahun`) VALUES
('ANG-001', '2013/2014'),
('ANG-002', '2011/2012'),
('ANG-003', '2012/2013');

-- --------------------------------------------------------

--
-- Table structure for table `tabeldatakelas`
--

CREATE TABLE `tabeldatakelas` (
  `idkelas` varchar(20) NOT NULL,
  `kelas` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tabeldatakelas`
--

INSERT INTO `tabeldatakelas` (`idkelas`, `kelas`) VALUES
('KLS-001', '10-TKJ'),
('KLS-002', '10-TSM'),
('KLS-003', '10-MM'),
('KLS-004', '11-TKJ'),
('KLS-005', '11-TSM'),
('KLS-006', '11-MM'),
('KLS-007', '12-TKJ'),
('KLS-008', '12-TSM'),
('KLS-009', '12-MM');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `data_siswa`
--
ALTER TABLE `data_siswa`
  ADD PRIMARY KEY (`nis`);

--
-- Indexes for table `jeniss`
--
ALTER TABLE `jeniss`
  ADD PRIMARY KEY (`id_jns`);

--
-- Indexes for table `pembayaranspp`
--
ALTER TABLE `pembayaranspp`
  ADD PRIMARY KEY (`no_pemb`);

--
-- Indexes for table `tabeldataangkatan`
--
ALTER TABLE `tabeldataangkatan`
  ADD PRIMARY KEY (`idtahun`);

--
-- Indexes for table `tabeldatakelas`
--
ALTER TABLE `tabeldatakelas`
  ADD PRIMARY KEY (`idkelas`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
