INSERT INTO ui_text (language_code, country_code, text_key, text_value) VALUES
('en', '', 'home.welcome','Welcome'),
('en', '', 'home.info','This page is displayed in English.'),
('en', 'US', 'home.favcolor','My favorite color is blue'),
('en', '', 'home.favcolor','My favourite colour is blue'),
('en', '', 'home.changelanguage','Supported languages : '),
('en', '', 'home.lang.en','English'),
('en', '', 'home.lang.de','German'),
('en', '', 'home.lang.zh','Chinese'),

('de', '', 'home.welcome','Wilkommen'),
('de', '', 'home.info','Diese Seite wird in deutscher Sprache angezeigt.'),
('de', '', 'home.changelanguage','Unterstützte Sprachen : '),
('de', '', 'home.lang.en','Englisch'),
('de', '', 'home.lang.de','Deutsch'),
('de', '', 'home.lang.zh','Chinesisch'),

('zh', '', 'home.welcome','歡迎'),
('zh', '', 'home.info','此頁面以中文顯示.'),
('zh', '', 'home.changelanguage','支持的語言  : '),
('zh', '', 'home.lang.en','英語'),
('zh', '', 'home.lang.de','德語'),
('zh', '', 'home.lang.zh','普通話'),

-- To test for language match but no country match
('en', 'AU', 'hello','Gday mate'),
('en', 'GB', 'hello','Good day old chap'),
('en', '', 'hello','Hello'),
('en', 'US', 'hello','Hi there')
;