-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2016 at 02:21 PM
-- Server version: 5.6.26
-- PHP Version: 5.5.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbfolbo`
--

-- --------------------------------------------------------

--
-- Table structure for table `tblprodcompany`
--

CREATE TABLE IF NOT EXISTS `tblprodcompany` (
  `prodCompanyID` int(11) NOT NULL,
  `prodCompany` varchar(150) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblprodcompany`
--

INSERT INTO `tblprodcompany` (`prodCompanyID`, `prodCompany`) VALUES
(1, 'ABS-CBN Film Productions, Inc.\r\n'),
(2, 'AgostoDos Pictures\r\n'),
(3, 'Ambient Media'),
(4, 'APT Entertainment\r\n'),
(5, 'Artikulo Uno Productions\r\n'),
(6, 'BIDA Foundation\r\n'),
(7, 'Buchi Boy Films \r\n'),
(8, 'Cinema One\r\n'),
(9, 'Cinemabuhay International\r\n'),
(10, 'CineMedia\r\n'),
(11, 'Creative Programs Inc.\r\n'),
(12, 'Cutting Edge Productions\r\n'),
(13, 'Dreamscape Cinema\r\n'),
(14, 'El Nido Palawan Film Proudctions\r\n'),
(15, 'GMA Films\r\n'),
(16, 'Haunted Tower Pictures\r\n'),
(17, 'Heaven''s Best Entertainment \r\n'),
(18, 'Imus Productions\r\n'),
(19, 'Media Asia Films\r\n'),
(20, 'MJM Productions\r\n'),
(21, 'Multivision Pictures Entertainment \r\n'),
(22, 'MZet Productions\r\n'),
(23, 'N2 Pictures\r\n'),
(24, 'OctoArts Films\r\n'),
(25, 'Parallax Studios\r\n'),
(26, 'Philippians Productions\r\n'),
(27, 'Philippine Film Studios\r\n'),
(28, 'PostManila\r\n'),
(29, 'Quantum Films\r\n'),
(30, 'RCP Productions \r\n'),
(31, 'Reality Entertainment Mothership, Inc.\r\n'),
(32, 'Reality Entertainment Strawdogs Studio\r\n'),
(33, 'Regal Entertainment, Inc.\r\n'),
(34, 'Regal Multimedia, Inc.\r\n'),
(35, 'RVQ Productions\r\n'),
(36, 'Scenema Concept International\r\n'),
(37, 'Sine Screen\r\n'),
(38, 'Skylight Films\r\n'),
(39, 'Solar Entertainment Corporation\r\n'),
(40, 'Solar Films\r\n'),
(41, 'Star Cinema\r\n'),
(42, 'Straight Shooters Media\r\n'),
(43, 'Summit Media\r\n'),
(44, 'Thaumatrope Animation Production\r\n'),
(45, 'The IdeaFirst Compan\r\n'),
(46, 'Tuko Film Productions\r\n'),
(47, 'Unitel Productions\r\n'),
(48, 'Videoflick\r\n'),
(49, 'VIVA Films\r\n'),
(50, 'XYZ Films\r\n'),
(51, 'Regal Films');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblprodcompany`
--
ALTER TABLE `tblprodcompany`
  ADD PRIMARY KEY (`prodCompanyID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblprodcompany`
--
ALTER TABLE `tblprodcompany`
  MODIFY `prodCompanyID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=52;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
