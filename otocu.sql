-- phpMyAdmin SQL Dump
-- version 4.9.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:8889
-- Generation Time: Dec 21, 2021 at 11:33 PM
-- Server version: 5.7.26
-- PHP Version: 7.4.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `otocu`
--

-- --------------------------------------------------------

--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `create_car` text NOT NULL,
  `nation` varchar(20) NOT NULL,
  `form` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `gear` varchar(10) NOT NULL,
  `fuel` varchar(10) NOT NULL,
  `gone` text NOT NULL,
  `quality` text NOT NULL,
  `price` text NOT NULL,
  `city` text NOT NULL,
  `brand` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `car`
--

INSERT INTO `car` (`id`, `name`, `create_car`, `nation`, `form`, `state`, `gear`, `fuel`, `gone`, `quality`, `price`, `city`, `brand`) VALUES
(62, 'Civic', '2021-12-18', 'Trong nước', 'Sedan', 'Mới', 'auto', 'xang', '10.000', 'Chưa có', '1.000.000.000', 'hcm', 'honda'),
(63, 'Inova', '2012-10-02', 'Trong nước', '7 chỗ', 'Cũ', 'manual', 'dau', '100.000', 'Thuỷ Kích', '300.000.000', 'dn', 'toyota');

-- --------------------------------------------------------

--
-- Table structure for table `car_pic`
--

DROP TABLE IF EXISTS `car_pic`;
CREATE TABLE `car_pic` (
  `id_pic` int(11) NOT NULL,
  `name` text NOT NULL,
  `id_car` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `car_pic`
--

INSERT INTO `car_pic` (`id_pic`, `name`, `id_car`) VALUES
(72, 'Civic-do-41-of-41-1634179887.jpg', 62),
(73, 'Civic-do-41-of-41-1634179887.jpg', 62),
(74, 'Civic-do-41-of-41-1634179887.jpg', 62),
(75, 'images-2.jpeg', 63),
(76, '640-honda-accord-ra-mat-tai-viet-nam.jpg', 63),
(77, 'Civic-do-41-of-41-1634179887.jpg', 63);

-- --------------------------------------------------------

--
-- Table structure for table `cat_user`
--

DROP TABLE IF EXISTS `cat_user`;
CREATE TABLE `cat_user` (
  `id_cat` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cat_user`
--

INSERT INTO `cat_user` (`id_cat`, `name`) VALUES
(1, 'Nhân Viên'),
(2, 'Admin'),
(3, 'qwe'),
(4, 'Khách hàng');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL,
  `name_comment` varchar(50) NOT NULL,
  `message` text NOT NULL,
  `created_date` date NOT NULL,
  `id_new` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `loai_tin`
--

DROP TABLE IF EXISTS `loai_tin`;
CREATE TABLE `loai_tin` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `price` int(11) NOT NULL,
  `upload` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `loai_tin`
--

INSERT INTO `loai_tin` (`id`, `name`, `price`, `upload`) VALUES
(5, 'Thường', 1000, 'Có'),
(6, 'VIP 1', 5000, 'Có'),
(7, 'Thường', 2000, 'Có');

-- --------------------------------------------------------

--
-- Table structure for table `News`
--

DROP TABLE IF EXISTS `News`;
CREATE TABLE `News` (
  `id_new` int(11) NOT NULL,
  `id_car` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `title` varchar(64) NOT NULL,
  `description` text NOT NULL,
  `supportfordrive` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `News`
--

INSERT INTO `News` (`id_new`, `id_car`, `id_user`, `title`, `description`, `supportfordrive`) VALUES
(9, 62, 11, 'Bán civic 1.5 Turbo', 'Xe mới chạy còn zin , và còn raast chi là mới xin anh chị liên hệ 0767029031 và xe có hỗ trợ trả góp luôn nha anh chị em mình , chúng mình chỉ bán xe trực tiếp', 'Đã thanh toán'),
(10, 63, 11, 'Bán xe inova nhà chạy', 'xe không chạy dịch vụ còn nguyên zin liên hệ 0767029031 để biết thêm chi tiết xem xe trực tiếp không qua cò lái', 'Đã thanh toán');

-- --------------------------------------------------------

--
-- Table structure for table `tinh_tong`
--

DROP TABLE IF EXISTS `tinh_tong`;
CREATE TABLE `tinh_tong` (
  `id` int(11) NOT NULL,
  `id_new` int(11) NOT NULL,
  `id_loaitin` int(11) NOT NULL,
  `quantity` int(11) NOT NULL,
  `total_price` int(11) NOT NULL,
  `state` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tinh_tong`
--

INSERT INTO `tinh_tong` (`id`, `id_new`, `id_loaitin`, `quantity`, `total_price`, `state`) VALUES
(1, 9, 6, 15, 75000, 'Còn hạn'),
(2, 10, 6, 30, 150000, 'Hết hạn');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `sdt` int(11) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` text NOT NULL,
  `id_cat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `name`, `sdt`, `email`, `password`, `id_cat`) VALUES
(10, 'Phạm Huy', 767029031, 'admin@gmail.com', '123123', 2),
(11, 'Xuyến', 903727402, 'xuyen123@gmail.com', '123', 3),
(12, 'Pham Hao', 717301348, 'phamhao1@gmail.com', '123', 1),
(13, 'Phạm Thị Hương', 902480997, 'phamthihuong@gmail.com', '123', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `car`
--
ALTER TABLE `car`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `car_pic`
--
ALTER TABLE `car_pic`
  ADD PRIMARY KEY (`id_pic`),
  ADD KEY `FK_car` (`id_car`);

--
-- Indexes for table `cat_user`
--
ALTER TABLE `cat_user`
  ADD PRIMARY KEY (`id_cat`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id_comment`),
  ADD KEY `FK_new_comment` (`id_new`);

--
-- Indexes for table `loai_tin`
--
ALTER TABLE `loai_tin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `News`
--
ALTER TABLE `News`
  ADD PRIMARY KEY (`id_new`),
  ADD KEY `FK_car_new` (`id_car`),
  ADD KEY `FK_user_new` (`id_user`);

--
-- Indexes for table `tinh_tong`
--
ALTER TABLE `tinh_tong`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_loai_tin` (`id_loaitin`),
  ADD KEY `FK_new` (`id_new`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD KEY `FK_cat_user` (`id_cat`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `car`
--
ALTER TABLE `car`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT for table `car_pic`
--
ALTER TABLE `car_pic`
  MODIFY `id_pic` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT for table `cat_user`
--
ALTER TABLE `cat_user`
  MODIFY `id_cat` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id_comment` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `loai_tin`
--
ALTER TABLE `loai_tin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `News`
--
ALTER TABLE `News`
  MODIFY `id_new` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tinh_tong`
--
ALTER TABLE `tinh_tong`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `car_pic`
--
ALTER TABLE `car_pic`
  ADD CONSTRAINT `FK_car` FOREIGN KEY (`id_car`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK_new_comment` FOREIGN KEY (`id_new`) REFERENCES `News` (`id_new`);

--
-- Constraints for table `News`
--
ALTER TABLE `News`
  ADD CONSTRAINT `FK_car_new` FOREIGN KEY (`id_car`) REFERENCES `car` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_user_new` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `tinh_tong`
--
ALTER TABLE `tinh_tong`
  ADD CONSTRAINT `FK_loai_tin` FOREIGN KEY (`id_loaitin`) REFERENCES `loai_tin` (`id`),
  ADD CONSTRAINT `FK_new` FOREIGN KEY (`id_new`) REFERENCES `News` (`id_new`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_cat_user` FOREIGN KEY (`id_cat`) REFERENCES `cat_user` (`id_cat`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
