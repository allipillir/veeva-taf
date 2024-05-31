package org.veeva.atf.stepdefinition;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import org.veeva.atf.task.NavigateTo;

public class CoreProductStepDefinition {

    @Given("User {actor} visits Core Product site")
    public void visitCoreProductSite(Actor actor) {
        actor.wasAbleTo(NavigateTo.visitCoreProductHomePage());
    }

    @When("User {actor} navigates to Shop Menu for search products")
    public void searchProducts(Actor actor, DataTable searchProduct) {

    }

    @Then("user {actor} should be able to view price, title and top seller details")
    public void viewProductDetails(Actor actor) {

    }

}
