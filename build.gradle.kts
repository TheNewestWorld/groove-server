val applicationVersion: String by project
val lombokVersion: String by project
val checkstyleVersion: String by project

plugins {
    id("java")
    id("java-library")
    id("checkstyle")
}

allprojects {
    group = "org.newest"
    version = applicationVersion

    repositories {
        mavenCentral()
    }

    apply(plugin = "java-library")
    apply(plugin = "checkstyle")

    checkstyle {
        maxWarnings = 0
        configFile = file("${rootDir}/config/checkstyle.xml")
        toolVersion = checkstyleVersion
    }
}

subprojects {
    dependencies {
        compileOnly("org.projectlombok:lombok:$lombokVersion")
        annotationProcessor("org.projectlombok:lombok:$lombokVersion")
    }
}
