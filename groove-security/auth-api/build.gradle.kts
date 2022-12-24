val springDocVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-security")
    compileOnly("org.springdoc:springdoc-openapi-ui:$springDocVersion")

    implementation(project(":common"))
    implementation(project(":groove-security:spring-security"))
    implementation(project(":groove-security:auth"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.boot:spring-boot-starter-data-jpa")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}