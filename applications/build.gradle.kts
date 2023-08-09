dependencies {
    implementation(project(":domain"))
    implementation(project(":infrastructure:db"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}