-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 26, 2016 at 02:20 PM
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
-- Table structure for table `tbldirector`
--

CREATE TABLE IF NOT EXISTS `tbldirector` (
  `directorID` int(11) NOT NULL,
  `directorName` varchar(200) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbldirector`
--

INSERT INTO `tbldirector` (`directorID`, `directorName`) VALUES
(1, 'Adelia Ramos\r\n'),
(2, 'Adolfo Alix, Jr. \r\n'),
(3, 'Aloy Adlawan\r\n'),
(4, 'Andoy Ranay\r\n'),
(5, 'Antoinette Jadaone\r\n'),
(6, 'Cathy Garcia-Molina\r\n'),
(7, 'Chito S. Rono \n'),
(8, 'Chris Martinez\r\n'),
(9, 'Dan Villegas\r\n'),
(10, 'Dante Nico Garcia\r\n'),
(11, 'Dominic Zapata\r\n'),
(12, 'Don Cuaresma\r\n'),
(13, 'Dondon Santos\r\n'),
(14, 'Emmanuel Quindo Palo\r\n'),
(15, 'Enrico C. Santos\r\n'),
(16, 'Enrico S. Quizon\r\n'),
(17, 'Enzo Williams\r\n'),
(18, 'Erik Matti\r\n'),
(19, 'Frasco Mortiz\r\n'),
(20, 'Gilbert Perez\r\n'),
(21, 'Gino M. Santos\r\n'),
(22, 'Jade Castro\r\n'),
(23, 'Jerome Chavez Pobocan\r\n'),
(24, 'Jerrold Tarog\r\n'),
(25, 'Jerry Lopez Sineneng\r\n'),
(26, 'Joel Lamangan\r\n'),
(27, 'John D. Lazatin\r\n'),
(28, 'Jon Villarin\r\n'),
(29, 'Jose Javier Reyes\r\n'),
(30, 'Joyce Bernal \r\n'),
(31, 'Jun Lana\r\n'),
(32, 'Laurenti M. Dyogi\r\n'),
(33, 'Laurice Guillen\r\n'),
(34, 'Lawrence Fajardo \r\n'),
(35, 'Lino Cayetano\r\n'),
(36, 'Luis C. Suarez\n'),
(37, 'Mac Alejandre\r\n'),
(38, 'Mae Cruz Alviar\r\n'),
(39, 'Mae Czarina Cruz\r\n'),
(40, 'Mark A. Reyes\r\n'),
(41, 'Mark Meily\r\n'),
(42, 'Marlon Rivera\r\n'),
(43, 'Maryo J. de los Reyes \r\n'),
(44, 'Michael Tuviera\r\n'),
(45, 'Muhammad Yusuf\r\n'),
(46, 'Nuel Crisostomo Naval\r\n'),
(47, 'Olivia Lamasan\r\n'),
(48, 'Onat Diaz\r\n'),
(49, 'Paul Daza\r\n'),
(50, 'Pedring Lopez\r\n'),
(51, 'Peque Gallaga\r\n'),
(52, 'Perci Intalan\r\n'),
(53, 'Randolph Longjas\r\n'),
(54, 'Randy Santiago\r\n'),
(55, 'Raz dela Torre\r\n'),
(56, 'Richard Somes\r\n'),
(57, 'Rico Gutierrez\r\n'),
(58, 'Rico Maria Ilarde\r\n'),
(59, 'Robert Quilao\r\n'),
(60, 'Rory Quintos\r\n'),
(61, 'Ruel S. Bayani\r\n'),
(62, 'Soxie H. Topacio\r\n'),
(63, 'Theodore Boborol\r\n'),
(64, 'Tikoy Aguiluz\r\n'),
(65, 'Tony Y. Reyes\r\n'),
(66, 'Topel Lee\r\n'),
(67, 'Veronica B. Velasco\r\n'),
(68, 'Wenn V. Deramas\r\n'),
(69, 'Yam Laranas\r\n'),
(70, 'Zoren Legaspi\r\n');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbldirector`
--
ALTER TABLE `tbldirector`
  ADD PRIMARY KEY (`directorID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbldirector`
--
ALTER TABLE `tbldirector`
  MODIFY `directorID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=71;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
