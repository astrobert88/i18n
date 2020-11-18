DROP TABLE IF EXISTS message;

CREATE TABLE message (
	id                  BIGINT(20) NOT NULL AUTO_INCREMENT,
	language_code       CHAR(2) NOT NULL,
	country_code        CHAR(2) NOT NULL DEFAULT '',
	message_key         VARCHAR(255),
	message_content     VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_520_ci,
	PRIMARY KEY (id),
	UNIQUE KEY locale (language_code, country_code, message_key)
);
