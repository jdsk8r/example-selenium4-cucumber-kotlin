package e2e.support

import e2e.Log
import e2e.support.config.ConfigUtil
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import lombok.extern.slf4j.Slf4j
import no.sanchezrolfsen.framework.selenium.AbstractHooks
import no.sanchezrolfsen.framework.selenium.Browser
import no.sanchezrolfsen.framework.selenium.config.BrowserConfig
import org.apache.commons.lang3.time.StopWatch
import org.assertj.core.api.Assertions.assertThat
import org.openqa.selenium.WebDriverException
import org.openqa.selenium.logging.LogType
import java.time.Duration
import java.util.logging.Level


@Slf4j
class Hooks: AbstractHooks() {
    companion object: Log()

    @Before
    override fun beforeEach() {
        if (runBeforeAll) {
            beforeAll()
        }
    }

    override fun beforeAll() {
        ConfigUtil.getConfig().printConfig()
        val stopWatch = StopWatch()
        stopWatch.start()
        try {
            Browser.init(this.browserConfig)
        } catch (ex: Exception) {
            ex.printStackTrace()
            this.unexpectedShutdown("Fail on browser configuration, stopping test-run")
        }
        Runtime.getRuntime().addShutdownHook(Thread {
            stopWatch.stop()
            log.info("Selenium has finished running the tests. Run-time: $stopWatch")
            Browser.vanillaDriver().quit()
        })

        Browser.vanillaDriver().manage().timeouts().scriptTimeout(Duration.ofSeconds(15))
        log.info("Starting running tests")
        val logEntries = Browser.vanillaDriver().manage().logs()[LogType.BROWSER]
        if (logEntries != null) {
            Browser.jsExecutor().executeScript("console.clear();")
        }
        runBeforeAll = false
    }

    override fun getBrowserConfig(): BrowserConfig {
        return ConfigUtil.getConfig().browserConfig
    }

    @After
    fun after() {
        clearBrowserStorage()
        checkBrowserConsoleErrors()
    }
    private fun clearBrowserStorage() {
        try {
            Browser.jsExecutor().executeScript("window.sessionStorage.clear();")
        } catch (e: WebDriverException) {
            log.warn("Couldn't delete session storage")
        }
        try {
            Browser.jsExecutor().executeScript("window.localStorage.clear();")
        } catch (e: WebDriverException) {
            log.warn("Couldn't delete local storage")
        }
    }

    private fun checkBrowserConsoleErrors() {
        val logEntries = Browser.vanillaDriver().manage().logs()[LogType.BROWSER]
        for (logEntry in logEntries) {
            if (logEntry.toString().contains("favicon.ico")) {
                continue
            }
            if (logEntry.level == Level.SEVERE) {
                log.error("Console error: " + logEntry.message)
            }
            assertThat(logEntry.level)
                .withFailMessage("There is a browser console-error: %s", logEntry.message)
                .isEqualTo(Level.SEVERE)
        }
    }

    @After
    fun tearDown(scenario: Scenario?) {
        afterFailedScenario(scenario)
    }
}