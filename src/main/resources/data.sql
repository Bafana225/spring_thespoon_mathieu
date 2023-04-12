CREATE TABLE IF NOT EXISTS Horaire (
    id INT AUTO_INCREMENT PRIMARY KEY,
    horaire VARCHAR(255) NOT NULL
);

CREATE TABLE Restaurant (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nom VARCHAR(255) NOT NULL,
    image_url VARCHAR(255) NOT NULL,
    adresse VARCHAR(255) NOT NULL,
    nb_couverts INT NOT NULL,
    accessibilite_pmr BOOLEAN NOT NULL,
    prix_moyen FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS Restaurant_horaires (
    id INT AUTO_INCREMENT PRIMARY KEY,
    restaurant_id INT NOT NULL,
    horaires_id INT NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurant(id),
    FOREIGN KEY (horaires_id) REFERENCES horaires(id)
);

CREATE TABLE Reservation (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nb_adultes INTEGER,
    nb_enfants INTEGER,
    heure_reservation_id INT NOT NULL,
    restaurant_id INT NOT NULL,
    FOREIGN KEY (heure_reservation_id) REFERENCES Horaires(id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id)
);


--INSERTIONS
INSERT INTO horaires (horaire) VALUES
  ('13h00-14h00'),
  ('19h00-20h00'),
  ('20h00-21h00'),
  ('21h00-22h00'),
  ('22h00-23h00');

INSERT INTO restaurant (nom, image_url, adresse, nb_couverts, accessibilite_pmr, prix_moyen) VALUES
  ('Le Petit Bistrot','https://www.lepetitbistrot-sarlat.com/LPBSarlat/wp-content/uploads/2021/08/terrasse-lepetitbistrot.jpg', '10 rue du Moulin', 30, true, 25.0),
  ('Chez Paul','https://chezpaul.com/wp-content/uploads/2019/04/chez-paul-lieu-11.jpg', '41 rue du Froulin', 50, false, 35.0),
  ('La Belle Epoque','https://www.audreycuisine.fr/wp-content/uploads/2019/07/Restaurant-la-belle-epoque-bordeaux-1200x900.jpg', '15 rue des Roses', 40, true, 45.0),
  ('Le Comptoir','https://media.timeout.com/images/105420341/image.jpg','7 rue de la Paix', 20, true, 20.0),
  ('Le Grand Café','https://www.restaurant-grand-cafe.fr/wp-content/uploads/2022/12/slide-grand-cafe.jpg', '2 place de la République', 60, false, 40.0),
  ('La Table d Arthur','https://www.futuroscope.com/upload/image/5f44d1f8e418a_header-restaurant-table-arthur_2.jpg', '14 avenue Victor Hugo', 30, true, 35.0),
  ('Le Jardin des Saveurs','https://www.lejardin-dessaveurs.fr/i/le-jardin-des-saveurs-421918/3/5/1/9/2/2/1/5/0/3/1/8/7/1542809378_432/ef43ebc3c84b4d92626d22d820b34c63.jpg', '18 rue de la Liberté', 50, true, 50.0),
  ('Le Café de la Gare','https://upload.wikimedia.org/wikipedia/commons/a/a9/Caf%C3%A9_de_la_Gare_Paris.jpg', '12 avenue de la Gare', 40, false, 30.0),
  ('Le Coin Gourmand','https://media-cdn.tripadvisor.com/media/photo-s/14/33/2e/10/le-coin-gourmand.jpg', '6 rue des Moulins', 25, true, 22.0),
  ('La Terrasse ensoleillée','https://www.materrazza.com/sites/default/files/Chez%20The%CC%81re%CC%80se.jpg', '22 boulevard des Alliés', 35, true, 42.0);

INSERT INTO restaurant_horaires (restaurant_id, horaires_id)
VALUES (1, 1), (1, 2), (1, 3), (1, 4), (1, 5);

INSERT INTO Reservation (nb_adultes, nb_enfants, restaurant_id, heure_reservation_id)
VALUES (2, 1, 1, 1), (4, 2, 2, 2), (3, 0, 3, 3), (2, 1, 4, 4), (5, 3, 5, 5);
