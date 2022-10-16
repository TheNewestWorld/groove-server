val springBootVersion: String by project

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-mail:$springBootVersion")

    implementation("com.google.api-client:google-api-client:2.0.0")

    implementation("com.google.oauth-client:google-oauth-client-jetty:1.34.1")
    implementation("com.google.apis:google-api-services-gmail:v1-rev20220404-2.0.0")

// https://mvnrepository.com/artifact/com.google.auth/google-auth-library-oauth2-http
    implementation("com.google.auth:google-auth-library-oauth2-http:1.11.0")

}