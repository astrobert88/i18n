package com.piecon.i18n;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface I18nTranslate {
    String tableName() default "some.default.tableName";
    String textKey() default "some.default.key";
    //String defaultValue() default "some.default.value";
}
