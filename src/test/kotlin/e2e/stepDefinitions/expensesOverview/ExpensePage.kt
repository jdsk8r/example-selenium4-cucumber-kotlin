package e2e.stepDefinitions.expensesOverview

import no.sanchezrolfsen.framework.selenium.Browser
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.FindBy
import org.openqa.selenium.support.PageFactory
import java.util.function.Function
import java.util.function.Predicate

class ExpensePage {
    private val driver : WebDriver? = Browser.vanillaDriver()

    @FindBy(css = "[cy-data-selector='add-new-expense']")
    var addNewExpenseButton : WebElement? = null

    init {
        PageFactory.initElements(driver, this)
    }

    fun existExpense(title : String?) : Boolean {
        return driver !!.findElements(ExpenseItemComponent().SELECTOR).stream()
            .map { ExpenseItemComponent() }
            .filter { ei -> ei.getDescription().equals(title) }
            .toList()
            .size > 0
    }
}