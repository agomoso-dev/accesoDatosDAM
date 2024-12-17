-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 13-12-2024 a las 11:48:18
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `ventas`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `licencias`
--

CREATE TABLE `licencias` (
  `id` int(11) NOT NULL,
  `tipo` varchar(2) NOT NULL,
  `expedicion` datetime NOT NULL,
  `caducidad` datetime NOT NULL,
  `usuario_id` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `licencias`
--

INSERT INTO `licencias` (`id`, `tipo`, `expedicion`, `caducidad`, `usuario_id`) VALUES
(6, 'A1', '2024-12-17 15:15:37', '2025-01-09 17:19:23', '12434403F'),
(7, 'B1', '2024-12-10 05:23:28', '2024-12-26 11:22:38', '12434403F'),
(8, 'B2', '2025-02-12 12:22:39', '2025-03-27 12:46:30', '33434412A'),
(9, 'E1', '2024-12-16 15:27:06', '2024-12-31 14:23:41', '33434412A'),
(10, 'F2', '2025-04-25 14:35:49', '2025-05-15 05:36:35', '33434412A'),
(11, 'B1', '2023-01-01 00:00:00', '2026-01-01 00:00:00', '33434412A'),
(13, 'B1', '2023-01-01 00:00:00', '2026-01-01 00:00:00', '33434412A'),
(14, 'A1', '2022-05-15 00:00:00', '2025-05-15 00:00:00', '12434403F'),
(17, 'B1', '2025-02-02 10:02:00', '2027-01-01 20:00:00', '15443402k'),
(18, 'D1', '2022-05-15 00:00:00', '2030-05-15 00:00:00', '15443402k');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(5) NOT NULL,
  `nombre` varchar(40) NOT NULL,
  `precio` double NOT NULL,
  `cantidad` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `productos`
--

INSERT INTO `productos` (`id`, `nombre`, `precio`, `cantidad`) VALUES
(1, 'Producto Actualizado', 150, 12),
(2, 'Mantas', 70, 50),
(4, 'Cojin', 11, 21783),
(5, 'calentador', 32, 1222);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `direccion` varchar(255) NOT NULL,
  `cp` varchar(5) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `dni` varchar(9) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`direccion`, `cp`, `nombre`, `dni`) VALUES
('Calel payo 1', '11632', 'Jamito jamiel', '12434403F'),
('Calle Magica, 32', '41001', 'Juan Manuel', '15443402k'),
('Calel maikel sien pies', '11530', 'Omaiga Rubius', '33434412A');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `licencias`
--
ALTER TABLE `licencias`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`dni`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `licencias`
--
ALTER TABLE `licencias`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
