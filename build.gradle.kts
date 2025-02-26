import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
    kotlin("jvm") version "2.1.0"
    kotlin("plugin.power-assert") version "2.1.0"
    application
}

sourceSets {
    main {
        kotlin.srcDir("src/main/kotlin")
    }
    test {
        kotlin.srcDir("src/test/kotlin")
    }
}

application {
    mainClass = providers.gradleProperty("day").map { "Day${it}Kt" }.getOrElse("Day01Kt")
}

dependencies {
    testImplementation(kotlin("test"))
    implementation("io.github.oshai:kotlin-logging-jvm:7.0.3")
    implementation("org.slf4j:slf4j-simple:2.0.3")
}

tasks {
    wrapper {
        gradleVersion = "8.11.1"
    }
}

tasks.test {
    useJUnitPlatform()
}

@OptIn(ExperimentalKotlinGradlePluginApi::class)
powerAssert {
    functions = listOf("kotlin.assert")
}
