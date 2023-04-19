CREATE TABLE IF NOT EXISTS horaire (id_horaire INT PRIMARY KEY,horaire VARCHAR(50));
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (1, '00h - 01h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (2, '01h - 02h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (3, '02h - 03h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (4, '03h - 04h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (5, '04h - 05h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (6, '05h - 06h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (7, '06h - 07h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (8, '07h - 08h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (9, '08h - 09h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (10, '09h - 10h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (11, '10h - 11h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (12, '11h - 12h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (13, '12h - 13h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (14, '13h - 14h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (15, '14h - 15h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (16, '15h - 16h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (17, '16h - 17h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (18, '17h - 18h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (19, '18h - 19h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (20, '19h - 20h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (21, '20h - 21h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (22, '21h - 22h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (23, '22h - 23h');
INSERT IGNORE INTO horaire (id_horaire, horaire) VALUES (24, '23h - 00h');
ALTER TABLE horaire MODIFY COLUMN id_horaire 	 INT auto_increment;


CREATE TABLE IF NOT EXISTS restaurant (id_restaurant INT PRIMARY KEY AUTO_INCREMENT, nom VARCHAR(50), adresse VARCHAR(300), nombre_place_max INT, pmr BOOLEAN, prix_moyen_repas INT);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (1, '47 rue de Saint-Anne', 'Au Délice de Rennes', 80, 1, 15);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (2, '12 rue du port', 'Chez Philou', 15, 0, 10);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (3, '99 rue de la paix', 'Miam-Miam', 80, 1, 45);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (4, '15 avenue des Champs-Élysées', 'Le Jardin de Paris', 50, 0, 30);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (5, '10 rue de la Gare', 'La Table du Chef', 20, 1, 25);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (6, '5 avenue Franklin Roosevelt', 'Le Bistrot de la Mer', 40, 1, 35);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (7, '22 rue de la Roquette', 'Le Petit Bouchon', 15, 1, 20);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (8, '120 rue du Faubourg Saint-Antoine', 'Le Comptoir du Marché', 25, 1, 40);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (9, '25 avenue des Ternes', 'Le Pain Quotidien', 30, 0, 18);
INSERT IGNORE INTO restaurant (id_restaurant, adresse, nom, nombre_place_max, pmr, prix_moyen_repas) VALUES (10, '6 rue du Temple', 'Le Café des Arts', 35, 0, 28);

ALTER TABLE restaurant MODIFY COLUMN id_restaurant 	 INT auto_increment;
--
--
CREATE TABLE IF NOT EXISTS reservation (id_reservation INT PRIMARY KEY AUTO_INCREMENT, nombre_adultes INT, nombre_enfants INT, id_horaire INT, id_restaurant INT);
INSERT IGNORE INTO reservation (id_reservation, nombre_adultes, nombre_enfants, id_horaire, id_restaurant) VALUES (1, 2, 1, 19, 1);
INSERT IGNORE INTO reservation (id_reservation, nombre_adultes, nombre_enfants, id_horaire, id_restaurant) VALUES (2, 4, 2, 20, 1);
-- INSERT IGNORE INTO reservation (id_reservation, nombre_adultes, nombre_enfants, id_horaire, id_restaurant) VALUES (3, 6, 3, 20, 1);
-- INSERT IGNORE INTO reservation (id_reservation, nombre_adultes, nombre_enfants, id_horaire, id_restaurant) VALUES (4, 4, 1, 17, 2);
ALTER TABLE reservation MODIFY COLUMN id_reservation 	 INT auto_increment;


-- CREATE TABLE IF NOT EXISTS restaurant_horaires (restaurant_id_restaurant INT, horaires_id_horaire INT);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 18);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 19);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 20);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 21);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 22);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 23);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (1, 24);
--
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (2, 16);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (2, 17);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (2, 18);
--
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (3, 13);
-- INSERT IGNORE INTO restaurant_horaires (restaurant_id_restaurant, horaires_id_horaire) VALUES (3, 14);
