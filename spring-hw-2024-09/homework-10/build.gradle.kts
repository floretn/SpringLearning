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
    implementation ("org.springframework.boot:spring-boot-starter-data-jdbc")

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    runtimeOnly("io.r2dbc:r2dbc-postgresql:0.8.11.RELEASE")

//    runtimeOnly("com.h2database:h2")
    implementation ("org.flywaydb:flyway-core")
    implementation ("org.postgresql:postgresql")

    annotationProcessor ("org.projectlombok:lombok")
    implementation ("org.projectlombok:lombok")

    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.testcontainers:testcontainers")
    testImplementation ("org.testcontainers:postgresql")
}

tasks.test {
    useJUnitPlatform()
}