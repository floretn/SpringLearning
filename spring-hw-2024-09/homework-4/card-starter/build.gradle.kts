import org.gradle.api.tasks.bundling.Jar
import org.springframework.boot.gradle.tasks.bundling.BootJar

plugins {
    id ("org.springframework.boot")
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor ("org.projectlombok:lombok")
    implementation ("org.projectlombok:lombok")
}

val jar: Jar by tasks
jar.enabled = true

val bootJar: BootJar by tasks
bootJar.enabled = false
