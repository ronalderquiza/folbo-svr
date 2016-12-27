-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 27, 2016 at 04:41 AM
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
-- Table structure for table `tblkeywords`
--

CREATE TABLE IF NOT EXISTS `tblkeywords` (
  `keywordID` int(11) NOT NULL,
  `keyword` varchar(50) NOT NULL,
  `theme` int(11) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblkeywords`
--

INSERT INTO `tblkeywords` (`keywordID`, `keyword`, `theme`) VALUES
(1, 'accept', 1),
(2, 'beauty', 2),
(3, 'beautiful', 2),
(4, 'left', 3),
(5, 'affair', 3),
(6, 'betray', 3),
(7, 'learn', 3),
(8, 'break up', 3),
(9, 'killed', 4),
(10, 'house', 4),
(11, 'help', 4),
(12, 'body', 4),
(13, 'leave', 4),
(14, 'death', 4),
(15, 'kills', 4),
(16, 'escape', 4),
(17, 'died', 4),
(18, 'kill', 4),
(19, 'dead', 4),
(20, 'spirit', 4),
(21, 'hospital', 4),
(22, 'life', 4),
(23, 'love', 5),
(24, 'life', 5),
(25, 'ex', 5),
(26, 'anger', 6),
(27, 'leading', 6),
(28, 'plan', 6),
(29, 'people', 7),
(30, 'healed', 7),
(31, 'possessed', 7),
(32, 'day', 7),
(33, 'following', 7),
(34, 'way', 7),
(35, 'life', 7),
(36, 'cured', 7),
(37, 'love', 7),
(38, 'healing', 7),
(39, 'church', 7),
(40, 'health', 7),
(41, 'family', 8),
(42, 'mother', 8),
(43, 'love', 8),
(44, 'daughter', 8),
(45, 'children', 8),
(46, 'life', 8),
(47, 'father', 8),
(48, 'son', 8),
(49, 'story', 8),
(50, 'husband', 8),
(51, 'lives', 8),
(52, 'wedding', 8),
(53, 'married', 8),
(54, 'soul', 8),
(55, 'man', 8),
(56, 'woman', 8),
(57, 'happy', 8),
(58, 'business', 8),
(59, 'parents', 8),
(60, 'young', 8),
(61, 'wife', 8),
(62, 'friend', 8),
(63, 'sisters', 8),
(64, 'child', 8),
(65, 'curse', 9),
(66, 'deadly', 9),
(67, 'strange', 9),
(68, 'spawned', 9),
(69, 'terror', 9),
(70, 'horrific', 9),
(71, 'unlucky', 9),
(72, 'negative', 9),
(73, 'others', 10),
(74, 'family', 10),
(75, 'friends', 10),
(76, 'girlfriend', 10),
(77, 'gang', 10),
(78, 'love', 10),
(79, 'school', 10),
(80, 'life', 10),
(81, 'party', 10),
(82, 'together', 10),
(83, 'friend', 10),
(84, 'break', 10),
(85, 'best', 10),
(86, 'birthday', 10),
(87, 'gold', 11),
(88, 'money', 11),
(89, 'corruption', 11),
(90, 'affair', 11),
(91, 'continues', 11),
(92, 'bitter', 12),
(93, 'hatred', 12),
(94, 'hate', 12),
(95, 'hope', 13),
(96, 'dreams', 13),
(97, 'love', 13),
(98, 'family', 13),
(99, 'life', 13),
(100, 'help', 13),
(101, 'heart', 13),
(102, 'better', 13),
(103, 'glory', 13),
(104, 'care', 13),
(105, 'loyalty', 13),
(106, 'again', 14),
(107, 'wants', 14),
(108, 'young', 14),
(109, 'like', 14),
(110, 'tell', 14),
(111, 'body', 14),
(112, 'pretend', 14),
(113, 'another', 14),
(114, 'gay', 14),
(115, 'lesbian', 14),
(116, 'live', 14),
(117, 'eye', 15),
(118, 'adulterous', 15),
(119, 'kid', 15),
(120, 'close', 15),
(121, 'dark', 15),
(122, 'discover', 15),
(123, 'killer', 16),
(124, 'apparitions', 16),
(125, 'just', 16),
(126, 'gang', 16),
(127, 'deaths', 16),
(128, 'suicide', 16),
(129, 'execute', 16),
(130, 'investigate', 16),
(131, 'rape', 16),
(132, 'imprisonment', 16),
(133, 'prison', 16),
(134, 'detective', 16),
(135, 'tragedy', 16),
(136, 'committing', 16),
(137, 'murder', 16),
(138, 'slaughter', 16),
(139, 'notorious', 16),
(140, 'security', 16),
(141, 'love', 17),
(142, 'life', 17),
(143, 'relationship', 17),
(144, 'girl', 17),
(145, 'family', 17),
(146, 'together', 17),
(147, 'happy', 17),
(148, 'wedding', 17),
(149, 'marry', 17),
(150, 'romance', 17),
(151, 'feelings', 17),
(152, 'loves', 17),
(153, 'creatures', 18),
(154, 'land', 18),
(155, 'save', 18),
(156, 'mythology', 18),
(157, 'heal', 19),
(158, 'uptight', 19),
(159, 'hopes', 19),
(160, 'guarded', 19),
(161, 'aspiring', 19),
(162, 'life', 19),
(163, 'people', 20),
(164, 'poor', 20),
(165, 'forces', 21),
(166, 'independence', 21),
(167, 'mission', 21),
(168, 'gradually', 21),
(169, 'revolutionary', 21),
(170, 'rebels', 21),
(171, 'represent', 21),
(172, 'revenge', 22),
(173, 'angry', 22),
(174, 'seeking', 22),
(175, 'sacrifice', 23),
(176, 'family', 23),
(177, 'work', 23),
(178, 'life', 23),
(179, 'hard', 23),
(180, 'problems', 23),
(181, 'journey', 23),
(182, 'promises', 23),
(183, 'longing', 23),
(184, 'stress', 23),
(185, 'against', 24),
(186, 'orders', 24),
(187, 'war', 24),
(188, 'troops', 24),
(189, 'soldiers', 24),
(190, 'killed', 24),
(191, 'shot', 24),
(192, 'authorities', 24),
(193, 'cabinet', 24),
(194, 'forces', 24),
(195, 'military', 24),
(196, 'headquarters', 24),
(197, 'invasion', 24),
(198, 'comrades', 24),
(199, 'peace', 24),
(200, 'survivors', 24),
(201, 'cops', 24),
(202, 'general', 24),
(203, 'officers', 24),
(204, 'stabbed', 24);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tblkeywords`
--
ALTER TABLE `tblkeywords`
  ADD PRIMARY KEY (`keywordID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tblkeywords`
--
ALTER TABLE `tblkeywords`
  MODIFY `keywordID` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=205;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
