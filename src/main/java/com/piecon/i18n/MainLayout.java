package com.piecon.i18n;

import com.vaadin.flow.component.UI;
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
import com.vaadin.flow.server.VaadinService;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Locale;

@Slf4j
public class MainLayout extends AppLayout {

    public MainLayout() {
        log.debug("MainLayout()");

        createHeader();
        createDrawer();
    }

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

        final Locale currentLocale = UI.getCurrent().getLocale();

        final List<Locale> providedLocales = VaadinService
                .getCurrent()
                .getInstantiator()
                .getI18NProvider()
                .getProvidedLocales();

        LanguageSelectionMenu languageSelectionMenu =
                new LanguageSelectionMenu(currentLocale, providedLocales);

        RouterLink logsLink = new RouterLink("Hello World", HelloWorld.class);
        logsLink.setHighlightCondition(HighlightConditions.sameLocation());

        addToDrawer(new VerticalLayout(
                languageSelectionMenu,
                new H3("Menu"),
                logsLink
        ));

    }

}