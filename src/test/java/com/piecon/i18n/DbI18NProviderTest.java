package com.piecon.i18n;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase
public class DbI18NProviderTest {

    @Autowired
    private DbI18NProvider dbI18NProvider;

    @Test
    public void testGetProvidedLocales() {
        log.info("testGetProvidedLocales()");

        // Check autowiring
        Assert.assertNotNull(dbI18NProvider);

        List<Locale> locales = dbI18NProvider.getProvidedLocales();
        Assert.assertNotNull(locales);
        Assert.assertTrue(locales.size() > 1);
    }

    @Test
    public void testGetTranslation_noKey() {
        log.info("testGetTranslation_noKey()");

        Locale aLocale = new Locale.Builder().setLanguage("").setRegion("").build();
        String uiTextValue = dbI18NProvider.getTranslation("no.such.key", aLocale);
        log.info("no.such.key="+uiTextValue);
        Assert.assertNotNull(uiTextValue);
        Assert.assertEquals("no.such.key", uiTextValue);
    }

    @Test
    public void testGetTranslation_noLanguageMatch() {
        log.info("testGetTranslation_noLanguageMatch()");

        Locale aLocale = new Locale.Builder().setLanguage("").setRegion("").build();
        String uiTextValue = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+uiTextValue);
        Assert.assertNotNull(uiTextValue);
        Assert.assertEquals("home.welcome", uiTextValue);
    }

    @Test
    public void testGetTranslation_languageMatchButNoCountryMatch() {
        log.info("testGetTranslation_languageMatchButNoCountryMatch()");

        Locale aLocale = new Locale.Builder().setLanguage("de").setRegion("CH").build();
        String uiTextValue = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+uiTextValue);
        Assert.assertNotNull(uiTextValue);
        // There is no country match for Switzerland (CH) but there is a German one without a country code so return that.
        Assert.assertEquals("Wilkommen", uiTextValue);
    }

    @Test
    // Almost identical to previous test but now the first entry to match the language has the wrong country code.
    // Even though there is one without a country code further down. It should return the one without a country code.
    public void testGetTranslation_languageMatchButNoCountryMatch2() {
        log.info("testGetTranslation_languageMatchButNoCountryMatch2()");

        Locale aLocale = new Locale.Builder().setLanguage("en").setRegion("CA").build();
        String uiTextValue = dbI18NProvider.getTranslation("hello", aLocale);
        log.info("hello="+uiTextValue);
        Assert.assertNotNull(uiTextValue);
        // There is no entry for Canada (CA) and the first entry with 'en' as language is AU. However, further down
        // the list there is one with country code ''. That's the one we expect
        Assert.assertEquals("Hello", uiTextValue);
    }

    @Test
    public void testGetTranslation() {
        log.info("testGetTranslation()");

        Locale aLocale = new Locale.Builder().setLanguage("en").setRegion("").build();
        String uiText = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+uiText);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("Welcome", uiText);

        aLocale = new Locale.Builder().setLanguage("en").setRegion("").build();
        uiText = dbI18NProvider.getTranslation("home.favcolor", aLocale);
        log.info("home.favcolor="+uiText);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("My favourite colour is blue", uiText);

        aLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        uiText = dbI18NProvider.getTranslation("home.favcolor", aLocale);
        log.info("home.favcolor="+uiText);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("My favorite color is blue", uiText);

        aLocale = new Locale.Builder().setLanguage("de").setRegion("").build();
        uiText = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+uiText);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("Wilkommen", uiText);

        aLocale = new Locale.Builder().setLanguage("zh").setRegion("").build();
        uiText = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+uiText);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("歡迎", uiText);
    }
}
