// This is the updated settings.gradle.kts file (located at marketmentor/android/settings.gradle.kts)
// It manages where Gradle downloads its plugins and libraries from.

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MarketMentor"
include(":app")