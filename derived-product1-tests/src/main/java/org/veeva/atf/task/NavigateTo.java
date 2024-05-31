package org.veeva.atf.task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.veeva.atf.pages.SixersHomePage;

public class NavigateTo {

    public static Performable visitNBASixersHomePage() {
        return Task.where("{0} Visits NBA Sixers Landing Page",
                Open.browserOn().the(SixersHomePage.class));
    }

}
