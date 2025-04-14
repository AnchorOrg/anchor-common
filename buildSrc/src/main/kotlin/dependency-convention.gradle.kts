plugins {
    `java-library`
    id("org.jetbrains.kotlin.jvm")
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