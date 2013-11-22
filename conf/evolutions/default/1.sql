# Trips schema

# --- !Ups

CREATE SEQUENCE trip_id_seq;
CREATE TABLE trips (
  id integer NOT NULL DEFAULT nextval('trip_id_seq'),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)

# --- !Downs

DROP TABLE trips;
DROP SEQUENCE trip_id_seq;
