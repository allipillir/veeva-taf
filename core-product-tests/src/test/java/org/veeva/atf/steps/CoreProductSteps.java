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
import org.veeva.atf.messages.CoreProductErrors;
import org.veeva.atf.webutils.FrameworkAssert;
import org.veeva.atf.webutils.JSClick;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.veeva.atf.constants.SessionVariable.*;
import static org.veeva.atf.constants.SessionVariable.BOOLEAN_TRUE;
import static org.veeva.atf.messages.CoreProductErrors.TOTAL_VIDEO_COUNT_NEW_FEATURE;
import static org.veeva.atf.messages.CoreProductErrors.TOTAL_VIDEO_FEED_PRESENT;
import static org.veeva.atf.pages.WarriorsLandingPage.*;
import static org.veeva.atf.utils.ProductInformation.*;

public class CoreProductSteps {

    public static final String PRODUCT_FILE = "PRODUCT_DETAILS";

    public static Performable closeWarriorsInsider() {
        delayMinTime();
        return Task.where("Close Warriors Insider signing process",
                Click.on(CLOSE_SIGNUP_BUTTON_TO_SIGNUP_WARRIORS.waitingForNoMoreThan(MAX_WAIT_TIME))
        );
    }

    public static Performable selectNNBAMenu(String nbaMenu) {
        return Task.where("search NBA Menu {0} for product category selection ",
                JSClick.on(SELECT_NBA_MENU_ITEM.of(nbaMenu.trim()).waitingForNoMoreThan(MIN_WAIT_TIME))
        );
    }

    public static Performable selectNNBAMenu() {
        return Task.where("search NBA Menu ... from menu ",
                MoveMouse.to(SELECT_NBA_EXT_MENU_ITEM.waitingForNoMoreThan(MIN_WAIT_TIME))
        );
    }

    public static Performable navigateToNewFeature() {
        return Task.where("Navigate to New & Feature",
                JSClick.on(SELECT_NEW_FEATURE_MENU_ITEM.waitingForNoMoreThan(MIN_WAIT_TIME))
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

    @Step("Validate total video feed total count")
    public static Actor.ErrorHandlingMode validateVideoFeedCount(Actor actor, int totalCount) {
        int actualCount = GET_TOTAL_VIDEO_FEED_COUNT.waitingForNoMoreThan(MAX_WAIT_TIME).resolveAllFor(actor).size();
        theActorInTheSpotlight()
                .should(seeThat("Validate total video feed count in New & Features is " + totalCount
                        , FrameworkAssert.assertEquals(totalCount==actualCount), equalTo(BOOLEAN_TRUE))
                        .orComplainWith(CoreProductErrors.class, TOTAL_VIDEO_COUNT_NEW_FEATURE + " expected video feed count " + totalCount
                                + " actual  video feed count " + actualCount));
        return null;
    }

    @Step("Validate video feed presence in page")
    public static Actor.ErrorHandlingMode validateVideoPresenceInPage(Actor actor, int count, int day) {
        int videoCount = 0;
        int actualCount = GET_TOTAL_VIDEO_FEED_COUNT.waitingForNoMoreThan(MAX_WAIT_TIME).resolveAllFor(actor).size();
        for(int index = 1; index <= actualCount ; index++){
            List<WebElement> day1Element =  BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(GET_VIDEO_LIST+index+"]/div/div[2]/div[2]/div/time/span"));
            List<WebElement> day2Element =  BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(GET_VIDEO_LIST+index+"]/div/div[2]/div[2]/div/div[3]/div/div/time/span"));
            List<WebElement> day3Element =  BrowseTheWeb.as(actor).getDriver().findElements(By.xpath(GET_VIDEO_LIST+index+"]/div/div[2]/div[2]/div/div[2]/div/div/time/span"));
            if(day1Element.size()>0) {
                int actualDays = getDaysFromString(day1Element.get(0).getText().trim());
                if(day < actualDays)
                    videoCount++;
            }
            else if(day2Element.size()>0) {
                int actualDays = getDaysFromString(day2Element.get(0).getText().trim());
                if(day < actualDays)
                    videoCount++;
            }else if(day3Element.size()>0) {
                int actualDays = getDaysFromString(day3Element.get(0).getText().trim());
                if(day < actualDays)
                    videoCount++;
            }
        }
        theActorInTheSpotlight()
                .should(seeThat("Validate total video feed count which are more that 2 days in New & Features page is "+count
                        , FrameworkAssert.assertEquals(count==videoCount), equalTo(BOOLEAN_TRUE))
                        .orComplainWith(CoreProductErrors.class, TOTAL_VIDEO_FEED_PRESENT + " expected video feed count " + count
                                + " actual  video feed count " + videoCount));
        return null;
    }

    private static int getDaysFromString(String daysStr) {
        int finalDays = 0;
        if(daysStr.contains("d")){
            finalDays =  Integer.parseInt(daysStr.trim().replace("d",""));
        } else if (daysStr.contains("mo")) {
            int days = Integer.parseInt(daysStr.trim().replace("mo",""));
            finalDays = Math.max(days, 3);
        }
        return finalDays;
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
                List<WebElement> sellerMessageList = BrowseTheWeb.as(actor).getDriver().findElements(
                        By.xpath(StringUtils.replace(GET_PRODUCT_ITEM_INDEX, "index", String.valueOf(productIndex)) + SELLER_MESSAGE_INDEX));
                if (sellerMessageList.size() > 0)
                    sellerMessage = sellerMessageList.get(0).getText();
                List<WebElement> priceList = BrowseTheWeb.as(actor).getDriver().findElements(
                        By.xpath(StringUtils.replace(GET_PRODUCT_ITEM_INDEX, "index", String.valueOf(productIndex)) + ITEM_PRICE_INDEX));
                if (priceList.size() > 0)
                    price = priceList.get(0).getText();
                List<WebElement> titleList = BrowseTheWeb.as(actor).getDriver().findElements(
                        By.xpath(StringUtils.replace(GET_PRODUCT_ITEM_INDEX, "index", String.valueOf(productIndex)) + ITEM_TITLE_INDEX));
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
