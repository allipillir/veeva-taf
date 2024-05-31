package org.veeva.atf.webutils;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

@AllArgsConstructor
public class IsDisabled implements Question<Boolean> {

    private Target target;

    @Override
    public Boolean answeredBy(Actor actor) {
        return this.target.resolveFor(actor).isDisabled();
    }

    public static IsDisabled value(Target target)
    {
        return new IsDisabled(target);
    }
}
