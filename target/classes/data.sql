CREATE TABLE IF NOT EXISTS Restaurant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(32) NOT NULL,
    Adresse VARCHAR(32),
    nbrPlaces INT NOT NULL,
    pmr BOOLEAN NOT NULL,
    prixMoyen FLOAT NOT NULL
);

CREATE TABLE IF NOT EXISTS Restaurant_Horaire (
    restaurant_id INT NOT NULL,
    horaire_id INT NOT NULL,
    PRIMARY KEY (restaurant_id, horaire_id),
    FOREIGN KEY (restaurant_id) REFERENCES Restaurant(id) ON DELETE CASCADE,
    FOREIGN KEY (horaire_id) REFERENCES Horaire(id) ON DELETE CASCADE
);

-- INSERTIONS
INSERT INTO Restaurant (nom, Adresse, nbrPlaces, pmr, prixMoyen) VALUES
  ('Le Jardin', '5 rue du Jardin', 50, true, 25.5),
  ('La Belle Époque', '15 avenue des Champs-Élysées', 100, false, 42.0),
  ('Le Bon Vivant', '18 rue de la Paix', 80, true, 35.0),
  ('Chez Gino', '10 rue du Commerce', 30, false, 18.5),
  ('Le Petit Bistrot', '7 rue de la Gare', 40, true, 22.0);

INSERT INTO Horaire (horaire) VALUES
  ('13h00-14h00'),
  ('19h00-20h00'),
  ('20h00-21h00'),
  ('21h00-22h00'),
  ('22h00-23h00');

INSERT INTO Restaurant_Horaire (restaurant_id, horaire_id) VALUES
  (1, 1),
  (1, 3),
  (1, 4),
  (2, 2),
  (2, 3),
  (2, 5),
  (3, 1),
  (3, 2),
  (4, 2),
  (4, 5),
  (5, 1),
  (5, 3),
  (5, 4);
