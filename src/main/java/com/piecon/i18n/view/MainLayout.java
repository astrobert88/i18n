package com.piecon.i18n.view;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MainLayout extends AppLayout {

    public MainLayout() {
        log.info("MainLayout()");

        createHeader();
        createDrawer();

    }

    /**
     *
    private void showSelectCountryNotification() {
        Span content = new Span("We were unable to determine your country. If you want currency and " +
                "date formats to be displayed correctly, please select your country by clicking " +
                "on the country flag on the menu bar.");
        NativeButton okButton = new NativeButton("OK");
        Notification notification = new Notification(content, okButton);
        notification.setDuration(0);
        okButton.addClickListener(event -> notification.close());
        notification.setPosition(Notification.Position.MIDDLE);
        notification.open();
    }
     */

    private void createHeader() {
        H1 logo = new H1("Piecon Core");
        logo.addClassName("logo");

        Anchor logout = new Anchor("/logout", "Log out");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo, logout);
        header.addClassName("header");
        header.setWidth("100%");
        header.expand(logo);
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {

/*
        final Locale currentLocale = UI.getCurrent().getLocale();

        final List<Locale> providedLocales = VaadinService
                .getCurrent()
                .getInstantiator()
                .getI18NProvider()
                .getProvidedLocales();

        LanguageSelectionMenu languageSelectionMenu =
                new LanguageSelectionMenu(currentLocale, providedLocales);
*/

        RouterLink regionalFormatsLink = new RouterLink("Regional Formats", RegionalFormatsView.class);
        regionalFormatsLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink countrySelectionLink = new RouterLink("Country Selection", LocalesView.class);
        countrySelectionLink.setHighlightCondition(HighlightConditions.sameLocation());
        RouterLink i18AnnotationViewLink = new RouterLink("i18 Annotation", I18nAnnotatedView.class, "psn");
        i18AnnotationViewLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                //languageSelectionMenu,
                new H3("Menu"),
                regionalFormatsLink,
                i18AnnotationViewLink,
                countrySelectionLink
        ));

    }

    /**
     *
     * @param locale
     * @return
    private Image createFlagImage(Locale locale) {
        final String flagName;

        if (!locale.getCountry().equals("")) {
            flagName = locale.getLanguage() + "_" + locale.getCountry();
        }
        else {
            flagName = locale.getLanguage();
        }

        Image flagImage = new Image("img/language-flags/"+flagName+".png", flagName+".png");
        flagImage.setClassName("my-flag");

        return flagImage;
    }
     */

    /**
     *
     * @param locale
     * @return
    private Button createFlagButton(Locale locale) {
        Image flagImage = createFlagImage(locale);
        Button button = new Button(flagImage);

        button.addClickListener(e -> {
            currentLanguageFlag.setSrc(flagImage.getSrc());
            VaadinSession.getCurrent().setLocale(locale);
        });

        return button;
    }
     */

}
