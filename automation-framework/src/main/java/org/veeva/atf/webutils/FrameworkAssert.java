package org.veeva.atf.webutils;

import lombok.AllArgsConstructor;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

@AllArgsConstructor
public class FrameworkAssert implements Question<Boolean> {

    private boolean expected;

    @Override
    public Boolean answeredBy(Actor actor) {
        return this.expected;
    }

    public static FrameworkAssert assertEquals(boolean expected)
    {
        return new FrameworkAssert(expected);
    }

}
