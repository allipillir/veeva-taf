package org.veeva.atf.stepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.veeva.atf.steps.NBABullsSteps;
import org.veeva.atf.task.NavigateTo;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NBABullsStepDefinition {

    @Given("User {actor} visits DP2 site")
    public void visitDPTwoSite(Actor actor) {
        actor.wasAbleTo(NavigateTo.visitDP2HomePage());

    }

    @When("User {actor} navigates to home page footer")
    public void navigatesToDP2Footer(Actor actor) {
        actor.wasAbleTo(NBABullsSteps.scrollToFooterSection());
    }

    @Then("user {actor} validates any reports duplicate links")
    public void validatesAllSlidesTitles(Actor actor) {
        theActorInTheSpotlight()
                .attemptsTo(NBABullsSteps.validateDuplicateLinks(actor));
    }

}
