DELETE FROM PLAYER WHERE first_name in ('LeBron', 'Kobe', 'Michael');

ALTER TABLE PLAYER
  DROP COLUMN last_name;

INSERT INTO PLAYER(first_name)
VALUES
  ('Sam'),
  ('Shyam'),
  ('Bradley'),
  ('Dylan'),
  ('Tony Japan'),
  ('Kurt'),
  ('Joe'),
  ('Nick'),
  ('Matt'),
  ('Shamit'),
  ('Trevor'),
  ('Chris'),
  ('Eric'),
  ('Pop'),
  ('Andrew'),
  ('Sanjee'),
  ('Bre')