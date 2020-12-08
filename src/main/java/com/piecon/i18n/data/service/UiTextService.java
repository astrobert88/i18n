package com.piecon.i18n.data.service;

import com.piecon.i18n.data.entity.I18nEntity;
import com.piecon.i18n.data.entity.UiText;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@Slf4j
public class UiTextService extends I18nEntityService {

    private UiTextRepository uiTextRepository;

    public UiTextService(UiTextRepository uiTextRepository) {
        super(uiTextRepository);
        this.uiTextRepository = uiTextRepository;
    }

    @SuppressWarnings("unchecked")
    public List<UiText> getAllUiTexts() {
        return (List<UiText>) (List<?>) getAllI18nEntities();
    }

    /**
     * Returns the UiText object with the given i18nKey for the given Locale.
     * <p>
     * This method never returns null; if no UiText is found with the given key and locale, an error is logged and
     * the method returns a UiText with the textValue set to the i18nKey.
     *
     * @param i18nKey
     * @param locale
     * @return
     */
    public UiText getUiText(@NonNull String i18nKey, @NonNull Locale locale) {
        log.info("getUiText(i18nKey=" + i18nKey + ", locale=" + locale + ")");

        I18nEntity i18nEntity = getI18nEntity(i18nKey, locale);

        if (i18nEntity == null) {
            UiText uiText = new UiText(i18nKey, locale.getLanguage(), locale.getCountry());
            uiText.setTextValue(i18nKey);

            return uiText;
        }
        else {
            return (UiText) i18nEntity;
        }
    }

    /**
     * UiText is the main i18nEntity class and used by DbI18NProvider to work out which locales are provided.
     * @return
     */
    public String[][] getDistinctLocales() {
        return uiTextRepository.findDistinctLocales();
    }

    public String[] getDistinctLanguages() {
        return uiTextRepository.findDistinctLanguages();
    }
}