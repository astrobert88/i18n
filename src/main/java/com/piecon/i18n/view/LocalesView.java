package com.piecon.i18n.view;

import com.piecon.i18n.core.DbI18NProvider;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;

@Route(value = "locales-view", layout = MainLayout.class)
@PageTitle("Select Country")
@Slf4j
public class LocalesView extends VerticalLayout implements LocaleChangeObserver {

    private final DbI18NProvider dbI18NProvider;
//    TextField numberField = new TextField();
//    TextField currencyField = new TextField();
    Span country = new Span();
    Span language = new Span();
//    Image currentCountryFlag = new Image();

    public LocalesView(DbI18NProvider dbI18NProvider) {
        log.info("LocalesView()");
        this.dbI18NProvider = dbI18NProvider;

        add(new H3("Current Locale"));

        // Don't set the text until the locale has been set
        //country.addAttachListener(e -> setUiText(e.getSource(), UI.getCurrent().getLocale().getCountry()));
        add(country);
        add(language);

/*
        currentCountryFlag = new Image("img/language-flags/"+currentLocale.getCountry()+".svg",
                currentLocale.getCountry()+".svg");
        currentCountryFlag.getStyle().set("width", "50px");
        add(currentCountryFlag);

        NumberFormat numberFormatter = NumberFormat.getNumberInstance(currentLocale);
        numberField.setValue(numberFormatter.format(1234567.890d));
        add(numberField);

        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(currentLocale);
        currencyField.setValue(currencyFormatter.format(1234567.89d));
        add(currencyField);

        add(new H3("Select Country"));

        List<Locale> supportedLanguageLocales = new ArrayList<>();
        List<String> excludedLocales = Arrays.asList("en_001", "en_150", "en_DG");

        // For each..
        for (String providedLanguage : dbI18NProvider.getProvidedLanguages()) {
            // provided language, get all countries where they speak it..
            List<Locale> locales = Arrays.asList(Locale.getAvailableLocales())
                    .stream().filter(l ->
                            // and provided there is a country code
                            l.getLanguage().equals(providedLanguage) &&
                                    l.getVariant().equals("") &&
                                    !l.getCountry().equals("") &&
                                    !excludedLocales.contains(l.toString()))
                    // add it to our collection
                    .collect(Collectors.toList());

            // Add this language/countries set to our collection
            supportedLanguageLocales.addAll(locales);
        }
        // We now have a List of all countries where they speak one of the lanugages supported by this website
        supportedLanguageLocales.sort((l1, l2) -> l1.getCountry().compareTo(l2.getCountry()));

        // Print them all out
        for (Locale locale : supportedLanguageLocales) {
            Button flagButton = createFlagButton(locale);
            add(flagButton);
            add(new Span(locale.getDisplayCountry(locale) + " " + locale.getDisplayLanguage(locale) + " " + locale));
        }
*/
    }

/*
    private Button createFlagButton(Locale locale) {
        Image flagImage = createFlagImage(locale);
        Button button = new Button(flagImage);

        button.addClickListener(e -> {
            currentLocaleSelected.setText(locale.getDisplayCountry(locale) + " " + locale.getDisplayLanguage(locale));
            currentCountryFlag.setSrc(flagImage.getSrc());
            VaadinSession.getCurrent().setLocale(locale);
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            currencyField.setValue(currencyFormatter.format(1234567.89d));
        });

        return button;
    }

    private Image createFlagImage(Locale locale) {
        final String flagName;

        if (!locale.getCountry().equals("")) {
            flagName = locale.getCountry();
        }
        else {
            flagName = locale.getLanguage();
        }

        Image flagImage = new Image("img/language-flags/"+flagName+".svg", flagName+".svg");
        flagImage.getStyle().set("width", "50px");

        return flagImage;
    }
*/

    /**
     * This method gets called whenever the Locale is set.
     *
     * The initial locale is decided by matching the locales provided by the I18NProvider against the Accept-Language
     * header in the initial response from the client.
     *
     * If an exact match (language + country) is found that will then be used, else we will try to match on only
     * language. If neither is found the locale will be set to the first 'supported' locale from
     * I18NProvider.getProvidedLocales() and if that is empty Locale.getDefault() will be used.
     *
     * @param localeChangeEvent
     */
    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        log.info("localeChange()");
        log.info("localeChangeEvent.getSource()="+localeChangeEvent.getSource());

        Locale newLocale = localeChangeEvent.getLocale();
        setUiText(newLocale);
    }

    private void setUiText(Locale locale) {
        log.info("setUiText("+locale+")");

        country.setText("Country: " + locale.getDisplayCountry());
        language.setText("Language: " + locale.getDisplayLanguage());
    }
}
