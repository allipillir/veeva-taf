package org.veeva.atf.stepdefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.*;
import lombok.SneakyThrows;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import java.lang.reflect.Type;

public class BaseStepDefinition {

    private final static int WAIT_TIME = 30000;

    @ParameterType(".*")
    public Actor actor(String actorName) {
        return OnStage.theActorCalled(actorName);
    }

    @Before
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    private final ObjectMapper objectMapper = new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer
    @DefaultDataTableCellTransformer
    public Object transformer(Object fromValue, Type toValueType) {
        return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
    }

    @SneakyThrows
    @After(value="@reset")
    public void restartBrowser() {

        if (Serenity.getWebdriverManager().getCurrentDriver() != null) {
            Serenity.getWebdriverManager().getCurrentDriver().close();
            Serenity.getWebdriverManager().getCurrentDriver().quit();
            Thread.sleep(WAIT_TIME);
        }
    }


}
