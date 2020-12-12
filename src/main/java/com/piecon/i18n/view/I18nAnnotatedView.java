package com.piecon.i18n.view;

import com.piecon.i18n.core.DbI18NProvider;
import com.piecon.i18n.core.I18nConstants;
import com.piecon.i18n.core.I18nGetText;
import com.piecon.i18n.core.I18nGetTextAnnotationException;
import com.piecon.i18n.data.entity.Page;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Route(value = "i18n-annotated-view", layout = MainLayout.class)
@PageTitle("Testing annotations")
@Slf4j
public class I18nAnnotatedView extends VerticalLayout implements I18nConstants, HasUrlParameter<String>, LocaleChangeObserver {

    @I18nGetText(i18nKey = PAGE_HOME, textSourceClass = Page.class, textSourceField = PAGE_TEXT1)
    private final Span pageText1 = new Span();
    @I18nGetText(i18nKey = PAGE_HOME, textSourceClass = Page.class, textSourceField = PAGE_TITLE)
    private final Span pageTitle = new Span();
    @I18nGetText(i18nKey = "username")
    private final TextField textFieldUsername = new TextField();


    public I18nAnnotatedView() {
        log.info("I18nAnnotatedView()");

        add(new H3("Annotations"));
        add(pageTitle);
        add(pageText1);
        add(textFieldUsername);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, String s) {

        log.info("setParameter(" + s + ")");
    }

    @Override
    public void localeChange(LocaleChangeEvent event) {
        final DbI18NProvider i18NProvider = (DbI18NProvider) VaadinService
                .getCurrent()
                .getInstantiator()
                .getI18NProvider();
        final Locale locale = event.getLocale();

        try {
            log.info("Processing @"+ I18nGetText.class.getName() + " annotations...");
            i18NProvider.setTextOnI18nGetTextAnnotatedFields(this, locale);
            log.info("Annotations processed!");
        } catch (I18nGetTextAnnotationException | IllegalAccessException e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
    }
}
