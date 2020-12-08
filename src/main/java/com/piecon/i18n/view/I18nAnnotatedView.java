package com.piecon.i18n.view;

import com.piecon.i18n.core.I18nConstants;
import com.piecon.i18n.I18nTranslate;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.Locale;

@Route(value = "i18n-annotated-view", layout = MainLayout.class)
@PageTitle("Testing annotations")
@Slf4j
public class I18nAnnotatedView extends VerticalLayout implements LocaleChangeObserver, I18nConstants, HasUrlParameter<String> {

    //@I18nTranslate(tableName = TABLE_UI_TEXT, textKey = FAV_COLOR)
    private final Span favColor = new Span();

    //@I18nTranslate(tableName = TABLE_PAGE, textKey = PAGE_HOME)
    private final Span pageTitle = new Span();


    public I18nAnnotatedView() {
        log.info("I18nAnnotatedView()");

        add(new H3("Annotations"));
        add(favColor);
    }

    /**
     * This method is called by the framework whenever the locale is changed.
     *
     * @param localeChangeEvent
     */
    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        log.info("localeChange()");

        translateViewContents(localeChangeEvent.getLocale());
    }

    private void translateViewContents(Locale newLocale) {
        // Cycle over each of this view's properties and see if any of them are annotated with @I18nTranslate
        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(I18nTranslate.class)) {
                log.info("Annotation @" + I18nTranslate.class.getSimpleName() + " found on property " + field.getName() +
                        " of " + field.getType());

                field.setAccessible(true);
                I18nTranslate annotation = field.getAnnotation(I18nTranslate.class);

                try {
                    // Make sure the annotated field implements HasText
                    Object annotatedProperty = field.get(this);

                    if (!HasText.class.isAssignableFrom(annotatedProperty.getClass())) {
                        log.error(annotatedProperty.getClass().getName() + " does not implement " +
                            HasText.class.getName() + ". " +
                            "The @" + annotation.getClass().getName() + " annotation can only be applied to properties " +
                            "which implement this interface. E.g. " + Component.class.getName());

                        // Default implementation.. to be done
                        return;
                    }

                    final String tableName = annotation.tableName();
                    log.info("tableName="+tableName);

                    final String textKey = annotation.textKey();
                    log.info("textKey="+textKey);

                    final I18NProvider i18NProvider = VaadinService
                            .getCurrent()
                            .getInstantiator()
                            .getI18NProvider();

                    String translation = i18NProvider.getTranslation(textKey, newLocale);
                    log.info("Translated text=" + translation);

                    ((HasText) field.get(this)).setText(translation);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

        log.info("setParameter("+s+")");
    }
}
