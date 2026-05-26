// This is the ROOT build.gradle.kts file (located at marketmentor/android/build.gradle.kts)
// It tells Gradle exactly which version numbers of the Android & Kotlin plugins to download.

plugins {
    // Defines the Android Application build tool version (Matches Compile SDK 34)
    id("com.android.application") version "8.2.2" apply false
    id("com.android.library") version "8.2.2" apply false

    // Defines the Kotlin Programming Language compiler version (Matches Compose compiler)
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
}