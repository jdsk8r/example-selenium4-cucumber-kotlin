package e2e.support.config

import e2e.Log
import no.sanchezrolfsen.framework.selenium.config.BrowserConfig
import no.sanchezrolfsen.framework.selenium.config.BrowserConfigImpl
import no.sanchezrolfsen.framework.selenium.config.BrowserType
import no.sanchezrolfsen.framework.selenium.rest.Config
import java.time.format.DateTimeFormatter

class ExamplePortalConfig: Config {
    private val frontendUrl = "https://expenses-react.sanchezrolfsen.no/"
    companion object: Log()


    override fun getBaseUrl(): String {
        return frontendUrl
    }

    override fun getSeleniumGridUrl(): String {
        return ""
    }

    override fun getBrowserConfig(): BrowserConfig {
        return BrowserConfigImpl(browserType = BrowserType.CHROME)
    }

    override fun printConfig() {
        log.info("Base URL: ${getBaseUrl()}")
    }

    override fun getStandardDateFormat(): DateTimeFormatter {
        return DateTimeFormatter.ofPattern("dd.MM.yyyy")
    }
}