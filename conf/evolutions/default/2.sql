# Users Schema

# --- !Ups

CREATE SEQUENCE user_id_seq;
CREATE TABLE users (
  id integer NOT NULL DEFAULT nextval('user_id_seq'),
  email varchar(255) NOT NULL,
  password varchar(255) NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

# --- !Downs

DROP TABLE users;
DROP SEQUENCE user_id_seq;
