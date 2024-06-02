package org.veeva.atf.webutils;

import net.serenitybdd.core.steps.Instrumented;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;


import java.util.Set;

import static net.serenitybdd.core.Serenity.getDriver;

public class SwitchToNewWindow implements Task {

    public SwitchToNewWindow (){}

    public static SwitchToNewWindow switchToNewTab(){
        return Instrumented.instanceOf(SwitchToNewWindow.class).withProperties();
    }

    public <T extends Actor> void performAs(T actor) {
        String currentWindow = getDriver().getWindowHandle();
        Set<String> allWindows = getDriver().getWindowHandles();
        for(String window : allWindows){
            if(!window.contentEquals(currentWindow)){
                getDriver().switchTo().window(window);
                break;
            }
        }
    }
}
