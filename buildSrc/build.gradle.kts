plugins {
    `kotlin-dsl`
    id("org.jetbrains.kotlin.jvm") version "2.0.21"
}

repositories {
    mavenLocal()
    mavenCentral()
    gradlePluginPortal()  // so that external plugins can be resolved in dependencies section
    google()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.9.0")
}

kotlin {
    jvmToolchain(17)
}