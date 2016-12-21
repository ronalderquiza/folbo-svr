-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 19, 2016 at 06:52 AM
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
  `theme` varchar(300) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbltheme`
--

INSERT INTO `tbltheme` (`themeID`, `theme`) VALUES
(1, 'Acceptance'),
(2, 'Beauty\r\n'),
(3, 'Betrayal\r\n'),
(4, 'Death\r\n'),
(5, 'Deception\r\n'),
(6, 'Despair\r\n'),
(7, 'Education\r\n'),
(8, 'Envy\r\n'),
(9, 'Fear\r\n'),
(10, 'Family \r\n'),
(11, 'Faith\r\n'),
(12, 'Freedom\r\n'),
(13, 'Friendship\r\n'),
(14, 'Greed\r\n'),
(15, 'Hatred\r\n'),
(16, 'Hope\r\n'),
(17, 'Honesty\r\n'),
(18, 'Identity\r\n'),
(19, 'Innocence\r\n'),
(20, 'Justice\r\n'),
(21, 'Law\r\n'),
(22, 'Love\r\n'),
(23, 'Money\r\n'),
(24, 'Nature\r\n'),
(25, 'Peace\r\n'),
(26, 'Poverty\r\n'),
(27, 'Pride\r\n'),
(28, 'Revenge\r\n'),
(29, 'Sacrifice\r\n'),
(30, 'Time\r\n'),
(31, 'Truth\r\n'),
(32, 'War\r\n');

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
  MODIFY `themeID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=33;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
