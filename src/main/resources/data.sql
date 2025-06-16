-- Ordre recommandé pour DELETE :
DELETE FROM meal_foods;
DELETE FROM meals;
DELETE FROM food_ingredients;
DELETE FROM allergy_suspected_foods;
DELETE FROM allergies;
DELETE FROM ingredients;
DELETE FROM foods;
DELETE FROM users;


INSERT INTO users (id,name, email, age, weight, health_info) VALUES
(1,'Alice Dupont', 'alice@example.com', 30, 65.5, 'Aucune allergie connue'),
(2,'Alice', 'alice@example.com', 25, 60, 'Rien à signaler'),
(3,'Pablo', 'pablo@example.com', 25, 60, 'Handicapé'),
(4,'Vincent Garcia', 'vincent.garcia@email.com', 39, 77.9, 'Régime méditerranéen'),
(5,'Vincent Garcia', 'vincent.garcia@email.com', 39, 77.9, 'Régime méditerranéen'),
(6,'Marie Dubois', 'marie.dubois@email.com', 28, 65.5, 'Aucun problème de santé particulier'),
(7,'Pierre Martin', 'pierre.martin@email.com', 34, 78.2, 'Diabète type 2 contrôlé'),
(8,'Sophie Leroy', 'sophie.leroy@email.com', 42, 58.7, 'Végétarienne, intolérance au lactose'),
(9,'Jean Moreau', 'jean.moreau@email.com', 31, 85.3, 'Sportif régulier, régime hyperprotéiné'),
(10,'Camille Petit', 'camille.petit@email.com', 26, 52.1, 'Anémie légère'),
(11,'Lucas Bernard', 'lucas.bernard@email.com', 29, 72.8, 'Aucun problème particulier'),
(12,'Emma Thomas', 'emma.thomas@email.com', 37, 63.4, 'Hypertension artérielle'),
(13,'Antoine Robert', 'antoine.robert@email.com', 45, 89.1, 'Cholestérol élevé'),
(14,'Léa Richard', 'lea.richard@email.com', 23, 55.9, 'Étudiante, régime équilibré'),
(15,'Maxime Durand', 'maxime.durand@email.com', 38, 76.5, 'Reflux gastro-œsophagien'),
(16,'Alice', 'alice@example.com', 25, 60, 'Aucune allergie'),
(17,'Bob', 'bob@example.com', 30, 75, 'Diabétique'),
(18,'Charlie', 'charlie@example.com', 28, 68.5, 'Sportif'),
(19,'David', 'david@example.com', 32, 82, 'Hypertension'),
(20,'Fatima', 'fatima@example.com', 26, 63, 'Aucune allergie'),
(21,'Georges', 'georges@example.com', 35, 88, 'Cardiaque'),
(22,'Hugo', 'hugo@example.com', 29, 71, 'Rien à signaler'),
(23,'Inès', 'ines@example.com', 27, 58, 'Asthme'),
(24,'Jean', 'jean@example.com', 40, 85, 'Rien à signaler'),
(25,'Julie Lefebvre', 'julie.lefebvre@email.com', 36, 64.8, 'Thyroïde sous-active'),
(26,'Hugo Laurent', 'hugo.laurent@email.com', 27, 68.7, 'Végétalien'),
(27,'Manon Simon', 'manon.simon@email.com', 41, 59.3, 'Maladie cœliaque'),
(28,'Sarah Rodriguez', 'sarah.rodriguez@email.com', 25, 56.4, 'Allergie aux fruits à coque'),
(29,'Nicolas Lopez', 'nicolas.lopez@email.com', 43, 81.7, 'Intolérance au gluten'),
(30,'Océane Martinez', 'oceane.martinez@email.com', 32, 62.1, 'Régime sans sucre'),
(31,'Romain Gonzalez', 'romain.gonzalez@email.com', 28, 75.3, 'Pratique le jeûne intermittent'),
(32,'Amélie Wilson', 'amelie.wilson@email.com', 35, 58.9, 'Végétarienne depuis 5 ans'),
(33,'Quentin Anderson', 'quentin.anderson@email.com', 40, 87.2, 'Diabète gestationnel passé'),
(34,'Pauline Taylor', 'pauline.taylor@email.com', 29, 60.5, 'Régime paléo'),
(35,'Alexandre Thomas', 'alexandre.thomas@email.com', 44, 79.8, 'Hypertension contrôlée'),
(36,'Anaïs Jackson', 'anais.jackson@email.com', 31, 57.6, 'Syndrome de l’intestin irritable'),
(37,'Florian White', 'florian.white@email.com', 26, 73.1, 'Sportif amateur'),
(38,'Julien Martin', 'julien.martin2@email.com', 33, 80.4, 'Apnée du sommeil'),
(39,'Elise Clark', 'elise.clark@email.com', 27, 54.2, 'Anorexie en rémission'),
(40,'Damien Lewis', 'damien.lewis@email.com', 42, 86.9, 'Goutte chronique');

-- Foods

INSERT INTO foods (id,name, image_url, calories, category) VALUES
(1,'Açorda à Alentejana', 'http://localhost:8080/images/acorda.png', 220, 'Entrée'),
(2,'Alheira de Mirandela', 'http://localhost:8080/images/alheira.png', 520, 'Viande'),
(3,'Amêijoas à Bulhão Pato', 'http://localhost:8080/images/ameijoas_bulhao.png', 380, 'Poisson'),
(4,'Arroz de Marisco', 'http://localhost:8080/images/arroz_marisco.png', 320, 'Poisson'),
(5,'Bacalhau à Brás', 'http://localhost:8080/images/bacalhau_bras.png', 450, 'Poisson'),
(6,'Bifanas', 'http://localhost:8080/images/bifanas.png', 360, 'Viande'),
(7,'Brocoli', 'https://example.com/brocoli.png', 55, 'Légume'),
(8,'Caldo Verde', 'http://localhost:8080/images/caldo_verde.png', 180, 'Entrée'),
(9,'Cozido à Portuguesa', 'http://localhost:8080/images/cozido_portuguesa.png', 550, 'Viande'),
(10,'Francesinha', 'http://localhost:8080/images/francesinha.png', 650, 'Viande'),
(11,'Ginjinha', 'http://localhost:8080/images/ginjinha.png', 120, 'Boisson'),
(12,'Leitão da Bairrada', 'http://localhost:8080/images/leitao_bairrada.png', 480, 'Viande'),
(13,'Pão de Ló', 'http://localhost:8080/images/pao_de_lo.png', 200, 'Snack'),
(14,'Pastéis de Nata', 'http://localhost:8080/images/pasteis_nata.png', 300, 'Dessert'),
(15,'Poulet', 'https://example.com/poulet.png', 165, 'Viande'),
(16,'Pudim Abade de Priscos', 'http://localhost:8080/images/pudim_abade.png', 320, 'Dessert'),
(17,'Queijo da Serra', 'http://localhost:8080/images/queijo_serra.png', 310, 'Entrée'),
(18,'Sardinhas Assadas', 'http://localhost:8080/images/sardinhas_assadas.png', 280, 'Poisson'),
(19,'Sopa Dourada', 'http://localhost:8080/images/sopa_dourada.png', 150, 'Entrée'),
(20,'Bolo do Caco', 'http://localhost:8080/images/bolo_caco.png', 280, 'Pain'),
(21,'Carne de Porco à Alentejana', 'http://localhost:8080/images/pork_alentejana.png', 520, 'Viande'),
(22,'Chanfana', 'http://localhost:8080/images/chanfana.png', 480, 'Viande'),
(23,'Feijoada à Transmontana', 'http://localhost:8080/images/feijoada.png', 550, 'Viande'),
(24,'Migas à Alentejana', 'http://localhost:8080/images/migas.png', 320, 'Accompagnement'),
(25,'Polvo à Lagareiro', 'http://localhost:8080/images/polvo_lagareiro.png', 380, 'Poisson'),
(26,'Rojões à Moda do Minho', 'http://localhost:8080/images/rojoes.png', 450, 'Viande'),
(27,'Salada de Polvo', 'http://localhost:8080/images/salada_polvo.png', 220, 'Entrée'),
(28,'Torricado', 'http://localhost:8080/images/torricado.png', 250, 'Snack');

--Meals

INSERT INTO meals (id,date, user_id, name) VALUES
(1,'2025-06-01 08:30:00', 2, 'Petit-déjeuner Lisbonne'),
(2,'2025-06-02 09:15:00', 4, 'Matin Porto'),
(3,'2025-06-03 08:00:00', 8, 'Café da manhã'),
(4,'2025-06-04 07:45:00', 12, 'Déjeuner léger'),
(5,'2025-06-05 10:00:00', 17, 'Brunch weekend'),
(6,'2025-06-04 19:57:04', 2, 'Nom par défaut'),
(7,'2025-06-01 12:30:00', 2, 'Déjeuner daffaires'),
(8,'2025-06-02 13:00:00', 5, 'Repas familial'),
(9,'2025-06-03 12:15:00', 9, 'Pause méridienne'),
(10,'2025-06-04 13:30:00', 13, 'Menu du jour'),
(11,'2025-06-05 12:45:00', 18, 'Déjeuner rapide'),
(12,'2025-06-06 12:00:00', 22, 'Repas traditionnel'),
(13,'2025-06-07 13:15:00', 26, 'Dégustation portugaise'),
(14,'2025-06-01 20:00:00', 3, 'Dîner romantique'),
(15,'2025-06-02 19:30:00', 6, 'Soirée entre amis'),
(16,'2025-06-03 21:00:00', 10, 'Repas tardif'),
(17,'2025-06-04 20:15:00', 14, 'Dîner en famille'),
(18,'2025-06-05 19:45:00', 19, 'Cuisine régionale'),
(19,'2025-06-06 20:30:00', 23, 'Festin portugais'),
(20,'2025-06-07 19:00:00', 27, 'Dîner léger'),
(21,'2025-06-08 14:00:00', 7, 'Fête nationale portugaise'),
(22,'2025-06-09 15:30:00', 11, 'Goûter gourmand'),
(23,'2025-06-10 22:00:00', 15, 'Souper nocturne'),
(24,'2025-06-11 16:00:00', 20, 'Merienda'),
(25,'2025-06-12 17:30:00', 24, 'Cocktail dinatoire'),
(26,'2025-06-13 23:00:00', 28, 'Collation nocturne');

--ingredient

INSERT INTO ingredients (id,benefits, image_url, name, nutrition_facts, risks) VALUES
(1,'Riche en protéines et oméga-3', 'http://localhost:8080/images/morue.png', 'Morue salée', 'Protéines: 62g/100g, Sodium: 8100mg', 'Teneur élevée en sodium'),
(2,'Source de sélénium et vitamine B12', 'http://localhost:8080/images/crevettes.png', 'Crevettes', 'Calories: 99kcal/100g, Protéines: 24g', 'Allergène commun'),
(3,'Fer et vitamine B12', 'http://localhost:8080/images/palourdes.png', 'Palourdes', 'Fer: 28mg/100g', 'Risque d''intoxication si mal conservées'),
(4,'Oméga-3 et calcium', 'http://localhost:8080/images/sardines.png', 'Sardines', 'Oméga-3: 2g/100g', 'Riche en purines'),
(5,'Source de thiamine', 'http://localhost:8080/images/porc.png', 'Porc', 'Lipides: 20g/100g', 'Cholestérol élevé'),
(6,'Apport en fer', 'http://localhost:8080/images/chouriço.png', 'Chouriço', 'Sodium: 1500mg/100g', 'Riche en graisses saturées'),
(7,'Protéines complètes', 'http://localhost:8080/images/jambon.png', 'Jambon fumé', 'Protéines: 30g/100g', 'Teneur en nitrates'),
(8,'Alternative à la viande rouge', 'http://localhost:8080/images/alheira.png', 'Alheira', 'Varié selon composition', 'Gluten si version traditionnelle'),
(9,'Source de potassium', 'http://localhost:8080/images/pommes_de_terre.png', 'Pommes de terre', 'Glucides: 17g/100g', 'Index glycémique élevé'),
(10,'Riche en vitamine K', 'http://localhost:8080/images/chou_vert.png', 'Chou vert', 'Fibres: 3g/100g', 'Peut causer des ballonnements'),
(11,'Antioxydants', 'http://localhost:8080/images/oignons.png', 'Oignons', 'Vitamine C: 7mg/100g', 'Irritation digestive possible'),
(12,'Sans gluten (traditionnel)', 'http://localhost:8080/images/pao_milho.png', 'Pain de maïs', 'Glucides: 43g/100g', 'Calories denses'),
(13,'Calcium et vitamine D', 'http://localhost:8080/images/lait.png', 'Lait entier', 'Calcium: 120mg/100ml', 'Intolérance au lactose'),
(14,'Protéines et phosphore', 'http://localhost:8080/images/queijo_serra.png', 'Fromage Serra da Estrela', 'Lipides: 30g/100g', 'Graisses saturées'),
(15,'Capsaïcine anti-inflammatoire', 'http://localhost:8080/images/pimenton.png', 'Pimentón', 'Antioxydants élevés', 'Irritation gastrique possible'),
(16,'Propriétés antibactériennes', 'http://localhost:8080/images/ail.png', 'Ail', 'Allicine: 5mg/100g', 'Odeur persistante'),
(17,'Vitamine K et C', 'http://localhost:8080/images/persil.png', 'Persil', 'Vitamine C: 133mg/100g', 'Effet diurétique'),
(18,'Acides gras monoinsaturés', 'http://localhost:8080/images/huile_olive.png', 'Huile d''olive', 'Lipides: 100g/100g', 'Calories denses'),
(19,'Protéines complètes', 'http://localhost:8080/images/oeufs.png', 'Œufs', 'Protéines: 13g/100g', 'Allergène commun'),
(20,'Énergie rapide', 'http://localhost:8080/images/sucre.png', 'Sucre', 'Glucides: 100g/100g', 'Diabète et caries'),
(21,'Régulation glycémique', 'http://localhost:8080/images/cannelle.png', 'Cannelle', 'Antioxydants: 131420 ORAC', 'Irritation à haute dose');



-- allergies

INSERT INTO allergies (id, date, description, user_id) VALUES
(1,	'2024-01-15 09:00:00',	'Allergie aux crustacés (crevettes, crabes)',	1),
(2,	'2023-11-22 14:30:00',	'Intolérance au lactose',	8),
(3,	'2024-02-10 16:45:00',	'Allergie aux arachides sévère',	30),
(4,	'2023-09-05 11:20:00',	'Maladie cœliaque (intolérance au gluten)',	28),
(5,	'2024-03-18 10:15:00',	'Allergie aux œufs',	31),
(6,	'2023-12-03 13:10:00',	'Allergie aux mollusques (palourdes, moules)',	4),
(7,	'2024-01-28 15:30:00',	'Allergie au poisson (morue, saumon)',	12),
(8,	'2023-10-14 08:45:00',	'Intolérance à l''ail',	38),
(9,	'2024-02-29 17:00:00',	'Allergie aux sulfites (vin)',	22),
(10,	'2022-05-07 10:30:00',	'Allergie au sésame',	17),
(11,	'2021-08-19 09:15:00',	'Réaction aux fruits à coque',	5),
(12,	'2023-06-12 14:00:00',	'Intolérance au gluten non cœliaque',	34),
(23,	'2024-04-01 10:00:00',	'Allergie aux produits laitiers (caséine)',	6),
(24,	'2023-12-15 11:20:00',	'Intolérance à l''histamine',	9),
(25,	'2024-01-30 14:15:00',	'Allergie au soja',	11),
(26,	'2023-11-10 09:45:00',	'Réaction aux additifs alimentaires (E220-E228)',	15),
(27,	'2024-02-25 16:30:00',	'Allergie aux fruits de mer (anaphylaxie)',	18),
(28,	'2023-10-05 13:10:00',	'Intolérance au fructose',	21),
(29,	'2024-03-12 08:20:00',	'Allergie aux sulfites (vin et fruits secs)',	24),
(30,	'2023-09-18 15:45:00',	'Dermatite herpétiforme (gluten)',	26),
(31,	'2024-01-05 12:30:00',	'Allergie aux protéines de blé',	29),
(32,	'2023-08-22 17:00:00',	'Syndrome d''allergie orale (pollens-fruits)',	33);

--autres

INSERT INTO allergy_suspected_foods (allergy_id, food_id) VALUES
(1,	3),
(1,	5),
(2,	8),
(2,	14),
(3,	15),
(4,	2),
(4,	8),
(4,	12),
(5,	1),
(5,	8),
(5,	16);


INSERT INTO food_ingredients (food_id, ingredient_id) VALUES
(1,	1),
(1,	9),
(1,	11),
(1,	19),
(1,	18),
(2,	5),
(2,	6),
(2,	7),
(2,	13),
(2,	15),
(2,	12),
(3,	2),
(3,	3),
(3,	9),
(3,	11),
(3,	16),
(3,	17),
(4,	5),
(4,	6),
(4,	10),
(4,	9),
(4,	11),
(5,	3),
(5,	16),
(5,	18),
(5,	17),
(5,	11),
(6,	5),
(6,	16),
(6,	14),
(6,	15),
(7,	6),
(7,	10),
(7,	9),
(7,	18),
(8,	19),
(8,	20),
(8,	13),
(8,	21),
(8,	12);

INSERT INTO meal_foods (meal_id, food_id) VALUES
(1,	19),
(1,	24),
(6,	1),
(6,	10),
(6,	23),
(13,5),
(13,16),
(19,4),
(19,8),
(19,22),
(2,	19),
(2,	24),
(2,	14),
(7,	3),
(7,	9),
(7,	18),
(14,6),
(14,11),
(14,20),
(20,7),
(20,12),
(20,21),
(25,2),
(25,17),
(25,23);




