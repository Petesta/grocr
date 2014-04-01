# Items schema

# --- !Ups

CREATE SEQUENCE item_id_seq;
CREATE TABLE items (
  id INTEGER NOT NULL DEFAULT nextval('item_id_seq'),
  tripID INTEGER NOT NULL,
  name VARCHAR(20) NOT NULL,
  createdAt TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  FOREIGN KEY (tripID) REFERENCES Trips(id),
  PRIMARY KEY (id)
)

# --- !Downs

DROP TABLE items;
DROP SEQUENCE item_id_seq;
