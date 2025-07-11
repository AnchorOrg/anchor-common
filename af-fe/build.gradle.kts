plugins {
    id("org.springframework.boot") version VersionManagement.SPRINGBOOT
    // help manage the version of the spring-related dependencies
    id("io.spring.dependency-management") version VersionManagement.SPRING_DEPENDENCY_MANAGEMENT
    // Kotlin has classes and their members final by default, which makes it inconvenient to use frameworks and libraries such as Spring AOP that require classes to be open. You can enable the kotlin-spring compiler plugin instead of specifying Spring annotations manually, to mark the class as open without explicitly specifying it.
    kotlin("plugin.spring") version VersionManagement.SPRING_PLUGIN
}

// disable spring boot packaging
tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    // Kotlin
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${VersionManagement.Kotlin.VERSION}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Spring Boot for internationalization support
    api("org.springframework.boot:spring-boot-starter")
    api("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}