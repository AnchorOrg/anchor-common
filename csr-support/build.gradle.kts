plugins {
    id("org.springframework.boot") version VersionManagement.SPRINGBOOT
    // help manage the version of the spring-related dependencies
    id("io.spring.dependency-management") version VersionManagement.SPRING_DEPENDENCY_MANAGEMENT
    // Kotlin has classes and their members final by default, which makes it inconvenient to use frameworks and libraries such as Spring AOP that require classes to be open. You can enable the kotlin-spring compiler plugin instead of specifying Spring annotations manually, to mark the class as open without explicitly specifying it.
    kotlin("plugin.spring") version VersionManagement.SPRING_PLUGIN
    // kotlin-jpa is wrapped on top of no-arg. The plugin specifies @Entity, @Embeddable, and @MappedSuperclass no-arg annotations automatically.
    id("org.jetbrains.kotlin.plugin.jpa") version VersionManagement.Kotlin.VERSION
}


// disable spring boot packaging
tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName<Jar>("jar") {
    enabled = true
}
dependencies {
    // Kotlin
    implementation(kotlin("reflect"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${VersionManagement.Kotlin.VERSION}")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    // Spring Boot
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.data:spring-data-commons")
    api("org.springframework.boot:spring-boot-starter-validation")


    // Hibernate
    testImplementation("com.h2database:h2:2.3.232")
    testImplementation("org.hibernate:hibernate-core:6.1.5.Final")
}



