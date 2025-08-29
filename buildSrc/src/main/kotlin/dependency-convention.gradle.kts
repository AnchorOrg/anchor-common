plugins {
    `java-library`
    id("org.jetbrains.kotlin.jvm")
}

// Configure JVM compatibility for Java 17 target
// Fixes: https://github.com/AnchorOrg/anchor-app/issues/217
// Using Java 17 as the target for maximum compatibility
tasks.withType<JavaCompile> {
    sourceCompatibility = "17"
    targetCompatibility = "17"
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
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