package org.veeva.atf.task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.veeva.atf.pages.WarriorsLandingPage;

public class NavigateTo {

    public static Performable visitWarriorsLandingPage() {
        return Task.where("{0} Visits Warriors Landing Page",
                Open.browserOn().the(WarriorsLandingPage.class));
    }

}
