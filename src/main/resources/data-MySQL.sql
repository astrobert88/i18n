INSERT INTO ui_text (language_code, country_code, text_key, text_value) VALUES
('en', '', 'home.welcome','Welcome'),
('en', '', 'home.info','This page is displayed in English.'),
('en', '', 'home.favcolor','My favourite colour is blue'),
('en', 'US', 'home.favcolor','My favorite color is blue'),
('en', '', 'home.changelanguage','Supported languages : '),
('en', '', 'home.lang.en','English'),
('en', '', 'home.lang.de','German'),
('en', '', 'home.lang.nl','Dutch'),
('en', '', 'home.lang.zh','Chinese'),

('de', '', 'home.welcome','Wilkommen'),
('de', '', 'home.info','Diese Seite wird in deutscher Sprache angezeigt.'),
('de', '', 'home.changelanguage','Unterstützte Sprachen : '),
('de', '', 'home.lang.en','Englisch'),
('de', '', 'home.lang.de','Deutsch'),
('de', '', 'home.lang.nl','Niederlandisch'),
('de', '', 'home.lang.zh','Chinesisch'),

('fr', '', 'home.welcome','Bienvenue'),

('nl', 'NL', 'home.welcome','Welkom'),
('nl', '', 'home.info','Deze pagina is in het Nederlands'),
('nl', '', 'home.changelanguage','Ondersteunde talen: '),
('nl', '', 'home.lang.en','Engels'),
('nl', '', 'home.lang.de','Duits'),
('nl', '', 'home.lang.nl','Nederlands'),
('nl', '', 'home.lang.zh','Chinees'),

('zh', '', 'home.welcome','歡迎'),
('zh', '', 'home.info','此頁面以中文顯示.'),
('zh', '', 'home.changelanguage','支持的語言  : '),
('zh', '', 'home.lang.en','英語'),
('zh', '', 'home.lang.de','德語'),
('zh', '', 'home.lang.nl','荷兰语'),
('zh', '', 'home.lang.zh','普通話')
;

INSERT INTO page (name, language_code, country_code, title, text1) VALUES
('home', 'en', '', 'The Home Page', 'This is text1 on the home page'),
('home', 'nl', '', 'De Thuis Pagina', 'Dit is de tekst op de thuis pagina')
;
