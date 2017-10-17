CREATE TABLE TEAM (
  id SERIAL PRIMARY KEY,
  team_name VARCHAR(100) NOT NULL
);

CREATE TABLE ANSWER (
  id SERIAL PRIMARY KEY,
  vote_name VARCHAR(100) NOT NULL,
  display_name VARCHAR(100) NOT NULL,
  correct_value INT NOT NULL,
  incorrect_value INT NOT NULL
);

INSERT INTO ANSWER(vote_name, display_name, correct_value, incorrect_value)
  VALUES
    ('OVER', 'Over', 1, 0),
    ('UNDER', 'Under', 1, 0),
    ('OVER LOCK', 'Over Lock', 3, -3),
    ('UNDER LOCK', 'Under Lock', 3, -3);

CREATE TABLE VOTE (
  id SERIAL PRIMARY KEY,
  team_id INT NOT NULL,
  answer_id INT NOT NULL,
  player_id INT NOT NULL,
  FOREIGN KEY (team_id) REFERENCES TEAM(id),
  FOREIGN KEY (answer_id) REFERENCES ANSWER(id),
  FOREIGN KEY (player_id) REFERENCES PLAYER(id)
);
