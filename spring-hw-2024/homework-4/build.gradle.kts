plugins {
    id ("org.springframework.boot")
}

dependencies {
    implementation(project(mapOf("path" to ":homework-4:card-starter")))
    implementation ("org.springframework.boot:spring-boot-starter")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    annotationProcessor ("org.projectlombok:lombok")
    implementation ("org.projectlombok:lombok")
}