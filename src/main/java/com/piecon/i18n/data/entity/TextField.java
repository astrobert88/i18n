package com.piecon.i18n.data.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class TextField extends I18nEntity {
    private String label;
    private String placeholder;
}
