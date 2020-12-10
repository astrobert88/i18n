package com.piecon.i18n.core;

import com.piecon.i18n.data.entity.Page;
import com.piecon.i18n.data.entity.UiText;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
@AutoConfigureTestDatabase
public class DbI18NProviderTest implements I18nConstants {

    @Autowired
    private DbI18NProvider dbI18NProvider;

    /*
    @Test
    public void getProvidedLocales() {
        log.info("getProvidedLocales()");

        // Check autowiring
        Assert.assertNotNull(dbI18NProvider);

        List<Locale> locales = dbI18NProvider.getProvidedLocales();
        Assert.assertNotNull(locales);
        Assert.assertEquals(8, locales.size());
    }

    @Test
    public void getTranslation_noKeyMatch() {
        log.info("getTranslation_noKeyMatch()");

        // Create locale. Language and country not used by this test
        Locale aLocale = new Locale.Builder().build();
        String translation = dbI18NProvider.getTranslation("no.such.key", aLocale);
        Assert.assertNotNull(translation);
        // translation should be set to the key itself.
        Assert.assertEquals("no.such.key", translation);
    }

    @Test
    public void getTranslation_keyMatch_noLanguageMatch() {
        log.info("getTranslation_keyMatch_noLanguageMatch()");

        // No entry for Zulu language...
        Locale aLocale = new Locale.Builder().setLanguage("zu").build();
        String uiTextValue = dbI18NProvider.getTranslation("login", aLocale);
        Assert.assertNotNull(uiTextValue);
        Assert.assertEquals("login", uiTextValue);
    }

    @Test
    public void getTranslation_keyMatch_languageMatch_noCountryMatch() {
        log.info("getTranslation_keyMatch_languageMatch_noCountryMatch()");

        // Belgium (BE) not in test data
        Locale aLocale = new Locale.Builder().setLanguage("de").setRegion("BE").build();
        String uiTextValue = dbI18NProvider.getTranslation("login", aLocale);
        Assert.assertNotNull(uiTextValue);
        // There is no country match for Belgium (BE) but there is a match for the German language without a
        // country code so return that.
        Assert.assertEquals("Anmelden", uiTextValue);
    }

    @Test
    // Almost identical to previous test but now the first entry to match the language has the wrong country code.
    // Even though there is one without a country code further down. It should return the one without a country code.
    public void getTranslation_keyMatch_languageMatch_noCountryMatch2() {
        log.info("testGetTranslation_keyMatch_languageMatch_noCountryMatch2()");

        // No entry for CA and the first entry found with matching language is AU. However, further down the table,
        // there is an entry with no country code set. That one should be returned instead.
        Locale aLocale = new Locale.Builder().setLanguage("en").setRegion("CA").build();
        String uiTextValue = dbI18NProvider.getTranslation("hello", aLocale);
        Assert.assertNotNull(uiTextValue);
        Assert.assertEquals("Hello", uiTextValue);
    }

    @SneakyThrows
    @Test(expected = NullPointerException.class)
    public void getTranslationFromBeanProperty_nullKey() {
        log.info("getTranslationFromBeanProperty_nullKey()");

        // Create locale. Language and country not used by this test
        Locale aLocale = new Locale.Builder().build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty(null, null, null, null);
    }

    @SneakyThrows
    @Test(expected = NullPointerException.class)
    public void getTranslationFromBeanProperty_nullTextSourceClass() {
        log.info("getTranslationFromBeanProperty_nullTextSourceClass()");

        // Create locale. Language and country not used by this test
        Locale aLocale = new Locale.Builder().build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty("no.such.key", null, null, null);
    }

    @SneakyThrows
    @Test(expected = NullPointerException.class)
    public void getTranslationFromBeanProperty_nullTextSourceProperty() {
        log.info("getTranslationFromBeanProperty_nullTextSourceProperty()");

        // Create locale. Language and country not used by this test
        Locale aLocale = new Locale.Builder().build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty("no.such.key", Page.class, null, null);
    }

    @SneakyThrows
    @Test(expected = NullPointerException.class)
    public void getTranslationFromBeanProperty_nullLocale() {
        log.info("getTranslationFromBeanProperty_nullLocale()");

        String translation = dbI18NProvider.getTranslationFromBeanProperty("no.such.key", Page.class, PAGE_TITLE, null);
    }


    @SneakyThrows
    @Test
    public void getTranslationFromBeanProperty_noKeyMatch() {
        log.info("getTranslationFromBeanProperty_noKeyMatch()");

        // Create locale. Language and country not used by this test
        Locale locale = new Locale.Builder().build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty("no.such.key", Page.class, PAGE_TITLE, locale);
        Assert.assertEquals(PAGE_404, translation);
    }

    @SneakyThrows
    @Test
    public void getTranslationFromBeanProperty_keyMatch_noLanguageMatch() {
        log.info("getTranslationFromBeanProperty_keyMatch_noLanguageMatch()");

        // No entry for Zulu language...
        Locale locale = new Locale.Builder().setLanguage("zu").build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty(PAGE_HOME, Page.class, PAGE_TITLE, locale);
        Assert.assertEquals(PAGE_404, translation);
    }

    @SneakyThrows
    @Test
    public void getTranslationFromBeanProperty_keyMatch_languageMatch_noCountryMatch() {
        log.info("getTranslationFromBeanProperty_keyMatch_languageMatch_noCountryMatch()");

        // Country NL with language en not in test data. Should return en with '' country code entity.
        Locale locale = new Locale.Builder().setLanguage("en").setRegion("NL").build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty(PAGE_HOME, Page.class, PAGE_TITLE, locale);
        Assert.assertEquals("Home Page", translation);
    }

    @SneakyThrows
    @Test
    public void getTranslationFromBeanProperty_keyMatch_languageMatch_countryMatch() {
        log.info("getTranslationFromBeanProperty_keyMatch_languageMatch_countryMatch()");

        Locale locale = new Locale.Builder().setLanguage("en").setRegion("AU").build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty(PAGE_HOME, Page.class, PAGE_TITLE, locale);
        Assert.assertEquals("Down Under", translation);
    }

    @SneakyThrows
    @Test
    public void getTranslationFromBeanProperty_keyMatch_languageMatch_countryMatch2() {
        log.info("getTranslationFromBeanProperty_keyMatch_languageMatch_countryMatch2()");

        Locale locale = new Locale.Builder().setLanguage("en").setRegion("AU").build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty(PAGE_HOME, Page.class, PAGE_TEXT1, locale);
        Assert.assertEquals("Welcome to Down Under!", translation);
    }

    @SneakyThrows
    @Test
    public void getTranslationFromBeanProperty_keyMatch_languageMatch_countryMatch3() {
        log.info("getTranslationFromBeanProperty_keyMatch_languageMatch_countryMatch3()");

        Locale locale = new Locale.Builder().setLanguage("nl").setRegion("").build();
        String translation = dbI18NProvider.getTranslationFromBeanProperty(PAGE_HOME, Page.class, PAGE_TITLE, locale);
        Assert.assertEquals("Thuis Pagina", translation);

        translation = dbI18NProvider.getTranslationFromBeanProperty(PAGE_HOME, Page.class, PAGE_TEXT1, locale);
        Assert.assertEquals("Welkom op de thuispagina!", translation);
    }

     */

    @SneakyThrows
    @Test(expected = I18nGetTextAnnotationException.class)
    public void setTextOnI18nGetTextAnnotatedFields_annotatedFieldDoesNotImplementHasText() {
        log.info("setTextOnI18nGetTextAnnotatedFields()");

        class ClassWithInvalidI18nGetTextAnnotatedField {
            // This field does not implement HasText so this annotation is invalid
            @I18nGetText(i18nKey = "login", textSourceClass = UiText.class, textSourceField = "textValue")
            int i = 666;

            int getI() { return i; }
        }
        ClassWithInvalidI18nGetTextAnnotatedField testClass = new ClassWithInvalidI18nGetTextAnnotatedField();

        Locale locale = new Locale.Builder().setLanguage("de").setRegion("").build();
        Assert.assertEquals(666, testClass.getI());
        dbI18NProvider.setTextOnI18nGetTextAnnotatedFields(testClass, locale);
        // Should never get this far
        Assert.assertTrue(false);
    }

    @SneakyThrows
    @Test
    public void setTextOnI18nGetTextAnnotatedFields_singleAnnotatedField() {
        log.info("setTextOnI18nGetTextAnnotatedFields_singleAnnotatedField()");

        // Define a class with an I18nGetText annotated property for testing
        class ClassWithValidI18nGetTextAnnotatedField {
            @I18nGetText(i18nKey = UI_TEXT_LOGIN, textSourceClass = UiText.class, textSourceField = "textValue")
            final Span span1 = new Span("before translation");
            Span getSpan1() { return span1; }
        }
        ClassWithValidI18nGetTextAnnotatedField testClass = new ClassWithValidI18nGetTextAnnotatedField();
        Assert.assertEquals("before translation", testClass.getSpan1().getText());

        Locale locale = new Locale.Builder().setLanguage("de").setRegion("").build();
        dbI18NProvider.setTextOnI18nGetTextAnnotatedFields(testClass, locale);
        Assert.assertEquals("Anmelden", testClass.getSpan1().getText());
    }

    @SneakyThrows
    @Test
    public void setTextOnI18nGetTextAnnotatedFields_multipleAnnotatedField() {
        log.info("setTextOnI18nGetTextAnnotatedFields_multipleAnnotatedField()");

        // Define a class with multiple I18nGetText annotated property for testing
        class ClassWithValidI18nGetTextAnnotatedFields {
            @I18nGetText(i18nKey = UI_TEXT_SETTINGS, textSourceClass = UiText.class, textSourceField = "textValue")
            final Span spanSettings = new Span("empty span");
            Span getSpanSettings() { return spanSettings; }

            @I18nGetText(i18nKey = PAGE_HOME, textSourceClass = Page.class, textSourceField = "title")
            final Button buttonTitle = new Button("empty button");
            Button getButtonTitle() { return buttonTitle; }

            @I18nGetText(i18nKey = PAGE_HOME, textSourceClass = Page.class, textSourceField = "text1")
            final TextField textFieldText1 = new TextField("empty textfield");
            TextField getTextFieldText1() { return textFieldText1; }
        }

        ClassWithValidI18nGetTextAnnotatedFields testClass = new ClassWithValidI18nGetTextAnnotatedFields();
        Assert.assertEquals("empty span", testClass.getSpanSettings().getText());
        Assert.assertEquals("empty button", testClass.getButtonTitle().getText());
        Assert.assertEquals("empty textfield", testClass.getTextFieldText1().getLabel());

        Locale locale = new Locale.Builder().setLanguage("en").setRegion("").build();
        dbI18NProvider.setTextOnI18nGetTextAnnotatedFields(testClass, locale);

        Assert.assertEquals("Settings", testClass.getSpanSettings().getText());
        Assert.assertEquals("Home Page", testClass.getButtonTitle().getText());
        Assert.assertEquals("Welcome to the home page!", testClass.getTextFieldText1().getLabel());
    }
}