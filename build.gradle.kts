import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

group = "ru.perm.v"
// change version on publishing
version = "0.15.02.2"
description = "Shop Kafka Producer"
val kafkaApiVersion = "3.3.1"
var springFoxVersion = "3.0.0"
var shopKotlinExtDtoVersion = "0.0.5"
var mockitoKotlinVersion = "4.0.0"

buildscript {
	var kotlinVersion: String? by extra; kotlinVersion = "1.1.51"

	repositories {
		mavenCentral()
	}

	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
	}

}

repositories {
	mavenCentral()
	mavenLocal()
	maven {

		url = uri("http://v.perm.ru:8082/repository/ru.perm.v") //OK
		isAllowInsecureProtocol = true
		credentials {
// export NEXUS_CRED_USR=admin
// echo $NEXUS_CRED_USR
			username = System.getenv("NEXUS_CRED_USR") ?: extra.properties["nexus-ci-username"] as String?
// export NEXUS_CRED_PSW=pass
// echo $NEXUS_CRED_PSW
			password = System.getenv("NEXUS_CRED_PSW") ?: extra.properties["nexus-ci-password"] as String?
//			username = "admin"
//			password = "pass"
		}
	}
}

plugins {
	val kotlinVersion = "1.8.21"
	id("org.springframework.boot") version "2.5.6"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	id("maven-publish")
	id("jacoco")
	kotlin("kapt") version "1.7.0"
	java
	idea
	application
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.springframework.kafka:spring-kafka")
	implementation("org.apache.kafka:kafka-clients:3.4.0")
// https://mvnrepository.com/artifact/io.springfox/springfox-boot-starter
	implementation("io.springfox:springfox-boot-starter:$springFoxVersion")
// validator
	implementation("org.hibernate.validator:hibernate-validator")
// EXAMPLE FOR KAFKA STREAM
//	implementation("org.apache.kafka:kafka-streams")
	implementation("ru.perm.v:shop_kotlin_extdto:$shopKotlinExtDtoVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Configure Spring Boot plugin task for running the application.
val bootJar by tasks.getting(BootJar::class) {
	enabled = true
}

publishing {
	repositories {
		maven {
			url = uri("http://v.perm.ru:8082/repository/ru.perm.v/")
			isAllowInsecureProtocol = true
			//  publish в nexus "./gradlew publish" из ноута и Jenkins проходит
			// export NEXUS_CRED_USR=admin
			// echo $NEXUS_CRED_USR
			credentials {
				username = System.getenv("NEXUS_CRED_USR")
				password = System.getenv("NEXUS_CRED_PSW")
			}
		}
	}
	publications {
		create<MavenPublication>("maven") {
			artifact(tasks["bootJar"])
		}
	}
}
