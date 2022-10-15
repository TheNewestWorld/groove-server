rootProject.name = "groove"
include("groove-api")
include("groove-security:auth")
include("groove-security:auth-api")
include("groove-security:spring-security")
include("storage")
include("common")
include("redis")

pluginManagement {
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
            }
        }
    }
}