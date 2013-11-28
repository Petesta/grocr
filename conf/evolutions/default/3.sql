# Items schema

# --- !Ups

CREATE SEQUENCE item_id_seq;
CREATE TABLE items (
  id integer NOT NULL DEFAULT nextval('item_id_seq'),
  name VARCHAR(255) NOT NULL,
  trip_id INTEGER NOT NULL,
  created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (trip_id) REFERENCES Trips(id),
  PRIMARY KEY (id)
)

# --- !Downs

DROP TABLE items;
DROP SEQUENCE item_id_seq;
