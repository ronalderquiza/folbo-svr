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
-- Table structure for table `tbltheme`
--

CREATE TABLE IF NOT EXISTS `tbltheme` (
  `themeID` int(11) NOT NULL,
  `theme` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbltheme`
--

INSERT INTO `tbltheme` (`themeID`, `theme`) VALUES
(1, 'Acceptance'),
(2, 'Beauty'),
(3, 'Betrayal'),
(4, 'Death'),
(5, 'Despair'),
(6, 'Envy'),
(7, 'Faith'),
(8, 'Family'),
(9, 'Fear'),
(10, 'Friendship'),
(11, 'Greed'),
(12, 'Hatred'),
(13, 'Hope'),
(14, 'Identity'),
(15, 'Innocence'),
(16, 'Justice'),
(17, 'Love'),
(18, 'Nature'),
(19, 'Peace'),
(20, 'Poverty'),
(21, 'Pride'),
(22, 'Revenge'),
(23, 'Sacrifice'),
(24, 'War');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbltheme`
--
ALTER TABLE `tbltheme`
  ADD PRIMARY KEY (`themeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbltheme`
--
ALTER TABLE `tbltheme`
  MODIFY `themeID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=25;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
