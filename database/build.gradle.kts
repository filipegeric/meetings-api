plugins {
    kotlin("jvm")
}

group = "com.meetings.database"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":users"))
}