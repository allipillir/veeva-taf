package org.veeva.atf.task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.veeva.atf.pages.CoreProductHomePage;

public class NavigateTo {

    public static Performable visitCoreProductHomePage() {
        return Task.where("{0} Visits Core Product Home Page",
                Open.browserOn().the(CoreProductHomePage.class));
    }

}
