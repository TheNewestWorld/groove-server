val applicationVersion: String by project
val lombokVersion: String by project
val checkstyleVersion: String by project

plugins {
    id("java")
    id("java-library")
    id("checkstyle")
    id("org.springframework.boot") apply false
}

group = "org.newest"
version = applicationVersion

allprojects {
    repositories {
        mavenCentral()
    }

    apply(plugin = "java-library")
}

subprojects {
    apply(plugin = "java")
    apply(plugin = "checkstyle")
    apply(plugin = "org.springframework.boot")

    checkstyle {
        maxWarnings = 0
        configFile = file("${rootDir}/config/checkstyle.xml")
        toolVersion = checkstyleVersion
    }

    dependencies {
        compileOnly("org.projectlombok:lombok:$lombokVersion")
        annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    }

    tasks.getByName("bootJar") {
        version = System.getenv("VERSION") ?: project.version
        enabled = false
    }
    tasks.getByName("jar") {
        enabled = true
    }
}