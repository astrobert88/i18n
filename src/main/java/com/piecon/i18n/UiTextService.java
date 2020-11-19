package com.piecon.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@Slf4j
public class UiTextService {

    private UiTextRepository uiTextRepository;

    public UiTextService(UiTextRepository uiTextRepository) { this.uiTextRepository = uiTextRepository; }

    public UiText getUiText(String textKey, Locale locale) {
        UiText uiText = uiTextRepository.findByLanguageCodeAndCountryCodeAndTextKey(
                locale.getLanguage(), locale.getCountry(), textKey);

        if (uiText == null) {
            uiText = new UiText();
            uiText.setTextValue("No UI uiText value found in table: languageCode="+ locale.getLanguage() + "," +
                                    "countryCode="+locale.getCountry() + " " + textKey
            );
        }

        return uiText;
    }

    public String[][] findDistinctLocales() {
        return uiTextRepository.findDistinctLocales();
    }
}
