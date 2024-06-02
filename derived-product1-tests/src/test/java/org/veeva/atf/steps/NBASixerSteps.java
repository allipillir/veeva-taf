package org.veeva.atf.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.MoveMouse;
import org.veeva.atf.messages.DPOneErrors;
import org.veeva.atf.model.Ticket;
import org.veeva.atf.model.TicketDetails;
import org.veeva.atf.webutils.FrameworkAssert;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.veeva.atf.constants.SessionVariable.BOOLEAN_TRUE;
import static org.veeva.atf.constants.SessionVariable.DATA_FOLDER_PATH;
import static org.veeva.atf.constants.SessionVariable.MAX_WAIT_TIME;
import static org.veeva.atf.constants.SessionVariable.MIN_WAIT_TIME;
import static org.veeva.atf.messages.DPOneErrors.*;
import static org.veeva.atf.pages.SixersHomePage.GET_TICKETS_MENU_ITEM;
import static org.veeva.atf.pages.SixersHomePage.SELECT_TICKETS_MAIN_MENU;
import static org.veeva.atf.pages.SixersHomePage.GET_TICKETS_MENU_ITEMS;
public class NBASixerSteps {

    public static Performable selectTicketsMenu() {
        return Task.where("Mouse hover on Tickets Menu",
                MoveMouse.to(SELECT_TICKETS_MAIN_MENU.waitingForNoMoreThan(MAX_WAIT_TIME))
        );
    }

    @SneakyThrows
    @Step("Validate slide count and titles of each slide")
    public static Actor.ErrorHandlingMode validateSlidesInTicketsMenu(Actor actor) {
        int slicesCount = GET_TICKETS_MENU_ITEMS.waitingForNoMoreThan(MIN_WAIT_TIME).resolveAllFor(actor).size();
        ObjectMapper objectMapper = new ObjectMapper();
        TicketDetails ticketDetails = objectMapper.readValue(new File(DATA_FOLDER_PATH+"tickets.json"), TicketDetails.class);
        theActorInTheSpotlight()
                .should(seeThat("Validate total number of slides count under ticket menu " + ticketDetails.getTicketTotalCount()
                        , FrameworkAssert.assertEquals(ticketDetails.getTicketTotalCount() == slicesCount), equalTo(BOOLEAN_TRUE))
                        .orComplainWith(DPOneErrors.class, TICKET_MENU_ITEM_COUNT + " expected count " + ticketDetails.getTicketTotalCount()
                                + " actual count " + slicesCount));
        for (int slideIndex = 1; slideIndex <= slicesCount; slideIndex++) {
            String title = GET_TICKETS_MENU_ITEM.of(String.valueOf(slideIndex)).resolveFor(actor).getText();
            actor.attemptsTo(
                    MoveMouse.to(GET_TICKETS_MENU_ITEM.of(String.valueOf(slideIndex)))
            );

            theActorInTheSpotlight()
                    .should(seeThat("Verify Slides title in Tickets  menu " + ticketDetails.getTicket().get(slideIndex - 1).getTitle()
                            , FrameworkAssert.assertEquals(title.trim().contentEquals(ticketDetails.getTicket().get(slideIndex - 1).getTitle().trim())), equalTo(BOOLEAN_TRUE))
                            .orComplainWith(DPOneErrors.class, TICKET_SLIDES_TITLE + " expected title " + ticketDetails.getTicket().get(slideIndex - 1).getTitle()
                                    + " actual title " + title));
        }
        return null;
    }

    @SneakyThrows
    @Step("Validate slide response time")
    public static Actor.ErrorHandlingMode validateSlideResponseTime(Actor actor) {
        ObjectMapper objectMapper = new ObjectMapper();
        TicketDetails ticketDetails = objectMapper.readValue(new File(DATA_FOLDER_PATH+"tickets.json"), TicketDetails.class);
        for(Ticket ticket: ticketDetails.getTicket()) {
            long actualTime= SerenityRest.get(ticket.getUrl()).timeIn(TimeUnit.MILLISECONDS);
            theActorInTheSpotlight()
                    .should(seeThat("Verify Slides Response time in mulliseconds" + ticket.getTitle()
                            , FrameworkAssert.assertEquals(ticket.getResponse() >= actualTime), equalTo(BOOLEAN_TRUE))
                            .orComplainWith(DPOneErrors.class, TICKET_SLIDES_TITLE_RESPONSE_TIME + " expected title " + ticket.getResponse()
                                    + " actual title " + actualTime +" Slide title "+ticket.getTitle()));
        }
        return null;
    }

}
