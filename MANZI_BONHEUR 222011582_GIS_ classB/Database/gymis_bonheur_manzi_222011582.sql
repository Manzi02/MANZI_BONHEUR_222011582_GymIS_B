-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 19, 2024 at 10:04 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gymis_bonheur_manzi_222011582`
--

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`manzi`@`localhost` PROCEDURE `DisplayGymInformation` ()   BEGIN
    
    SELECT * FROM Member;

    
    SELECT * FROM Trainer;

    
    SELECT * FROM Attendance;

    
    SELECT * FROM Payment;

    
    SELECT * FROM Equipment;

    

END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `AttendanceID` int(11) NOT NULL,
  `MemberID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `CheckInTime` time DEFAULT NULL,
  `CheckOutTime` time DEFAULT NULL,
  `TrainerID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`AttendanceID`, `MemberID`, `Date`, `CheckInTime`, `CheckOutTime`, `TrainerID`) VALUES
(90, 22, '2023-12-20', '06:30:00', '09:00:00', 10);

-- --------------------------------------------------------

--
-- Table structure for table `classschedule`
--

CREATE TABLE `classschedule` (
  `ClassID` int(11) NOT NULL,
  `TrainerID` int(11) DEFAULT NULL,
  `Time` time DEFAULT NULL,
  `ClassType` varchar(50) DEFAULT NULL,
  `Duration` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `classschedule`
--

INSERT INTO `classschedule` (`ClassID`, `TrainerID`, `Time`, `ClassType`, `Duration`) VALUES
(1, 10, '06:00:00', NULL, 3);

-- --------------------------------------------------------

--
-- Table structure for table `equipment`
--

CREATE TABLE `equipment` (
  `EquipmentID` int(11) NOT NULL,
  `Name` varchar(255) NOT NULL,
  `Type` varchar(50) DEFAULT NULL,
  `Quantity` int(11) DEFAULT NULL,
  `PurchaseDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `equipment`
--

INSERT INTO `equipment` (`EquipmentID`, `Name`, `Type`, `Quantity`, `PurchaseDate`) VALUES
(1, 'Weight benches', 'strength equipment', 3, '2023-01-01');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

CREATE TABLE `member` (
  `MemberID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Address` varchar(255) DEFAULT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `DateOfBirth` date DEFAULT NULL,
  `PaymentInformation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`MemberID`, `Name`, `Address`, `Phone`, `Email`, `DateOfBirth`, `PaymentInformation`) VALUES
(11, 'mahadi', 'kigali', '0785767688', 'mahadi@gmail.com', '2002-05-15', 'momo'),
(12, 'Gifaru', 'Muhanga', '07987659', 'gifa@gmail.com', '2001-01-01', 'MTN Mobile money'),
(22, 'emmy', '', '', '', '2003-09-08', 'MTN Mobile money'),
(33, 'Fabrice', 'Rusizi', '078654321', 'fab@gmail.com', '2002-01-01', 'Airtel money');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `PaymentID` int(11) NOT NULL,
  `MemberID` int(11) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `Amount` decimal(10,2) DEFAULT NULL,
  `PaymentMethod` varchar(50) DEFAULT NULL,
  `TransactionID` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`PaymentID`, `MemberID`, `Date`, `Amount`, `PaymentMethod`, `TransactionID`) VALUES
(1, 11, '2023-01-01', 120.00, 'momo', '1101'),
(2, 22, '2023-02-02', 150.00, '', '1102'),
(3, 33, '2023-01-01', 130.00, '', '1103');

-- --------------------------------------------------------

--
-- Table structure for table `trainer`
--

CREATE TABLE `trainer` (
  `TrainerID` int(11) NOT NULL,
  `Name` varchar(255) DEFAULT NULL,
  `Specialization` varchar(255) DEFAULT NULL,
  `ContactInformation` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `trainer`
--

INSERT INTO `trainer` (`TrainerID`, `Name`, `Specialization`, `ContactInformation`) VALUES
(2, 'Dodos', 'Weight Loss Trainer', '0791234568'),
(5, 'Emile', 'Strength trainer', '078965432'),
(10, 'Manzi', 'fitness coach', '0781234567');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `UserID` int(11) NOT NULL,
  `UserName` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`UserID`, `UserName`, `Password`) VALUES
(1, 'Bonheur', '12345'),
(2, 'Joel', '12345');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`AttendanceID`),
  ADD KEY `MemberID` (`MemberID`),
  ADD KEY `TrainerID` (`TrainerID`);

--
-- Indexes for table `classschedule`
--
ALTER TABLE `classschedule`
  ADD PRIMARY KEY (`ClassID`),
  ADD KEY `TrainerID` (`TrainerID`);

--
-- Indexes for table `equipment`
--
ALTER TABLE `equipment`
  ADD PRIMARY KEY (`EquipmentID`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`MemberID`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`PaymentID`),
  ADD KEY `MemberID` (`MemberID`);

--
-- Indexes for table `trainer`
--
ALTER TABLE `trainer`
  ADD PRIMARY KEY (`TrainerID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`UserID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `PaymentID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`MemberID`) REFERENCES `member` (`MemberID`),
  ADD CONSTRAINT `attendance_ibfk_2` FOREIGN KEY (`TrainerID`) REFERENCES `trainer` (`TrainerID`);

--
-- Constraints for table `classschedule`
--
ALTER TABLE `classschedule`
  ADD CONSTRAINT `classschedule_ibfk_1` FOREIGN KEY (`TrainerID`) REFERENCES `trainer` (`TrainerID`);

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`MemberID`) REFERENCES `member` (`MemberID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
