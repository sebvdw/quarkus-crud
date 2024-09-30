-- Game Entity
INSERT INTO gameEntity(id, name) VALUES (1, 'Poker');
INSERT INTO gameEntity(id, name) VALUES (2, 'Blackjack');
INSERT INTO gameEntity(id, name) VALUES (3, 'Roulette');
INSERT INTO gameEntity(id, name) VALUES (4, 'Baccarat');
INSERT INTO gameEntity(id, name) VALUES (5, 'Craps');
ALTER SEQUENCE gameEntity_seq RESTART WITH 6;

-- Player Entity
INSERT INTO playerEntity(id, name, game_id) VALUES (1, 'John Doe', 1);
INSERT INTO playerEntity(id, name, game_id) VALUES (2, 'Jane Smith', 1);
INSERT INTO playerEntity(id, name, game_id) VALUES (3, 'Bob Johnson', 2);
INSERT INTO playerEntity(id, name, game_id) VALUES (4, 'Alice Brown', 2);
INSERT INTO playerEntity(id, name, game_id) VALUES (5, 'Charlie Davis', 3);
INSERT INTO playerEntity(id, name, game_id) VALUES (6, 'Diana Wilson', 4);
INSERT INTO playerEntity(id, name, game_id) VALUES (7, 'Ethan Hunt', 5);
INSERT INTO playerEntity(id, name, game_id) VALUES (8, 'Fiona Gallagher', 5);
ALTER SEQUENCE playerEntity_seq RESTART WITH 9;

-- Dealer Entity
INSERT INTO dealerEntity(id, name, employeeId) VALUES (1, 'Michael Scott', 'D001');
INSERT INTO dealerEntity(id, name, employeeId) VALUES (2, 'Pam Beesly', 'D002');
INSERT INTO dealerEntity(id, name, employeeId) VALUES (3, 'Jim Halpert', 'D003');
INSERT INTO dealerEntity(id, name, employeeId) VALUES (4, 'Dwight Schrute', 'D004');
INSERT INTO dealerEntity(id, name, employeeId) VALUES (5, 'Angela Martin', 'D005');
ALTER SEQUENCE dealerEntity_seq RESTART WITH 6;