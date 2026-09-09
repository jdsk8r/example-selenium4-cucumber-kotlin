import org.jetbrains.kotlin.gradle.dsl.JvmTarget

object Versions {
    const val JUNIT_VERSION = "6.1.3"
    const val E2E_FRAMEWORK_VERSION = "1.2.7"
    const val SELENIUM_VERSION = "4.48.0"
    const val CUCUMBER_VERSION = "7.34.8"
    const val SLF4J_VERSION = "2.0.19"
}

plugins {
    kotlin("jvm") version "2.4.20"
    `maven-publish`
}

group = "no.sanchezrolfsen.framework.examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("no.sanchezrolfsen.framework:selenium:${Versions.E2E_FRAMEWORK_VERSION}")
    implementation("org.slf4j:slf4j-api:${Versions.SLF4J_VERSION}")
    testRuntimeOnly("org.slf4j:slf4j-simple:${Versions.SLF4J_VERSION}")
    testImplementation("org.junit.jupiter:junit-jupiter-api:${Versions.JUNIT_VERSION}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${Versions.JUNIT_VERSION}")
    testImplementation("org.junit.jupiter:junit-jupiter-params:${Versions.JUNIT_VERSION}")
    implementation("org.seleniumhq.selenium:selenium-api:${Versions.SELENIUM_VERSION}")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:${Versions.SELENIUM_VERSION}")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:${Versions.SELENIUM_VERSION}")
    implementation("org.seleniumhq.selenium:selenium-remote-driver:${Versions.SELENIUM_VERSION}")
    implementation("org.seleniumhq.selenium:selenium-support:${Versions.SELENIUM_VERSION}")
    implementation("org.seleniumhq.selenium:selenium-java:${Versions.SELENIUM_VERSION}")
    implementation("io.cucumber:cucumber-core:${Versions.CUCUMBER_VERSION}")
    implementation("io.cucumber:cucumber-java:${Versions.CUCUMBER_VERSION}")
    implementation("io.cucumber:cucumber-java8:${Versions.CUCUMBER_VERSION}")
    implementation("io.cucumber:cucumber-junit:${Versions.CUCUMBER_VERSION}")
    implementation("org.apache.commons:commons-lang3:3.20.0")
    implementation("commons-io:commons-io:2.22.0")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
    testImplementation("org.junit.platform:junit-platform-runner:1.14.4")
    testImplementation("org.assertj:assertj-core:3.27.7")
    testImplementation("org.mockito:mockito-core:5.23.0")
}

tasks.test {
    useJUnitPlatform()
    failOnNoDiscoveredTests = false
}

val cucumberRuntime = configurations.create("cucumberRuntime") {
    extendsFrom(configurations.testImplementation.get())
}

tasks.register("cucumber", JavaExec::class) {
    dependsOn("assemble", "testClasses")
    mainClass.set("io.cucumber.core.cli.Main")
    classpath = cucumberRuntime + sourceSets.main.get().output + sourceSets.test.get().output
    args = listOf("--plugin", "pretty", "--glue", "e2e", "src/test/resources")
}

java {
    sourceCompatibility = JavaVersion.VERSION_26
    targetCompatibility = JavaVersion.VERSION_26
}

kotlin {
    compilerOptions {
        jvmTarget.set(JvmTarget.JVM_26)
    }
}
