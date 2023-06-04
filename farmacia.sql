-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-06-2023 a las 23:44:49
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `farmacia`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `mail` varchar(50) NOT NULL,
  `pass` varchar(100) NOT NULL,
  `name` varchar(100) NOT NULL,
  `lastLog` date NOT NULL,
  `session` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`mail`, `pass`, `name`, `lastLog`, `session`) VALUES
('dr.dani@gmeil.com', '1245', 'Daniel Sastre', '2023-06-01', '302130272'),
('Dr.Marco@gmeil.com', '1234', 'Helmut Marko Visth', '2023-06-04', '875116851'),
('drNano@gmeil.com', '3333', 'Fernando Alonso Diaz', '2023-06-04', '780778437');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicine`
--

CREATE TABLE `medicine` (
  `id` int(20) UNSIGNED NOT NULL,
  `name` varchar(50) NOT NULL,
  `tmax` float NOT NULL,
  `tmin` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `medicine`
--

INSERT INTO `medicine` (`id`, `name`, `tmax`, `tmin`) VALUES
(1, 'ARANESP ', 10, 5),
(2, 'BLEOMICINA', 12, 6),
(3, 'ESMERON', 6, 0),
(5, 'Kyubi', 30, 3),
(6, 'nomolestar', 11, 2),
(7, 'Warhammer', 5, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `patient`
--

CREATE TABLE `patient` (
  `mail` varchar(50) NOT NULL,
  `name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `patient`
--

INSERT INTO `patient` (`mail`, `name`) VALUES
('jf@gmeil.com', 'Juan Francisco '),
('lucas@gmail.com', 'Lucas Montes'),
('lunaestigma@gmeil.es', 'Luna de la Rosa'),
('naniroma@gmeil.es', 'Fernando Roma Atril'),
('naruto@gmeil.com', 'Naruto Uzumaki '),
('Sol@gmeil.com', 'Sol Musica');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `xip`
--

CREATE TABLE `xip` (
  `id` int(10) NOT NULL,
  `doctor_mail` varchar(50) NOT NULL,
  `id_medicine` int(20) NOT NULL,
  `id_patient` varchar(50) NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_spanish2_ci;

--
-- Volcado de datos para la tabla `xip`
--

INSERT INTO `xip` (`id`, `doctor_mail`, `id_medicine`, `id_patient`, `date`) VALUES
(1, 'drNano@gmeil.com', 2, 'lucas@gmail.com', '2023-06-05'),
(8, 'drNano@gmeil.com', 6, 'lunaestigma@gmeil.es', '2023-06-05'),
(12, 'Dr.Marco@gmeil.com', 5, 'lunaestigma@gmeil.es', '2023-06-06'),
(40000, 'Dr.Marco@gmeil.com', 7, 'jf@gmeil.com', '2023-06-07');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `medicine`
--
ALTER TABLE `medicine`
  ADD PRIMARY KEY (`id`) USING BTREE;

--
-- Indices de la tabla `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`mail`);

--
-- Indices de la tabla `xip`
--
ALTER TABLE `xip`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `id_medicine` (`id_medicine`),
  ADD KEY `doctor_mail` (`doctor_mail`),
  ADD KEY `id_patient` (`id_patient`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `medicine`
--
ALTER TABLE `medicine`
  MODIFY `id` int(20) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `xip`
--
ALTER TABLE `xip`
  MODIFY `id_medicine` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `xip`
--
ALTER TABLE `xip`
  ADD CONSTRAINT `xip_ibfk_1` FOREIGN KEY (`doctor_mail`) REFERENCES `doctor` (`mail`),
  ADD CONSTRAINT `xip_ibfk_2` FOREIGN KEY (`id_patient`) REFERENCES `patient` (`mail`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
