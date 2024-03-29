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
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(project(":storage"))
    implementation(project(":common"))
    implementation(project(":object-storage"))
    implementation(project(":client:user"))

    implementation("org.springdoc:springdoc-openapi-ui:$springDocVersion")
    implementation("org.springdoc:springdoc-openapi-security:$springDocVersion")

    //freemarker
    implementation("org.springframework.boot:spring-boot-starter-freemarker")

    // auth
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation(project(":groove-security:spring-security"))
    implementation(project(":groove-security:auth-api"))
}
