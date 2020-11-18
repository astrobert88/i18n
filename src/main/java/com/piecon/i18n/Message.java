package com.piecon.i18n;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Message {

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
    private String messageKey;

    private String messageContent;

}
