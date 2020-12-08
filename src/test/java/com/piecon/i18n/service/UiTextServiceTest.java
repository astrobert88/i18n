package com.piecon.i18n.service;

import com.piecon.i18n.core.I18nConstants;
import com.piecon.i18n.data.entity.UiText;
import com.piecon.i18n.data.service.UiTextService;
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
public class UiTextServiceTest implements I18nConstants {

    @Autowired
    private UiTextService uiTextService;

    @Test
    public void getAllUiTextsReturnsAllAsList() {
        log.info("getAllUiTextsReturnsAllAsList()");

        List<UiText> allUiTexts = uiTextService.getAllUiTexts();
        Assert.assertEquals(21, allUiTexts.size());
    }

    @Test(expected = NullPointerException.class)
    public void getUiTextThrowsNullPointerExceptionWhenI18nKeyIsNull() {
        log.info("getUiTextThrowsNullPointerExceptionWhenI18nKeyIsNull()");

        uiTextService.getUiText(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void getUiTextThrowsNullPointerExceptionWhenLocaleIsNull() {
        log.info("getUiTextThrowsNullPointerExceptionWhenLocaleIsNull()");

        uiTextService.getUiText(UI_TEXT_LOGIN, null);
    }

    @Test
    public void getUiTextWhenI18nKeyNotFound() {
        log.info("getUiTextWhenI18nKeyNotFound()");

        Locale locale = new Locale.Builder().build();
        UiText uiText = uiTextService.getUiText("no.such.key", locale);

        Assert.assertNotNull(uiText);
        Assert.assertEquals("no.such.key", uiText.getTextValue());
    }

    @Test
    public void getUiTextWhenLanguageCodeNotFound() {
        log.info("getUiTextWhenLanguageCodeNotFound()");

        Locale locale = new Locale.Builder().setLanguage("zu").build();
        UiText uiText = uiTextService.getUiText(UI_TEXT_LOGIN, locale);

        Assert.assertNotNull(uiText);
        Assert.assertEquals(UI_TEXT_LOGIN, uiText.getTextValue());
    }

    @Test
    public void getUiTextWhenCountryCodeNotFoundButEmptyCountryCodeExists() {
        log.info("getUiTextWhenCountryCodeNotFoundButEmptyCountryCodeExists()");

        Locale locale = new Locale.Builder().setLanguage("en").setRegion("GB").build();
        UiText uiText = uiTextService.getUiText("hello", locale);

        // Should return UiText with language code equal to 'en' and country code ''
        Assert.assertNotNull(uiText);
        Assert.assertEquals("Hello", uiText.getTextValue());
    }

    @Test
    public void getUiTextWhenCountryCodeNotFoundButAnotherCountryCodeExists() {
        log.info("getUiTextWhenCountryCodeNotFoundButAnotherCountryCodeExists()");

        Locale locale = new Locale.Builder().setLanguage("de").setRegion("BE").build();
        UiText uiText = uiTextService.getUiText("hello", locale);

        // Should return UiText with language code equal to 'de' and country code 'DE'
        Assert.assertNotNull(uiText);
        Assert.assertEquals("Guten tag", uiText.getTextValue());
    }
}
