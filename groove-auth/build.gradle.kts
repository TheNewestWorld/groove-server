val springBootVersion: String by project
val jwtVersion: String by project
val h2Version: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("com.h2database:h2:$h2Version")
    implementation("com.auth0:java-jwt:$jwtVersion")
    implementation(project(":common"))
}
