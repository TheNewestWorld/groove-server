val springBootVersion: String by project
val h2Version: String by project
val postgresqlVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation(project(":common"))

    implementation("com.h2database:h2:$h2Version")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
}