plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
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
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
}