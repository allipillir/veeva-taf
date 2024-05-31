package org.veeva.atf.pages;

import net.serenitybdd.screenplay.targets.Target;
import net.thucydides.core.pages.PageObject;

public class DP2HomePage extends PageObject {

    public static Target CHK_FOOTER_TEAMS_LNK = Target.the("Check Teams link in footer in home page").locatedBy("//h2[@id='footer1']");
    public static Target CHK_FOOTER_TICKETS_LNK = Target.the("Check Tickets link in footer in home page").locatedBy("//h2[@id='footer2']");
    public static Target CHK_FOOTER_SHOP_LNK = Target.the("Check Shop link in footer in home page").locatedBy("//h2[@id='footer3']");
    public static Target CHK_FOOTER_COMMUNITY_LNK = Target.the("Check COMMUNITY link in footer in home page").locatedBy("//h2[@id='footer4']");
    public static Target CHK_FOOTER_UNITED_CENTER_LNK = Target.the("Check UNITED CENTER link in footer in home page").locatedBy("//h2[@id='footer5']");
    public static Target CHK_FOOTER_NEWS_LNK = Target.the("Check NEWS link in footer in home page").locatedBy("//h2[@id='footer6']");

    public static Target CHK_FOOTER_TEAMS_LNK_COUNT = Target.the("Check Teams links count").locatedBy("(//ul[@data-testid='footer-list'])[1]/li");
    public static Target CHK_FOOTER_TICKETS_LNK_COUNT = Target.the("Check Tickets links count").locatedBy("(//ul[@data-testid='footer-list'])[2]/li");
    public static Target CHK_FOOTER_SHOP_LNK_COUNT = Target.the("Check Shop links count").locatedBy("(//ul[@data-testid='footer-list'])[3]/li");
    public static Target CHK_FOOTER_COMMUNITY_LNK_COUNT = Target.the("Check COMMUNITY  links count").locatedBy("(//ul[@data-testid='footer-list'])[4]/li");
    public static Target CHK_FOOTER_UNITED_CENTERLNK_COUNT = Target.the("Check UNITED CENTER  links count").locatedBy("(//ul[@data-testid='footer-list'])[5]/li");
    public static Target CHK_FOOTER_NEWS_LNK_COUNT = Target.the("Check NEWS  links count").locatedBy("(//ul[@data-testid='footer-list'])[6]/li");

    public static Target GET_FOOTER_TEAMS_LINKS = Target.the("Gets Teams links").locatedBy("(//ul[@data-testid='footer-list'])[1]/li[{0}]/a");
    public static Target GET_FOOTER_TICKETS_LINKS  = Target.the("Get Tickets links").locatedBy("(//ul[@data-testid='footer-list'])[2]/li[{0}]/a");
    public static Target GET_FOOTER_SHOP_LINKS  = Target.the("Get Shop links").locatedBy("(//ul[@data-testid='footer-list'])[3]/li[{0}]/a");
    public static Target GET_FOOTER_COMMUNITY_LINKS  = Target.the("Get COMMUNITY  links").locatedBy("(//ul[@data-testid='footer-list'])[4]/li[{0}]/a");
    public static Target GET_FOOTER_UNITED_CENTER__LINKS = Target.the("Get UNITED CENTER  links").locatedBy("(//ul[@data-testid='footer-list'])[5]/li[{0}]/a");
    public static Target GET_FOOTER_NEWS_LINKS  = Target.the("Get NEWS  links").locatedBy("(//ul[@data-testid='footer-list'])[6]/li[{0}]/a");


}
