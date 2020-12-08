package com.piecon.i18n;

import com.piecon.i18n.core.DbI18NProvider;
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
        Assert.assertEquals(6, locales.size());
    }

    @Test
    public void testGetTranslation_noKeyMatch() {
        log.info("testGetTranslation_noKeyMatch()");

        // Create locale. Language and country not used by this test
        Locale aLocale = new Locale.Builder().build();
        String translation = dbI18NProvider.getTranslation("no.such.key", aLocale);
        Assert.assertNotNull(translation);
        // translation should be set to the key itself.
        Assert.assertEquals("no.such.key", translation);
    }

    @Test
    public void testGetTranslation_keyMatch_noLanguageMatch() {
        log.info("testGetTranslation_keyMatch_noLanguageMatch()");

        // No entry for Zulu language...
        Locale aLocale = new Locale.Builder().setLanguage("zu").build();
        String uiTextValue = dbI18NProvider.getTranslation("home.welcome", aLocale);
        Assert.assertNotNull(uiTextValue);
        Assert.assertEquals("home.welcome", uiTextValue);
    }

    @Test
    public void testGetTranslation_keyMatch_languageMatch_noCountryMatch() {
        log.info("testGetTranslation_keyMatch_languageMatch_noCountryMatch()");

        // Switzerland (CH) not in test data
        Locale aLocale = new Locale.Builder().setLanguage("de").setRegion("CH").build();
        String uiTextValue = dbI18NProvider.getTranslation("home.welcome", aLocale);
        Assert.assertNotNull(uiTextValue);
        // There is no country match for Switzerland (CH) but there is a match for the German language without a
        // country code so return that.
        Assert.assertEquals("Wilkommen", uiTextValue);
    }

    @Test
    // Almost identical to previous test but now the first entry to match the language has the wrong country code.
    // Even though there is one without a country code further down. It should return the one without a country code.
    public void testGetTranslation_keyMatch_languageMatch_noCountryMatch2() {
        log.info("testGetTranslation_keyMatch_languageMatch_noCountryMatch2()");

        // No entry for CA and the first entry found with matching language is AU. However, further down the table,
        // there is an entry with no country code set. That one should be returned instead.
        Locale aLocale = new Locale.Builder().setLanguage("en").setRegion("CA").build();
        String uiTextValue = dbI18NProvider.getTranslation("hello", aLocale);
        Assert.assertNotNull(uiTextValue);
        Assert.assertEquals("Hello", uiTextValue);
    }

    @Test
    public void testGetTranslation() {
        log.info("testGetTranslation()");

        // English, no country
        Locale aLocale = new Locale.Builder().setLanguage("en").setRegion("").build();
        String uiText = dbI18NProvider.getTranslation("home.welcome", aLocale);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("Welcome", uiText);

        // English, no country. First entry found is US but should still return the entry without a country code
        aLocale = new Locale.Builder().setLanguage("en").setRegion("").build();
        uiText = dbI18NProvider.getTranslation("home.favcolor", aLocale);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("My favourite colour is blue", uiText);

        // English, US. Expect the entry with US spelling ie. 'favorite' instead of 'favourite',
        // 'color' instead of 'colour'
        aLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        uiText = dbI18NProvider.getTranslation("home.favcolor", aLocale);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("My favorite color is blue", uiText);

        // German, no country. Expect German version of 'home.welcome'
        aLocale = new Locale.Builder().setLanguage("de").setRegion("").build();
        uiText = dbI18NProvider.getTranslation("home.welcome", aLocale);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("Wilkommen", uiText);

        // Chinese, no country. Expect Chinese version of 'home.welcome'
        aLocale = new Locale.Builder().setLanguage("zh").setRegion("").build();
        uiText = dbI18NProvider.getTranslation("home.welcome", aLocale);
        Assert.assertNotNull(uiText);
        Assert.assertEquals("歡迎", uiText);
    }
}
