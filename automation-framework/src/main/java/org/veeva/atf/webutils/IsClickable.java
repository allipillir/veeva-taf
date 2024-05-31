package org.veeva.atf.webutils;


import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.targets.Target;

@AllArgsConstructor
public class IsClickable implements Question<Boolean> {

    private Target target;

    @Override
    public Boolean answeredBy(Actor actor) {
        return this.target.resolveFor(actor).isClickable();
    }

    public static IsClickable value(Target target)
    {
        return new IsClickable(target);
    }

}
