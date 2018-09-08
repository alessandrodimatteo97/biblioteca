-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Creato il: Ago 07, 2018 alle 23:14
-- Versione del server: 10.1.28-MariaDB
-- Versione PHP: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `oosde`
--
drop database if exists  oosde ;
CREATE DATABASE IF NOT EXISTS `oosde` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `oosde`;

-- --------------------------------------------------------

--
-- Struttura della tabella `artwork`
--

DROP TABLE IF EXISTS `artwork`;
CREATE TABLE `artwork` (
  `art_id` smallint(10) NOT NULL,
  `isbn` char(13) NOT NULL,
  `title` varchar(250) NOT NULL,
  `description` text,
  `language` char(2) NOT NULL,
  `year` smallint(6) NOT NULL,
  `cat_id` smallint(10) NOT NULL,
  `artwork_main_img_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `artwork`
--

INSERT INTO `artwork` (`art_id`, `isbn`, `title`, `description`, `language`, `year`, `cat_id`, `artwork_main_img_url`) VALUES
(1, '123stella', 'Il Signore degli Anelli - La Compagnia dell\'Anello', 'Descrizione del signore degli anelli - la compagnia dell\'anello', 'IT', 1954, 1, 'LOTR.jpg');

-- --------------------------------------------------------

--
-- Struttura della tabella `author`
--

DROP TABLE IF EXISTS `author`;
CREATE TABLE `author` (
  `auth_id` smallint(10) NOT NULL,
  `name` varchar(100) NOT NULL,
  `surname` varchar(100) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `birth_place` varchar(200) DEFAULT NULL,
  `birth_country` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `author`
--

INSERT INTO `author` (`auth_id`, `name`, `surname`, `birth_date`, `birth_place`, `birth_country`) VALUES
(1, 'John Ronald Reuel', 'Tolkien', '1892-01-03', 'Bloemfontein', 'Sudafrica');

-- --------------------------------------------------------

--
-- Struttura della tabella `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `cat_id` smallint(10) NOT NULL,
  `cat_name` char(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `category`
--

INSERT INTO `category` (`cat_id`, `cat_name`) VALUES
(1, 'Fantasy');

-- --------------------------------------------------------

--
-- Struttura della tabella `image`
--

DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `img_id` smallint(10) NOT NULL,
  `image_url` varchar(255) NOT NULL,
  `artwork_id` smallint(10) NOT NULL,
  `transcription` text ,
  `validation` bool default  0 
  
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `image`
--

INSERT INTO `image` (`img_id`, `image_url`, `artwork_id`, `transcription`) VALUES
(1, 'LOTR.jpg', 1, 'trascrizione della prima pagina del signore degli anelli');

-- --------------------------------------------------------

--
-- Struttura della tabella `paternity`
--

DROP TABLE IF EXISTS `paternity`;
CREATE TABLE `paternity` (
  `pat_id` smallint(10) NOT NULL,
  `author_id` smallint(10) NOT NULL,
  `artwork_id` smallint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `paternity`
--

INSERT INTO `paternity` (`pat_id`, `author_id`, `artwork_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Struttura della tabella `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `usr_id` smallint(10) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` char(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `surname` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `residence` varchar(50) NOT NULL,
  `qualification` varchar(50) NOT NULL,
  `profession` varchar(50) NOT NULL,
  `fiscal_code` varchar(16) NOT NULL,
  `transcriber` tinyint(1) DEFAULT NULL,
  `uploader` tinyint(1) DEFAULT NULL,
  `manager` tinyint(1) DEFAULT NULL,
  `administrator` tinyint(1) DEFAULT NULL,
  `viewer` tinyint(1) DEFAULT NULL,
  `request` tinyint(1) DEFAULT NULL,
  `downloader` tinyint(1) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dump dei dati per la tabella `user`
--

INSERT INTO `user` (`usr_id`, `email`, `password`, `name`, `surname`, `birth_date`, `residence`, `qualification`, `profession`, `fiscal_code`, `transcriber`, `uploader`, `manager`, `administrator`, `viewer`, `request`, `downloader`) VALUES
(1, 'prova', 'aaa', 'Simone', 'Bucciarelli', '1990-07-04', 'Spoltore', 'Coltivatore diretto', 'Zappaorto', 'BCCSMN90L04G482O', 1, 1, 1, 1, 1, 1, 0);

-- --------------------------------------------------------

--
-- Struttura della tabella `write_assignment`
--

DROP TABLE IF EXISTS `write_assignment`;
CREATE TABLE `write_assignment` (
  `w_a_id` smallint(10) NOT NULL,
  `image_id` smallint(10) NOT NULL,
  `user_id` smallint(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Indici per le tabelle scaricate
--

--
-- Indici per le tabelle `artwork`
--
ALTER TABLE `artwork`
  ADD PRIMARY KEY (`art_id`),
  ADD UNIQUE KEY `isbn` (`isbn`),
  ADD KEY `id_categ` (`cat_id`);

--
-- Indici per le tabelle `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`auth_id`);

--
-- Indici per le tabelle `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`cat_id`);

--
-- Indici per le tabelle `image`
--
ALTER TABLE `image`
  ADD PRIMARY KEY (`img_id`),
  ADD UNIQUE KEY `url` (`image_url`),
  ADD KEY `id_pubb` (`artwork_id`);

--
-- Indici per le tabelle `paternity`
--
ALTER TABLE `paternity`
  ADD PRIMARY KEY (`pat_id`),
  ADD KEY `id_autore` (`author_id`),
  ADD KEY `id_pubb` (`artwork_id`);

--
-- Indici per le tabelle `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`usr_id`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `cf` (`fiscal_code`);

--
-- Indici per le tabelle `write_assignment`
--
ALTER TABLE `write_assignment`
  ADD PRIMARY KEY (`w_a_id`),
  ADD KEY `id_imm` (`image_id`),
  ADD KEY `id_utente` (`user_id`);

--
-- AUTO_INCREMENT per le tabelle scaricate
--

--
-- AUTO_INCREMENT per la tabella `artwork`
--
ALTER TABLE `artwork`
  MODIFY `art_id` smallint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `author`
--
ALTER TABLE `author`
  MODIFY `auth_id` smallint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `category`
--
ALTER TABLE `category`
  MODIFY `cat_id` smallint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `image`
--
ALTER TABLE `image`
  MODIFY `img_id` smallint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `paternity`
--
ALTER TABLE `paternity`
  MODIFY `pat_id` smallint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT per la tabella `user`
--
ALTER TABLE `user`
  MODIFY `usr_id` smallint(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT per la tabella `write_assignment`
--
ALTER TABLE `write_assignment`
  MODIFY `w_a_id` smallint(10) NOT NULL AUTO_INCREMENT;

--
-- Limiti per le tabelle scaricate
--

--
-- Limiti per la tabella `artwork`
--
ALTER TABLE `artwork`
  ADD CONSTRAINT `artwork_ibfk_1` FOREIGN KEY (`cat_id`) REFERENCES `category` (`cat_id`);

--
-- Limiti per la tabella `image`
--
ALTER TABLE `image`
  ADD CONSTRAINT `image_ibfk_1` FOREIGN KEY (`artwork_id`) REFERENCES `artwork` (`art_id`);

--
-- Limiti per la tabella `paternity`
--
ALTER TABLE `paternity`
  ADD CONSTRAINT `paternity_ibfk_1` FOREIGN KEY (`author_id`) REFERENCES `author` (`auth_id`),
  ADD CONSTRAINT `paternity_ibfk_2` FOREIGN KEY (`artwork_id`) REFERENCES `artwork` (`art_id`);

--
-- Limiti per la tabella `write_assignment`
--
ALTER TABLE `write_assignment`
  ADD CONSTRAINT `write_assignment_ibfk_1` FOREIGN KEY (`image_id`) REFERENCES `image` (`img_id`),
  ADD CONSTRAINT `write_assignment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`usr_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
