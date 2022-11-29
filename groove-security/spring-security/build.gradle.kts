dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-oauth2-client")

    implementation(project(":common"))
    implementation(project(":storage"))
    implementation(project(":groove-security:auth"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}