-- Used by in-memory H2 database for testing only.
-- No need to drop tables since the database is created and destroyed every time.
CREATE TABLE ui_text (
	id                  BIGINT(20) NOT NULL,
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	text_key            VARCHAR(255),
	text_value          VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE KEY locale (language_code, country_code, text_key)
);

-- Need to create the standard sequence HIBERNATE_SEQUENCE since we are using this schema.sql script instead of letting
-- Hibernate create the ddl automatically
-- In your INSERT statements, include column 'id' and use value hibernate_sequence.nextval - see data.sql
CREATE SEQUENCE HIBERNATE_SEQUENCE
   MINVALUE 1
   MAXVALUE 9999999999999999
   START WITH 1
   INCREMENT BY 100
   CACHE 100;
