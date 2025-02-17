plugins {
    `java-library`
    `maven-publish`
}

java {
    withJavadocJar()
    withSourcesJar()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:2.0.21")
    implementation("org.jetbrains.kotlin:kotlin-script-runtime:1.9.0")
}

val groupName = "org.anchor"
val versionVal = "1.0.1"

val baseName = "anchor-common"

publishing {

    tasks.withType<PublishToMavenRepository>().configureEach {
        doFirst {
            println("Starting publication to Maven repository...")
        }
    }

    publications {
        create<MavenPublication>("maven") {
            groupId = groupName
            artifactId = baseName+"_" + project.name
            version =  versionVal

            from(components["java"])
        }
    }
}


