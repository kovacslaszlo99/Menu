-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Ápr 23. 17:53
-- Kiszolgáló verziója: 10.4.11-MariaDB
-- PHP verzió: 7.4.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Adatbázis: `menu`
--

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `asztalok`
--

CREATE TABLE `asztalok` (
  `id` int(11) NOT NULL,
  `ferohely` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `asztalok`
--

INSERT INTO `asztalok` (`id`, `ferohely`) VALUES
(1, 2),
(2, 2),
(3, 4),
(4, 4),
(5, 4),
(6, 4),
(7, 4),
(8, 6),
(9, 6),
(10, 8);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `etlap`
--

CREATE TABLE `etlap` (
  `id` int(11) NOT NULL,
  `nev` text COLLATE utf8_hungarian_ci NOT NULL,
  `ar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `etlap`
--

INSERT INTO `etlap` (`id`, `nev`, `ar`) VALUES
(1, 'Rántott sertésborda', 1600),
(2, 'Fish & Chips', 1800),
(3, 'Ananászos csirke ragu', 1900),
(4, 'Diego lakomája', 2000),
(5, 'Sid kedvence', 1350),
(6, 'Somlói galuska', 790),
(7, 'Son-go-ku', 1450),
(8, 'Hawaii pizza', 1550),
(9, 'Húsimádó pizza', 1870),
(10, 'Bambi', 450);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `foglalas`
--

CREATE TABLE `foglalas` (
  `id` int(11) NOT NULL,
  `start_idopont` datetime NOT NULL,
  `end_idopont` datetime NOT NULL,
  `asztal_id` int(11) NOT NULL,
  `nev` text COLLATE utf8_hungarian_ci NOT NULL,
  `active` enum('0','1') COLLATE utf8_hungarian_ci NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `foglalas`
--

INSERT INTO `foglalas` (`id`, `start_idopont`, `end_idopont`, `asztal_id`, `nev`, `active`) VALUES
(1, '2020-04-13 11:00:00', '2020-04-13 12:00:00', 3, 'Asztalos András', '1'),
(2, '2020-05-18 12:30:00', '2020-05-18 13:30:00', 4, 'Balogh Béla', '1'),
(3, '2020-04-26 12:00:00', '2020-04-26 13:30:00', 3, 'Sári Sára', '1'),
(4, '2020-05-02 21:30:00', '2020-05-02 22:00:00', 9, 'Kondorosi Kelemen', '1'),
(5, '2020-05-10 09:30:00', '2020-05-10 10:30:00', 10, 'Debreczeni Dávid', '1'),
(6, '2020-05-30 13:00:00', '2020-05-30 13:30:00', 1, 'Zsindelyes Zsuzsa', '1'),
(7, '2020-05-07 15:00:00', '2020-05-07 15:30:00', 1, 'Lakatos Lali', '1'),
(8, '2020-05-22 17:30:00', '2020-05-22 18:30:00', 6, 'Hármán Hugó', '1');

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendeles`
--

CREATE TABLE `rendeles` (
  `id` int(11) NOT NULL,
  `fogalas_id` int(11) NOT NULL,
  `etel_id` int(11) NOT NULL,
  `asztal_id` int(11) NOT NULL,
  `mennyiseg` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

--
-- A tábla adatainak kiíratása `rendeles`
--

INSERT INTO `rendeles` (`id`, `fogalas_id`, `etel_id`, `asztal_id`, `mennyiseg`) VALUES
(1, 1, 3, 3, 1),
(2, 2, 10, 4, 3),
(3, 1, 1, 3, 2),
(4, 4, 9, 9, 1),
(5, 7, 2, 1, 2);

--
-- Indexek a kiírt táblákhoz
--

--
-- A tábla indexei `asztalok`
--
ALTER TABLE `asztalok`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `etlap`
--
ALTER TABLE `etlap`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `foglalas`
--
ALTER TABLE `foglalas`
  ADD PRIMARY KEY (`id`);

--
-- A tábla indexei `rendeles`
--
ALTER TABLE `rendeles`
  ADD PRIMARY KEY (`id`);

--
-- A kiírt táblák AUTO_INCREMENT értéke
--

--
-- AUTO_INCREMENT a táblához `asztalok`
--
ALTER TABLE `asztalok`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `etlap`
--
ALTER TABLE `etlap`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT a táblához `foglalas`
--
ALTER TABLE `foglalas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT a táblához `rendeles`
--
ALTER TABLE `rendeles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
