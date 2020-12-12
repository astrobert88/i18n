-- Used by in-memory H2 database for testing only.
-- No need to drop tables since the database is created and destroyed every time.
CREATE TABLE ui_text (
	id                  BIGINT(20) NOT NULL,
	i18n_key            VARCHAR(255),
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	text_value          VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE KEY ui_text_key (i18n_key, language_code, country_code)
);

CREATE TABLE page (
	id                  BIGINT(20) NOT NULL,
	i18n_key            VARCHAR(255),
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	title               VARCHAR(255),
	text1               VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE KEY page_key (i18n_key, language_code, country_code)
);

CREATE TABLE text_field (
	id                  BIGINT(20) NOT NULL,
	i18n_key            VARCHAR(255),
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	label               VARCHAR(255),
	placeholder         VARCHAR(255),
	PRIMARY KEY (id),
	UNIQUE KEY text_field_key (i18n_key, language_code, country_code)
);

-- Need to create the standard sequence HIBERNATE_SEQUENCE since we are using this schema.sql script instead of letting
-- Hibernate create the ddl automatically
-- In your INSERT statements, include column 'id' and use value hibernate_sequence.nextval - see data.sql
CREATE SEQUENCE HIBERNATE_SEQUENCE
   MINVALUE 1
   MAXVALUE 9999999999999999
   START WITH 1
   INCREMENT BY 1
   CACHE 100;
