rootProject.name = "sofronov-home-tasks"

pluginManagement {
//    repositories {
//        mavenCentral()
//    }

    val dependencyManagementVersion: String by settings
    val springframeworkBootVersion: String by settings
    val jibVersion: String by settings
    val spotlessVersion: String by settings
    val sonarlintVersion: String by settings
    val lombokVersion: String by settings

    plugins {
        id("io.spring.dependency-management") version dependencyManagementVersion
        id("org.springframework.boot") version springframeworkBootVersion
        id("com.google.cloud.tools.jib") version jibVersion
        id("com.diffplug.spotless") version spotlessVersion
        id("name.remal.sonarlint") version sonarlintVersion
        id("io.freefair.lombok") version lombokVersion
    }
}

include("homework-1")
include("homework-2")
include("homework-3")
include("homework-4")
include("homework-4:card-starter")
include("homework-5")
include("homework-6")
include("homework-7")
include("homework-8")
include("homework-9")
include("homework-10")
