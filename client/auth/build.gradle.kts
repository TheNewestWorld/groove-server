val springBootVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web:$springBootVersion")
    implementation(project(":common"))
    implementation(project(":groove-auth-api"))
}