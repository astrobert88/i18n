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

    private MessageService messageService;

    public DbI18NProvider(@Autowired MessageService messageService) {
        log.debug("DbI18NProvider() constructor called. messageService autowired to "+messageService);
        this.messageService = messageService;
    }


    @Override
    public List<Locale> getProvidedLocales() {
        List<Locale> providedLocales = new ArrayList<Locale>();

        String[][] languagesCountriesArray = messageService.findDistinctLocales();

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
    public String getTranslation(String messageKey, Locale locale, Object... objects) {
        log.debug("getTranslation(messageKey:"+messageKey+", local:"+locale+") called");

        Message msg = messageService.getMessage(messageKey, locale);

        if (msg == null) {
            msg = new Message();
            msg.setMessageContent("No message in table: languageCode="+
                            locale.getLanguage()+", countryCode="+locale.getCountry()
            + " "+messageKey
            );
        }

        return msg.getMessageContent();
    }
}
