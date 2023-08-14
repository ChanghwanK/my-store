dependencies {
    implementation(project(":domain"))
    implementation(project(":applications"))

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("com.mysql:mysql-connector-j:8.0.32")
}