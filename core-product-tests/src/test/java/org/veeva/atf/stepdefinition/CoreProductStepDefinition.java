package org.veeva.atf.stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.veeva.atf.steps.CoreProductSteps;
import org.veeva.atf.task.NavigateTo;
import org.veeva.atf.webutils.SwitchToNewWindow;

import java.util.Map;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.veeva.atf.constants.SessionVariable.delayTime;

public class CoreProductStepDefinition {

    @Given("User {actor} visits Core Product site")
    public void visitCoreProductSite(Actor actor) {
        actor.wasAbleTo(NavigateTo.visitWarriorsLandingPage());
        actor.wasAbleTo(CoreProductSteps.closeWarriorsInsider());
    }

    @When("User {actor} searches product details by navigating to {string} Menu")
    public void searchProducts(Actor actor, String nbaType, DataTable searchProduct) {
        Map<String,String> searchRequest = searchProduct.asMaps().get(0);
        actor.wasAbleTo(CoreProductSteps.selectNNBAMenu(nbaType));
        actor.wasAbleTo(SwitchToNewWindow.switchToNewTab());
        delayTime();
        actor.wasAbleTo(CoreProductSteps.selectProductCategory(searchRequest.get("Category")));
        theActorInTheSpotlight()
                .attemptsTo(CoreProductSteps.selectProductType(actor, searchRequest.get("Category"), searchRequest.get("Type")));
    }

    @When("User {actor} navigates to News & Features from {string} Menu")
    public void searchNewsFeatures(Actor actor, String dotsMenu) {
        actor.wasAbleTo(CoreProductSteps.selectNNBAMenu());
        actor.wasAbleTo(CoreProductSteps.navigateToNewFeature());
    }



    @Then("user {actor} should be able to view price, title and top seller details")
    public void viewProductDetails(Actor actor) {
        theActorInTheSpotlight()
                .attemptsTo(CoreProductSteps.viewProductDetails(actor));

    }

    @Then("user {actor} validate total count {int} videos feed")
    public void validateTotalVideoCount(Actor actor, int totalCount) {
        theActorInTheSpotlight()
                .attemptsTo(CoreProductSteps.validateVideoFeedCount(actor, totalCount));

    }

    @And("user {actor} validate {int} videos feeds those are present more than {int} day in the page")
    public void validateVideoPresence(Actor actor, int count, int day) {
        theActorInTheSpotlight()
                .attemptsTo(CoreProductSteps.validateVideoPresenceInPage(actor, count,day));
    }
}
