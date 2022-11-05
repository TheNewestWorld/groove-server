val jwtVersion: String by project
val h2Version: String by project
val postgresqlVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("com.auth0:java-jwt:$jwtVersion")
    implementation(project(":common"))
    implementation(project(":redis"))
    implementation(project(":object-storage"))

    implementation("com.h2database:h2:$h2Version")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
}
