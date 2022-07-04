package PhoneCode.App.service;


import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;



@Component
@AllArgsConstructor

public class RunAfterStartup {

    private final BrowserOpener browserOpener;


    @EventListener(ApplicationReadyEvent.class)
    public void runAfterStartup() throws IOException {


        System.out.println("Application is running........");


        browserOpener.openLinkInBrowser("http://localhost:8080/");







    }
}