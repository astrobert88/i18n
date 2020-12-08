-- Hibernate v5.2.x no longer auto generates an @Entity's id value so specify it explicitly in INSERT statements
-- (If we don't do this here, we'll get an error about attempting to insert null for id)
-- Make sure you have created the SEQUENCE in your schema.sql file!
INSERT INTO ui_text (id, language_code, country_code, i18n_key, text_value) VALUES
(hibernate_sequence.nextval, 'en', '', 'login', 'Log in'),
(hibernate_sequence.nextval, 'en', '', 'settings', 'Settings'),
(hibernate_sequence.nextval, 'en', '', 'close', 'Close'),
(hibernate_sequence.nextval, 'en', '', 'english', 'English'),
(hibernate_sequence.nextval, 'en', '', 'german', 'German'),
(hibernate_sequence.nextval, 'en', '', 'chinese', 'Chinese'),

(hibernate_sequence.nextval, 'de', '', 'login', 'Anmelden'),
(hibernate_sequence.nextval, 'de', '', 'settings', 'Einstellungen'),
(hibernate_sequence.nextval, 'de', '', 'close', 'Schließen'),
(hibernate_sequence.nextval, 'de', '', 'english', 'Englisch'),
(hibernate_sequence.nextval, 'de', '', 'german', 'Deutsch'),
(hibernate_sequence.nextval, 'de', '', 'chinese', 'Chinesisch'),

(hibernate_sequence.nextval, 'zh', '', 'english', '英語'),
(hibernate_sequence.nextval, 'zh', '', 'german', '德語'),
(hibernate_sequence.nextval, 'zh', '', 'chinese', '普通話'),

-- To test for language match but no country match. Should return UiText with countryCode=''
(hibernate_sequence.nextval, 'en', 'AU', 'hello','Gday mate'),
(hibernate_sequence.nextval, 'en', '', 'hello','Hello'),
(hibernate_sequence.nextval, 'en', 'US', 'hello','Hi there'),

-- To test for language match but no country match. Should return UiText with countryCode='DE' since no ''
(hibernate_sequence.nextval, 'de', 'DE', 'hello','Guten tag'),
(hibernate_sequence.nextval, 'de', 'CH', 'hello','Grüezi'),
(hibernate_sequence.nextval, 'de', 'AT', 'hello','Grüß Gott')
;

INSERT INTO page (id, language_code, country_code, i18n_key, title, text1) VALUES
(hibernate_sequence.nextval, 'en', '', 'home', 'Home Page','Welcome to the home page!'),
(hibernate_sequence.nextval, 'nl', '', 'home', 'Thuis Pagina','Welkom op de thuispagina!'),
(hibernate_sequence.nextval, 'en', '', '404', '404','Page not found!')
;
