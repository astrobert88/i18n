package com.piecon.i18n;

import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterListener;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.UIInitEvent;
import com.vaadin.flow.server.UIInitListener;
import com.vaadin.flow.server.VaadinServiceInitListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class I18NEngine implements VaadinServiceInitListener, UIInitListener, BeforeEnterListener {

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        log.info("beforeEnter()");
    }

    @Override
    public void uiInit(UIInitEvent event) {
        log.info("uiInit()");

        //event.getUI().addBeforeEnterListener(this);
    }

    @Override
    public void serviceInit(ServiceInitEvent event) {
        log.info("serviceInit()");

        //event.getSource().addUIInitListener(this);
    }
}