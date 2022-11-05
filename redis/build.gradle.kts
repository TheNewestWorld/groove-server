val embeddedRedisVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-test")
    api("it.ozimov:embedded-redis:$embeddedRedisVersion") {
        exclude(group = "org.slf4j", module = "slf4j-simple")
    }
}