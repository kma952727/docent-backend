plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
	alias(libs.plugins.kotlinJpa)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
}

tasks.getByName("bootJar") {
    enabled = false
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {

	// 스프링
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// 코틀린
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// 테스트
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// monitor
	implementation("io.micrometer:micrometer-registry-prometheus")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("io.micrometer:micrometer-core")

	// project
	implementation(project(":app:shared:jpa"))
	implementation(project(":app:shared:utils"))
	implementation(project(":app:shared:http"))

	implementation(project(":app:model"))
}