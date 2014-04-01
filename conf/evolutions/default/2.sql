# Trips schema

# --- !Ups

CREATE SEQUENCE trip_id_seq;
CREATE TABLE trips (
  id INTEGER NOT NULL DEFAULT nextval('trip_id_seq'),
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id)
)

# --- !Downs

DROP TABLE trips;
DROP SEQUENCE trip_id_seq;
