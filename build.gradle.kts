import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

object Versions {
    const val e2eFrameworkVersion = "1.2.4"
    const val seleniumVersion = "4.6.0"
    const val cucumberVersion = "7.8.1"
    const val lombokVersion = "1.18.24"
}

plugins {
    kotlin("jvm") version "1.7.10"
}

group = "no.sanchezrolfsen.framework.examples"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("no.sanchezrolfsen.framework:selenium:${Versions.e2eFrameworkVersion}")
    implementation("org.seleniumhq.selenium:selenium-api:${Versions.seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-chrome-driver:${Versions.seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:${Versions.seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-remote-driver:${Versions.seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-support:${Versions.seleniumVersion}")
    implementation("org.seleniumhq.selenium:selenium-java:${Versions.seleniumVersion}")
    implementation("io.cucumber:cucumber-core:${Versions.cucumberVersion}")
    implementation("io.cucumber:cucumber-java:${Versions.cucumberVersion}")
    implementation("io.cucumber:cucumber-java8:${Versions.cucumberVersion}")
    implementation("io.cucumber:cucumber-junit:${Versions.cucumberVersion}")
    implementation("io.cucumber:cucumber-jvm:${Versions.cucumberVersion}")
    implementation("io.cucumber:cucumber-junit-platform-engine:${Versions.cucumberVersion}")
    compileOnly("org.projectlombok:lombok:${Versions.lombokVersion}")
    annotationProcessor("org.projectlombok:lombok:${Versions.lombokVersion}")
    testCompileOnly("org.projectlombok:lombok:${Versions.lombokVersion}")
    testAnnotationProcessor("org.projectlombok:lombok:${Versions.lombokVersion}")
    implementation("org.apache.commons:commons-lang3:3.12.0")
    implementation("commons-io:commons-io:2.11.0")
    implementation("javax.ws.rs:javax.ws.rs-api:2.1.1")
    testImplementation("org.junit.platform:junit-platform-runner:1.9.0")
    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.mockito:mockito-core:4.8.0")
}

/*tasks.test {
    useJUnitPlatform()
}*/

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}