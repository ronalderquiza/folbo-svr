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
-- Table structure for table `tblmtrcbrating`
--

CREATE TABLE IF NOT EXISTS `tblmtrcbrating` (
  `mtrcbRatingID` int(11) NOT NULL,
  `mtrcbRate` varchar(300) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblmtrcbrating`
--

INSERT INTO `tblmtrcbrating` (`mtrcbRatingID`, `mtrcbRate`) VALUES
(1, 'G'),
(2, 'PG'),
(3, 'R-13'),
(4, 'R-16'),
(5, 'R-18');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblmtrcbrating`
--
ALTER TABLE `tblmtrcbrating`
  ADD PRIMARY KEY (`mtrcbRatingID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblmtrcbrating`
--
ALTER TABLE `tblmtrcbrating`
  MODIFY `mtrcbRatingID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
