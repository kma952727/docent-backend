plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
	kotlin("plugin.jpa") version "1.9.25"
	id("org.springframework.boot") version "3.3.5"
	id("io.spring.dependency-management") version "1.1.6"
}
java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(17)
	}
}
kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}
group = "com.mydocent"
version = "0.0.1"


allprojects {
	plugins.apply("org.jetbrains.kotlin.plugin.allopen")
	plugins.apply("org.jetbrains.kotlin.plugin.noarg")
	/**
	 * implementation 함수를 사용하려면
	 * `java` or `org.jetbrains.kotlin.jvm` plugin 필요
	 */
	plugins.apply("org.jetbrains.kotlin.jvm")

	allOpen {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.MappedSuperclass")
		annotation("jakarta.persistence.Embeddable")
	}
	noArg {
		annotation("jakarta.persistence.Entity")
		annotation("jakarta.persistence.MappedSuperclass")
		annotation("jakarta.persistence.Embeddable")
	}

	dependencies {
		implementation("io.github.oshai:kotlin-logging-jvm:5.1.1")
	}

	repositories {
		mavenCentral()
	}
}

subprojects {
	tasks.withType<Test> {
		useJUnitPlatform()
	}
}