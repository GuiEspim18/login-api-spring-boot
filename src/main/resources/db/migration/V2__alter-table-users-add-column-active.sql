ALTER TABLE users ADD active tinyint NOT NULL;
UPDATE users SET active = 1;