# Users Schema

# --- !Ups

CREATE SEQUENCE user_id_seq;
CREATE TABLE users (
  id INTEGER NOT NULL DEFAULT nextval('user_id_seq'),
  firstName VARCHAR(20) NOT NULL,
  lastName VARCHAR(20) NOT NULL,
  email VARCHAR(20) NOT NULL,
  password VARCHAR(32) NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)

# --- !Downs

DROP TABLE users;
DROP SEQUENCE user_id_seq;
