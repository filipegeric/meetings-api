plugins {
    kotlin("jvm")
}

group = "com.meetings.ktor-application"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(project(":database"))
    implementation(project(":users"))

}