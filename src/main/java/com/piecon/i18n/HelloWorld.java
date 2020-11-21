package com.piecon.i18n;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.i18n.I18NProvider;
import com.vaadin.flow.i18n.LocaleChangeEvent;
import com.vaadin.flow.i18n.LocaleChangeObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.LocaleUtils;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Route("")
@Slf4j
public class HelloWorld extends Composite<Div> implements LocaleChangeObserver {

    private final Select<String> localeSelect = new Select<>();
    private final Button button = new Button();
    private final HorizontalLayout layout = new HorizontalLayout(localeSelect, button);

    public HelloWorld() {
        layout.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.END);

        final I18NProvider i18NProvider = VaadinService
                .getCurrent()
                .getInstantiator()
                .getI18NProvider();
        List<Locale> providedLocales = i18NProvider.getProvidedLocales();

        localeSelect.setLabel("Language");
        localeSelect.addValueChangeListener(e -> {
            Locale newLocale = LocaleUtils.toLocale(e.getValue());
            VaadinSession.getCurrent().setLocale(newLocale);
            button.setText(getTranslation("home.welcome"));
        });
/*
        localeSelect.setItems(providedLocales.get(0).toString(),
                providedLocales.get(1).toString(),
                providedLocales.get(2).toString(),
                providedLocales.get(3).toString());
*/
        localeSelect.setItems(providedLocales.stream().map(l -> l.toString()).collect(Collectors.toList()));
        Locale currentLocale = UI.getCurrent().getLocale();
        localeSelect.setValue(currentLocale.toString());

        getContent().add(layout);
    }

    @Override
    public void localeChange(LocaleChangeEvent localeChangeEvent) {
        Notification.show(UI.getCurrent().getLocale().toString());
    }
}