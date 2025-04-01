plugins {
    `java-library`
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