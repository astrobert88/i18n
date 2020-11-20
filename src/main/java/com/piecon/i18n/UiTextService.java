package com.piecon.i18n;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UiTextService {

    private final UiTextRepository uiTextRepository;
    private final List<UiText> uiTexts;

    public UiTextService(UiTextRepository uiTextRepository) {
        this.uiTextRepository = uiTextRepository;
        uiTexts = uiTextRepository.findAll();
        log.info("Loaded " + uiTexts.size() + " uiTexts into memory");
    }

    public UiText getUiText(String textKey, Locale locale) {
        log.info("getUiText(textKey='" + textKey + "', locale='" + locale + "')");

        UiText uiText = null;
        String language = locale.getLanguage();
        String country = locale.getCountry();

        // Reduce list of UiTexts to those that have the given key and language
        Predicate<UiText> withKeyAndLang =
                uit -> uit.getTextKey().equals(textKey) && uit.getLanguageCode().equals(language);
        List<UiText> uiTextsWithKeyAndLang = uiTexts.stream().filter(withKeyAndLang).collect(Collectors.toList());

        // If there is no UiText with the key and language, just set the text value to the text key and log error
        if (uiTextsWithKeyAndLang.size() == 0) {
            log.error("No UiText found with textKey = '" + textKey + "'!");

            UiText uiTxt = new UiText(textKey);
            uiTxt.setTextValue(textKey);

            return uiTxt;
        }

        // One or more UiTexts with the given textKey and language were found.
        // Cycle over all of them and look for a country code match.
        // If there is no country code match, just return the first text in the list. This means you might get a text
        // in the right language but for another country than asked for.
        for (UiText uiText1 : uiTextsWithKeyAndLang) {
            // If we have a perfect match
            if (uiText1.getCountryCode().equals(country)) {
                return uiText1;
            }
            // else if first in list, remember it. This may have the wrong country code.
            if (uiText == null ) {
                uiText = uiText1;
            }
            // If first in list was remembered, it may have the wrong country code. If the current entry has no
            // country code, remember that instead
            else if (uiText1.getCountryCode().equals("")) {
                uiText = uiText1;
            }

        }
        // If we got to here, there was a language match but no country match. Just return the first one in the list
        return uiText;
    }


    public String[][] findDistinctLocales() {
        return uiTextRepository.findDistinctLocales();
    }
}