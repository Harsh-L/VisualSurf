-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 05, 2023 at 10:52 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `visualsurfengine`
--

-- --------------------------------------------------------

--
-- Table structure for table `accounttypetb`
--

CREATE TABLE `accounttypetb` (
  `accID` int(11) NOT NULL,
  `accName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `boardtb`
--

CREATE TABLE `boardtb` (
  `boardid` int(11) NOT NULL,
  `boardName` varchar(30) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `board_imagetb`
--

CREATE TABLE `board_imagetb` (
  `boardID` int(11) DEFAULT NULL,
  `imageID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `categorytb`
--

CREATE TABLE `categorytb` (
  `catID` int(11) NOT NULL,
  `catName` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `followtb`
--

CREATE TABLE `followtb` (
  `user` int(11) NOT NULL,
  `follower` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `imagetb`
--

CREATE TABLE `imagetb` (
  `imageID` int(11) NOT NULL,
  `title` varchar(25) DEFAULT NULL,
  `description` varchar(150) DEFAULT NULL,
  `image_url` varchar(100) DEFAULT NULL,
  `tags` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL CHECK (json_valid(`tags`)),
  `creationDate` date NOT NULL,
  `likeCount` int(11) DEFAULT NULL,
  `userID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `roletb`
--

CREATE TABLE `roletb` (
  `roleID` int(11) NOT NULL,
  `roleName` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `usertb`
--

CREATE TABLE `usertb` (
  `UserID` int(11) NOT NULL,
  `username` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(35) DEFAULT NULL,
  `password` varchar(1024) NOT NULL,
  `accountID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_boardtb`
--

CREATE TABLE `user_boardtb` (
  `userID` int(11) DEFAULT NULL,
  `boardID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_cattb`
--

CREATE TABLE `user_cattb` (
  `userID` int(11) DEFAULT NULL,
  `catID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `user_roletb`
--

CREATE TABLE `user_roletb` (
  `userID` int(11) NOT NULL,
  `roleID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `accounttypetb`
--
ALTER TABLE `accounttypetb`
  ADD PRIMARY KEY (`accID`);

--
-- Indexes for table `boardtb`
--
ALTER TABLE `boardtb`
  ADD PRIMARY KEY (`boardid`);

--
-- Indexes for table `board_imagetb`
--
ALTER TABLE `board_imagetb`
  ADD KEY `boardID` (`boardID`),
  ADD KEY `imageID` (`imageID`);

--
-- Indexes for table `categorytb`
--
ALTER TABLE `categorytb`
  ADD PRIMARY KEY (`catID`);

--
-- Indexes for table `followtb`
--
ALTER TABLE `followtb`
  ADD KEY `user` (`user`),
  ADD KEY `follower` (`follower`);

--
-- Indexes for table `imagetb`
--
ALTER TABLE `imagetb`
  ADD PRIMARY KEY (`imageID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `roletb`
--
ALTER TABLE `roletb`
  ADD PRIMARY KEY (`roleID`);

--
-- Indexes for table `usertb`
--
ALTER TABLE `usertb`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `unique_username` (`username`),
  ADD KEY `accountID` (`accountID`);

--
-- Indexes for table `user_boardtb`
--
ALTER TABLE `user_boardtb`
  ADD KEY `userID` (`userID`),
  ADD KEY `boardID` (`boardID`);

--
-- Indexes for table `user_cattb`
--
ALTER TABLE `user_cattb`
  ADD KEY `userID` (`userID`),
  ADD KEY `catID` (`catID`);

--
-- Indexes for table `user_roletb`
--
ALTER TABLE `user_roletb`
  ADD KEY `roleID` (`roleID`),
  ADD KEY `userID` (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `accounttypetb`
--
ALTER TABLE `accounttypetb`
  MODIFY `accID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `boardtb`
--
ALTER TABLE `boardtb`
  MODIFY `boardid` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `categorytb`
--
ALTER TABLE `categorytb`
  MODIFY `catID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `imagetb`
--
ALTER TABLE `imagetb`
  MODIFY `imageID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `roletb`
--
ALTER TABLE `roletb`
  MODIFY `roleID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `usertb`
--
ALTER TABLE `usertb`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `board_imagetb`
--
ALTER TABLE `board_imagetb`
  ADD CONSTRAINT `board_imagetb_ibfk_1` FOREIGN KEY (`boardID`) REFERENCES `boardtb` (`boardid`),
  ADD CONSTRAINT `board_imagetb_ibfk_2` FOREIGN KEY (`imageID`) REFERENCES `imagetb` (`imageID`);

--
-- Constraints for table `followtb`
--
ALTER TABLE `followtb`
  ADD CONSTRAINT `followtb_ibfk_1` FOREIGN KEY (`user`) REFERENCES `usertb` (`UserID`),
  ADD CONSTRAINT `followtb_ibfk_2` FOREIGN KEY (`follower`) REFERENCES `usertb` (`UserID`);

--
-- Constraints for table `imagetb`
--
ALTER TABLE `imagetb`
  ADD CONSTRAINT `imagetb_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `usertb` (`UserID`);

--
-- Constraints for table `usertb`
--
ALTER TABLE `usertb`
  ADD CONSTRAINT `usertb_ibfk_1` FOREIGN KEY (`accountID`) REFERENCES `accounttypetb` (`accID`);

--
-- Constraints for table `user_boardtb`
--
ALTER TABLE `user_boardtb`
  ADD CONSTRAINT `user_boardtb_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `usertb` (`UserID`),
  ADD CONSTRAINT `user_boardtb_ibfk_2` FOREIGN KEY (`boardID`) REFERENCES `boardtb` (`boardid`);

--
-- Constraints for table `user_cattb`
--
ALTER TABLE `user_cattb`
  ADD CONSTRAINT `user_cattb_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `usertb` (`UserID`),
  ADD CONSTRAINT `user_cattb_ibfk_2` FOREIGN KEY (`catID`) REFERENCES `categorytb` (`catID`);

--
-- Constraints for table `user_roletb`
--
ALTER TABLE `user_roletb`
  ADD CONSTRAINT `user_roletb_ibfk_1` FOREIGN KEY (`roleID`) REFERENCES `roletb` (`roleID`),
  ADD CONSTRAINT `user_roletb_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `usertb` (`UserID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
