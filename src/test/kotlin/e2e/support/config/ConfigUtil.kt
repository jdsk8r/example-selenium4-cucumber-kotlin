package e2e.support.config

import no.sanchezrolfsen.framework.selenium.rest.Config

class ConfigUtil {
    companion object {

        fun getConfig(): Config {
            return ExamplePortalConfig()
        }
    }
}