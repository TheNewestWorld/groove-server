val springBootVersion: String by project
val springDocVersion: String by project

tasks.getByName("bootJar") {
    version = System.getenv("VERSION") ?: project.version
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

tasks.withType<Test> {
    useJUnitPlatform()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation(project(":storage"))
    implementation(project(":common"))
    implementation(project(":object-storage"))

    // auth
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation("org.springdoc:springdoc-openapi-security:$springDocVersion")
    implementation(project(":groove-security:spring-security"))
    implementation(project(":groove-security:auth-api"))
}
