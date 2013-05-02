-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 02, 2013 at 12:41 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `friendmapper`
--

-- --------------------------------------------------------

--
-- Table structure for table `friends`
--

CREATE TABLE IF NOT EXISTS `friends` (
  `ID` int(30) NOT NULL AUTO_INCREMENT,
  `User_ID` varchar(100) NOT NULL,
  `Friend_ID` varchar(100) NOT NULL,
  `Confirmation` int(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `User_ID` (`User_ID`),
  KEY `Friend_ID` (`Friend_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Dumping data for table `friends`
--

INSERT INTO `friends` (`ID`, `User_ID`, `Friend_ID`, `Confirmation`) VALUES
(4, '123', '123', 0),
(5, '123', '456', 0);

-- --------------------------------------------------------

--
-- Table structure for table `members`
--

CREATE TABLE IF NOT EXISTS `members` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `Name` varchar(55) NOT NULL,
  `PhoneNumber` varchar(55) NOT NULL,
  `PIN` varchar(55) NOT NULL,
  `Registered` int(1) NOT NULL,
  `Visibility` int(1) NOT NULL,
  `Latitude` float NOT NULL,
  `Longitude` float NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `PhoneNumber` (`PhoneNumber`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `members`
--

INSERT INTO `members` (`id`, `Name`, `PhoneNumber`, `PIN`, `Registered`, `Visibility`, `Latitude`, `Longitude`) VALUES
(2, 'Mutahir', '123', '123', 1, 1, 32.44, 72.3),
(3, 'Kazmi', '456', '456', 1, 1, 39.5, 37.2);

-- --------------------------------------------------------

--
-- Table structure for table `panic`
--

CREATE TABLE IF NOT EXISTS `panic` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` varchar(55) NOT NULL,
  `Friend_ID` varchar(55) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `User_ID` (`User_ID`),
  KEY `Friend_ID` (`Friend_ID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `panic`
--

INSERT INTO `panic` (`ID`, `User_ID`, `Friend_ID`) VALUES
(2, '123', '456');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `panic`
--
ALTER TABLE `panic`
  ADD CONSTRAINT `panic_ibfk_1` FOREIGN KEY (`User_ID`) REFERENCES `members` (`PhoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `panic_ibfk_2` FOREIGN KEY (`Friend_ID`) REFERENCES `members` (`PhoneNumber`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
