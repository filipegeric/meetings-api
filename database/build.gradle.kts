plugins {
    kotlin("jvm")
}

group = "com.meetings.database"
version = "1.0-SNAPSHOT"

dependencies {
    val exposedVersion = "0.31.1"

    runtimeOnly("org.postgresql:postgresql:42.2.2")
    implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
    implementation("org.jetbrains.exposed:exposed-java-time:$exposedVersion")
    implementation("org.flywaydb:flyway-core:7.15.0")

    implementation(project(":users"))
    implementation(project(":meetings"))
}