val h2Version: String by project
val postgresqlVersion: String by project
val queryDslVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation(project(":common"))

    implementation("com.h2database:h2:$h2Version")
    implementation("org.postgresql:postgresql:$postgresqlVersion")
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api:2.2.3")
    annotationProcessor("com.querydsl:querydsl-apt:$queryDslVersion:jpa")
}

// QueryDSL
sourceSets {
    main {
        java {
            srcDirs ("$projectDir/src/main/java")
            srcDirs ("$projectDir/build/generated")
        }
    }
}

