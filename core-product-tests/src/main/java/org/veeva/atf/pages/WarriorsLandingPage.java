package org.veeva.atf.pages;


import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

public class WarriorsLandingPage extends PageObject {
    public static Target CLOSE_SIGNUP_BUTTON_TO_SIGNUP_WARRIORS = Target.the("Close Signup process for Warriors insider ").located(By.xpath("(//div[contains(text(), 'x')])[3]"));
    public static Target SELECT_NBA_MENU_ITEM = Target.the("Select {0} menu item from NBA Menu for product category selection").locatedBy("(//a/span[contains(text(), '{0}')])[1]");
    public static Target MOUSE_HOVER_PRODUCT_CATEGORY = Target.the("Mouse Hover on  {0} product category selection").locatedBy("//*[@id='{0}']");
    public static String CONSTRUCT_PRODUCT_TYPE = "//li[@role='menuitem']/a[text()='CATEGORY']//following-sibling::div/div/div/div/div[PRODUCT_INDEX]/a/div[text()='PRODUCT_TYPE']";
    public static String GET_PRODUCT_ITEM_LIST = "//div[@class='column']";
    public static String GET_PRODUCT_ITEM_INDEX = "(//div[@class='column'])[index]";
    public static String SELLER_MESSAGE_INDEX = "/div/div[1]/div/div[2]/div";
    public static String ITEM_PRICE_INDEX = "/div/div[2]/div/div/div/div/span/span/span[1]/span[1]";
    public static String ITEM_TITLE_INDEX = "/div/div[2]/div[2]/a";
    public static Target NEXT_PAGE_BUTTON = Target.the("Click Navigate to next page ").located(By.xpath("(//a[@aria-label='next page'])[1]"));


}
