val springBootVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mail:$springBootVersion")

    implementation(project(":common"))
}