package org.veeva.atf.stepdefinition;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.veeva.atf.steps.NBASixerSteps;
import org.veeva.atf.task.NavigateTo;

import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class NBASixerStepDefinition {

    @Given("User {actor} visits DP1 site")
    public void visitDPOneSite(Actor actor) {
        actor.wasAbleTo(NavigateTo.visitNBASixersHomePage());

    }

    @When("User {actor} navigates to tickets menu")
    public void navigatesToTicketsMenu(Actor actor) {
        actor.wasAbleTo(NBASixerSteps.selectTicketsMenu());
    }

    @Then("user {actor} validates all slides titles")
    public void validatesAllSlidesTitles(Actor actor) {
        theActorInTheSpotlight()
                .attemptsTo(NBASixerSteps.validateSlidesInTicketsMenu(actor));

    }

    @And("user {actor} validates each slide playing time")
    public void validatesEachSlidePlayingTime(Actor actor) {
        theActorInTheSpotlight()
                .attemptsTo(NBASixerSteps.validateSlideResponseTime(actor));
    }
}
