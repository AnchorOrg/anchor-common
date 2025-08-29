plugins {
    `java-library`
    id("org.jetbrains.kotlin.jvm")
}

// Configure JVM toolchain to use Java 17 for compatibility
// Fixes: https://github.com/AnchorOrg/anchor-app/issues/217
java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

kotlin {
    jvmToolchain(17)
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()
    google()
}


dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${VersionManagement.Kotlin.VERSION}")
}