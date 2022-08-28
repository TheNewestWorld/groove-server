val springBootVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-security:$springBootVersion")
    implementation(project(":common"))
    implementation(project(":groove-security:auth"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}