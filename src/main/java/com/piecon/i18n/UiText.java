package com.piecon.i18n;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
@NoArgsConstructor
public class UiText {

    public UiText(String textKey) {
        this.textKey = textKey;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @NotEmpty
    private String languageCode;

    @NotEmpty
    private String countryCode;

    @NotNull
    @NotEmpty
    private String textKey;

    private String textValue;

}
