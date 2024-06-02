package org.veeva.atf.pages;

import net.serenitybdd.screenplay.targets.Target;

public class WarriorsHomePage {

    public static Target SELECT_PRODUCT_CATEGORY = Target.the("Select Product Category {0} from  Warriors Home Page main menu").locatedBy("//li[@role='menuitem']/a[text()='{0}']");

    public static Target SELECT_PRODUCT_TYPE= Target.the("Select Product Type {0} from  Warriors Home Page sub menu").locatedBy("//li[@role='menuitem']/a[text()='{0}']");


}
