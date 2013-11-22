# Users Schema


# --- !Ups 

CREATE SEQUENCE user_id_seq;
CREATE TABLE users (
  id integer NOT NULL DEFAULT nextval('user_id_seq'),
  first_name varchar(255) NULL,
  last_name varchar(255) NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

# --- !Downs

DROP TABLE users;
DROP SEQUENCE user_id_seq;
