plugins {
    id("org.jetbrains.kotlin.jvm")
    id("java")
    idea
    id("com.github.ben-manes.versions") version "0.47.0"
}

allprojects {
    apply(plugin = "test-convention")
    apply(plugin = "dependency-convention")
}

idea {
    module {
        isDownloadJavadoc = true
        isDownloadSources = true
    }
}

subprojects {
    apply(plugin = "java-library")
    apply(plugin = "publishing-conventions")

    dependencies {
        compileOnly("org.projectlombok:lombok:1.18.36")
    }


}

