package org.veeva.atf.steps;

import lombok.SneakyThrows;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.MoveMouse;
import net.serenitybdd.screenplay.actions.Scroll;
import org.veeva.atf.dateutils.DateUtils;
import org.veeva.atf.fileutils.TextFileUtils;
import org.veeva.atf.model.FooterSection;
import org.veeva.atf.model.FooterSubSection;

import java.util.ArrayList;
import java.util.List;

import static org.veeva.atf.constants.SessionVariable.MAX_WAIT_TIME;
import static org.veeva.atf.pages.DP2HomePage.*;

public class NBABullsSteps {

    private static final TextFileUtils fileUtils = new TextFileUtils();

    public static Performable scrollToFooterSection() {
        return Task.where("Scroll to view Footer in DP2 home page",
                Scroll.to(CHK_FOOTER_TEAMS_LNK.waitingForNoMoreThan(MAX_WAIT_TIME))
        );
    }

    @SneakyThrows
    @Step("Validate duplicate links exists in footer")
    public static Actor.ErrorHandlingMode validateDuplicateLinks(Actor actor) {
        String fileName = "DP2_Report_" + DateUtils.generateFileNameTimeStamp() + ".txt";
        fileUtils.createFileDuplicateFooterLinkReport(fileName);
        List<FooterSection> footerSections = readFooterDetails(actor);
        int checkFooterSectionIndex = 0;
        for (FooterSection footerSection : footerSections) {
            if(checkFooterSectionIndex == footerSections.size()-1)
                for(int index = 0 ; index < footerSection.getFooterSubSections().size(); index++){
                    String report = "| " + footerSection.getSectionName() + " |  " + footerSection.getFooterSubSections().get(index).getSubLinkName() + "  |   " + footerSection.getFooterSubSections().get(index).getLinkHref() + "  |   " + " No Duplicate link found   |";
                    fileUtils.appendFooterReport(report);
                }
            else
                checkDuplicateLinks(footerSections, checkFooterSectionIndex, footerSection);
            checkFooterSectionIndex++;
        }
        return null;
    }

    private static void checkDuplicateLinks(List<FooterSection> footerSections, int setIndexSection, FooterSection checkFooterSection) {
        boolean reportFlag = false;
        for (int index = setIndexSection+1 ; index < footerSections.size(); index++) {
            int checkSize = checkFooterSection.getFooterSubSections().size();
            int checkSectionSize = footerSections.get(index).getFooterSubSections().size();
            FooterSection checkWithFooterSection = footerSections.get(index);
            List<FooterSubSection> ckhFS = checkFooterSection.getFooterSubSections();
            List<FooterSubSection> toChkFS = checkWithFooterSection.getFooterSubSections();
            if (checkSize <= checkSectionSize) {
                for (int chkIndex = 0; chkIndex < ckhFS.size(); chkIndex++) {
                    String report = "";
                    report = "| " + checkFooterSection.getSectionName() + " |  " + ckhFS.get(chkIndex).getSubLinkName() + "  |   " + ckhFS.get(chkIndex).getLinkHref() + "  |   " + " No Duplicate link found   |";
                    for (FooterSubSection toChkF : toChkFS) {
                        if (ckhFS.get(chkIndex).getLinkHref().contentEquals(toChkF.getLinkHref())) {
                            report = "| " + checkFooterSection.getSectionName() + " |  " + ckhFS.get(chkIndex).getSubLinkName() + "  |   " + ckhFS.get(chkIndex).getLinkHref() + "  |   " + " Duplicate link found at " + toChkFS.get(chkIndex).getSubLinkName() + " at " + checkWithFooterSection.getSectionName() + " |";
                            fileUtils.appendFooterReport(report);
                            break;
                        }
                    }
                    if (!reportFlag) {
                        fileUtils.appendFooterReport(report);
                    }
                }
            } else {
                for (int chkIndex = 0; chkIndex < toChkFS.size(); chkIndex++) {
                    String report = "";
                    report = "| " + checkFooterSection.getSectionName() + " |  " + ckhFS.get(chkIndex).getSubLinkName() + "  |   " + ckhFS.get(chkIndex).getLinkHref() + "  |   " + " No Duplicate link found   |";
                    for (FooterSubSection toChkF : toChkFS) {
                        if (ckhFS.get(chkIndex).getLinkHref().contentEquals(toChkF.getLinkHref())) {
                            report = "| " + checkFooterSection.getSectionName() + " |  " + ckhFS.get(chkIndex).getSubLinkName() + "  |   " + ckhFS.get(chkIndex).getLinkHref() + "  |   " + " Duplicate link found at " + toChkF.getSubLinkName() + " at " + checkWithFooterSection.getSectionName() + " |";
                            fileUtils.appendFooterReport(report);
                            break;
                        }
                    }
                    if (!reportFlag) {
                        fileUtils.appendFooterReport(report);
                    }
                }
                if (!reportFlag) {
                    for(int extIndex= toChkFS.size() ; extIndex < ckhFS.size(); extIndex++){
                        String extreport = "| " + checkFooterSection.getSectionName() + " |  " + ckhFS.get(extIndex).getSubLinkName() + "  |   " + ckhFS.get(extIndex).getLinkHref() + "  |   " + " No Duplicate link found   |";
                        fileUtils.appendFooterReport(extreport);
                    }
                }

            }
            reportFlag = true;
        }
    }

    private static FooterSection getTeamsLinks(Actor actor) {
        FooterSection footerSection = new FooterSection();
        List<FooterSubSection> footerSubSectionList = new ArrayList<>();
        footerSection.setSectionName(CHK_FOOTER_TEAMS_LNK.resolveFor(actor).getText().trim());

        int teamsSectionLinks = CHK_FOOTER_TEAMS_LNK_COUNT.resolveAllFor(actor).size();
        for (int index = 1; index <= teamsSectionLinks; index++) {
            FooterSubSection footerSubSection = new FooterSubSection();
            actor.attemptsTo(
                    MoveMouse.to(GET_FOOTER_TEAMS_LINKS.of(String.valueOf(index)).resolveFor(actor)));
            footerSubSection.setSubLinkName(GET_FOOTER_TEAMS_LINKS.of(String.valueOf(index)).resolveFor(actor).getText());
            footerSubSection.setLinkHref(GET_FOOTER_TEAMS_LINKS.of(String.valueOf(index)).resolveFor(actor).getAttribute("href"));
            footerSubSectionList.add(footerSubSection);
        }
        footerSection.setFooterSubSections(footerSubSectionList);
        return footerSection;
    }

    private static FooterSection getTicketsLinks(Actor actor) {
        FooterSection footerSection = new FooterSection();
        List<FooterSubSection> footerSubSectionList = new ArrayList<>();
        footerSection.setSectionName(CHK_FOOTER_TICKETS_LNK.resolveFor(actor).getText().trim());
        int sectionLinks = CHK_FOOTER_TICKETS_LNK_COUNT.resolveAllFor(actor).size();
        for (int index = 1; index <= sectionLinks; index++) {
            FooterSubSection footerSubSection = new FooterSubSection();
            actor.attemptsTo(
                    MoveMouse.to(GET_FOOTER_TICKETS_LINKS.of(String.valueOf(index)).resolveFor(actor)));
            footerSubSection.setSubLinkName(GET_FOOTER_TICKETS_LINKS.of(String.valueOf(index)).resolveFor(actor).getText());
            footerSubSection.setLinkHref(GET_FOOTER_TICKETS_LINKS.of(String.valueOf(index)).resolveFor(actor).getAttribute("href"));
            footerSubSectionList.add(footerSubSection);
        }
        footerSection.setFooterSubSections(footerSubSectionList);
        return footerSection;
    }

    private static FooterSection getShopLinks(Actor actor) {
        FooterSection footerSection = new FooterSection();
        List<FooterSubSection> footerSubSectionList = new ArrayList<>();
        footerSection.setSectionName(CHK_FOOTER_SHOP_LNK.resolveFor(actor).getText().trim());
        int sectionLinks = CHK_FOOTER_SHOP_LNK_COUNT.resolveAllFor(actor).size();
        for (int index = 1; index <= sectionLinks; index++) {
            FooterSubSection footerSubSection = new FooterSubSection();
            actor.attemptsTo(
                    MoveMouse.to(GET_FOOTER_SHOP_LINKS.of(String.valueOf(index)).resolveFor(actor)));
            footerSubSection.setSubLinkName(GET_FOOTER_SHOP_LINKS.of(String.valueOf(index)).resolveFor(actor).getText());
            footerSubSection.setLinkHref(GET_FOOTER_SHOP_LINKS.of(String.valueOf(index)).resolveFor(actor).getAttribute("href"));
            footerSubSectionList.add(footerSubSection);
        }
        footerSection.setFooterSubSections(footerSubSectionList);
        return footerSection;
    }

    private static FooterSection getCommunityLinks(Actor actor) {
        FooterSection footerSection = new FooterSection();
        List<FooterSubSection> footerSubSectionList = new ArrayList<>();
        footerSection.setSectionName(CHK_FOOTER_COMMUNITY_LNK.resolveFor(actor).getText().trim());
        int sectionLinks = CHK_FOOTER_COMMUNITY_LNK_COUNT.resolveAllFor(actor).size();
        for (int index = 1; index <= sectionLinks; index++) {
            FooterSubSection footerSubSection = new FooterSubSection();
            actor.attemptsTo(
                    MoveMouse.to(GET_FOOTER_COMMUNITY_LINKS.of(String.valueOf(index)).resolveFor(actor)));
            footerSubSection.setSubLinkName(GET_FOOTER_COMMUNITY_LINKS.of(String.valueOf(index)).resolveFor(actor).getText());
            footerSubSection.setLinkHref(GET_FOOTER_COMMUNITY_LINKS.of(String.valueOf(index)).resolveFor(actor).getAttribute("href"));
            footerSubSectionList.add(footerSubSection);
        }
        footerSection.setFooterSubSections(footerSubSectionList);
        return footerSection;
    }

    private static FooterSection getUnitedCenterLinks(Actor actor) {
        FooterSection footerSection = new FooterSection();
        List<FooterSubSection> footerSubSectionList = new ArrayList<>();
        footerSection.setSectionName(CHK_FOOTER_UNITED_CENTER_LNK.resolveFor(actor).getText().trim());
        int sectionLinks = CHK_FOOTER_UNITED_CENTERLNK_COUNT.resolveAllFor(actor).size();
        for (int index = 1; index <= sectionLinks; index++) {
            FooterSubSection footerSubSection = new FooterSubSection();
            actor.attemptsTo(
                    MoveMouse.to(GET_FOOTER_UNITED_CENTER__LINKS.of(String.valueOf(index)).resolveFor(actor)));
            footerSubSection.setSubLinkName(GET_FOOTER_UNITED_CENTER__LINKS.of(String.valueOf(index)).resolveFor(actor).getText());
            footerSubSection.setLinkHref(GET_FOOTER_UNITED_CENTER__LINKS.of(String.valueOf(index)).resolveFor(actor).getAttribute("href"));
            footerSubSectionList.add(footerSubSection);
        }
        footerSection.setFooterSubSections(footerSubSectionList);
        return footerSection;
    }

    private static FooterSection getNewsLinks(Actor actor) {
        FooterSection footerSection = new FooterSection();
        List<FooterSubSection> footerSubSectionList = new ArrayList<>();
        footerSection.setSectionName(CHK_FOOTER_NEWS_LNK.resolveFor(actor).getText().trim());
        int sectionLinks = CHK_FOOTER_NEWS_LNK_COUNT.resolveAllFor(actor).size();
        for (int index = 1; index <= sectionLinks; index++) {
            FooterSubSection footerSubSection = new FooterSubSection();
            actor.attemptsTo(
                    MoveMouse.to(GET_FOOTER_NEWS_LINKS.of(String.valueOf(index)).resolveFor(actor)));
            footerSubSection.setSubLinkName(GET_FOOTER_NEWS_LINKS.of(String.valueOf(index)).resolveFor(actor).getText());
            footerSubSection.setLinkHref(GET_FOOTER_NEWS_LINKS.of(String.valueOf(index)).resolveFor(actor).getAttribute("href"));
            footerSubSectionList.add(footerSubSection);
        }
        footerSection.setFooterSubSections(footerSubSectionList);
        return footerSection;
    }

    private static List<FooterSection> readFooterDetails(Actor actor) {
        List<FooterSection> footerSections = new ArrayList<>();
        footerSections.add(getTeamsLinks(actor));
        footerSections.add(getTicketsLinks(actor));
        footerSections.add(getShopLinks(actor));
        footerSections.add(getCommunityLinks(actor));
        footerSections.add(getUnitedCenterLinks(actor));
        footerSections.add(getNewsLinks(actor));
        return footerSections;
    }

}
