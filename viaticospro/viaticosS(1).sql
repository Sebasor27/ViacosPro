-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 19-01-2025 a las 03:40:44
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `viaticosS`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `facturas`
--

CREATE TABLE `facturas` (
  `id` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `detalle` text NOT NULL,
  `nombre_establecimiento` varchar(255) NOT NULL,
  `ruc_establecimiento` varchar(13) NOT NULL,
  `numero_factura` varchar(50) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `iva` decimal(10,2) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `numero_invitados` int(11) NOT NULL CHECK (`numero_invitados` <= 15),
  `created_at` timestamp NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `facturas`
--

INSERT INTO `facturas` (`id`, `fecha`, `detalle`, `nombre_establecimiento`, `ruc_establecimiento`, `numero_factura`, `subtotal`, `iva`, `total`, `numero_invitados`, `created_at`) VALUES
(1, '2025-01-14', 'ej', 'eg', '3454544545', '230088666', 20.00, 15.00, 23.00, 3, '2025-01-16 21:20:37'),
(2, '2025-01-15', 'Servicios de catering para evento corporativo', 'Restaurante La Plaza', '1234567890', '987654321', 100.00, 21.00, 121.00, 4, '2025-01-16 22:10:31'),
(3, '2025-01-17', 'ass', 'assss', '22323232', '123456', 50.00, 10.50, 60.50, 2, '2025-01-16 05:00:00'),
(4, '2025-01-17', 'assss', 'asdfsss', '2232333232', '123456', 50.00, 10.50, 60.50, 2, '2025-01-16 05:00:00'),
(5, '2025-01-17', 'assss', 'asdfsss', '2232333232', '123456', 50.00, 10.50, 60.50, 2, '2025-01-16 05:00:00'),
(6, '2025-01-15', 'Evento Corporativo', 'Restaurante El Gourmet', '1122334455', '987654', 150.00, 18.00, 168.00, 3, '2025-01-16 05:00:00'),
(7, '2025-01-15', 'Evento Corporo', 'Restaurante El Gourmet', '1122334455', '987654', 150.00, 18.00, 168.00, 3, '2025-01-16 05:00:00'),
(8, '2025-01-15', 'Evento Corporo', 'Katty Elizabeth', '2300298003001', '987654', 150.00, 18.00, 168.00, 3, '2025-01-18 05:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `invitados`
--

CREATE TABLE `invitados` (
  `id` int(11) NOT NULL,
  `factura_id` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `cargo` varchar(255) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `invitados`
--

INSERT INTO `invitados` (`id`, `factura_id`, `nombre`, `cargo`, `created_at`) VALUES
(1, 1, 'asas', 'asa', '2025-01-16 21:56:22'),
(2, 2, 'Carlos Pérez', 'Gerente General', '2025-01-16 05:00:00'),
(3, 2, 'Ana López', 'Directora de Marketing', '2025-01-16 05:00:00'),
(4, 2, 'José García', 'Jefe de Ventas', '2025-01-16 05:00:00'),
(5, 2, 'Marta Fernández', 'Responsable de Finanzas', '2025-01-16 05:00:00'),
(6, 3, 'Ana Pérez', 'Directora', '2025-01-16 23:25:56'),
(7, 3, 'Juan Ruiz', 'Gerente', '2025-01-16 23:25:56'),
(8, 4, 'An Pérez', 'Direcra', '2025-01-16 23:25:56'),
(9, 4, 'Ju Ruiz', 'Gernte', '2025-01-16 23:25:56'),
(10, 5, 'An Pérez', 'Direcra', '2025-01-16 23:25:56'),
(11, 5, 'Ju Ruiz', 'Gernte', '2025-01-16 23:25:56'),
(12, 6, 'Ana López', 'Directora', '2025-01-16 23:25:56'),
(13, 6, 'Luis Gómez', 'Gerente', '2025-01-16 23:25:56'),
(14, 6, 'Carlos Pérez', 'Coordinador', '2025-01-16 23:25:56'),
(15, 7, 'Ana López', 'Directora', '2025-01-16 23:26:34'),
(16, 7, 'Luis Gómez', 'Gerente', '2025-01-16 23:26:34'),
(17, 7, 'Carlos Pérez', 'Coordinador', '2025-01-16 23:26:34'),
(18, 8, 'Ana López', 'Dectora', '2025-01-19 02:35:04'),
(19, 8, 'Lu Gómez', 'Gerente', '2025-01-19 02:35:04'),
(20, 8, 'Carlos Pérez', 'Cooinador', '2025-01-19 02:35:04');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `logs_consultas`
--

CREATE TABLE `logs_consultas` (
  `id` int(11) NOT NULL,
  `ruc_establecimiento` varchar(13) NOT NULL,
  `fecha_consulta` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `facturas`
--
ALTER TABLE `facturas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `invitados`
--
ALTER TABLE `invitados`
  ADD PRIMARY KEY (`id`),
  ADD KEY `factura_id` (`factura_id`);

--
-- Indices de la tabla `logs_consultas`
--
ALTER TABLE `logs_consultas`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `facturas`
--
ALTER TABLE `facturas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `invitados`
--
ALTER TABLE `invitados`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT de la tabla `logs_consultas`
--
ALTER TABLE `logs_consultas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `invitados`
--
ALTER TABLE `invitados`
  ADD CONSTRAINT `invitados_ibfk_1` FOREIGN KEY (`factura_id`) REFERENCES `facturas` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
