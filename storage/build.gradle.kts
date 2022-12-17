val h2Version: String by project
val oracleVersion: String by project
val queryDslVersion: String by project

dependencies {
    api("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation(project(":common"))

    implementation("com.h2database:h2:$h2Version")
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
    annotationProcessor("jakarta.persistence:jakarta.persistence-api:2.2.3")
    annotationProcessor("com.querydsl:querydsl-apt:$queryDslVersion:jpa")


    runtimeOnly("com.oracle.database.jdbc:ojdbc8:$oracleVersion")
    implementation("com.oracle.database.security:oraclepki:$oracleVersion")
    implementation("com.oracle.database.security:osdt_core:$oracleVersion")
    implementation("com.oracle.database.security:osdt_cert:$oracleVersion")
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

