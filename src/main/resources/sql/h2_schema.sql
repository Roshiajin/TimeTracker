CREATE TABLE person (
  id INT NOT NULL AUTO_INCREMENT,
  name VARCHAR(100) NOT NULL,
  PRIMARY KEY (id) );

CREATE TABLE timelog (
  id INT NOT NULL AUTO_INCREMENT,
  person_id INT NOT NULL,
  log_description VARCHAR(200) NULL,
  start_datetime TIMESTAMP NOT NULL,
  end_datetime TIMESTAMP NOT NULL,
  PRIMARY KEY (id) );