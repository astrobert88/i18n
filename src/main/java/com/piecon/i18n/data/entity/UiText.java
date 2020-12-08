package com.piecon.i18n.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class UiText extends I18nEntity {

    private String textValue;

    public UiText(String i18nKey, String languageCode, String countryCode) {
        super(i18nKey, languageCode, countryCode);
    }
}
