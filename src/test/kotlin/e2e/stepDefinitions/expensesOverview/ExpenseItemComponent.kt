package e2e.stepDefinitions.expensesOverview

import no.sanchezrolfsen.framework.selenium.Browser
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.PageFactory

class ExpenseItemComponent {
    private var driver: WebDriver? = Browser.vanillaDriver()
    val SELECTOR = By.cssSelector("[cy-data-selector='expense-item']")
    val expenseItemDescription: By = By.cssSelector("[cy-data-selector='expenseDescription']")

    init {
        PageFactory.initElements(driver, this)
    }

    fun getDescription(): String? {
        return driver!!.findElement(expenseItemDescription).text
    }
}