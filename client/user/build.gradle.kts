dependencies {
    compileOnly("org.springframework:spring-context")
    implementation(project(":groove-security:auth-api"))
    implementation(project(":common"))
}