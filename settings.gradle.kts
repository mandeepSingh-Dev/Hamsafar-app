pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "1.0.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Hmsfr"
include(":app")
include(":core-common")
include(":core-network")
include(":core-ui")
include(":core-ui")
include(":core-navigation")
include(":core-auth")
include(":feature-splash")
include(":feature-auth")
include(":feature-home")
include(":feature-chat")
