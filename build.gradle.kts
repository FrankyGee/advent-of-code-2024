plugins {
    kotlin("jvm") version "2.1.0"
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

tasks {
    wrapper {
        gradleVersion = "8.11.1"
    }
}
