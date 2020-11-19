DROP TABLE IF EXISTS ui_text;

CREATE TABLE ui_text (
	id                  BIGINT(20) NOT NULL AUTO_INCREMENT,
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	text_key            VARCHAR(255),
	text_value          VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
	PRIMARY KEY (id),
	UNIQUE KEY locale (language_code, country_code, text_key)
);
