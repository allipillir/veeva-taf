package org.veeva.atf.pages;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

public class SixersHomePage  extends PageObject {

    public static Target SELECT_TICKETS_MAIN_MENU = Target.the("Select Tickets main menu under NBA Sixer Home Page main menu").locatedBy("//li[@role='menuitem']/a/span[text()='Tickets']");

    public static Target GET_TICKETS_MENU_ITEMS= Target.the("Get Tickets menu items").locatedBy("//li[@role='menuitem']/a/span[text()='Tickets']/parent::a/following-sibling::nav/ul/li");

    public static Target GET_TICKETS_MENU_ITEM= Target.the("Get Tickets menu item {0} ").locatedBy("(//li[@role='menuitem']/a/span[text()='Tickets']/parent::a/following-sibling::nav/ul/li)[{0}]");


}
