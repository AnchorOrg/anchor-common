package org.anchor.csrSupport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// This file needs to be moved to the test directory
internal class SpringBootLifecycleEventApplication

fun main(args: Array<String>) {
    runApplication<SpringBootLifecycleEventApplication>(*args)
}
