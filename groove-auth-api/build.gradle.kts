val springBootVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")

    implementation(project(":common"))
    implementation(project(":groove-auth"))
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}