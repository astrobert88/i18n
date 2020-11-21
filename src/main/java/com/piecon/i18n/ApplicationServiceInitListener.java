package com.piecon.i18n;

import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationServiceInitListener implements VaadinServiceInitListener {

    @Override
    public void serviceInit(ServiceInitEvent event) {
        log.info("serviceInit("+event+")");
/*
        event.getSource().addSessionInitListener(sessionInitEvent -> {
            final VaadinRequest request = sessionInitEvent.getRequest();
            final VaadinSession session = sessionInitEvent.getSession();

            // TODO: change the cookie name to the name that you actually use ;)
            Cookie localeCookie = Arrays.stream(request.getCookies()).
                    filter(c -> c.getName().equals("myPreferredLocale")).findFirst().orElse(null);

            if(localeCookie != null){
                session.setLocale(new Locale(localeCookie.getValue()));
            }
        });
*/
    }
}