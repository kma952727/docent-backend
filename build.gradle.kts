plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
	alias(libs.plugins.kotlinJpa)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
}

group = "com.mydocent"
version = "0.0.1"

allprojects {
	plugins.apply("org.jetbrains.kotlin.plugin.allopen")
	plugins.apply("org.jetbrains.kotlin.plugin.noarg")
	plugins.apply("org.jetbrains.kotlin.jvm") // implementation 함수 사용에 필요

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
	tasks.withType<Test> {
		useJUnitPlatform()
	}
}