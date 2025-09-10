-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 10, 2025 at 12:45 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oop_project`
--

-- --------------------------------------------------------

--
-- Table structure for table `bus`
--

CREATE TABLE `bus` (
  `bus_id` int(11) NOT NULL,
  `bus_name` varchar(50) DEFAULT NULL,
  `source` varchar(50) DEFAULT NULL,
  `destination` varchar(50) DEFAULT NULL,
  `seat` int(11) DEFAULT NULL,
  `starting_time` varchar(20) DEFAULT NULL,
  `starting_place` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `bus`
--

INSERT INTO `bus` (`bus_id`, `bus_name`, `source`, `destination`, `seat`, `starting_time`, `starting_place`) VALUES
(1, 'Sagorika', 'Sylhet', 'Rajshahi', 40, '2:00PM', 'Kodomtoli'),
(3, 'Sagorika', 'Sylhet', 'Naogaon', 45, '10:00 AM', 'Sylhet Bus Terminal'),
(4, 'Green Line', 'Dhaka', 'Chittagong', 50, '09:30 AM', 'Dhaka Sadarghat'),
(5, 'Hanif Paribahan', 'Dhaka', 'Rajshahi', 42, '07:45 AM', 'Gabtoli Bus Stand'),
(6, 'Shyamoli', 'Chittagong', 'Dhaka', 48, '06:00 AM', 'Chittagong Bus Terminal'),
(7, 'SR', 'Sylhet', 'Dhaka', 40, '2:00PM', 'Sylhet Bus Terminal'),
(8, 'United express', 'Sylhet', 'Rajshahi', 45, '3:00PM', 'Kodomtoli');

-- --------------------------------------------------------

--
-- Table structure for table `passenger`
--

CREATE TABLE `passenger` (
  `passenger_id` int(11) NOT NULL,
  `passenger_name` varchar(50) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `gender` varchar(10) DEFAULT NULL,
  `contact_num` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `passenger`
--

INSERT INTO `passenger` (`passenger_id`, `passenger_name`, `age`, `gender`, `contact_num`, `email`) VALUES
(1, 'Nitu', 22, 'Female', '018*****', 'nitu*****'),
(2, 'joya', 21, 'Female', '018******', 'joya******'),
(3, 'Fahiyan', 23, 'Male', '013*****', 'fahiyan******'),
(4, 'Niha', 16, 'Female', '019*****', 'niha******'),
(5, 'Shayonti', 22, 'Female', '014******', 'shayonti******'),
(6, 'Fiha', 8, 'Female', '01303****', 'fiha******'),
(7, 'Niha', 22, 'female', '014****', 'niha*****');

-- --------------------------------------------------------

--
-- Table structure for table `reservation`
--

CREATE TABLE `reservation` (
  `reservation_id` int(11) NOT NULL,
  `bus_id` int(11) DEFAULT NULL,
  `bus_name` varchar(50) DEFAULT NULL,
  `passenger_id` int(11) DEFAULT NULL,
  `passenger_name` varchar(50) DEFAULT NULL,
  `seat_number` int(11) DEFAULT NULL,
  `reservation_date` date DEFAULT NULL,
  `reservation_time` varchar(20) DEFAULT NULL,
  `travel_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reservation`
--

INSERT INTO `reservation` (`reservation_id`, `bus_id`, `bus_name`, `passenger_id`, `passenger_name`, `seat_number`, `reservation_date`, `reservation_time`, `travel_date`) VALUES
(1, 1, 'Sagorika', 1, 'Nitu', 5, '2025-09-07', '11:04:38', '2025-02-01'),
(2, 3, 'Sagorika', 2, 'joya', 4, '2025-09-07', '11:05:31', '2025-10-21'),
(4, 5, 'Hanif Paribahan', 4, 'Niha', 1, '2025-09-07', '11:11:07', '2025-10-25'),
(5, 3, 'Sagorika', 5, 'Shayonti', 8, '2025-09-07', '11:50:45', '2025-10-21'),
(6, 1, 'Sagorika', 6, 'Fiha', 3, '2025-09-08', '14:06:28', '2025-01-29'),
(7, 3, 'Sagorika', 7, 'Niha', 2, '2025-09-08', '14:47:31', '2025-01-02');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bus`
--
ALTER TABLE `bus`
  ADD PRIMARY KEY (`bus_id`);

--
-- Indexes for table `passenger`
--
ALTER TABLE `passenger`
  ADD PRIMARY KEY (`passenger_id`);

--
-- Indexes for table `reservation`
--
ALTER TABLE `reservation`
  ADD PRIMARY KEY (`reservation_id`),
  ADD KEY `bus_id` (`bus_id`),
  ADD KEY `passenger_id` (`passenger_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `bus`
--
ALTER TABLE `bus`
  MODIFY `bus_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `passenger`
--
ALTER TABLE `passenger`
  MODIFY `passenger_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `reservation`
--
ALTER TABLE `reservation`
  MODIFY `reservation_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `reservation`
--
ALTER TABLE `reservation`
  ADD CONSTRAINT `reservation_ibfk_1` FOREIGN KEY (`bus_id`) REFERENCES `bus` (`bus_id`),
  ADD CONSTRAINT `reservation_ibfk_2` FOREIGN KEY (`passenger_id`) REFERENCES `passenger` (`passenger_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
