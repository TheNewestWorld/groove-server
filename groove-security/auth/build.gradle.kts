val jwtVersion: String by project
val h2Version: String by project
val postgresqlVersion: String by project

dependencies {
    implementation("org.springframework.security:spring-security-crypto")

    implementation("com.auth0:java-jwt:$jwtVersion")
    implementation(project(":common"))
    implementation(project(":redis"))
    implementation(project(":mail"))
    implementation(project(":object-storage"))
    implementation(project(":storage"))
}
