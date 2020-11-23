package com.piecon.i18n;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;


@Route(value = "hello-world", layout = MainLayout.class)
@Slf4j
public class HelloWorld extends VerticalLayout implements LocaleChangeObserver {
    private static int counter = 0;

    Button localeDependentButton = new Button();
    Span someText = new Span();

    public HelloWorld() {
        add(localeDependentButton, someText);

        setUiTexts();
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        Notification.show("HelloWorld: locale changed: "+ ++counter);
        setUiTexts();
    }

    private void setUiTexts() {
        localeDependentButton.setText(getTranslation("home.welcome"));
        someText.setText(getTranslation("home.info"));
    }
}