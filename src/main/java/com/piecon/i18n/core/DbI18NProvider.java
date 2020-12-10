package com.piecon.i18n.core;

import com.piecon.i18n.data.entity.I18nEntity;
import com.piecon.i18n.data.entity.Page;
import com.piecon.i18n.data.entity.UiText;
import com.piecon.i18n.data.service.PageService;
import com.piecon.i18n.data.service.UiTextService;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.i18n.I18NProvider;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Component
@Slf4j
public class DbI18NProvider implements I18NProvider {

    private final UiTextService uiTextService;
    private final PageService pageService;

    /**
     * @param uiTextService
     */
    public DbI18NProvider(@Autowired UiTextService uiTextService, @Autowired PageService pageService) {
        log.debug("DbI18NProvider() constructor called. uiTextService autowired to " + uiTextService);
        log.debug("pageService autowired to " + pageService);

        this.uiTextService = uiTextService;
        this.pageService = pageService;
    }


    /**
     * @return
     */
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
     * This method will fetch the text value of the UiText entity with the given i18nKey.
     * <p>
     * There may be multiple UiText entities with the given i18nKey but only one for the given locale.
     *
     * @param i18nKey
     * @param locale
     * @param objects
     * @return
     */
    @Override
    public String getTranslation(String i18nKey, Locale locale, Object... objects) {
        log.info("getTranslation(i18nKey:" + i18nKey + ", locale:" + locale + ")");

        String translation = null;
        try {
            translation = getTranslationFromBeanProperty(i18nKey, UiText.class, "textValue", locale);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // Never going to happen unless the UiText class is refactored and field 'textValue' is removed.
            e.printStackTrace();
        }

        return translation;
    }


    /**
     * This method will get the entity of the specified class with the specified i18nKey.
     * From this entity, it will return the value of the specified textSourceField
     */
    public String getTranslationFromBeanProperty(@NonNull String i18nKey,
                                                 @NonNull Class<? extends I18nEntity> textSourceClass,
                                                 @NonNull String textSourceField,
                                                 @NonNull Locale locale,
                                                 Object... objects) throws NoSuchFieldException, IllegalAccessException {

        log.info("getTranslationFromBeanProperty(i18nKey=" + i18nKey + ", " +
                "textSourceClass=" + textSourceClass + ", " +
                "textSourceField=" + textSourceField + ", " +
                "Locale:" + locale);

        // Find a more elegant approach..
        if (textSourceClass == UiText.class) {
            return uiTextService.getUiText(i18nKey, locale).getTextValue();
        } else if (textSourceClass == Page.class) {
            Page page = pageService.getPage(i18nKey, locale);
            Field field = textSourceClass.getDeclaredField(textSourceField);
            field.setAccessible(true); // Turn off otherwise private access modifier will cause Exception

            return (String) field.get(page);
        } else {
            throw new RuntimeException("Unsupported class:" + textSourceClass);
        }

    }


    /**
     *
     * @return public String[] getProvidedLanguages() {
    return uiTextService.getDistinctLanguages();
    }
     */

    /**
     * This method looks for any properties in the given class which are annotated with @I18nGetText. It then gets the
     * text in the language specified by the locale and calls the setText() method on each property.
     */
    public void setTextOnI18nGetTextAnnotatedFields(Object o, Locale locale)
            throws I18nGetTextAnnotationException, IllegalAccessException {

        log.info("setTextOnI18nGetTextAnnotatedFields(Object=" + o + ", locale=" + locale + ")");
        String objectClassname = o.getClass().getName();

        for (Field field : o.getClass().getDeclaredFields()) {
            field.setAccessible(true);

            if (field.isAnnotationPresent(I18nGetText.class)) {
                String fieldName = field.getName();
                Class<?> fieldClass = field.get(o).getClass();
                String fieldClassname = fieldClass.getName();

                log.info("@" + I18nGetText.class.getSimpleName() + " annotation found on field " +
                        objectClassname + "." + fieldName + " (" + fieldClassname + ")");

                // Sanity check; only properties implementing HasText() should be annotated with I18nGetText
                // How else can we call setText() on it?
                if (!HasText.class.isAssignableFrom(fieldClass)) {

                    String errorMessage =
                            "Property " + objectClassname + "." + fieldName + " is of type " +
                            fieldClassname + ". Properties of type " + fieldClassname + " cannot be annotated with " +
                            "@" + I18nGetText.class.getSimpleName() + " because it does not implement " +
                            HasText.class.getName() + ". This means we cannot call setText() on property " +
                            fieldName + "!";

                    log.error(errorMessage);

                    throw new I18nGetTextAnnotationException(errorMessage);
                }

                HasText property = (HasText) field.get(o);

                I18nGetText annotation = field.getAnnotation(I18nGetText.class);
                String i18nKey = annotation.i18nKey();
                Class<? extends I18nEntity> textSourceClass = annotation.textSourceClass();
                String textSourceField = annotation.textSourceField();

                try {
                    String text = getTranslationFromBeanProperty(i18nKey, textSourceClass, textSourceField, locale);
                    log.info("Calling " + objectClassname + "." + fieldName + ".setText('"+ text + "')");
                    property.setText(text);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                    log.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}
