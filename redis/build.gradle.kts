val springBootVersion: String by project
val embeddedRedisVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-redis:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-test:$springBootVersion")
    api("it.ozimov:embedded-redis:$embeddedRedisVersion") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
}