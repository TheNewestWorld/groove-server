val springBootVersion: String by project
val springDocVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation(project(":storage"))
    implementation(project(":common"))
    implementation(project(":groove-auth-api"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}