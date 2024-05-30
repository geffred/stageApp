-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : jeu. 30 mai 2024 à 11:31
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `stage`
--

-- --------------------------------------------------------

--
-- Structure de la table `enfant`
--

CREATE TABLE `enfant` (
  `date_naiss` date NOT NULL,
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `sexe` enum('M','F') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `enfant`
--

INSERT INTO `enfant` (`date_naiss`, `id`, `nom`, `prenom`, `sexe`) VALUES
('2008-12-12', 1, 'Martin', 'Lea', 'F'),
('2010-07-17', 2, 'Dupont', 'Lucas', 'M'),
('2011-11-15', 3, 'Durand', 'Emma', 'F'),
('2014-09-30', 4, 'Lefevre', 'Hugo', 'M'),
('2014-09-30', 5, 'Moreau', 'Chloe', 'F'),
('2015-06-17', 6, 'Girard', 'Nathan', 'M'),
('2012-12-08', 7, 'Lambert', 'Alice', 'F'),
('2008-04-21', 8, 'Bonnet', 'Gabriel', 'F'),
('2013-08-10', 9, 'Morel', 'Zoe', 'F'),
('2009-02-11', 10, 'Petit', 'Louis', 'M');

-- --------------------------------------------------------

--
-- Structure de la table `inscription`
--

CREATE TABLE `inscription` (
  `enfant_id` int(11) DEFAULT NULL,
  `id` int(11) NOT NULL,
  `paye` bit(1) NOT NULL,
  `stage_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `inscription`
--

INSERT INTO `inscription` (`enfant_id`, `id`, `paye`, `stage_id`) VALUES
(1, 1, b'1', 1),
(2, 2, b'1', 5),
(3, 3, b'0', 4),
(7, 4, b'0', 3),
(5, 5, b'1', 1),
(8, 6, b'1', 4),
(4, 7, b'1', 5),
(6, 8, b'0', 1),
(8, 9, b'0', 7);

-- --------------------------------------------------------

--
-- Structure de la table `stage`
--

CREATE TABLE `stage` (
  `age_max` int(11) NOT NULL,
  `age_min` int(11) NOT NULL,
  `date_deb` date NOT NULL,
  `date_fin` date NOT NULL,
  `id` int(11) NOT NULL,
  `prix` int(11) NOT NULL,
  `denom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `stage`
--

INSERT INTO `stage` (`age_max`, `age_min`, `date_deb`, `date_fin`, `id`, `prix`, `denom`) VALUES
(18, 6, '2024-05-30', '2024-09-02', 1, 750, 'informatique'),
(12, 8, '2024-06-03', '2024-07-30', 2, 500, 'Decouverte des Sciences'),
(18, 5, '2024-06-30', '2024-07-31', 3, 450, 'Sports et Aventures'),
(15, 7, '2024-08-05', '2024-09-02', 4, 550, 'Cuisine '),
(18, 3, '2024-06-10', '2024-07-31', 5, 400, 'Creation Artistique'),
(18, 12, '2024-05-30', '2024-08-31', 6, 950, 'informatique de gestion'),
(18, 15, '2024-06-03', '2024-07-31', 7, 650, 'mecanique');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `enfant`
--
ALTER TABLE `enfant`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKt1dtysbbuk15u2e7c79dyem57` (`enfant_id`),
  ADD KEY `FK1dhcqs9gvxhe7myd7nx3ko8jp` (`stage_id`);

--
-- Index pour la table `stage`
--
ALTER TABLE `stage`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_i1nni3gfm5kx7whqga9yp2ml6` (`denom`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `enfant`
--
ALTER TABLE `enfant`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `inscription`
--
ALTER TABLE `inscription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT pour la table `stage`
--
ALTER TABLE `stage`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `inscription`
--
ALTER TABLE `inscription`
  ADD CONSTRAINT `FK1dhcqs9gvxhe7myd7nx3ko8jp` FOREIGN KEY (`stage_id`) REFERENCES `stage` (`id`),
  ADD CONSTRAINT `FKt1dtysbbuk15u2e7c79dyem57` FOREIGN KEY (`enfant_id`) REFERENCES `enfant` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
