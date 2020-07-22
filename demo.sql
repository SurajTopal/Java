-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: Jun 24, 2020 at 08:56 PM
-- Server version: 10.4.10-MariaDB
-- PHP Version: 7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `demo`
--

-- --------------------------------------------------------

--
-- Table structure for table `bankform`
--

DROP TABLE IF EXISTS `bankform`;
CREATE TABLE IF NOT EXISTS `bankform` (
  `email` varchar(30) NOT NULL,
  `accountno` varchar(12) NOT NULL,
  `ifsccode` varchar(10) NOT NULL,
  `accounttype` varchar(20) NOT NULL,
  `branchname` varchar(20) NOT NULL,
  `bankbalance` int(20) NOT NULL,
  `annualincome` varchar(20) NOT NULL,
  `state` varchar(30) NOT NULL,
  `occupation` varchar(20) NOT NULL,
  `maritalstatus` varchar(20) NOT NULL,
  `language` varchar(20) NOT NULL,
  `nationality` varchar(20) NOT NULL,
  `openaccountdate` varchar(20) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bankform`
--

INSERT INTO `bankform` (`email`, `accountno`, `ifsccode`, `accounttype`, `branchname`, `bankbalance`, `annualincome`, `state`, `occupation`, `maritalstatus`, `language`, `nationality`, `openaccountdate`) VALUES
('sonunegi@gmail.com', '515802474', 'SBIN742', 'Saving Account', 'Noida', 1700, '1200000', 'Jharkhand', 'Doctor', 'Married', 'English', 'Indian', '18/06/2020'),
('neeraj123@gmail.com', '76044732', 'SBIN43072', 'Regular Saving', 'Ashok Vihar', 2050, '1000000', 'Arunachal Pradesh', 'Architecture', 'Married', 'English', 'Indian', '18/06/2020'),
('rashminegi@gmail.com', '155326300', 'SBIN42452', 'Fixed Deposit', 'Noida', 3750, '4500000', 'Arunachal Pradesh', 'Teacher', 'Married', 'German', 'Candian', '18/06/2020'),
('meenarawat@gmail.com', '921346613', 'SBIN28441', 'Saving Account', 'Noida', 7800, '4500000', 'Bihar', 'Professor', 'Married', 'English', 'Indian', '18/06/2020'),
('minitopal@gmail.com', '709343531', 'SBIN87320', 'Saving Account', 'Noida', 39000, '4500000', 'Uttrakhand', 'Teacher', 'Single', 'Bihari', 'Indian', '22/06/2020'),
('rahulnegi@gmail.com', '532007438', 'SBIN60303', 'Regular Saving', 'Noida', 120000, '5400000', 'Uttar Pradesh', 'Teacher', 'Married', 'English', 'Indian', '25/06/2020'),
('ansikanegi@gmail.com', '546582390', 'SBIN30151', 'Saving Account', 'Noida', 0, '45000000', 'Uttar Pradesh', 'Teacher', 'Married', 'French', 'Indian', '25/06/2020');

-- --------------------------------------------------------

--
-- Table structure for table `login`
--

DROP TABLE IF EXISTS `login`;
CREATE TABLE IF NOT EXISTS `login` (
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `pin` int(5) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `login`
--

INSERT INTO `login` (`email`, `password`, `pin`) VALUES
('sonunegi@gmail.com', 'sonunegi', 10111),
('neeraj123@gmail.com', 'neeraj123@', 78780),
('rashminegi@gmail.com', 'rashminegi', 78786),
('meenarawat@gmail.com', 'meenarawat@', 45454),
('rahulnegi@gmail.com', 'rahulnegi@', 0),
('minitopal@gmail.com', 'minitopal@', 12121),
('ansikanegi@gmail.com', 'ansikasingh', 11111);

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

DROP TABLE IF EXISTS `register`;
CREATE TABLE IF NOT EXISTS `register` (
  `firstname` varchar(30) NOT NULL,
  `lastname` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `phoneno` varchar(20) NOT NULL,
  `address` varchar(30) NOT NULL,
  `dob` varchar(15) NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`firstname`, `lastname`, `email`, `password`, `gender`, `phoneno`, `address`, `dob`) VALUES
('ansika', 'singh', 'ansikanegi@gmail.com', 'ansikasingh', 'female', '9876555310', 'B-13 Ashok Nagar', '1994-10-03'),
('rahul', 'negi', 'rahulnegi@gmail.com', 'rahulnegi@', 'male', '9876543210', 'B-14 Noida', '1998-06-05'),
('mini', 'topal', 'minitopal@gmail.com', 'minitopal@', 'female', '9876543200', 'B-13 Ashok Nagar', '1995-01-05'),
('meena', 'rawat', 'meenarawat@gmail.com', 'meenarawat@', 'female', '9876500000', 'C-87 Noida D', '1998-05-05'),
('rashmi', 'negi', 'rashminegi@gmail.com', 'rashminegi', 'female', '9953456700', 'B-13 / A , Noida', '2000-02-01'),
('sonu', 'negi', 'sonunegi@gmail.com', 'sonunegi', 'male', '8765432145', 'C-24, gali no. 3', '1997-06-02'),
('neeraj', 'singh', 'neeraj123@gmail.com', 'neeraj123@', 'male', '9555543210', 'B-56/ A Ashok Nagar', '1998-11-01');

-- --------------------------------------------------------

--
-- Table structure for table `user76044732`
--

DROP TABLE IF EXISTS `user76044732`;
CREATE TABLE IF NOT EXISTS `user76044732` (
  `transactionid` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `date` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `amount` int(15) NOT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user76044732`
--

INSERT INTO `user76044732` (`transactionid`, `title`, `date`, `time`, `username`, `amount`) VALUES
('TRANS8086', 'Paid', '20/06/2020', '01:29:28', 'meena rawat', 10000),
('TRANS7855', 'Received', '20/06/2020', '11:20:16', 'sonu negi', 500),
('TRANS6573', 'Paid', '23/06/2020', '15:46:18', 'rashmi negi', 450);

-- --------------------------------------------------------

--
-- Table structure for table `user155326300`
--

DROP TABLE IF EXISTS `user155326300`;
CREATE TABLE IF NOT EXISTS `user155326300` (
  `transactionid` varchar(12) NOT NULL,
  `title` varchar(15) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `amount` int(15) NOT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user155326300`
--

INSERT INTO `user155326300` (`transactionid`, `title`, `date`, `time`, `username`, `amount`) VALUES
('TRANS3675', 'Received', '20/06/2020', '00:37:54', 'meena rawat', 100),
('TRANS2179', 'Received', '23/06/2020', '15:41:40', 'mini topal', 2000),
('TRANS4136', 'Received', '23/06/2020', '15:43:31', 'meena rawat', 2300),
('TRANS5882', 'Paid', '23/06/2020', '15:44:35', 'mini topal', 1000),
('TRANS6573', 'Received', '23/06/2020', '15:46:18', 'neeraj singh', 450),
('TRANS8409', 'Paid', '23/06/2020', '15:47:44', 'sonu negi', 200),
('TRANS3041', 'Paid', '24/06/2020', '23:38:18', 'sonu negi', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `user515802474`
--

DROP TABLE IF EXISTS `user515802474`;
CREATE TABLE IF NOT EXISTS `user515802474` (
  `transactionid` varchar(20) NOT NULL,
  `title` varchar(20) NOT NULL,
  `date` varchar(20) NOT NULL,
  `time` varchar(20) NOT NULL,
  `username` varchar(30) NOT NULL,
  `amount` int(15) NOT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user515802474`
--

INSERT INTO `user515802474` (`transactionid`, `title`, `date`, `time`, `username`, `amount`) VALUES
('TRANS7855', 'Paid', '20/06/2020', '11:20:16', 'neeraj singh', 500),
('TRANS8409', 'Received', '23/06/2020', '15:47:44', 'rashmi negi', 200),
('TRANS3041', 'Received', '24/06/2020', '23:38:18', 'rashmi negi', 1000);

-- --------------------------------------------------------

--
-- Table structure for table `user532007438`
--

DROP TABLE IF EXISTS `user532007438`;
CREATE TABLE IF NOT EXISTS `user532007438` (
  `transactionid` varchar(12) NOT NULL,
  `title` varchar(15) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `amount` int(15) DEFAULT NULL,
  `accountno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `user546582390`
--

DROP TABLE IF EXISTS `user546582390`;
CREATE TABLE IF NOT EXISTS `user546582390` (
  `transactionid` varchar(12) NOT NULL,
  `title` varchar(15) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `amount` int(15) DEFAULT NULL,
  `accountno` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user546582390`
--

INSERT INTO `user546582390` (`transactionid`, `title`, `date`, `time`, `username`, `amount`, `accountno`) VALUES
('TRANS3840', 'Paid', '25/06/2020', '00:55:24', 'mini topal', 10000, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `user709343531`
--

DROP TABLE IF EXISTS `user709343531`;
CREATE TABLE IF NOT EXISTS `user709343531` (
  `transactionid` varchar(12) NOT NULL,
  `title` varchar(15) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `amount` int(15) DEFAULT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user709343531`
--

INSERT INTO `user709343531` (`transactionid`, `title`, `date`, `time`, `username`, `amount`) VALUES
('TRANS2179', 'Paid', '23/06/2020', '15:41:40', 'rashmi negi', 2000),
('TRANS5882', 'Received', '23/06/2020', '15:44:35', 'rashmi negi', 1000),
('TRANS3840', 'Received', '25/06/2020', '00:55:24', 'ansika singh', 10000);

-- --------------------------------------------------------

--
-- Table structure for table `user921346613`
--

DROP TABLE IF EXISTS `user921346613`;
CREATE TABLE IF NOT EXISTS `user921346613` (
  `transactionid` varchar(12) NOT NULL,
  `title` varchar(15) DEFAULT NULL,
  `date` varchar(15) DEFAULT NULL,
  `time` varchar(15) DEFAULT NULL,
  `username` varchar(20) DEFAULT NULL,
  `amount` int(15) NOT NULL,
  PRIMARY KEY (`transactionid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user921346613`
--

INSERT INTO `user921346613` (`transactionid`, `title`, `date`, `time`, `username`, `amount`) VALUES
('TRANS4136', 'Paid', '23/06/2020', '15:43:31', 'rashmi negi', 2300),
('TRANS8086', 'Received', '20/06/2020', '01:29:28', 'neeraj singh', 10000),
('TRANS3675', 'Paid', '20/06/2020', '00:37:54', 'rashmi negi', 100);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
