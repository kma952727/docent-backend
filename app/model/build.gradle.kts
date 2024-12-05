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

	// api docs
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation(project(":app:shared:jpa"))
}
