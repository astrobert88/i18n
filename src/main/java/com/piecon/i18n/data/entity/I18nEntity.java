package com.piecon.i18n.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A class extending this class must have an i18nKey property. This key in combination with a language code and a
 * country code makes the object unique in a given locale.
 *
 * For example, the class UiText extends this class. The i18nKey for UiText is the message key e.g. welcome
 * for which there is a unique translation for each locale;
 *
 * i18nKey        language_code  country_code  textValue
 * ------------   -------------  ------------  ---------
 * welcome        en             AU            Welcome
 * welcome        nl             NL            Welkom
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
@NoArgsConstructor
public class I18nEntity extends AbstractEntity {

    @NotNull
    @NotEmpty
    private String i18nKey;

    @NotNull
    @NotEmpty
    @Size(max = 2)
    private String languageCode;

    @NotNull
    @Size(max = 2)
    private String countryCode;

    public I18nEntity(@NonNull String i18nKey, @NonNull String languageCode, @NonNull String countryCode) {
        this.i18nKey = i18nKey;
        this.languageCode = languageCode;
        this.countryCode = countryCode;
    }
}
