package com.piecon.i18n;

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
        log.debug("DbI18NProvider() constructor called. uiTextService autowired to "+ uiTextService);
        this.uiTextService = uiTextService;
    }


    @Override
    public List<Locale> getProvidedLocales() {
        List<Locale> providedLocales = new ArrayList<Locale>();

        String[][] languagesCountriesArray = uiTextService.findDistinctLocales();

        for (int i = 0; i < languagesCountriesArray.length; i++) {
            String languageCode = languagesCountriesArray[i][0];
            String countryCode = languagesCountriesArray[i][1];

            Locale aLocale = new Locale.Builder().setLanguage(languageCode).setRegion(countryCode).build();
            providedLocales.add(aLocale);
            log.info("Added: "+aLocale);
        }

        return providedLocales;
    }

    @Override
    public String getTranslation(String uiTextKey, Locale locale, Object... objects) {
        log.debug("getTranslation(uiTextKey:"+uiTextKey+", local:"+locale+") called");

        UiText uiText = uiTextService.getUiText(uiTextKey, locale);

        if (uiText == null) {
            uiText = new UiText();
            uiText.setTextValue("No UiText in table: languageCode="+
                            locale.getLanguage()+", countryCode="+locale.getCountry()
            + " " + uiTextKey
            );
        }

        return uiText.getTextValue();
    }
}
