package com.piecon.i18n;

import com.github.appreciated.papermenubutton.PaperMenuButton;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;
import java.util.Locale;

@CssImport("./styles/piecon-styles.css")
public class LanguageSelectionMenu extends VerticalLayout {

    final PaperMenuButton flagSelectionMenu;
    final Image currentLanguageFlag;

    public LanguageSelectionMenu(Locale currentLocale, List<Locale> providedLocales ) {

        setWidth("100%");

        VerticalLayout popupContent = new VerticalLayout();

        popupContent.getStyle().set("border-radius", "8px");
        popupContent.getStyle().set("margin", "0px 0px 0px 0px");
        popupContent.getStyle().set("padding", "15px 5px 0px 5px");
        popupContent.setAlignItems(Alignment.CENTER);
        popupContent.setJustifyContentMode(JustifyContentMode.CENTER);

        for (Locale locale : providedLocales) {
            popupContent.add(createFlagButton(locale));
        }

        currentLanguageFlag = createFlagImage(currentLocale);
        flagSelectionMenu = new PaperMenuButton(currentLanguageFlag, popupContent);

        add(flagSelectionMenu);
    }

    private Image createFlagImage(Locale locale) {
        final String flagName;

        if (!locale.getCountry().equals("")) {
            flagName = locale.getLanguage() + "_" + locale.getCountry();
        }
        else {
            flagName = locale.getLanguage();
        }

        Image flagImage = new Image("img/language-flags/"+flagName+".png", "Flag");
        flagImage.setClassName("my-flag");

        return flagImage;
    }

    private Button createFlagButton(Locale locale) {
        Image flagImage = createFlagImage(locale);
        Button button = new Button(flagImage);

        button.addClickListener(e -> {
            currentLanguageFlag.setSrc(flagImage.getSrc());
            flagSelectionMenu.close();
            VaadinSession.getCurrent().setLocale(locale);
        });

        return button;
    }
}
