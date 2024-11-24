plugins {
    id("java")
}

group = "org.example"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-devtools")
    implementation("org.springframework.boot:spring-boot-starter-web")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")

    annotationProcessor ("org.projectlombok:lombok")
    implementation ("org.projectlombok:lombok")
}

tasks.test {
    useJUnitPlatform()
}