plugins {
    id("java")
}

group = "ru.noion"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "ru.noion.Main"
    }
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absolutePath))
    }
}