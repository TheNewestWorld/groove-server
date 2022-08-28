val springBootVersion: String by project
val springDocVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation(project(":storage"))
    implementation(project(":common"))

    // auth
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation(project(":groove-security:spring-security"))
    implementation(project(":groove-security:auth-api"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}