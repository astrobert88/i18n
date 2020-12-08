package com.piecon.i18n.core;

import com.piecon.i18n.data.entity.I18nEntity;
import com.piecon.i18n.data.entity.Page;
import com.piecon.i18n.data.entity.UiText;
import com.piecon.i18n.data.service.UiTextService;
import com.vaadin.flow.i18n.I18NProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class DbI18NProvider implements I18NProvider {

    private UiTextService uiTextService;

    public DbI18NProvider(@Autowired UiTextService uiTextService) {
        log.debug("DbI18NProvider() constructor called. uiTextService autowired to " + uiTextService);
        this.uiTextService = uiTextService;
    }


    @Override
    public List<Locale> getProvidedLocales() {
        List<Locale> providedLocales = new ArrayList<>();

        String[][] distinctLocales = uiTextService.getDistinctLocales();

        for (String[] strings : distinctLocales) {
            String languageCode = strings[0];
            String countryCode = strings[1];

            Locale aLocale = new Locale.Builder().setLanguage(languageCode).setRegion(countryCode).build();
            providedLocales.add(aLocale);
            log.info("Added: " + aLocale);
        }

        return providedLocales;
    }

    /**
     * This method will fetch the text value of the UiText entity with the given i18NKey.
     *
     * There may be multiple UiText entities with the given i18NKey but only one for the given locale.
     *
     * @param i18NKey
     * @param locale
     * @param objects
     * @return
     */
    @Override
    public String getTranslation(String i18NKey, Locale locale, Object... objects) {
        log.info("getTranslation(i18NKey:" + i18NKey + ", locale:" + locale + ")");

        return getTranslation(UiText.class, i18NKey, locale);
    }


    /**
     * This method will fetch the text value of the given entity with the given i18NKey.
     *
     * The entity must implement I18nEntity.
     *
     * There may be multiple entities with the given i18NKey but only one for the given locale.
     *
     */
     public String getTranslation(Class<? extends I18nEntity> textSourceClass,
                                  String textSourceProperty,
                                  Locale locale,
                                  Object... objects) {
        log.info("getTranslation(textSourceClass:" + textSourceClass + ", textSourceProperty:" + textSourceProperty +
                 ", Locale:" + locale);

        // Find a more elegant approach..
         if (textSourceClass == UiText.class) {
             return uiTextService.getUiText(textSourceProperty, locale).getTextValue();
         }
         else if (textSourceClass == Page.class) {
             return null;//pageService.getPage(textSourceProperty, locale).getTextValue();
         }
         else {
             throw new RuntimeException("Unsupported class:" + textSourceClass);
         }

    }


    public String[] getProvidedLanguages() {
        return uiTextService.getDistinctLanguages();
    }

    /**
     * This method looks for any properties in the given class which are annotated with @GetTextFrom. It then gets the
     * text in the language specified by the locale and calls the setText() method on each property.
    void setTextOnAnnotatedProperties(Class<?> annotatedClass, Locale locale) {

        log.info("setTextOnAnnotatedProperties()");

        for (Field field : annotatedClass.getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(GetTextFrom.class)) {
                log.info("Annotation found for " + field);

                GetTextFrom annotation = field.getAnnotation(GetTextFrom.class);

                final Class<?> textSourceClass = annotation.textSourceClass();
                final String textSourceProperty = annotation.textSourceProperty();

                log.info("textSourceClass =" + textSourceClass);
                log.info("textSourceProperty =" + textSourceProperty);

                String text = getTranslation(messageKey, locale);
                log.info("translation=" + translation);
            }
        }
        I18nTranslate annotation = navigationTarget.getAnnotation(I18nTranslate.class);
        if (annotation == null) {
            log.info(ERROR_MSG_NO_ANNOTATION + navigationTarget.getName());
        } else {
            log.info("annotation found!!!!");
            final String messageKey = (annotation.messageKey().isEmpty())
                    ? annotation.defaultValue()
                    : annotation.messageKey();

            log.info("messageKey=" + messageKey);

            final I18NProvider i18NProvider = VaadinService
                    .getCurrent()
                    .getInstantiator()
                    .getI18NProvider();
            final Locale locale = event.getUI().getLocale();
            final List<Locale> providedLocales = i18NProvider.getProvidedLocales();

            Locale providedLocale = null;

            if (locale == null && providedLocales.isEmpty()) {
                log.info(ERROR_MSG_NO_LOCALE + i18NProvider.getClass().getName());
            } else if (locale == null) {
                providedLocale = providedLocales.get(0);
            } else if (providedLocales.contains(locale)) {
                providedLocale = locale;
            } else {
                providedLocale = providedLocales.get(0);
            }
        }
    }
     */
}
