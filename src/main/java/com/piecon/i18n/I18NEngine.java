package com.piecon.i18n;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.UIInitEvent;
import com.vaadin.flow.server.UIInitListener;
import com.vaadin.flow.server.VaadinServiceInitListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class I18NEngine implements VaadinServiceInitListener, UIInitListener, BeforeEnterListener {

    public static final String ERROR_MSG_NO_LOCALE = "no locale provided and i18nProvider #getProvidedLocales()# list is empty !! ";
    public static final String ERROR_MSG_NO_ANNOTATION = "no annotation found at class ";

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        log.info("beforeEnter()");
/*

        Class<?> navigationTarget = event.getNavigationTarget();
        log.info("navigationTarget="+navigationTarget);

        for (Field field : navigationTarget.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(I18nTranslate.class)) {
                log.info("annotation found!!!!"+field);
                I18nTranslate annotation = field.getAnnotation(I18nTranslate.class);

                final String messageKey = (annotation.messageKey().isEmpty())
                        ? annotation.defaultValue()
                        : annotation.messageKey();

                log.info("messageKey=" + messageKey);

                final I18NProvider i18NProvider = VaadinService
                        .getCurrent()
                        .getInstantiator()
                        .getI18NProvider();
                final Locale locale = event.getUI().getLocale();
                String translation = i18NProvider.getTranslation(messageKey, locale);
                log.info("translation="+translation);
            }
        }
        I18nTranslate annotation = navigationTarget.getAnnotation(I18nTranslate.class);
        if (annotation == null) {
            log.info(ERROR_MSG_NO_ANNOTATION + navigationTarget.getName());
        } else {
            log.info("annotation found!!!!");
            final String messageKey = (annotation.messageKey().isEmpty())
                    ? annotation.defaultValue()
                    : annotation.messageKey();

            log.info("messageKey=" + messageKey);

            final I18NProvider i18NProvider = VaadinService
                    .getCurrent()
                    .getInstantiator()
                    .getI18NProvider();
            final Locale locale = event.getUI().getLocale();
            final List<Locale> providedLocales = i18NProvider.getProvidedLocales();

            Locale providedLocale = null;

            if (locale == null && providedLocales.isEmpty()) {
                log.info(ERROR_MSG_NO_LOCALE + i18NProvider.getClass().getName());
            } else if (locale == null) {
                providedLocale = providedLocales.get(0);
            } else if (providedLocales.contains(locale)) {
                providedLocale = locale;
            } else {
                providedLocale = providedLocales.get(0);
            }

*/

/*
      final Class<? extends TitleFormatter> formatterCls = annotation.formatter();

      try {
        final TitleFormatter formatter = formatterCls.getDeclaredConstructor().newInstance();
        formatter.apply(i18NProvider , providedLocale , messageKey)
        .ifPresentOrElse(txt -> UI.getCurrent()
                                    .getPage()

                                .setTitle(txt),
                         failed -> logger().info(failed)
        );

      } catch (InstantiationException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      }
        }
*/
    }

    @Override
    public void uiInit(UIInitEvent event) {
        log.info("uiInit()");

        final UI ui = event.getUI();
        ui.addBeforeEnterListener(this);
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        log.info("serviceInit()");

        event
                .getSource()
                .addUIInitListener(this);
    }
}