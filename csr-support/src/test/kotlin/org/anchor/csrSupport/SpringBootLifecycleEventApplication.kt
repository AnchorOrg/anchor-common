package org.anchor.csrSupport

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
// このファイルはテストのディレクトリに移行する必要があります
internal class SpringBootLifecycleEventApplication

fun main(args: Array<String>) {
    runApplication<SpringBootLifecycleEventApplication>(*args)
}
