package e2e.stepDefinitions.expensesOverview


import e2e.support.config.ConfigUtil
import io.cucumber.java8.En
import no.sanchezrolfsen.framework.selenium.Browser
import no.sanchezrolfsen.framework.selenium.SeleniumUtils
import org.assertj.core.api.Assertions.assertThat
import java.time.LocalDateTime


class ExpensesSteps : En {
    init {
        Given("that I am on the main page") {
            Browser.vanillaDriver()
                .navigate()
                .to(ConfigUtil.getConfig().baseUrl)
        }
        When("I press on add new expense") { ExpensePage().addNewExpenseButton?.click() }
        Then(
            "the form to add expense is visible"
        ) {
            assertThat(SeleniumUtils.safeIsVisible(NewExpenseForm().newExpenseForm))
                .isTrue()
        }
        Then(
            "title textbox is on the left of amount text box"
        ) {
            assertThat(NewExpenseForm().isTitleToTheLeftOfAmount()).isTrue()
        }
        Then(
            "date textbox is below title textbox"
        ) { assertThat(NewExpenseForm().isDateBelowOfTitle()).isTrue() }
        When("I enter \"{}\" as title") { title : String? ->
            NewExpenseForm().newExpenseTitle?.sendKeys(
                title
            )
        }
        When("I enter {int} as amount") { amount : Int ->
            NewExpenseForm().newExpenseAmount?.sendKeys(
                amount.toString()
            )
        }
        When(
            "I enter today as date"
        ) {
            NewExpenseForm().newExpenseDate?.sendKeys(
                LocalDateTime.now()
                    .format(ConfigUtil.getConfig().standardDateFormat)
            )
        }
        When("press on add expense") { NewExpenseForm().addExpense?.click() }
        Then(
            "expense \"{}\" is visible on overview"
        ) { title : String? -> assertThat(ExpensePage().existExpense(title)).isTrue() }
    }
}