INSERT INTO ui_text (language_code, country_code, i18n_key, text_value) VALUES
('en', '', 'login', 'Log in'),
('en', '', 'settings', 'Settings'),
('en', '', 'close', 'Close'),
('en', '', 'english', 'English'),
('en', '', 'german', 'German'),
('en', '', 'chinese', 'Chinese'),

('de', '', 'login', 'Anmelden'),
('de', '', 'settings', 'Einstellungen'),
('de', '', 'close', 'Schließen'),
('de', '', 'english', 'Englisch'),
('de', '', 'german', 'Deutsch'),
('de', '', 'chinese', 'Chinesisch'),

('zh', '', 'english', '英語'),
('zh', '', 'german', '德語'),
('zh', '', 'chinese', '普通話'),

-- To test for language match but no country match. Should return UiText with countryCode=''
('en', 'AU', 'hello','Gday mate'),
('en', '', 'hello','Hello'),
('en', 'US', 'hello','Hi there'),

-- To test for language match but no country match. Should return UiText with countryCode='DE' since no ''
('de', 'DE', 'hello','Guten tag'),
('de', 'CH', 'hello','Grüezi'),
('de', 'AT', 'hello','Grüß Gott')
;

INSERT INTO page (language_code, country_code, i18n_key, title, text1) VALUES
('en', '', 'home', 'Home Page','Welcome to the home page!'),
('nl', '', 'home', 'Thuis Pagina','Welkom op de thuispagina!'),
('en', '', '404', '404','Page not found!')
;

INSERT INTO text_field (language_code, country_code, i18n_key, label, placeholder) VALUES
('en', '', 'username', 'Username','Enter username'),
('nl', '', 'username', 'Gebruikersnaam','Gebruikersnaam hier')
;
