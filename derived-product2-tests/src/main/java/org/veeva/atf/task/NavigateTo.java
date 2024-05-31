package org.veeva.atf.task;

import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Open;
import org.veeva.atf.pages.DP2HomePage;

public class NavigateTo {

    public static Performable visitDP2HomePage() {
        return Task.where("{0} Visits NBA Bulls Home Page",
                Open.browserOn().the(DP2HomePage.class));
    }

}
