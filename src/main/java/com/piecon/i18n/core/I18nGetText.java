package com.piecon.i18n.core;

import com.piecon.i18n.data.entity.I18nEntity;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface I18nGetText {
    String i18nKey();
    Class<? extends I18nEntity> textSourceClass();
    String textSourceField();
}
