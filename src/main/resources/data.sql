DROP TABLE IF EXISTS billionaires;

CREATE TABLE billionaires (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  career VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS articles;

CREATE TABLE articles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    pDate DATE NOT NULL,
    title VARCHAR(50) NOT NULL,
    body VARCHAR(500) NOT NULL,
    tags VARCHAR(50) NOT NULL
);

INSERT INTO billionaires (first_name, last_name, career) VALUES
  ('Aliko', 'Dangote', 'Billionaire Industrialist'),
  ('Bill', 'Gates', 'Billionaire Tech Entrepreneur'),
  ('Folrunsho', 'Alakija', 'Billionaire Oil Magnate');


INSERT INTO articles (pDate, title, body, tags) VALUES
('2021-07-07', 'Queen Bee', 'Lots and lots of queen bees in nature', 'nature,science,animals'),
('2021-07-06', 'CW Longbottom', 'Literally genius - is he or is he not?', 'author,fiction,books'),
('2021-07-05', 'Polar Bear', 'The king or queen of the artic!!!', 'nature,winter,animals'),
('2021-07-04', 'Issac Assimov', 'True genius - best sci-fi author', 'author,sci-fi,books');


