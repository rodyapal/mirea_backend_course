val ktorVersion: String by project
val kotlinVersion: String by project
val logbackVersion: String by project
val exposedVersion: String by project

plugins {
	kotlin("jvm") version "1.7.22"
	id("io.ktor.plugin") version "2.2.1"
	id("org.jetbrains.kotlin.plugin.serialization") version "1.7.22"
}

group = "rodyapal.mirea"
version = "0.0.1"
application {
	mainClass.set("rodyapal.mirea.ApplicationKt")

	val isDevelopment: Boolean = project.ext.has("development")
	applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("io.ktor:ktor-server-core-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-auth-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-sessions-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-swagger:$ktorVersion")
	implementation("io.ktor:ktor-server-thymeleaf-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-content-negotiation-jvm:$ktorVersion")
	implementation("io.ktor:ktor-serialization-kotlinx-json-jvm:$ktorVersion")
	implementation("io.ktor:ktor-server-tomcat-jvm:$ktorVersion")
	implementation("ch.qos.logback:logback-classic:$logbackVersion")
	implementation("io.ktor:ktor-server-cors:$ktorVersion")
	implementation("io.ktor:ktor-server-cors-jvm:2.2.1")
	testImplementation("io.ktor:ktor-server-tests-jvm:$ktorVersion")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinVersion")

	// Exposed ORM
	implementation("org.jetbrains.exposed:exposed-core:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-dao:$exposedVersion")
	implementation("org.jetbrains.exposed:exposed-jdbc:$exposedVersion")
	implementation("mysql:mysql-connector-java:8.0.30")

	// Redis
	implementation("io.github.crackthecodeabhi:kreds:0.8")

	// HTML DSL
	implementation("io.ktor:ktor-server-html-builder:$ktorVersion")
}