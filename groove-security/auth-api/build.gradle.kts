val springBootVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")

    implementation(project(":common"))
    implementation(project(":groove-security:spring-security"))
    implementation(project(":groove-security:auth"))

    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}