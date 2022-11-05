val applicationVersion: String by project
val lombokVersion: String by project
val checkstyleVersion: String by project

plugins {
    id("java")
    id("java-library")
    id("checkstyle")
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
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
    apply(plugin = "io.spring.dependency-management")

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