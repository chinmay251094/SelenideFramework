package com.extras;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.pages.BasePage;
import org.openqa.selenium.By;
import org.testng.SkipException;

import java.util.List;

import static com.codeborne.selenide.Selenide.$$;

public class HomePage extends BasePage {
    private static String username = null;
    private final By LIST_OF_TEAM_MEMBERS = By.xpath("(//table[@id='table-propeller'])[2]/tbody/tr[position() > 1]");
    private final By LINK_TEAM_MEMBER = By.xpath("(//table[@id='table-propeller'])[2]/tbody/tr[position() > 1]//a");
    private final By BTN_SUSPEND_USER = By.id("autoSuspendOrDelAcct");
    private final By BTN_CONFIRM_SUSPEND_USER = By.id("autoContinueWithSuspension");
    private String userStatus = "(//table[@id='table-propeller'])[2]/tbody/tr[position() > 1]//span[contains(text(),'%s')]";

    public int clickApprovedUser() {
        int flag = -1;

        ElementsCollection team = $$(LIST_OF_TEAM_MEMBERS);

        for (SelenideElement t : team) {
            List<SelenideElement> buttons = t.findAll(By.xpath(".//button"));

            if (buttons.isEmpty()) {
                SelenideElement user = getElement(LINK_TEAM_MEMBER);
                username = user.getText();
                user.click();
                flag = 1;
                break;
            } else {
                throw new SkipException("No users to suspend.");
            }
        }
        return flag;
    }

    public HomePage clickSuspendUser() {
        click(BTN_SUSPEND_USER, Condition.editable, "Suspend User");
        return this;
    }

    public HomePage clickConfirmSuspendUser() {
        click(BTN_CONFIRM_SUSPEND_USER, Condition.editable, "Confirm Suspend User");
        return this;
    }

    public int verifyUserSuspended(String status) {
        int flag = -1;
        final By LIST_OF_TEAM_MEMBERS = By.xpath("(//table[@id='table-propeller'])[2]/tbody/tr[position() > 1]");
        ElementsCollection team = $$(LIST_OF_TEAM_MEMBERS);

        for (SelenideElement t : team) {
            if (t.find(LINK_TEAM_MEMBER).getText().equals(username) && generateDynamicSelenideElement(userStatus, status).getText().contains(status)) {
                flag = 1;
                break;
            }
        }
        return flag;
    }
}
