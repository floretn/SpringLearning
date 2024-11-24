plugins {
    id ("org.springframework.boot")
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    implementation("org.aspectj:aspectjweaver")
    annotationProcessor ("org.projectlombok:lombok")
    implementation ("org.projectlombok:lombok")
}