plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
	alias(libs.plugins.kotlinJpa)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
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