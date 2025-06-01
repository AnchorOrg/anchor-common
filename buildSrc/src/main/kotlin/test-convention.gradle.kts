plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
}


dependencies {
    testImplementation("org.junit:junit-bom:5.7.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
}


tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}