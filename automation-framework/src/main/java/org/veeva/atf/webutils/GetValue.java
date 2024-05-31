package org.veeva.atf.webutils;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

@AllArgsConstructor
public class GetValue implements Question<String> {

    private Target target;

    @Override
    public String answeredBy(Actor actor) {
        return this.target.resolveFor(actor).getValue();
    }

    public static GetValue value(Target target) {
        return new GetValue(target);
    }
}
