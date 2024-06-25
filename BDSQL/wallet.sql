-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 25-06-2024 a las 08:09:19
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
-- Base de datos: `wallet`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `run` varchar(12) NOT NULL,
  `nombre1` varchar(45) NOT NULL,
  `nombre2` varchar(45) NOT NULL,
  `appaterno` varchar(45) NOT NULL,
  `apmaterno` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`run`, `nombre1`, `nombre2`, `appaterno`, `apmaterno`) VALUES
('16286667-2', 'Claudia', 'Maria', 'Hope', 'Fierro'),
('16330225-k', 'pedro', 'carlos', 'Carmelo', 'Cuevas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuenta`
--

CREATE TABLE `cuenta` (
  `nroCuenta` int(20) NOT NULL,
  `alias` varchar(45) NOT NULL,
  `banco` varchar(45) NOT NULL,
  `saldo` double NOT NULL,
  `run_cliente` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuenta`
--

INSERT INTO `cuenta` (`nroCuenta`, `alias`, `banco`, `saldo`, `run_cliente`) VALUES
(5, 'LaClaudia', 'Banco de Chile', 0, '16286667-2'),
(37, 'ElPedrox', 'Banco Estado', 57, '16330225-k');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentatercero`
--

CREATE TABLE `cuentatercero` (
  `idCuentaTercero` int(11) NOT NULL,
  `nroCuentaTercero` int(20) NOT NULL,
  `run_clienteCuentaTercero` varchar(12) NOT NULL,
  `nroCuentaOrigen` int(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuentatercero`
--

INSERT INTO `cuentatercero` (`idCuentaTercero`, `nroCuentaTercero`, `run_clienteCuentaTercero`, `nroCuentaOrigen`) VALUES
(1, 37, '16330225-k', 37),
(2, 5, '16286667-2', 37);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `moneda`
--

CREATE TABLE `moneda` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `signo` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transaccion`
--

CREATE TABLE `transaccion` (
  `id` int(11) NOT NULL,
  `idUsuarioEnvia` int(11) NOT NULL,
  `idUsuarioRecive` int(11) NOT NULL,
  `saldo` double NOT NULL,
  `date` date NOT NULL,
  `idMoneda` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `correo_electronico` varchar(60) NOT NULL,
  `contrasena` varchar(100) NOT NULL,
  `run_cliente` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `nombre`, `correo_electronico`, `contrasena`, `run_cliente`) VALUES
(1, 'Non sed recusandae ', 'pinuheman@mailinator.com', '111', '16330225-k'),
(2, 'Id et neque veniam ut nostrud quaerat optio', 'qeziju@mailinator.com', '111', '16286667-2');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`run`);

--
-- Indices de la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD PRIMARY KEY (`nroCuenta`),
  ADD KEY `fk_run_cliente_cuenta` (`run_cliente`);

--
-- Indices de la tabla `cuentatercero`
--
ALTER TABLE `cuentatercero`
  ADD PRIMARY KEY (`idCuentaTercero`),
  ADD KEY `fk_run_cliente_cuentatercero` (`run_clienteCuentaTercero`),
  ADD KEY `fk_nroCuenta_cuentatercero` (`nroCuentaOrigen`);

--
-- Indices de la tabla `moneda`
--
ALTER TABLE `moneda`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_idUsuarioEnvia_transaccion` (`idUsuarioEnvia`),
  ADD KEY `fk_idUsuarioRecive_transaccion` (`idUsuarioRecive`),
  ADD KEY `fk_idMoneda_transaccion` (`idMoneda`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_run_cliente_usuario` (`run_cliente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentatercero`
--
ALTER TABLE `cuentatercero`
  MODIFY `idCuentaTercero` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `moneda`
--
ALTER TABLE `moneda`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuenta`
--
ALTER TABLE `cuenta`
  ADD CONSTRAINT `fk_run_cliente_cuenta` FOREIGN KEY (`run_cliente`) REFERENCES `cliente` (`run`);

--
-- Filtros para la tabla `cuentatercero`
--
ALTER TABLE `cuentatercero`
  ADD CONSTRAINT `fk_nroCuenta_cuentatercero` FOREIGN KEY (`nroCuentaOrigen`) REFERENCES `cuenta` (`nroCuenta`),
  ADD CONSTRAINT `fk_run_cliente_cuentatercero` FOREIGN KEY (`run_clienteCuentaTercero`) REFERENCES `cliente` (`run`);

--
-- Filtros para la tabla `transaccion`
--
ALTER TABLE `transaccion`
  ADD CONSTRAINT `fk_idMoneda_transaccion` FOREIGN KEY (`idMoneda`) REFERENCES `moneda` (`id`),
  ADD CONSTRAINT `fk_idUsuarioEnvia_transaccion` FOREIGN KEY (`idUsuarioEnvia`) REFERENCES `usuario` (`id`),
  ADD CONSTRAINT `fk_idUsuarioRecive_transaccion` FOREIGN KEY (`idUsuarioRecive`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `fk_run_cliente_usuario` FOREIGN KEY (`run_cliente`) REFERENCES `cliente` (`run`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
