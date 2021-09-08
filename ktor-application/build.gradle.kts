plugins {
    application
    kotlin("jvm")
}

application {
    mainClass.set("com.meetings.api.MainKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}

group = "com.meetings.ktor-application"
version = "1.0-SNAPSHOT"

dependencies {
    val ktorVersion = "1.6.3"

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-jackson:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.2.5")

    implementation("com.sksamuel.hoplite:hoplite-core:1.4.7")
    implementation("com.sksamuel.hoplite:hoplite-yaml:1.4.7")

    implementation(project(":database"))
    implementation(project(":users"))

}
