package com.piecon.i18n.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Page extends I18nEntity {

    public Page(String i18nKey, String languageCode, String countryCode) {
        super(i18nKey, languageCode, countryCode);
    }

    private String title;
    private String text1;
}
