package com.piecon.i18n;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
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
    public void testGetTranslation() {
        log.info("testGetTranslation()");

        Locale aLocale = new Locale.Builder().setLanguage("en").setRegion("").build();
        String message = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+message);
        Assert.assertNotNull(message);
        Assert.assertEquals("Welcome", message);

        aLocale = new Locale.Builder().setLanguage("en").setRegion("").build();
        message = dbI18NProvider.getTranslation("home.favcolor", aLocale);
        log.info("home.favcolor="+message);
        Assert.assertNotNull(message);
        Assert.assertEquals("My favourite colour is blue", message);

        aLocale = new Locale.Builder().setLanguage("en").setRegion("US").build();
        message = dbI18NProvider.getTranslation("home.favcolor", aLocale);
        log.info("home.favcolor="+message);
        Assert.assertNotNull(message);
        Assert.assertEquals("My favorite color is blue", message);

        aLocale = new Locale.Builder().setLanguage("de").setRegion("").build();
        message = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+message);
        Assert.assertNotNull(message);
        Assert.assertEquals("Wilkommen", message);

        aLocale = new Locale.Builder().setLanguage("zh").setRegion("").build();
        message = dbI18NProvider.getTranslation("home.welcome", aLocale);
        log.info("home.welcome="+message);
        Assert.assertNotNull(message);
        Assert.assertEquals("歡迎", message);
    }
}
