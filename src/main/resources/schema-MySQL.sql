DROP TABLE IF EXISTS ui_text;
DROP TABLE IF EXISTS page;

CREATE TABLE ui_text (
	id                  BIGINT(20) NOT NULL AUTO_INCREMENT,
	i18n_key            VARCHAR(255),
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	text_value          VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
	PRIMARY KEY (id),
	UNIQUE KEY ui_text_key (i18n_key, language_code, country_code)
);

CREATE TABLE page (
	id                  BIGINT(20) NOT NULL AUTO_INCREMENT,
	i18n_key            VARCHAR(255),
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	title               VARCHAR(255),
	text1               VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
	PRIMARY KEY (id),
	UNIQUE KEY page_key (i18n_key, language_code, country_code)
);
