package e2e.stepDefinitions.expensesOverview

import no.sanchezrolfsen.framework.selenium.Browser
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import org.openqa.selenium.support.locators.RelativeLocator

class NewExpenseForm {
        private var driver: WebDriver? = Browser.vanillaDriver()

        @FindBy(css = "[cy-data-selector='new-expense-form']")
        var newExpenseForm: WebElement? = null

        @FindBy(css = "[cy-data-selector='new-expense-title']")
        var newExpenseTitle: WebElement? = null

        @FindBy(css = "[cy-data-selector='new-expense-amount']")
        var newExpenseAmount: WebElement? = null

        @FindBy(css = "[cy-data-selector='new-expense-date']")
        var newExpenseDate: WebElement? = null

        @FindBy(css = "[cy-data-selector='add-expense']")
        var addExpense: WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun isTitleToTheLeftOfAmount(): Boolean {
        return driver!!.findElement(
            RelativeLocator.with(By.cssSelector("[cy-data-selector='new-expense-title']"))
                .toLeftOf(By.cssSelector("[cy-data-selector='new-expense-amount']"))
        ).isDisplayed
    }

    fun isDateBelowOfTitle(): Boolean {
        return driver!!.findElement(
            RelativeLocator.with(By.cssSelector("[cy-data-selector='new-expense-date']"))
                .below(By.cssSelector("[cy-data-selector='new-expense-title']"))
        ).isDisplayed
    }
}