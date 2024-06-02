package org.veeva.atf.steps;

import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.MoveMouse;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebElement;
import org.veeva.atf.dateutils.DateUtils;
import org.veeva.atf.fileutils.TextFileUtils;
import org.veeva.atf.webutils.JSClick;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.veeva.atf.constants.SessionVariable.*;
import static org.veeva.atf.pages.WarriorsLandingPage.*;
import static org.veeva.atf.utils.ProductInformation.*;

public class CoreProductSteps {

    public static final String PRODUCT_FILE = "PRODUCT_DETAILS";

    public static Performable closeWarriorsInsider() {
        return Task.where("Close Warriors Insider signing process",
                Click.on(CLOSE_SIGNUP_BUTTON_TO_SIGNUP_WARRIORS.waitingForNoMoreThan(MIN_WAIT_TIME))
        );
    }

    public static Performable selectNNBAMenu(String nbaMenu) {
        return Task.where("search NBA Menu {0} for product category selection ",
                JSClick.on(SELECT_NBA_MENU_ITEM.of(nbaMenu.trim()).waitingForNoMoreThan(MIN_WAIT_TIME))
        );
    }

    public static Performable selectProductCategory(String productCategory) {
        String category = String.valueOf(getProductMenu(productCategory));
        return Task.where("Mouse hover on {0} product category",
                MoveMouse.to(MOUSE_HOVER_PRODUCT_CATEGORY.of(category).waitingForNoMoreThan(MAX_WAIT_TIME))
        );
    }

    @Step("Search {2} product type")
    public static Actor.ErrorHandlingMode selectProductType(Actor actor, String productCategory, String productType) {
        String productIndex = String.valueOf(getProductIndex(productCategory, productType));
        String category = getProductCategory(productCategory);
        String type = getProductType(productType);
        CONSTRUCT_PRODUCT_TYPE = StringUtils.replace(CONSTRUCT_PRODUCT_TYPE, "CATEGORY", category)
                .replace("PRODUCT_INDEX", productIndex)
                .replace("PRODUCT_TYPE", type);
        actor.attemptsTo(MoveMouse.to(By.xpath(CONSTRUCT_PRODUCT_TYPE)));
        actor.attemptsTo(Click.on(By.xpath(CONSTRUCT_PRODUCT_TYPE)));
        return null;
    }

    @SneakyThrows
    @Step("Read  {2} product type")
    public static Actor.ErrorHandlingMode viewProductDetails(Actor actor) {
        TextFileUtils fileUtils = new TextFileUtils();
        String sellerMessage = "";
        String price = "";
        String title = "";
        boolean nextPageExists = true;
        String productDetails;
        String fileName = PRODUCT_FILE + DateUtils.generateFileNameTimeStamp() + ".txt";
        fileUtils.createFileHeader(fileName);
        List<WebElement> productItems = BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(GET_PRODUCT_ITEM_LIST));
        while (nextPageExists) {
            for (int productIndex = 1; productIndex <= productItems.size(); productIndex++) {
                productDetails = null;
                List<WebElement> sellerMessageList = BrowseTheWeb.as(actor).getDriver().findElements(
                        By.xpath(StringUtils.replace(GET_PRODUCT_ITEM_INDEX, "index", String.valueOf(productIndex)) + SELLER_MESSAGE_INDEX));
                ;
                if (sellerMessageList.size() > 0)
                    sellerMessage = sellerMessageList.get(0).getText();
                List<WebElement> priceList = BrowseTheWeb.as(actor).getDriver().findElements(
                        By.xpath(StringUtils.replace(GET_PRODUCT_ITEM_INDEX, "index", String.valueOf(productIndex)) + ITEM_PRICE_INDEX));
                ;
                if (priceList.size() > 0)
                    price = priceList.get(0).getText();
                List<WebElement> titleList = BrowseTheWeb.as(actor).getDriver().findElements(
                        By.xpath(StringUtils.replace(GET_PRODUCT_ITEM_INDEX, "index", String.valueOf(productIndex)) + ITEM_TITLE_INDEX));
                ;
                if (titleList.size() > 0)
                    title = titleList.get(0).getText();
                productDetails = "|  " + price + "  |    " + title + "    |    " + sellerMessage + "    |    ";
                fileUtils.appendContentToFile(productDetails);
            }

            if (NEXT_PAGE_BUTTON.resolveAllFor(actor).size() > 0)
                if (NEXT_PAGE_BUTTON.resolveFor(actor).getAttribute("aria-disabled").trim().contentEquals("false"))
                    NEXT_PAGE_BUTTON.resolveFor(actor).click();
                else
                    nextPageExists = false;
            delayMinTime();
        }

        Path generatedReport = Paths.get(fileUtils.getFilePath().getAbsolutePath());
        Serenity.recordReportData().withTitle("Upload Top Seller Product Details ")
                .fromFile(generatedReport);
        return null;
    }

}
