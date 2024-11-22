plugins {
	kotlin("jvm") version "1.9.25"
	kotlin("plugin.spring") version "1.9.25"
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

	repositories {
		mavenCentral()
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

subprojects {

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}