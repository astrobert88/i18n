package com.piecon.i18n.view;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Locale;


@Route(value = "regional-formats-view", layout = MainLayout.class)
@Slf4j
public class RegionalFormatsView extends VerticalLayout implements LocaleChangeObserver {
    private static int counter = 0;

    final Locale currentLocale = UI.getCurrent().getLocale();

    Button localeDependentButton = new Button();
    Span someText = new Span("Formats for locale " + currentLocale.toString());
    TextField numberField = new TextField();
    TextField currencyField = new TextField();
    TextField longDate = new TextField("long date");
    TextField shortTime= new TextField();
    DatePicker shortDate = new DatePicker(LocalDate.now());


    public RegionalFormatsView() {
        add(new H3("currentLocale.toString()="+currentLocale.toString()));
        add(new H3("Country: "+currentLocale.getDisplayCountry()));
        add(new H3("Country2: "+currentLocale.getDisplayCountry(currentLocale)));
        add(new H3("Language: "+currentLocale.getDisplayLanguage()));
        add(someText);

        NumberFormat numberFormatter = NumberFormat.getNumberInstance(currentLocale);
        numberField.setValue(numberFormatter.format(1234567.890d));
        add(numberField);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        currencyField.setValue(currencyFormatter.format(1234567.89d));
        add(currencyField);

        setUiTexts();
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        Notification.show("RegionalFormatsView: locale changed: "+ ++counter);
        setUiTexts();
    }

    private void setUiTexts() {
        localeDependentButton.setText(getTranslation("home.welcome"));
    }
}