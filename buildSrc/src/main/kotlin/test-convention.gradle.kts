plugins {
    id("org.jetbrains.kotlin.jvm")
    `java-library`
}

dependencies {
    testImplementation(kotlin("test"))
}


tasks.test {
    useJUnitPlatform()
}