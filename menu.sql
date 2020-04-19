-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Gép: 127.0.0.1
-- Létrehozás ideje: 2020. Ápr 19. 16:46
-- Kiszolgáló verziója: 10.4.11-MariaDB
-- PHP verzió: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
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
(1, 4),
(2, 6);

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `etlap`
--

CREATE TABLE `etlap` (
  `id` int(11) NOT NULL,
  `nev` text COLLATE utf8_hungarian_ci NOT NULL,
  `ar` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

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

-- --------------------------------------------------------

--
-- Tábla szerkezet ehhez a táblához `rendeles`
--

CREATE TABLE `rendeles` (
  `id` int(11) NOT NULL,
  `fogalas_id` int(11) NOT NULL,
  `etel_id` int(11) NOT NULL,
  `asztal_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_hungarian_ci;

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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT a táblához `etlap`
--
ALTER TABLE `etlap`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `foglalas`
--
ALTER TABLE `foglalas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT a táblához `rendeles`
--
ALTER TABLE `rendeles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
