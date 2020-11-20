-- Hibernate v5.2.x no longer auto generates an @Entity's id value so specify it explicitly in INSERT statements
-- (If we don't do this here, we'll get an error about attempting to insert null for id)
-- Make sure you have created the SEQUENCE in your schema.sql file!
INSERT INTO ui_text (id, language_code, country_code, text_key, text_value) VALUES
(hibernate_sequence.nextval, 'en', '', 'home.welcome','Welcome'),
(hibernate_sequence.nextval,'en', '', 'home.info','This page is displayed in English.'),
(hibernate_sequence.nextval, 'en', 'US', 'home.favcolor','My favorite color is blue'),
(hibernate_sequence.nextval, 'en', '', 'home.favcolor','My favourite colour is blue'),
(hibernate_sequence.nextval, 'en', '', 'home.changelanguage','Supported languages : '),
(hibernate_sequence.nextval, 'en', '', 'home.lang.en','English'),
(hibernate_sequence.nextval, 'en', '', 'home.lang.de','German'),
(hibernate_sequence.nextval, 'en', '', 'home.lang.zh','Chinese'),

(hibernate_sequence.nextval, 'de', '', 'home.welcome','Wilkommen'),
(hibernate_sequence.nextval, 'de', '', 'home.info','Diese Seite wird in deutscher Sprache angezeigt.'),
(hibernate_sequence.nextval, 'de', '', 'home.changelanguage','Unterstützte Sprachen : '),
(hibernate_sequence.nextval, 'de', '', 'home.lang.en','Englisch'),
(hibernate_sequence.nextval, 'de', '', 'home.lang.de','Deutsch'),
(hibernate_sequence.nextval, 'de', '', 'home.lang.zh','Chinesisch'),

(hibernate_sequence.nextval, 'zh', '', 'home.welcome','歡迎'),
(hibernate_sequence.nextval, 'zh', '', 'home.info','此頁面以中文顯示.'),
(hibernate_sequence.nextval, 'zh', '', 'home.changelanguage','支持的語言  : '),
(hibernate_sequence.nextval, 'zh', '', 'home.lang.en','英語'),
(hibernate_sequence.nextval, 'zh', '', 'home.lang.de','德語'),
(hibernate_sequence.nextval, 'zh', '', 'home.lang.zh','普通話'),

-- To test for language match but no country match
(hibernate_sequence.nextval, 'en', 'AU', 'hello','Gday mate'),
(hibernate_sequence.nextval, 'en', 'GB', 'hello','Good day old chap'),
(hibernate_sequence.nextval, 'en', '', 'hello','Hello'),
(hibernate_sequence.nextval, 'en', 'US', 'hello','Hi there')
;