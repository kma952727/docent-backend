plugins {
	alias(libs.plugins.kotlinJvm)
	alias(libs.plugins.kotlinSpring)
	alias(libs.plugins.springBoot)
	alias(libs.plugins.springDependencyManagement)
	application
}

tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

dependencies {

	// 스프링
	implementation("org.springframework.boot:spring-boot-starter-web")

	// 코틀린
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	// api docs
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.4")

	// project
    implementation(project(":app:core:user"))
    implementation(project(":app:core:art"))

    implementation(project(":app:model"))

	implementation(project(":app:shared:utils"))
	implementation(project(":app:shared:auth"))

	runtimeOnly(project(":app:shared:db"))
}