-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost
-- Tiempo de generación: 29-05-2018 a las 16:41:46
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `Ecommerce`
--
DROP DATABASE IF EXISTS `Ecommerce`;
CREATE DATABASE IF NOT EXISTS `Ecommerce` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `Ecommerce`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `LINEA_PEDIDOS`
--

CREATE TABLE `LINEA_PEDIDOS` (
  `ID_LINEA_PEDIDO` int(11) NOT NULL,
  `COSTE_UNITARIO` decimal(10,2) NOT NULL,
  `CANTIDAD` int(11) NOT NULL,
  `SUBTOTAL` decimal(15,2) NOT NULL,
  `ID_PEDIDO` int(11) NOT NULL,
  `ID_PRODUCTO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PEDIDOS`
--

CREATE TABLE `PEDIDOS` (
  `ID_PEDIDO` int(11) NOT NULL,
  `FECHA_PEDIDO` date NOT NULL,
  `ID_USUARIO` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PRODUCTOS`
--

CREATE TABLE `PRODUCTOS` (
  `ID_PRODUCTO` int(11) NOT NULL,
  `NOMBRE` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `PRECIO_COMPRA` decimal(10,2) NOT NULL,
  `PRECIO_VENTA` decimal(10,2) NOT NULL,
  `PORCENTAJE_OFERTA` decimal(10,2) NOT NULL,
  `DESCRIPCION` varchar(60) COLLATE utf8_spanish2_ci NOT NULL,
  `FOTO` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `ACTIVO` char(1) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `PRODUCTOS`
--

INSERT INTO `PRODUCTOS` (`ID_PRODUCTO`, `NOMBRE`, `PRECIO_COMPRA`, `PRECIO_VENTA`, `PORCENTAJE_OFERTA`, `DESCRIPCION`, `FOTO`, `ACTIVO`) VALUES
(1, 'Mastil Aluminio (Anemometro Ultrasonico)', '30.00', '50.00', '1.00', 'Compuesto por 3 partes: Base, mastil y parte superior.', 'aluminum.jpeg', 'S'),
(2, 'Mastil Fibra Carbono (Anem. Ultrasonico)', '50.00', '75.00', '1.00', 'Compuesto por 3 partes: Base, mastil y parte superior.', 'carbon.jpeg', 'S'),
(3, 'Cups 4.0', '450.00', '575.00', '1.00', 'Anemometro alimentado con energia solar.', 'cups.jpeg', 'S'),
(4, 'Soporte mastil Cups 4.0', '25.00', '32.00', '1.00', 'Accesorio para el Cups 4.0. Soporte para mastil.', 'mast.jpeg', 'S'),
(5, 'Nmea Connect', '150.00', '181.00', '1.00', 'Accesorio para Cups 4.0. Permite la conexion Bluetooth.', 'nmeaCon.jpeg', 'S'),
(6, 'Soporte rail Cups 4.0', '35.00', '59.00', '1.00', 'Accesorio Cups 4.0. Permite colocar el Cups en un rail.', 'rail.jpeg', 'S'),
(7, 'Montura mastil y rail Cups 4.0', '80.00', '99.00', '1.00', 'Accesorio Cups 4.0. Tambien se venden por separado.', 'railMout.jpeg', 'S'),
(8, 'Mastil Fibra Carbono (Cups 4.0)', '160.00', '190.00', '1.00', 'Accesorio para Cups 4.0. Tubo de fibra de carbono.', 'tuboFibra.jpeg', 'S'),
(9, 'Anemometro Ultrasonico Portable', '400.00', '499.00', '1.00', 'Conectividad Bluetooth 4.0. Alimentado por energia solar.', 'ultraPortable.jpeg', 'S'),
(10, 'Anemometro Ultrasonico Wired', '410.00', '499.00', '1.00', 'Conectividad Bluetooth 4.0. Precisa de alimentacion externa.', 'ultraWired.jpeg', 'S');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ROLES`
--

CREATE TABLE `ROLES` (
  `ID_ROL` int(11) NOT NULL,
  `NOMBRE` varchar(25) COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `ROLES`
--

INSERT INTO `ROLES` (`ID_ROL`, `NOMBRE`) VALUES
(1, 'Administrador'),
(2, 'Registrado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `USUARIOS`
--

CREATE TABLE `USUARIOS` (
  `ID_USUARIO` int(11) NOT NULL,
  `NOMBRE` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `APELLIDO_1` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `APELLIDO_2` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `FECHA_NACIMIENTO` date NOT NULL,
  `DIRECCION_ENVIO` varchar(50) COLLATE utf8_spanish2_ci NOT NULL,
  `EMAIL` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `PASSWORD` varchar(40) COLLATE utf8_spanish2_ci NOT NULL,
  `ACTIVO` char(1) COLLATE utf8_spanish2_ci NOT NULL,
  `ID_ROL` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `USUARIOS`
--

INSERT INTO `USUARIOS` (`ID_USUARIO`, `NOMBRE`, `APELLIDO_1`, `APELLIDO_2`, `FECHA_NACIMIENTO`, `DIRECCION_ENVIO`, `EMAIL`, `PASSWORD`, `ACTIVO`, `ID_ROL`) VALUES
(1, 'Guillermo', 'S.', 'S.', '1990-03-21', 'Calle S.A.', 'guillermo@gmail.com', '1', 'S', 1),
(2, 'Daniel', 'S.', 'S.', '1991-04-03', 'Calle A.S.', 'daniel@gmail.com', '1', 'S', 2),
(3, 'Alberto', 'H.', 'K.', '1991-05-09', 'Calle S.V.', 'alberto@gmail.com', '1', 'S', 1),
(4, 'Manuel', 'S.', 'S.', '1981-12-20', 'Calle M.N.', 'manolo@gmail.com', '1', 'S', 2);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `LINEA_PEDIDOS`
--
ALTER TABLE `LINEA_PEDIDOS`
  ADD PRIMARY KEY (`ID_LINEA_PEDIDO`),
  ADD UNIQUE KEY `U_PEDIDO_PRODUCTO` (`ID_PEDIDO`,`ID_PRODUCTO`) USING BTREE,
  ADD KEY `FK_ID_PRODUCTO` (`ID_PRODUCTO`);

--
-- Indices de la tabla `PEDIDOS`
--
ALTER TABLE `PEDIDOS`
  ADD PRIMARY KEY (`ID_PEDIDO`),
  ADD KEY `FK_ID_USUARIO` (`ID_USUARIO`) USING BTREE;

--
-- Indices de la tabla `PRODUCTOS`
--
ALTER TABLE `PRODUCTOS`
  ADD PRIMARY KEY (`ID_PRODUCTO`);

--
-- Indices de la tabla `ROLES`
--
ALTER TABLE `ROLES`
  ADD PRIMARY KEY (`ID_ROL`),
  ADD UNIQUE KEY `nombre` (`NOMBRE`);

--
-- Indices de la tabla `USUARIOS`
--
ALTER TABLE `USUARIOS`
  ADD PRIMARY KEY (`ID_USUARIO`),
  ADD UNIQUE KEY `U_EMAIL` (`EMAIL`) USING BTREE,
  ADD KEY `FK_ID_ROL` (`ID_ROL`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `LINEA_PEDIDOS`
--
ALTER TABLE `LINEA_PEDIDOS`
  MODIFY `ID_LINEA_PEDIDO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `PEDIDOS`
--
ALTER TABLE `PEDIDOS`
  MODIFY `ID_PEDIDO` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `PRODUCTOS`
--
ALTER TABLE `PRODUCTOS`
  MODIFY `ID_PRODUCTO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ROLES`
--
ALTER TABLE `ROLES`
  MODIFY `ID_ROL` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `USUARIOS`
--
ALTER TABLE `USUARIOS`
  MODIFY `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `LINEA_PEDIDOS`
--
ALTER TABLE `LINEA_PEDIDOS`
  ADD CONSTRAINT `FK_ID_PEDIDO` FOREIGN KEY (`ID_PEDIDO`) REFERENCES `PEDIDOS` (`ID_PEDIDO`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `FK_ID_PRODUCTO` FOREIGN KEY (`ID_PRODUCTO`) REFERENCES `PRODUCTOS` (`ID_PRODUCTO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `PEDIDOS`
--
ALTER TABLE `PEDIDOS`
  ADD CONSTRAINT `FK_ID_USUARIO` FOREIGN KEY (`ID_USUARIO`) REFERENCES `USUARIOS` (`ID_USUARIO`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `USUARIOS`
--
ALTER TABLE `USUARIOS`
  ADD CONSTRAINT `FK_ID_ROL` FOREIGN KEY (`ID_ROL`) REFERENCES `ROLES` (`ID_ROL`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
